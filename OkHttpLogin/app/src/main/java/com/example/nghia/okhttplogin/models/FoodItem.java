package com.example.nghia.okhttplogin.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nghia on 12/3/2016.
 */

public class FoodItem {
    @SerializedName("name")
    private String name;
    @SerializedName("detail")
    private String detail;
    @SerializedName("price")
    private int price;
    @SerializedName("image")
    private String imageUrl;

    public FoodItem(String name, String detail, int price, String imageUrl) {
        this.name = name;
        this.detail = detail;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }



    public String getPriceText() {
        return String.format("%s VND", price);
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public   static FoodItem[] foodItems = {
            new FoodItem("pizza", "large pizza", 100, "image")
            , new FoodItem("haburger", "large haburger", 200, "image")
            , new FoodItem("my quang", "my quang Da Nang", 100, "image")
    };

    public static ArrayList<FoodItem> foodItemArrayList = new ArrayList<>(Arrays.asList(foodItems));

}
