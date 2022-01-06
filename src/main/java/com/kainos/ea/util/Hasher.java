package com.kainos.ea.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

public class Hasher {
    public String hashPassword(String password, String salt) throws Exception{
        byte[] saltBytes = Base64.getDecoder().decode(salt);

        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), saltBytes, 65536,128);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hashBytes = secretKeyFactory.generateSecret(keySpec).getEncoded();
        return Base64.getEncoder().encodeToString(hashBytes);
    }

    public String getNewSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[64];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}
