package main.java.com.keldorn.challenges;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class StoreFrontMain {
    private static final String SCHEMA_NAME = "storefront";
    private static final String USE_SCHEMA = "USE %s".formatted(SCHEMA_NAME);
    private static final int MYSQL_DB_NOT_FOUND = 1049;

    public static void main(String[] args) {


        var dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setUser(System.getenv("MYSQL_USER"));
        dataSource.setPassword(System.getenv("MYSQL_PASS"));

        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            System.out.println(metaData.getSQLStateType());

            if (!checkSchema(conn)) {
                System.out.println("storefront scheme does not exist");
                setUpSchema(conn);
            }
            int orderId = insertOrder(conn, "Snacks", "Shoes");
            TimeUnit.SECONDS.sleep(5);
            deleteOrder(conn, orderId);
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean checkSchema(Connection conn) throws SQLException {
        try (Statement statement = conn.createStatement()) {
            statement.execute(USE_SCHEMA);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            if (conn.getMetaData().getDatabaseProductName().equals("MySQL")
                && e.getErrorCode() == MYSQL_DB_NOT_FOUND) {
                return false;
            } else throw e;
        }
        return true;
    }

    private static void setUpSchema(Connection conn) throws SQLException {
        String createSchema = "CREATE SCHEMA storefront";
        String createOrder = """
                CREATE TABLE storefront.order(
                order_id int NOT NULL AUTO_INCREMENT,
                order_date DATETIME NOT NULL,
                PRIMARY KEY(order_id))""";
        String createOrderDetails = """
                CREATE TABLE storefront.order_details (
                order_detail_id int NOT NULL AUTO_INCREMENT,
                item_description text,
                order_id int DEFAULT NULL,
                PRIMARY KEY (order_detail_id),
                KEY FK_ORDERID (order_id),
                CONSTRAINT FK_ORDERID FOREIGN KEY (order_id)
                REFERENCES storefront.order (order_id) ON DELETE CASCADE)""";

        try (Statement statement = conn.createStatement()) {
            System.out.println("Creating storefront Database");
            statement.execute(createSchema);
            if (checkSchema(conn)) {
                statement.execute(createOrder);
                System.out.println("Successfully Created Order");
                statement.execute(createOrderDetails);
                System.out.println("Successfully Created Order Details");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int insertOrder(Connection conn, String... orderDescription)
            throws SQLException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String orderDateFormatted = LocalDateTime.now().format(dtf);
        final String orderInsert = "INSERT INTO %s.order (order_date) VALUES (%s)";
        final String orderDetailsInsert = "INSERT INTO %s.order_details (item_description, order_id) VALUES (%s, %s)";
        int orderId = -1;
        try (Statement statement = conn.createStatement()) {
            conn.setAutoCommit(false);
            String order = orderInsert.formatted(SCHEMA_NAME, statement.enquoteLiteral(orderDateFormatted));
            statement.execute(order, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            for (String description : orderDescription) {
                String details = orderDetailsInsert
                        .formatted(SCHEMA_NAME, statement.enquoteLiteral(description), orderId);
                statement.execute(details);
            }
            conn.commit();
            System.out.println("Successfully Inserted Data");
        } catch (SQLException e) {
            conn.rollback();
            throw new RuntimeException(e);
        } finally {
            conn.setAutoCommit(true);
        }
        return orderId;
    }

    private static void deleteOrder(Connection conn, int orderId) {
        final String deleteOrder = "DELETE FROM %s.order WHERE order_id = %d".formatted(SCHEMA_NAME, orderId);
        try (Statement statement = conn.createStatement()) {
            statement.execute(deleteOrder);
            System.out.println("Successfully Deleted Data");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
