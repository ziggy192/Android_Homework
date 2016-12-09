package com.example.nghia.lab4.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.nghia.lab4.events.ChangeToDetailFragmentEvent;
import com.example.nghia.lab4.R;
import com.example.nghia.lab4.events.DataLoadedEvent;
import com.example.nghia.lab4.events.StartWebsite;
import com.example.nghia.lab4.fragments.ContactDetailFragment;
import com.example.nghia.lab4.fragments.ContactListFragment;
import com.example.nghia.lab4.models.CompanyResponseData;
import com.example.nghia.lab4.models.Contact;
import com.example.nghia.lab4.models.Content;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.Arrays;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String CONTACT_ID_KEY = "contac_position key";
    public static final String WEBSITE_KEY = "website_change_key";
    private static final String TAG = MainActivity.class.toString();
    FrameLayout flDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getReferences();
        EventBus.getDefault().register(this);
        setupUI();


    }

    private void setupUI() {

    }


    private void updateUI() {
        changeFragment(new ContactListFragment(),R.id.fl_container, false);
//        Toast.makeText(MainActivity.this, "Load data successfully!", Toast.LENGTH_SHORT).show();
    }

    private void changeFragment(Fragment fragment, int frameLayoutId,boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction().replace(frameLayoutId, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Subscribe
    public void onChangeToDetailFragmentEvent(ChangeToDetailFragmentEvent event){
        ContactDetailFragment contactDetailFragment = new ContactDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(
                CONTACT_ID_KEY, event.getContactId());

        contactDetailFragment.setArguments(bundle);

        if (flDetail == null) {
            //portrait
            changeFragment(contactDetailFragment, R.id.fl_container, event.isAddToBackStack());
        } else {
            //landscape
            changeFragment(contactDetailFragment, R.id.fl_detail, event.isAddToBackStack());

        }
    }

    @Subscribe
    public void onStartWebsite(StartWebsite event) {
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        intent.putExtra(WEBSITE_KEY, event.getWebSite());
        startActivity(intent);

    }


    public void getReferences() {
        flDetail = (FrameLayout) findViewById(R.id.fl_detail);

    }

    @Subscribe(sticky = true)
    public void onDataLoaded(DataLoadedEvent event) {
        updateUI();
    }
}
