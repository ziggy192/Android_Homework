package com.example.nghia.dictionary.modules;

/**
 * Created by Nghia on 11/16/2016.
 */

public class WordSample {
    private int id;
    private String originalWord;

    public WordSample(int id, String originalWord) {
        this.id = id;
        this.originalWord = originalWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalWord() {
        return originalWord;
    }

    public void setOriginalWord(String originalWord) {
        this.originalWord = originalWord;
    }

    @Override
    public String toString() {
        return originalWord;
    }
}
