package com.example.nghia.dictionary.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nghia.dictionary.modules.Word;
import com.example.nghia.dictionary.modules.WordSample;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nghia on 11/16/2016.
 */

public class DBManager extends SQLiteAssetHelper {


    private static final String DATABASE_NAME = "dictionary.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DICTIONARY_TABLE_NAME = "tbl_dictionary";

    /*=====================================DICTIONARY CONSTANTS==========================*/

    private static final String DICTIONARY_ID_COLUMN = "id";
    private static final String DICTIONARY_ORIGINAL_WORD_COLUMN = "original_word";
    private static final String DICTIONARY_TRANSLATED_WORD_COLUMN = "translated_word";
    private static final String DICTIONARY_DATE_CREATED_COLUMN = "date_created";
    private static final String DICTIONARY_IS_FAVORITE_COLUMN = "is_favorite";


    private static final String[] DICTIONARY_COLUMNS = {
            DICTIONARY_ID_COLUMN,
            DICTIONARY_ORIGINAL_WORD_COLUMN,
            DICTIONARY_TRANSLATED_WORD_COLUMN,
            DICTIONARY_DATE_CREATED_COLUMN,
            DICTIONARY_IS_FAVORITE_COLUMN
    };

    /*==============================CONSTRUCTOR AND SINGLETON==========================*/

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public static DBManager instance;

    public static void init(Context context) {
        instance = new DBManager(context);
    }


    /*================================methods====================================*/


    public static ArrayList<Word> getWordListFromCursor(Cursor cursor) {
        ArrayList<Word> wordArrayList = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(DBManager.DICTIONARY_ID_COLUMN));
            String originalWord = cursor.getString(cursor.getColumnIndex(DICTIONARY_ORIGINAL_WORD_COLUMN));
            String translatedWord = cursor.getString(cursor.getColumnIndex(DICTIONARY_TRANSLATED_WORD_COLUMN));
            String dateCreated = cursor.getString(cursor.getColumnIndex(DICTIONARY_DATE_CREATED_COLUMN));
            int isFavorite = cursor.getInt(cursor.getColumnIndex(DICTIONARY_IS_FAVORITE_COLUMN));

            Word word = new Word(id, originalWord, translatedWord, dateCreated,isFavorite);
            wordArrayList.add(word);
        }
        return wordArrayList;
    }

    public static ArrayList<WordSample> getWordSampleListFromCursor(Cursor cursor) {

        ArrayList<WordSample> wordSampleArrayList = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(DBManager.DICTIONARY_ID_COLUMN));
            String originalWord = cursor.getString(cursor.getColumnIndex(DICTIONARY_ORIGINAL_WORD_COLUMN));

            WordSample wordSample = new WordSample(id, originalWord);
            wordSampleArrayList.add(wordSample);
        }
        return wordSampleArrayList;
    }

    public List<Word> selectAll() {
        List<Word> wordArrayList ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DICTIONARY_TABLE_NAME, DICTIONARY_COLUMNS, null, null, null, null, null);

        wordArrayList = getWordListFromCursor(cursor);

        db.close();
        return wordArrayList;
    }

    public void insert(Word newWord) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if (newWord.getId() != -1) {
            contentValues.put(DICTIONARY_ID_COLUMN, newWord.getId());
        }
        contentValues.put(DICTIONARY_ORIGINAL_WORD_COLUMN, newWord.getOriginalWord());
        contentValues.put(DICTIONARY_TRANSLATED_WORD_COLUMN, newWord.getTranslatedWord());
        contentValues.put(DICTIONARY_IS_FAVORITE_COLUMN, newWord.getIsFavorite());


        db.insert(DICTIONARY_TABLE_NAME, null, contentValues);
        db.close();
    }

    public void deleteById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] args = {String.format("%s", id)};
        db.delete(DICTIONARY_TABLE_NAME, "id = ?", args);
        db.close();
    }


    public void update(Word word) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DICTIONARY_ID_COLUMN, word.getId());
        contentValues.put(DICTIONARY_ORIGINAL_WORD_COLUMN, word.getOriginalWord());
        contentValues.put(DICTIONARY_TRANSLATED_WORD_COLUMN, word.getTranslatedWord());
        contentValues.put(DICTIONARY_DATE_CREATED_COLUMN, word.getDateCreated());
        contentValues.put(DICTIONARY_IS_FAVORITE_COLUMN, word.getIsFavorite());

        db.update(DICTIONARY_TABLE_NAME, contentValues, String.format("id = %s", word.getId()),null);
        db.close();
    }

    public Word selectRandom() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DICTIONARY_TABLE_NAME, DICTIONARY_COLUMNS, null, null, null, null, "random()", "1");

        Word word = getWordListFromCursor(cursor).get(0);

        db.close();
        return word;
    }

    public ArrayList<WordSample> searchWordSampleByContent(String content) {
        ArrayList<WordSample> wordSampleList ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DICTIONARY_TABLE_NAME, DICTIONARY_COLUMNS,
                DICTIONARY_ORIGINAL_WORD_COLUMN + " like ?", new String[]{"%" + content + "%"}, null, null, null);


        wordSampleList = getWordSampleListFromCursor(cursor);
        db.close();
        return wordSampleList;
    }

    public List<Word> selectByFavorite(int favorite) {
        List<Word> wordList ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DICTIONARY_TABLE_NAME, DICTIONARY_COLUMNS,
                DICTIONARY_IS_FAVORITE_COLUMN + " = ?", new String[]{String.format("%s", favorite)}
                , null,null,null);



        wordList = getWordListFromCursor(cursor);
        db.close();
        return wordList;
    }

    public Word selectWordById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DICTIONARY_TABLE_NAME, DICTIONARY_COLUMNS,
                DICTIONARY_ID_COLUMN + String.format(" = %s", id), null, null, null, null);
        ArrayList<Word> wordArrayList = getWordListFromCursor(cursor);
        if (!wordArrayList.isEmpty()) {
            return wordArrayList.get(0);
        } else {
            return null;
        }
    }




}
