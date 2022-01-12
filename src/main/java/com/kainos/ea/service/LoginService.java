package com.kainos.ea.service;

import com.kainos.ea.data.LoginDAO;
import com.kainos.ea.model.TokenSubject;
import com.kainos.ea.model.UserRequestModel;
import com.kainos.ea.model.DatabaseUserModel;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.util.Hasher;
import com.kainos.ea.util.TokenHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class LoginService {

    private final LoginDAO loginDAO;
    private final DatabaseConnector connector;
    private final Hasher hasher;
    private final TokenHandler tokenHandler;

    public LoginService() {
        loginDAO = new LoginDAO();
        connector = new DatabaseConnector();
        hasher = new Hasher();
        tokenHandler = new TokenHandler();
    }

    public LoginService(LoginDAO loginDAO, DatabaseConnector connector, Hasher hasher) {
        this.loginDAO = loginDAO;
        this.connector = connector;
        this.hasher = hasher;
        tokenHandler = new TokenHandler();
    }

    public LoginService(LoginDAO loginDao, DatabaseConnector connector, Hasher hasher, TokenHandler tokenHandler){
        this.connector = connector;
        this.loginDAO = loginDao;
        this.hasher = hasher;
        this.tokenHandler = tokenHandler;
    }

    public String checkDetails(UserRequestModel loginInfo) throws Exception {

        Connection connection = connector.getConnection();
        DatabaseUserModel user = loginDAO.getDetails(connection, loginInfo.getUsername());
        String givenPasswordHash = hasher.hashPassword(loginInfo.getPassword(), user.getSalt());

        if(user.getPasswordHash().equals(givenPasswordHash)){
            TokenSubject tokenSubject = new TokenSubject(user.getUsername(), user.isAdmin());

            String tokenString = tokenHandler.createToken(tokenSubject, TimeUnit.HOURS.toMillis(1));
            return tokenString;
        }
        else {
            throw new SQLException("Passwords don't match");
        }
    }

    public void registerUser(UserRequestModel userInfo) throws Exception{
        Connection connection = connector.getConnection();
        DatabaseUserModel user = new DatabaseUserModel();
        user.setUsername(userInfo.getUsername());
        user.setSalt(hasher.getNewSalt());
        user.setPasswordHash(hasher.hashPassword(userInfo.getPassword(), user.getSalt()));
        user.setAdmin(userInfo.getRoles()[0].equals("Admin"));

        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: "+ user.getPasswordHash());
        System.out.println("Salt: "+ user.getSalt());
        System.out.println("isAdmin: "+ user.isAdmin());

        loginDAO.registerUser(connection,user);
    }
}
