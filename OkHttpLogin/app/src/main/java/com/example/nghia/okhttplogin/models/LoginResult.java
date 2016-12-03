package com.example.nghia.okhttplogin.models;

/**
 * Created by Nghia on 12/3/2016.
 */

public class LoginResult {
    private int code;
    private String message;

    public LoginResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
