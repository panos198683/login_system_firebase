package com.example.upgrade;

public class userHelperClass {
    String username,phoneNo,password;
    Float speedlimit;

    public userHelperClass() {

    }

    public userHelperClass(String username, Float speedlimit, String phoneNo, String password) {
        this.username = username;
        this.speedlimit = speedlimit;
        this.phoneNo = phoneNo;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Float getspeedlimit() {
        return speedlimit;
    }

    public void setSpeedlimit(Float speedlimit) {
        this.speedlimit = speedlimit;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
