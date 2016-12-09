package com.example.nghia.lab4.activities;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.example.nghia.lab4.events.DataLoadedEvent;
import com.example.nghia.lab4.models.CompanyResponseData;
import com.example.nghia.lab4.models.Contact;
import com.example.nghia.lab4.models.Content;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Nghia on 12/4/2016.
 */

public class Lab4Application extends Application {

    private static final String DATA_URL = "https://a-server.herokuapp.com/api/company";
    private static final String TAG = Application.class.toString();

    @Override
    public void onCreate() {
        super.onCreate();
        sendGet();

    }

    private void sendGet() {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(DATA_URL).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();

                final CompanyResponseData companyResponseData = new Gson().fromJson(body, CompanyResponseData.class);
                final Content content = companyResponseData.getContent();
                Log.d(TAG, String.format("onResponse: content = %s", content));
                Contact.updateDataList(content.getContacts());
                EventBus.getDefault().postSticky(new DataLoadedEvent());
            }
        });

    }
}
