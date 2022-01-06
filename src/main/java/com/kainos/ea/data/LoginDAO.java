package com.kainos.ea.data;

import com.kainos.ea.model.UserRequestModel;
import com.kainos.ea.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    public Boolean checkDetails(Connection connection, UserRequestModel loginInfo) {
//        try {
//            PreparedStatement st = connection.prepareStatement("SELECT * FROM Details");
//            List<Details> list = new ArrayList<>();
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                list.add(new Details(
//                        rs.getString(1),
//                        rs.getString(2)));
//            }
//            for (Details value : list) {
//                if (details.equals(value)) {
//                    return true;
//                }
//            }
//            return false;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
        return true;
    }

    public User getDetails(Connection connection,String username) throws SQLException{
        String query = "SELECT * from users where username = ?";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1,username);

        ResultSet rs = statement.executeQuery();
        User user = new User();

        if (rs.next()){
            user.setUsername(rs.getString("username"));
            user.setPasswordHash(rs.getString("passwordHash"));
            user.setSalt(rs.getString("salt"));
            user.setAdmin(rs.getBoolean("isAdmin"));
            return user;
        }
        throw new SQLException("No username in database");
    }

    public void registerUser(Connection connection, User user) throws SQLException{
        String query = "INSERT INTO users VALUES (? ,? ,?);";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,user.getUsername());
        statement.setString(2, user.getPasswordHash());
        statement.setString(3, user.getSalt());

        statement.executeUpdate();
    }
}
