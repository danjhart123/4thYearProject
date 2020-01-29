package com.example.a4thyearproject;

public class User {

    private int userID;
    private String email;
    private String password;
    private int isAdmin;

    public User(String email, String password, int isAdmin){
        setEmail(email);
        setPassword(password);
        setAdmin(isAdmin);
    }

    public User(int userID, String email, String password, int isAdmin){
        setUserID(userID);
        setEmail(email);
        setPassword(password);
        setAdmin(isAdmin);
    }

    public User() {

    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public void setAdmin(int admin) {
        isAdmin = admin;
    }

    public int getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public int isAdmin() {
        return isAdmin;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
