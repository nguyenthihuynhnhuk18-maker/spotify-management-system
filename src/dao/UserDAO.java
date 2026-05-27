package dao;

import model.User;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    Connection conn =
            DatabaseConnection
                    .getInstance()
                    .getConnection();

    public User login(
            String username,
            String password
    ) {

        try {

            String sql =

            "SELECT * FROM users " +

            "WHERE username=? " +

            "AND password_hash=SHA2(?,256)";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, username);

            ps.setString(2, password);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                return new User(

                        rs.getInt("user_id"),

                        rs.getString("username"),

                        rs.getString("full_name"),

                        rs.getString("role")
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}