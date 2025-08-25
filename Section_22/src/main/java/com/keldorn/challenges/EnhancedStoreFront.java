package main.java.com.keldorn.challenges;

import com.mysql.cj.jdbc.MysqlDataSource;
import main.java.com.keldorn.challenges.dto.Order;
import main.java.com.keldorn.challenges.dto.OrderDetails;
import main.java.com.keldorn.mains.PreparedStatementMain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnhancedStoreFront {
    private static final String INSERT_ORDER = "INSERT INTO storefront.order (order_date) VALUES (?)";
    private static final String INSERT_ORDER_DETAILS =
            "INSERT INTO storefront.order_details (item_description, order_id, quantity) VALUES (?, ?, ?)";
    private static final Path ordersPath = Path.of("./resources/Orders.csv");
    private static final List<Order> orders = readOrders();

    public static void main(String[] args) {
        var dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("storefront");
        dataSource.setUser(System.getenv("MYSQL_USER"));
        dataSource.setPassword(System.getenv("MYSQL_PASS"));
        try {
            dataSource.setContinueBatchOnError(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = dataSource.getConnection()) {
            addOrders(connection, orders);
//            deleteAllOrders(connection);
            System.out.println("Records successfully added");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Order> readOrders() {
        List<String> lines;
        try {
            lines = Files.readAllLines(ordersPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Order> orderList = new ArrayList<>();
        int index = -1;
        for (String line : lines) {
            String[] data = line.split(",");
            if (data[0].equals("order")) {
                index++;
                orderList.add(new Order(data[1]));
                continue;
            }
            orderList.get(index).addOrderDetail(new OrderDetails(Integer.parseInt(data[1]), data[2]));
        }
        return orderList;
    }

    private static int addOrder(Connection conn, Order order) throws SQLException {
        conn.setAutoCommit(false);
        int orderId = -1;
        if (!order.validateOrderDate()) {
            System.err.println("Skipping invalid order date: " + order.getOrderDate());
            System.err.println("Skipped Order: " + order);
        } else {
            try (PreparedStatement psOrder = conn.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement psOrderDetails = conn.prepareStatement(INSERT_ORDER_DETAILS)
            ) {
                psOrder.setString(1, order.getOrderDate());
                orderId = PreparedStatementMain.getGeneratedAutoId(psOrder);
                for (OrderDetails orderDetail : order.getOrderDetails()) {
                    psOrderDetails.setString(1, orderDetail.itemDescription());
                    psOrderDetails.setInt(2, orderId);
                    psOrderDetails.setInt(3, orderDetail.quantity());
                    psOrderDetails.addBatch();
                }
                psOrderDetails.executeBatch();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException(e);
            } finally {
                conn.setAutoCommit(true);
            }
        }
        return orderId;
    }

    private static List<Integer> addOrders(Connection conn, List<Order> orders) throws SQLException {
        List<Integer> orderIds = new ArrayList<>();
        for (Order order : orders) {
            orderIds.add(addOrder(conn, order));
        }
        return orderIds;
    }

    private static void deleteAllOrders(Connection conn) throws SQLException {
        try (Statement statement = conn.createStatement()) {
            statement.execute("SELECT order_id FROM storefront.order");
            ResultSet rs = statement.getResultSet();
            List<Integer> ids = new ArrayList<>();
            while (rs.next()) {
                ids.add(rs.getInt(1));
            }
            for (int id : ids) {
                statement.execute("DELETE FROM storefront.order WHERE order_id = %d".formatted(id));
            }
        }
    }
}
