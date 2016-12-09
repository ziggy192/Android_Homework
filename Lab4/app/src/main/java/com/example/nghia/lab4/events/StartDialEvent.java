package com.example.nghia.lab4.events;

/**
 * Created by Nghia on 12/4/2016.
 */

public class StartDialEvent {
    private String phoneNumber;

    public StartDialEvent(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
