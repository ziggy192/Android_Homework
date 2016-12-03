package com.example.nghia.dailyquote;

import android.support.v4.app.Fragment;

/**
 * Created by Nghia on 12/3/2016.
 */

public class ChangeFragmentEvent {
    private Fragment fragment;
    private boolean addToBackStack;

    public ChangeFragmentEvent(Fragment fragment, boolean addToBackStack) {
        this.fragment = fragment;
        this.addToBackStack = addToBackStack;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public boolean isAddToBackStack() {
        return addToBackStack;
    }
}
