package com.makkras.shop.util.encryptor;

/**
 * The interface Custom password encryptor.
 */
public interface CustomPasswordEncryptor {

    /**
     * Encrypt password and return string encrypted password.
     *
     * @param password the raw, not encrypted password
     * @return the string encrypted password
     */
    String encryptPassword(String password);
}
