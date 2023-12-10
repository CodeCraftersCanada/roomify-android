package edu.lambton.roomify.landlord.view.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import edu.lambton.roomify.landlord.view.PropertyInfoActivity;

public class ImageSliderAdapter extends PagerAdapter {

    public ImageSliderAdapter(PropertyInfoActivity propertyInfoActivity, int totalImages) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}
