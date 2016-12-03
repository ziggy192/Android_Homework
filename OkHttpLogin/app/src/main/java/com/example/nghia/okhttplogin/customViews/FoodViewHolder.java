package com.example.nghia.okhttplogin.customViews;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nghia.okhttplogin.R;
import com.example.nghia.okhttplogin.models.FoodItem;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nghia on 12/3/2016.
 */

public class FoodViewHolder {

    @BindView(R.id.tv_food_name)
    TextView tvFoodName;
    @BindView(R.id.tv_food_detail)
    TextView tvFoodDetail;
    @BindView(R.id.tv_food_price)
    TextView tvFoodPrice;
    @BindView(R.id.iv_food_image)
    ImageView ivFoodImage;


    public FoodViewHolder(View rootView) {
        ButterKnife.bind(this, rootView);
    }
    public void setData(Context context, FoodItem foodItem) {
        tvFoodName.setText(foodItem.getName());
        tvFoodDetail.setText(foodItem.getDetail());
        tvFoodPrice.setText(foodItem.getPriceText());
        Picasso.with(context).load(foodItem.getImageUrl()).into(ivFoodImage);

    }
}
