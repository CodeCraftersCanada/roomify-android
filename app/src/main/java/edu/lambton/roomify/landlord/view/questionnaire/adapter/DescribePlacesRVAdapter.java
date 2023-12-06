package edu.lambton.roomify.landlord.view.questionnaire.adapter;

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
import edu.lambton.roomify.landlord.model.PlaceRowOption;

public class DescribePlacesRVAdapter extends RecyclerView.Adapter<DescribePlacesRVAdapter.DescribePlacesViewHolder> {

    private final List<PlaceRowOption> placeRowOptions;
    private final OnSelectionHandlerListener onSelectionHandlerListener;

    public DescribePlacesRVAdapter(List<PlaceRowOption> placeRowOptions, OnSelectionHandlerListener onSelectionHandlerListener) {
        this.placeRowOptions = placeRowOptions;
        this.onSelectionHandlerListener = onSelectionHandlerListener;
    }

    @NonNull
    @Override
    public DescribePlacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_place_action, parent, false);
        return new DescribePlacesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DescribePlacesViewHolder holder, int position) {
        holder.bind(placeRowOptions.get(position), onSelectionHandlerListener, position);
    }

    @Override
    public int getItemCount() {
        return placeRowOptions.size();
    }

    static class DescribePlacesViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iconPlaceImageView;
        private final TextView describePlaceTextView;
        private final MaterialCardView placeOptionCardView;

        public DescribePlacesViewHolder(@NonNull View itemView) {
            super(itemView);

            iconPlaceImageView = itemView.findViewById(R.id.iconPlace);
            describePlaceTextView = itemView.findViewById(R.id.descriptionPlace);
            placeOptionCardView = itemView.findViewById(R.id.placeOptionCard);
        }

        public void bind(@NonNull PlaceRowOption placeRowOption, OnSelectionHandlerListener listener, int position) {
            iconPlaceImageView.setImageDrawable(placeRowOption.drawable());
            describePlaceTextView.setText(placeRowOption.text());

            placeOptionCardView.setOnClickListener(v -> listener.onSelectOption(position));
        }
    }

    public interface OnSelectionHandlerListener {
        void onSelectOption(int position);
    }
}
