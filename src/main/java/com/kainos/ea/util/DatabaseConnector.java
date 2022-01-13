package com.kainos.ea.util;

import java.io.FileInputStream;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnector {
    private java.sql.Connection conn;

    public java.sql.Connection getConnection() {
        String user;
        String password;
        String host;

        if (conn != null) {
            return conn;
        }

        try {
            FileInputStream propsStream =
                    new FileInputStream("database.properties");

            Properties props = new Properties();
            props.load(propsStream);

            user = props.getProperty("user");
            password = props.getProperty("password");
            host = props.getProperty("host");

            if (user == null || password == null || host == null) {
                throw new IllegalArgumentException(
                        "Properties file must exist and must contain "
                                + "user, password, and host properties.");
            }


            conn = DriverManager.getConnection("jdbc:mysql://"
                    + host + "/kManGroup_gillon?useSSL=false", user, password);

            return conn;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
