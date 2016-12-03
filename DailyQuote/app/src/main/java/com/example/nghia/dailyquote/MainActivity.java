package com.example.nghia.dailyquote;

import android.nfc.Tag;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.transition.Transition;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nghia.dailyquote.fragments.LoginFragment;
import com.example.nghia.dailyquote.fragments.QuoteFragment;
import com.example.nghia.dailyquote.managers.Preferences;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupUI();

    }

    private void init() {
        EventBus.getDefault().register(this);
    }

    private void setupUI() {
        if (Preferences.getInstance().getUserName() == null) {
            changeFragment(new LoginFragment(), false);

        } else {
            changeFragment(new QuoteFragment(), false);
        }
    }

    private void changeFragment(Fragment fragment, boolean addToBackStack) {
        Log.d(TAG, String.format("ChangeFragment: %s", fragment.getClass().toString()));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }


    @Subscribe
    public void onChangeFragment(ChangeFragmentEvent event) {
        changeFragment(event.getFragment(),event.isAddToBackStack());

    }
}
