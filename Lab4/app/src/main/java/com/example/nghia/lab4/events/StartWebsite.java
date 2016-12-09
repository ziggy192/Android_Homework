package com.example.nghia.lab4.events;

/**
 * Created by Nghia on 12/4/2016.
 */

public class StartWebsite {
    private String webSite;

    public StartWebsite(String webSite) {
        this.webSite = webSite;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }
}
