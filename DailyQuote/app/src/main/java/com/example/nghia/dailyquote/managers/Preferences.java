package com.example.nghia.dailyquote.managers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Nghia on 12/3/2016.
 */

public class Preferences {
    private static final String PREFERENCES_KEY = "Quote";
    private static final String USERNAME_KEY = "userName";
    SharedPreferences sharedPreferences;

    public Preferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
    }

    public void putUserName(String userName) {
        sharedPreferences.edit().putString(USERNAME_KEY, userName).commit();
    }

    public String getUserName() {
        return sharedPreferences.getString(USERNAME_KEY, null);
    }

    private static Preferences instance;

    public static Preferences getInstance() {
        return instance;
    }

    public static void init(Context context) {
        instance = new Preferences(context);
    }

}
