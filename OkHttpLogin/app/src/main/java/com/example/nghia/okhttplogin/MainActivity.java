package com.example.nghia.okhttplogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nghia.okhttplogin.models.LoginAccount;
import com.example.nghia.okhttplogin.models.LoginResult;
import com.google.gson.Gson;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    private static final String LOGIN_URL = "https://a5-tumblelog.herokuapp.com/api/login";
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    LoginResult result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_log_in)
    public void loginButtonClicked(){
        LoginAccount loginAccount = new LoginAccount(etUserName.getText().toString(),
                                etPassword.getText().toString());
        startLogin(loginAccount);
    }
    public void startLogin(LoginAccount loginAccount){
        MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();
        String data = gson.toJson(loginAccount);

        RequestBody body = RequestBody.create(JSON,data);


        Request request = new Request.Builder().url(LOGIN_URL).post(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                result = new Gson().fromJson(body, LoginResult.class);
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }

    @OnClick(R.id.btn_go_to_food)
    public void goToFoodMenu() {
        Intent intent = new Intent(this, FoodMenuActivity.class);
        startActivity(intent);
    }
}
