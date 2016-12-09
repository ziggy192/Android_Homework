package com.example.nghia.lab4.models;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Created by Nghia on 12/4/2016.
 */

public class Content {
    @SerializedName("items")
    private Contact[] contacts;

    public Content(Contact[] contacts) {
        this.contacts = contacts;

    }

    public Contact[] getContacts() {
        return contacts;
    }

    public void setContacts(Contact[] contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Content{" +
                "contacts=" + Arrays.toString(contacts) +
                '}';
    }
}
