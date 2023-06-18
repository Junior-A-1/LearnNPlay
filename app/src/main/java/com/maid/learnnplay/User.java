package com.maid.learnnplay;

public class User {

    private String username;
    private String  email;
    private String profilePicture;

    private int correctMe;
    private int multyMe;
    private int shapeMe;
    public User(){

    }
    public User(String username, String email, String profilePicture, int correctMe,int multyMe,int shapeMe) {
        this.username = username;
        this.email = email;
        this.profilePicture = profilePicture;
        this.correctMe = correctMe;
        this.multyMe = multyMe;
        this.shapeMe = shapeMe;
    }

    public int getMultyMe() {
        return multyMe;
    }

    public void setMultyMe(int multyMe) {
        this.multyMe = multyMe;
    }

    public int getShapeMe() {
        return shapeMe;
    }

    public void setShapeMe(int shapeMe) {
        this.shapeMe = shapeMe;
    }

    public int getCorrectMe() {
        return correctMe;
    }

    public void setCorrectMe(int correctMe) {
        this.correctMe = correctMe;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }





}
