package com.example.nghia.dailyquote.fragments;


import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.nghia.dailyquote.ChangeFragmentEvent;
import com.example.nghia.dailyquote.R;
import com.example.nghia.dailyquote.managers.Preferences;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private static final String TAG = LoginFragment.class.toString();

    @BindView(R.id.et_name)
    EditText etName;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_save)
    public void changeFragment() {
        String newUserName = etName.getText().toString();
        Preferences.getInstance().putUserName(newUserName);
        EventBus.getDefault().post(new ChangeFragmentEvent(new QuoteFragment(), true));
    }

}
