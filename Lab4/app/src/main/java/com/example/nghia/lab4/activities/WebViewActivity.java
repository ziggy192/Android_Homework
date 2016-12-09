package com.example.nghia.lab4.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.nghia.lab4.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.wv_browser)
    WebView wvBrowser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        wvBrowser.setWebViewClient(new WebViewClient());
        String webURl = getIntent().getStringExtra(MainActivity.WEBSITE_KEY);
        wvBrowser.loadUrl(webURl);
    }
}
