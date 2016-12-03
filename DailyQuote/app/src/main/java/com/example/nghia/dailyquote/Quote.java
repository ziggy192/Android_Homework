package com.example.nghia.dailyquote;

/**
 * Created by Nghia on 12/3/2016.
 */

public class Quote {
    private String title;
    private String content;

    public Quote(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content.replace("\n","");
    }

    public void setContent(String content) {
        this.content = content;
    }
}
