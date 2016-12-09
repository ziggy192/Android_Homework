package com.example.nghia.lab4.events;

import android.support.v4.app.Fragment;

import com.example.nghia.lab4.models.Contact;

/**
 * Created by Nghia on 12/4/2016.
 */

public class ChangeToDetailFragmentEvent {

    private int contactId;
    private boolean addToBackStack;

    public ChangeToDetailFragmentEvent(int contactId, boolean addToBackStack) {
        this.contactId = contactId;
        this.addToBackStack = addToBackStack;
    }

    public boolean isAddToBackStack() {
        return addToBackStack;
    }

    public void setAddToBackStack(boolean addToBackStack) {
        this.addToBackStack = addToBackStack;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
}

