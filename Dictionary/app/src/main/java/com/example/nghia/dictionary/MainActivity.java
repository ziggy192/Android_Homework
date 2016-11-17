package com.example.nghia.dictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.nghia.dictionary.managers.DBManager;
import com.example.nghia.dictionary.modules.Word;
import com.example.nghia.dictionary.modules.WordSample;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.toString();

    private DBManager dbManager;

    private SearchView searchView;
    private ListView listView;
    private ArrayAdapter arrayAdapter;
    private ArrayList<WordSample> wordSampleList = new ArrayList<>(

    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        dbManager = DBManager.instance;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getReferrences();
        setupUI();
        addListeners();






    }

    private void addListeners() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d(TAG, "searchView: Searching...");
                Log.d(TAG, searchView.getQuery().toString());


                wordSampleList.clear();
                wordSampleList.addAll(dbManager.searchWordSampleByContent(searchView.getQuery().toString()));
                arrayAdapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(Word.ID_KEY, wordSampleList.get(position).getId());
                startActivity(intent);
            }
        });


    }

    private void setupUI() {
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, wordSampleList);
        listView.setAdapter(arrayAdapter);



    }

    private void getReferrences() {
        searchView = (SearchView) findViewById(R.id.sv_search_word);
        listView = (ListView) findViewById(R.id.lv_search_results);

    }

    private void selectAll() {
        List<Word> wordList = dbManager.selectAll();

        for (Word word :
                wordList) {

            Log.d(TAG, String.format("%s", word));
        }

    }

    private void testSelectRandom() {
        Word word = dbManager.selectRandom();
        Log.d(TAG, String.format("Random = %s", word));
    }

    private void testSelectByFavorite(int favorite) {
        Log.d(TAG, "select on favorite");
        List<Word> wordList;
        wordList = dbManager.selectByFavorite(1);
        for (Word word :
                wordList) {

            Log.d(TAG, String.format("%s", word));
        }
    }

//    private void testSearchByContent(String content) {
//        List<Word> wordList;
//        wordList = dbManager.searchByContent(content);
//        Log.d(TAG, "search result");
//        for (Word word :
//                wordList) {
//
//            Log.d(TAG, String.format("%s", word));
//        }
//    }
}
