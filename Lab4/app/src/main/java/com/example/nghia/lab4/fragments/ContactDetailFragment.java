package com.example.nghia.lab4.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nghia.lab4.activities.MainActivity;
import com.example.nghia.lab4.R;
import com.example.nghia.lab4.events.StartWebsite;
import com.example.nghia.lab4.models.Contact;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactDetailFragment extends Fragment {


    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;
    @BindView(R.id.tv_website)
    TextView tvWebsite;
    ImageView imvContactPhoto;

    Contact contact;
    public ContactDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_contact_detail, container, false);
        ButterKnife.bind(this, rootView);
        setupUI();

        return rootView;
    }

    private void setupUI() {

        contact = Contact.contacts.get(
                getArguments().getInt(
                        MainActivity.CONTACT_ID_KEY));

        tvName.setText(contact.getName());
        tvPhoneNumber.setText(contact.getPhoneNumber());
        tvWebsite.setText(contact.getWebsite());
    }

    @OnClick(R.id.tv_phone_number)
    public void startCall() {
        String phoneNumber = tvPhoneNumber.getText().toString();
        String uri = "tel:" + phoneNumber.trim() ;
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    @OnClick(R.id.tv_website)
    public void startWebsite() {
        EventBus.getDefault().post(new StartWebsite(tvWebsite.getText().toString()));
    }

    @OnClick(R.id.imb_popup_picture)
    public void popupPicture() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_photo_view, (ViewGroup) getView().getParent(), false);
        imvContactPhoto = (ImageView) view.findViewById(R.id.imv_contact_full_photo);
        Picasso.with(getContext())
                .load(contact.getFullPhotoUrl())
                .placeholder(R.drawable.ic_image_black_48dp)
                .error(R.drawable.ic_error_black_48dp)
                .into(imvContactPhoto);
        AlertDialog customDialog = new AlertDialog.Builder(getContext())
                .setView(view)
                .create();
        customDialog.show();

    }

}
