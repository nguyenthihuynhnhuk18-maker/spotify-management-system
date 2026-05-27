package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    private String url =
            "jdbc:mysql://localhost:3306/spotify_db";

    private String username = "root";
    private String password = "Nguyennhu8016550.vvv";

    private DatabaseConnection() {

        try {

            Class.forName(
                    "com.mysql.cj.jdbc.Driver");

            connection =
                    DriverManager.getConnection(
                            url,
                            username,
                            password
                    );

            System.out.println(
                    "Connected to MySQL"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() {

        if(instance == null) {
            instance = new DatabaseConnection();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}