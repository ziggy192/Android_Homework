package com.example.nghia.okhttplogin.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nghia on 12/3/2016.
 */

public class GitHubData {
    @SerializedName("name")
    private String name;
    @SerializedName("owner")
    private GithubDataOwner owner;

    public GitHubData(String name, GithubDataOwner owner) {
        this.name = name;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GithubDataOwner getOwner() {
        return owner;
    }

    public void setOwner(GithubDataOwner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", owner=" + owner +
                '}';
    }
}
