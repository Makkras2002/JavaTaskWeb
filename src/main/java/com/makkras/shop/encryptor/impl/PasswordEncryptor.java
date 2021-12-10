package com.makkras.shop.encryptor.impl;

import com.makkras.shop.encryptor.CustomPasswordEncryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptor implements CustomPasswordEncryptor {
    private static Logger logger = LogManager.getLogger();
    private static final String ENCRYPTOR_SRC = "MD5";
    @Override
    public String encryptPassword(String password){
        String encryptedPassword = null;
        try {
            MessageDigest message = MessageDigest.getInstance(ENCRYPTOR_SRC);
            message.update(password.getBytes());
            byte[] bytes = message.digest();
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            encryptedPassword = s.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        }

        return encryptedPassword;
    }
}
