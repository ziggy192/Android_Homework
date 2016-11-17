package com.example.nghia.dictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nghia.dictionary.managers.DBManager;
import com.example.nghia.dictionary.modules.Word;

public class DetailActivity extends AppCompatActivity {


    private TextView tv_original_word;
    private TextView tv_translated_word;
    private ImageView iv_heart;

    private Word word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getReferrences();
        setupUI();
        addListeners();
    }

    private void addListeners() {
        iv_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (word != null) {
                    DBManager db = DBManager.instance;

                    word.changeFavorite();
                    db.update(word);

                    int is_favorite = word.getIsFavorite();
                    setHeartUI(is_favorite);
                }






            }
        });
    }

    private void setHeartUI(int is_favorite) {
        switch (is_favorite) {
            case 0:
                iv_heart.setImageResource(R.drawable.red_heart);
                break;
            case 1:
                iv_heart.setImageResource(R.drawable.transparent_heart);
                break;
        }
    }


    private void setupUI() {
        int id = getIntent().getIntExtra(Word.ID_KEY,-1);
        DBManager db = DBManager.instance;
        if (id != -1) {
            word = db.selectWordById(id);
            if (word != null) {
                tv_original_word.setText(word.getOriginalWord());
                tv_translated_word.setText(word.getTranslatedWord());

                int is_favorite = word.getIsFavorite();
                setHeartUI( is_favorite);

            }
        }

    }

    private void getReferrences() {
        tv_original_word = (TextView) findViewById(R.id.tv_original_word);
        tv_translated_word = (TextView) findViewById(R.id.tv_translated_word);
        iv_heart = (ImageView) findViewById(R.id.iv_heart);
    }
}
