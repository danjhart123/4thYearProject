package com.example.a4thyearproject;

public class User {

    private int userID;
    private String email;
    private String password;
    private String securityQuestion;


    private int isAdmin;

    public User(String email, String password, String securityQuestion, int isAdmin){
        setEmail(email);
        setPassword(password);
        setSecurityQuestion(securityQuestion);
        setAdmin(isAdmin);
    }

    public User(int userID, String email, String password, String securityQuestion, int isAdmin){
        setUserID(userID);
        setEmail(email);
        setPassword(password);
        setSecurityQuestion(securityQuestion);
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

    public String getPassword()
    {
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

    public int getIsAdmin() {

        return isAdmin;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }


    @Override
    public String toString() {
        return "User{" +
                "userID=" + this.getUserID() +
                ", email='" + this.getEmail() + '\'' +
                ", password='" + this.getPassword() + '\'' +
                ", securityQuestion='" + this.getSecurityQuestion() + '\'' +
                ", isAdmin=" + this.getIsAdmin() +
                '}';
    }
}
