package com.kainos.ea.data;

import com.kainos.ea.model.UserRequestModel;
import com.kainos.ea.model.DatabaseUserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    public DatabaseUserModel getDetails(Connection connection, String username) throws SQLException {
        String query = "SELECT * from users where username = ?";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, username);

        ResultSet rs = statement.executeQuery();
        DatabaseUserModel user = new DatabaseUserModel();

        if (rs.next()) {
            user.setUsername(rs.getString("username"));
            user.setPasswordHash(rs.getString("passwordHash"));
            user.setSalt(rs.getString("salt"));
            user.setAdmin(rs.getBoolean("isAdmin"));
            return user;
        }
        throw new SQLException("No username in database");
    }

    public void registerUser(Connection connection, DatabaseUserModel user) throws SQLException {
        String query = "INSERT INTO users VALUES (? ,? ,?,?);";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPasswordHash());
        statement.setString(3, user.getSalt());
        statement.setBoolean(4, user.isAdmin());

        statement.executeUpdate();
    }
}
