package edu.lambton.roomify.landlord.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.List;

import edu.lambton.roomify.R;
import edu.lambton.roomify.common.FormatUtils;
import edu.lambton.roomify.landlord.dto.PropertyResponseComplete;

public class PropertyListLandlordRVAdapter extends RecyclerView.Adapter<PropertyListLandlordRVAdapter.PropertyViewHolder> {


    private final List<PropertyResponseComplete.Property> propertyList;
    private static OnPropertyCardListener onCardListener;
    private final Context context;

    public PropertyListLandlordRVAdapter(Context context, List<PropertyResponseComplete.Property> propertyList, OnPropertyCardListener onCardListener) {
        this.context = context;
        this.propertyList = propertyList;
        PropertyListLandlordRVAdapter.onCardListener = onCardListener;
    }

    @NonNull
    @Override
    public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row_property, parent, false);
        return new PropertyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyViewHolder holder, int position) {
        PropertyResponseComplete.Property property = propertyList.get(position);
        holder.bind(property);
    }

    @Override
    public int getItemCount() {
        return propertyList.size();
    }

    public static class PropertyViewHolder extends RecyclerView.ViewHolder {

        private final CardView propertyListCardView;
        private final ImageView propertyPicture;
        private final TextView descriptionPropertyTextView;
        private final TextView addressTextViewTextView;
        private final TextView pricePropertyTextView;
        private final TextView creationDateTextView;
        private final TextView bathroomCounterTextView;
        private final TextView bedroomCounterTextView;
        private final MaterialButton callMeButton;
        /*private final TextView statusTextView;*/

        public PropertyViewHolder(@NonNull View itemView) {
            super(itemView);
            propertyPicture = itemView.findViewById(R.id.propertyMainImage);
            descriptionPropertyTextView = itemView.findViewById(R.id.descriptionProperty);
            propertyListCardView = itemView.findViewById(R.id.cardPropertyListing);
            addressTextViewTextView = itemView.findViewById(R.id.addressTextView);
            pricePropertyTextView = itemView.findViewById(R.id.priceProperty);
            creationDateTextView = itemView.findViewById(R.id.creationDateTextView);
            bathroomCounterTextView = itemView.findViewById(R.id.bathroomCounterTextView);
            bedroomCounterTextView = itemView.findViewById(R.id.bedroomCounterTextView);

            callMeButton = itemView.findViewById(R.id.callMeButton);

            /*statusTextView = itemView.findViewById(R.id.statusTextView);*/

        }

        public void bind(@NonNull PropertyResponseComplete.Property property) {

            if (property.getPhotos() != null || property.getPhotos().size() > 0) {
                Picasso.get()
                        .load(property.getPhotos().get(0).getPath())
                        .fit()
                        .into(propertyPicture);
            }


            descriptionPropertyTextView.setText(property.getDescription());
            pricePropertyTextView.setText(String.valueOf(property.getPrice().getValue()));
            String cityCountry = property.getCity() + ", " + property.getCountry();
            addressTextViewTextView.setText(cityCountry);
            bedroomCounterTextView.setText(String.valueOf(property.getBedroom_number()));
            creationDateTextView.setText(FormatUtils.FormatDate(property.getCreatedAt()));

            //statusTextView.setText(property.getStatus());
            propertyListCardView.setOnClickListener(v -> {
                // Invoke the callback when the card is clicked
                if (onCardListener != null) {
                    onCardListener.onPropertyCardHandle(property.get_id());
                }
            });


            callMeButton.setOnClickListener(v -> {
                String phoneNumber = property.getUser_id().getPhone();
                onCardListener.onPhoneCallHandle(phoneNumber);
            });
        }


    }

    public interface OnPropertyCardListener {
        void onPropertyCardHandle(String propertyId);

        void onPhoneCallHandle(String phoneNumber);
    }
}
