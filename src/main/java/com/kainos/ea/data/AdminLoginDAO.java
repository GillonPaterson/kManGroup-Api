package com.kainos.ea.data;

import com.kainos.ea.model.Details;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminLoginDAO {
    public Boolean checkDetails(Connection connection, Details details) {
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM Details");
            List<Details> list = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Details(
                        rs.getString(1),
                        rs.getString(2)));
            }
            for (Details value : list) {
                if (details.equals(value)) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
