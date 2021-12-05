package com.makkras.shop.entity;

public enum UserRole{
    ADMIN (0),
    CLIENT (1);
    private int roleId;
    UserRole(int roleId){
        this.roleId = roleId;
    }
    public int getRoleId() {
        return roleId;
    }
}
