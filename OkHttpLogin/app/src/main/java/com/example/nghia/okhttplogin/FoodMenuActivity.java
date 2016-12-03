package com.example.nghia.okhttplogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nghia.okhttplogin.customViews.FoodItemAddapter;
import com.example.nghia.okhttplogin.models.FoodItem;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FoodMenuActivity extends AppCompatActivity {

    private static final String FOOD_URL = "https://a-server.herokuapp.com/api/food";
    @BindView(R.id.lv_food_menu)
    ListView lvFoodMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);
        ButterKnife.bind(this);


        sendGet(FOOD_URL);

    }

    private void setupUI() {
        lvFoodMenu.setAdapter(new FoodItemAddapter(this,
                R.layout.food_item_view,
                FoodItem.foodItemArrayList));
    }

    void sendGet(String url){
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                FoodMenuActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(FoodMenuActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                FoodItem.foodItems  = new Gson().fromJson(body,FoodItem[].class);
                FoodItem.foodItemArrayList.clear();
                FoodItem.foodItemArrayList.addAll(Arrays.asList(FoodItem.foodItems));

                FoodMenuActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(FoodMenuActivity.this, "Got data successfully", Toast.LENGTH_SHORT).show();
                        setupUI();
                    }
                });
            }
        });
    }

    @OnClick(R.id.btn_go_to_github)
    public void goToGithubActivity() {
        Intent intent = new Intent(this, GitHubActivity.class);
        startActivity(intent);
    }

}
