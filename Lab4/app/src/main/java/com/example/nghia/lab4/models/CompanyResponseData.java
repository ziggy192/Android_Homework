package com.example.nghia.lab4.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nghia on 12/4/2016.
 */

public class CompanyResponseData {
    @SerializedName("content")
    private Content content;

    public CompanyResponseData(Content content) {
        this.content = content;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
