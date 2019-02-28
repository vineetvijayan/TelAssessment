package com.example.telassessment.adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class DataBindingAdapter {

    @BindingAdapter("android:imgSrc")
    public static void setImageUri(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }
}
