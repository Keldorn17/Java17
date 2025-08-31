package main.java.com.keldorn.challenges;

import com.mysql.cj.jdbc.MysqlDataSource;
import main.java.com.keldorn.challenges.dto.Order;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class CallableStoreFront {
    private static final List<Order> orders = EnhancedStoreFront.readOrders();

    public static void main(String[] args) {
        var dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("storefront");
        dataSource.setUser(System.getenv("MYSQL_USER"));
        dataSource.setPassword(System.getenv("MYSQL_PASS"));
        try (Connection connection = dataSource.getConnection()) {
            callableStatementInsert(connection);
//            EnhancedStoreFront.deleteAllOrders(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void callableStatementInsert(Connection connection) {
        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "CALL storefront.addOrder(?, ?, ?, ?)");
            orders.forEach(order -> {
                try {
                    if (!order.validateOrderDate()) {
                        System.err.println("Skipping invalid order date: " + order.getOrderDate());
                        System.err.println("Skipped Order: " + order);
                    }
                    else {
                        callableStatement.setTimestamp(1, order.getOrderDateTimestamp());
                        callableStatement.setString(2, order.toJSON());
                        callableStatement.registerOutParameter(3, Types.INTEGER);
                        callableStatement.registerOutParameter(4, Types.INTEGER);
                        callableStatement.execute();
                        System.out.printf("Order successfully added with and orderId of %d and %d records inserted%n",
                                callableStatement.getInt(3), callableStatement.getInt(4));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
