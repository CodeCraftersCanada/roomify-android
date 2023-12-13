package edu.lambton.roomify.landlord.view.questionnaire.adapter;

import android.graphics.Color;
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
    private final int[] selectedPosition = {RecyclerView.NO_POSITION};

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
        holder.bind(placeRowOptions.get(position), onSelectionHandlerListener, position, selectedPosition);
    }

    @Override
    public int getItemCount() {
        return placeRowOptions.size();
    }

    class DescribePlacesViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iconPlaceImageView;
        private final TextView describePlaceTextView;
        private final MaterialCardView placeOptionCardView;

        public DescribePlacesViewHolder(@NonNull View itemView) {
            super(itemView);

            iconPlaceImageView = itemView.findViewById(R.id.iconPlace);
            describePlaceTextView = itemView.findViewById(R.id.descriptionPlace);
            placeOptionCardView = itemView.findViewById(R.id.placeOptionCard);
        }

        public void bind(@NonNull PlaceRowOption placeRowOption, OnSelectionHandlerListener listener, int position, int[] selectedPosition) {
            iconPlaceImageView.setImageDrawable(placeRowOption.drawable());
            describePlaceTextView.setText(placeRowOption.text());

            // Update the UI based on the selection status
            placeOptionCardView.setCardBackgroundColor(position == selectedPosition[0] ? Color.LTGRAY : Color.WHITE);

            placeOptionCardView.setOnClickListener(v -> {
                if (selectedPosition[0] != position) {
                    // Update the UI based on the updated selection status
                    notifyItemChanged(selectedPosition[0]);
                    selectedPosition[0] = position;
                    notifyItemChanged(selectedPosition[0]);

                    listener.onSelectOption(position, placeRowOption.isSelected());
                }
            });
        }
    }

    public interface OnSelectionHandlerListener {
        void onSelectOption(int position, boolean isSelected);
    }
}