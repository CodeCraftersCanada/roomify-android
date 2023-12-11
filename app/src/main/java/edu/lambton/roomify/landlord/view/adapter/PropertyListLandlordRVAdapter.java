package edu.lambton.roomify.landlord.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

import edu.lambton.roomify.R;
import edu.lambton.roomify.landlord.model.Property;

public class PropertyListLandlordRVAdapter extends RecyclerView.Adapter<PropertyListLandlordRVAdapter.PropertyViewHolder> {


    private final List<Property> propertyList;
    private static OnPropertyCardListener onCardListener;
    private final Context context;

    public PropertyListLandlordRVAdapter(Context context, List<Property> propertyList, OnPropertyCardListener onCardListener) {
        this.context = context;
        this.propertyList = propertyList;
        PropertyListLandlordRVAdapter.onCardListener = onCardListener;
    }

    @NonNull
    @Override
    public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.property_card_row, parent, false);
        return new PropertyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyViewHolder holder, int position) {
        Property property = propertyList.get(position);
        holder.bind(property);
    }

    @Override
    public int getItemCount() {
        return propertyList.size();
    }

    public static class PropertyViewHolder extends RecyclerView.ViewHolder {

        private final MaterialCardView propertyListCardView;
        private final ImageView propertyPicture;
        private final TextView namePropertyTextView;
        /*private final TextView statusTextView;*/

        public PropertyViewHolder(@NonNull View itemView) {
            super(itemView);
            propertyPicture = itemView.findViewById(R.id.propertyPicture);
            namePropertyTextView = itemView.findViewById(R.id.namePropertyTextView);
            propertyListCardView = itemView.findViewById(R.id.propertyListCardView);
            /*statusTextView = itemView.findViewById(R.id.statusTextView);*/

            propertyListCardView.setOnClickListener(v -> {
                // Invoke the callback when the card is clicked
                if (onCardListener != null) {
                    onCardListener.onPropertyCardHandle(v);
                }
            });
        }

        public void bind(@NonNull Property property) {
            // Bind data to views
            // For example:
            // propertyPicture.setImageResource(property.getPictureResource());
            namePropertyTextView.setText(property.description());
            //statusTextView.setText(property.getStatus());
        }
    }

    public interface OnPropertyCardListener {
        void onPropertyCardHandle(View view);
    }
}
