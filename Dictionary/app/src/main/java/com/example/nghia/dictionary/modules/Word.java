package com.example.nghia.dictionary.modules;

import android.database.Cursor;

import com.example.nghia.dictionary.managers.DBManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nghia on 11/16/2016.
 */

public class Word {

    public static final String ID_KEY = "id";
    private int id;
    private String originalWord;
    private String translatedWord;
    private String dateCreated;
    private int isFavorite;

    public Word(int id, String originalWord, String translatedWord, String dateCreated, int isFavorite) {
        this.id = id;
        this.originalWord = originalWord;
        this.translatedWord = translatedWord;
        this.dateCreated = dateCreated;
        this.isFavorite = isFavorite;
    }

    public Word(String originalWord, String translatedWord, String dateCreated, int isFavorite) {
        this(-1, originalWord, translatedWord, dateCreated, isFavorite);
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

    public String getTranslatedWord() {
        return translatedWord;
    }

    public void setTranslatedWord(String translatedWord) {
        this.translatedWord = translatedWord;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.isFavorite = isFavorite;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", originalWord='" + originalWord + '\'' +
                ", translatedWord='" + translatedWord + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", isFavorite=" + isFavorite +
                '}';
    }

    public void changeFavorite() {
        isFavorite = 1 - isFavorite;
    }


}
