package com.makkras.shop.entity;

public class User extends CustomEntity{
    private Long userId;
    private String login;
    private String email;
    private String password;
    private UserRole userRole;
    private boolean isActive;
    private boolean isOnline;
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserRoleId() {
        return userRole.ordinal();
    }
    public String getUserRoleName() {
        return userRole.toString();
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActiveStatus(boolean active) {
        isActive = active;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnlineStatus(boolean online) {
        isOnline = online;
    }
    public User(){
    }

    public User(Long userId, String login, String email, String password, UserRole userRole, boolean isActive, boolean isOnline) {
        this.userId = userId;
        this.login = login;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.isActive = isActive;
        this.isOnline = isOnline;
    }

    public User(String login, String email, String password, UserRole userRole, boolean isActive, boolean isOnline) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.isActive = isActive;
        this.isOnline = isOnline;
    }
    public User(String login, String email, boolean isActive) {
        this.login = login;
        this.email = email;
        this.isActive = isActive;
    }
    public User(Long userId, String login, String email, boolean isActive) {
        this.login = login;
        this.email = email;
        this.isActive = isActive;
        this.userId = userId;
    }
    public User(Long userId, String login, String email) {
        this.login = login;
        this.email = email;
        this.userId = userId;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (isActive != user.isActive) return false;
        if (isOnline != user.isOnline) return false;
        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return userRole == user.userRole;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + (isOnline ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId=").append(userId);
        sb.append(", login='").append(login).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", userRole=").append(userRole);
        sb.append(", isActive=").append(isActive);
        sb.append(", isOnline=").append(isOnline);
        sb.append('}');
        return sb.toString();
    }
}
