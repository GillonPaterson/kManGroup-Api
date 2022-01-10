package com.kainos.ea.service;

import com.kainos.ea.data.LoginDAO;
import com.kainos.ea.model.TokenSubject;
import com.kainos.ea.model.UserRequestModel;
import com.kainos.ea.model.User;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.util.Hasher;
import com.kainos.ea.util.Token;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;

public class LoginService {

    private final LoginDAO loginDAO;
    private final DatabaseConnector connector;
    private final Hasher hasher;
    private final Token token;

    public LoginService() {
        loginDAO = new LoginDAO();
        connector = new DatabaseConnector();
        hasher = new Hasher();
        token = new Token();
    }

    public LoginService(LoginDAO loginDao, DatabaseConnector connector){
        this.connector = connector;
        this.loginDAO = loginDao;
        hasher = new Hasher();
        token = new Token();
    }

    public String checkDetails(UserRequestModel loginInfo) throws Exception {

        Connection connection = connector.getConnection();
        User user = loginDAO.getDetails(connection, loginInfo.getUsername());
        if(user.getUsername() != null){
            String givenPasswordHash = hasher.hashPassword(loginInfo.getPassword(), user.getSalt());

            if(user.getPasswordHash().equals(givenPasswordHash)){
                TokenSubject tokenSubject = new TokenSubject(user.getUsername(), user.isAdmin());
                String tokenString = token.createToken(tokenSubject, TimeUnit.HOURS.toMillis(1));
                return tokenString;
            }
        }
        return null;
    }

    public void registerUser(UserRequestModel userInfo) throws Exception{
        Connection connection = connector.getConnection();
        User user = new User();
        user.setUsername(userInfo.getUsername());
        user.setSalt(hasher.getNewSalt());
        user.setPasswordHash(hasher.hashPassword(userInfo.getPassword(), user.getSalt()));

        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: "+ user.getPasswordHash());
        System.out.println("Salt: "+ user.getSalt());

        loginDAO.registerUser(connection,user);
    }
}
