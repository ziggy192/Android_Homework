package com.example.nghia.okhttplogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nghia.okhttplogin.models.GitHubData;
import com.google.gson.Gson;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GitHubActivity extends AppCompatActivity {

    private static final String GIT_HUB_URL = "https://api.github.com/repositories";
    @BindView(R.id.lv_github_data)
    ListView lvGitHubData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_hub);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_get_github)
    public void getGithubData() {
        sendGet();
    }

    private void setupUI(GitHubData[] gitHubDatas) {
        ArrayAdapter<GitHubData> githubArrayAdapter = new ArrayAdapter<GitHubData>(this
                , android.R.layout.simple_list_item_1
                , gitHubDatas);
        lvGitHubData.setAdapter(githubArrayAdapter);

    }

    public void sendGet() {
        OkHttpClient clien = new OkHttpClient();
        Request request = new Request.Builder().url(GIT_HUB_URL).build();
        clien.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                final GitHubData[] gitHubDatas = new Gson().fromJson(body, GitHubData[].class);

                GitHubActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setupUI(gitHubDatas);
                        Toast.makeText(GitHubActivity.this, "Got data successfully", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
