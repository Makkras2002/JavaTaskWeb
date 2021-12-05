package com.makkras.shop.encryptor;

import com.makkras.shop.exception.InteractionException;

import java.security.NoSuchAlgorithmException;

public interface CustomPasswordEncryptor {
    String encryptPassword(String password) throws InteractionException;
}
