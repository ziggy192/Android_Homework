package com.example.nghia.okhttplogin.customViews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nghia.okhttplogin.R;
import com.example.nghia.okhttplogin.models.FoodItem;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nghia on 12/3/2016.
 */

public class FoodItemAddapter extends ArrayAdapter<FoodItem> {


    public FoodItemAddapter(Context context, int resource, List<FoodItem> objects) {
        super(context, resource, objects);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.food_item_view, parent, false);
        }

        new FoodViewHolder(convertView).setData(getContext(),getItem(position));

        return convertView;
    }
}
