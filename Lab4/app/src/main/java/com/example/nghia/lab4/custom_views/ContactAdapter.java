package com.example.nghia.lab4.custom_views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nghia.lab4.R;
import com.example.nghia.lab4.models.Contact;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nghia on 12/8/2016.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {
    @BindView(R.id.tv_contact_name)
    TextView tvContactName;



    @BindView(R.id.imv_contact_logo)
    ImageView imvContactLogo;

    public ContactAdapter(Context context, int resource, List<Contact> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.contact_list_item, parent,false);
            ButterKnife.bind(this, convertView);
            updateUI( position);
        }


        return convertView;
    }

    private void updateUI(int position) {
        Contact contact = getItem(position);
        tvContactName.setText(contact.getName());
        Picasso.with(getContext())
                .load(contact.getLogoImageUrl())
                .error(R.drawable.ic_error_black_48dp)
                .placeholder(R.drawable.ic_image_black_48dp)
                .into(imvContactLogo);
    }
}
