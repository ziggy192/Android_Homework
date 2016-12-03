package com.example.nghia.dailyquote;

import android.app.Application;

import com.example.nghia.dailyquote.managers.Preferences;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Nghia on 12/3/2016.
 */

public class QuoteApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Preferences.init(this);
        initLoader();
    }
    private void initLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
    }
}
