package edu.lambton.roomify.student.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.lambton.roomify.R;
import edu.lambton.roomify.student.dto.StudentPropertyResponse;

public class ExplorePropertyRVAdapter extends RecyclerView.Adapter<ExplorePropertyRVAdapter.ExplorePropertyViewHolder> {

    private final List<StudentPropertyResponse.PropertyDTO> propertyList;

    public ExplorePropertyRVAdapter(List<StudentPropertyResponse.PropertyDTO> propertyList) {
        this.propertyList = propertyList;
    }

    @NonNull
    @Override
    public ExplorePropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_properety_preview, parent, false);

        return new ExplorePropertyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExplorePropertyViewHolder holder, int position) {
        StudentPropertyResponse.PropertyDTO property = propertyList.get(position);
        holder.bind(property);
    }

    @Override
    public int getItemCount() {
        return propertyList.size();
    }

    static class ExplorePropertyViewHolder extends RecyclerView.ViewHolder {

        private final TextView ratingTextView;

        private final TextView cityCountryTextView;
        private final ImageView propertyImagePreview;

        public ExplorePropertyViewHolder(@NonNull View itemView) {
            super(itemView);
            ratingTextView = itemView.findViewById(R.id.ratingTextView);
            propertyImagePreview = itemView.findViewById(R.id.propertyImage);
            cityCountryTextView = itemView.findViewById(R.id.cityCountryTextView);
        }

        public void bind(@NonNull StudentPropertyResponse.PropertyDTO property) {

            String cityCountry = property.getCity() + ", " + property.getCountry();

            cityCountryTextView.setText(cityCountry);
            ratingTextView.setText("3.5");
        }
    }
}
