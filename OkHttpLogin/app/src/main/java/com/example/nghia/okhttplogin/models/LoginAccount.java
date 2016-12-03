package com.example.nghia.okhttplogin.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nghia on 12/3/2016.
 */

public class LoginAccount {
    @SerializedName("username")
    private String userName;
    @SerializedName("password")
    private String password;

    public LoginAccount(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
