package com.makkras.shop.encryptor.impl;

import com.makkras.shop.encryptor.CustomPasswordEncryptor;
import com.makkras.shop.exception.InteractionException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptor implements CustomPasswordEncryptor {
    @Override
    public String encryptPassword(String password) throws InteractionException {
        String encryptedPassword = null;
        try
        {
            MessageDigest message = MessageDigest.getInstance("MD5");
            message.update(password.getBytes());
            byte[] bytes = message.digest();
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            encryptedPassword = s.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new InteractionException(e.getMessage());
        }

        return encryptedPassword;
    }
}
