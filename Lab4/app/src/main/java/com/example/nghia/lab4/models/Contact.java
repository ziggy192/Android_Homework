package com.example.nghia.lab4.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nghia on 12/4/2016.
 */

public class Contact {
    @SerializedName("name")
    private String name;
    @SerializedName("phone")
    private String phoneNumber;
    @SerializedName("website")
    private String website;
    @SerializedName("images")
    private Image[] images;

    public Contact(String name, String phoneNumber, String website, Image[] images) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.images = images;
    }

    public String getName() {
        return name;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public String getWebsite() {
        return website;
    }

    public String getLogoImageUrl() {
        for (Image image : images)
            if (image.isLogo()) {
                return image.getUrl();
            }
        return null;
    }
    public String getFullPhotoUrl() {
        for (Image image : images)
            if (image.isPhoto()) {
                return image.getUrl();
            }
        return null;
    }


    @Override
    public String toString() {
        return this.name;
    }

    public static ArrayList<Contact> contacts = new ArrayList<>(
//            Arrays.asList(new Contact[]{
//                    new Contact("FPT Software", "0473007575", "https://www.fpt-software.com")
//                    , new Contact("EWay", "+84432595450", "https://eway.vn")
//                    , new Contact("KMS", "+84838486888", "http://www.kms-technology.com")
//                    , new Contact("BraveBits", " +84463260066", "http://www.bravebits.vn")
//                    , new Contact("TechKids", "+841653005670", "http://techkids.vn")
//            })
    );
    public static void updateDataList(Contact[] newContacs) {
        contacts.clear();
        contacts.addAll(Arrays.asList(newContacs));
    }


}
