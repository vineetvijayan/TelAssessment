package com.example.telassessment.adapter;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.telassessment.R;
import com.example.telassessment.model.Rows;

public class DataBindingAdapter {

    @BindingAdapter("android:imgSrc")
    public static void setImageUri(ImageView view, Rows rowData) {
        // Avoid loading previously failed image
        if (!rowData.isImgLoadFailed()) {
            Glide.with(view.getContext())
                    .load(rowData.getImageHref())
                    .placeholder(R.drawable.loading_spinner)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            rowData.setImgLoadFailed(true);
                            view.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            rowData.setImgLoadFailed(false);
                            view.setVisibility(View.VISIBLE);
                            return false;
                        }
                    })
                    .into(view);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}
