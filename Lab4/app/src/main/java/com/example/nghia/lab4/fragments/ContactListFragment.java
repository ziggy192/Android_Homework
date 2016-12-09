package com.example.nghia.lab4.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nghia.lab4.custom_views.ContactAdapter;
import com.example.nghia.lab4.events.ChangeToDetailFragmentEvent;
import com.example.nghia.lab4.R;
import com.example.nghia.lab4.models.Contact;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactListFragment extends Fragment {

    ContactAdapter contactArrayAdapter;
    private static final String TAG = ContactListFragment.class.toString();

    @BindView(R.id.lv_contact)
    ListView lvContacts;

    private Contact contact;

    public ContactListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_contact_list, container, false);
        ButterKnife.bind(this, rootView);
        setupUI();
        addListenners();
        Log.d(TAG, "On Fragement Created");
        return rootView;
    }

    private void addListenners() {
        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                EventBus.getDefault().post(new ChangeToDetailFragmentEvent(
                        position,true
                ));
                Log.d(TAG, String.format("item clicked position = %s", position));
            }
        });
    }

    private void setupUI() {
        contactArrayAdapter = new ContactAdapter(getActivity(),
                R.layout.contact_list_item
                ,Contact.contacts);
        lvContacts.setAdapter(contactArrayAdapter);
    }

    public void updateListView() {
        if (contactArrayAdapter != null) {
            contactArrayAdapter.notifyDataSetChanged();

        }
    }


}
