package com.example.nghia.lab4.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nghia on 12/8/2016.
 */

public class Image {

    private static final  String LOGO_STRING = "logo";
    private static final String PHOTO_STRING = "picture";
    @SerializedName("url")
    private String url;
    @SerializedName("type")
    private String type;

    public Image(String type, String url) {
        this.type = type;
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public boolean isLogo() {
        return type.equals(LOGO_STRING);
    }

    public boolean isPhoto() {
        return type.equals(PHOTO_STRING);
    }
}
