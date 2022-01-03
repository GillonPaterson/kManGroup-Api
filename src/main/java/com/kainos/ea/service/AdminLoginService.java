package com.kainos.ea.service;

import com.kainos.ea.data.AdminLoginDAO;
import com.kainos.ea.model.Details;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.Connection;

public class AdminLoginService {

    private final AdminLoginDAO loginData;
    private DatabaseConnector databaseConnector;

    public AdminLoginService() {
        loginData = new AdminLoginDAO();
        databaseConnector = new DatabaseConnector();
    }

    public AdminLoginService(AdminLoginDAO loginDao){
        loginData = loginDao;
    }

    public Boolean checkDetails(Details details) {
        Connection connection = databaseConnector.getConnection();
        return  loginData.checkDetails(connection, details);
    }
}
