package com.example.nghia.dailyquote.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.EventLog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nghia.dailyquote.ChangeFragmentEvent;
import com.example.nghia.dailyquote.Constant;
import com.example.nghia.dailyquote.MainActivity;
import com.example.nghia.dailyquote.Quote;
import com.example.nghia.dailyquote.R;
import com.example.nghia.dailyquote.managers.Preferences;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteFragment extends Fragment {

    private static final String TAG = QuoteFragment.class.toString();
    @BindView(R.id.imv_background)
    ImageView imvBackground;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_hello)
    TextView tvHello;

    public QuoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quote, container, false);
        ButterKnife.bind(this,view);
        setupUI();
        return view;
    }

    private void setupUI() {
        ImageLoader.getInstance().displayImage(Constant.UNSPLASH_API,imvBackground);

        tvHello.setText(String.format("Hello %s", Preferences.getInstance().getUserName()));

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(Constant.QUOTE_API).build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                Quote[] quotes = new Gson().fromJson(body, Quote[].class);
                if (quotes.length > 0) {
                    updateQuote(quotes[0]);

                }

            }
        });
    }

    private void updateQuote(final Quote quote) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvContent.setText(Html.fromHtml(quote.getContent()).toString().trim());
                tvTitle.setText(quote.getTitle());
            }
        });
    }

//    @OnClick(R.id.btn_login)
//    public void backToLogin() {
//        EventBus.getDefault().post(new ChangeFragmentEvent(new LoginFragment(),false));
//    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Preferences.getInstance().putUserName("");

    }
}
