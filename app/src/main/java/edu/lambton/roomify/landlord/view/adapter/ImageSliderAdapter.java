package edu.lambton.roomify.landlord.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import edu.lambton.roomify.R;
import edu.lambton.roomify.landlord.dto.PropertyResponseInfo;
import edu.lambton.roomify.landlord.view.PropertyInfoActivity;


public class ImageSliderAdapter extends PagerAdapter {

    private List<PropertyResponseInfo.PhotoDTO> photoDTOS;
    private LayoutInflater layoutInflater;
    private Context context;

    public ImageSliderAdapter(PropertyInfoActivity propertyInfoActivity, List<PropertyResponseInfo.PhotoDTO> photoDTOS) {
        this.context = propertyInfoActivity;
        this.photoDTOS = photoDTOS;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return photoDTOS.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.item_image_slider, container, false);

        ImageView imageView = view.findViewById(R.id.imageViewSliderItem);
        Picasso.get().setLoggingEnabled(true);
        Picasso.get()
                .load(photoDTOS.get(position).getPath())
                .placeholder(R.drawable.profile_placeholder)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        System.out.println("Picasso Image Loader Successfully");
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
