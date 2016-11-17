package com.example.nghia.dictionary;

import android.app.Application;
import android.util.Log;

import com.example.nghia.dictionary.managers.DBManager;

/**
 * Created by Nghia on 11/16/2016.
 */

public class DictionaryApplication extends Application {

    private static final String TAG = DictionaryApplication.class.toString();


    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate(");
        DBManager.init(this);
        super.onCreate();
    }
}
