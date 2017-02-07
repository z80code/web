package by.gsu.epamlab.factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getAccessConnection() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
            String dbUrl = "jdbc:mysql://localhost/webfilms?useSSL=false";
            String user = "root";
            String password = "root";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            return DriverManager.getConnection(dbUrl, user, password);
    }
}
