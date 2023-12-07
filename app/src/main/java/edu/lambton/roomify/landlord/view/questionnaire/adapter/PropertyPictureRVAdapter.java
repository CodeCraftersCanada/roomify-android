package edu.lambton.roomify.landlord.view.questionnaire.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PropertyPictureRVAdapter extends RecyclerView.Adapter<PropertyPictureRVAdapter.PropertyPictureViewHolder> {

    @NonNull
    @Override
    public PropertyPictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        //View view = layoutInflater.inflate(R.layout.row_picture, parent, false);

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyPictureViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class PropertyPictureViewHolder extends RecyclerView.ViewHolder {
        //private final ImageView pictureImageView;
        //private final ImageButton overflowMenu;

        public PropertyPictureViewHolder(@NonNull View itemView) {
            super(itemView);
            //pictureImageView = itemView.findViewById(R.id.pictureImage);
            //overflowMenu = itemView.findViewById(R.id.pictureOverflowMenu);
        }
    }

    public interface OnPictureNoteCallback {
        void onDeletePicture(View view, int position);
    }
}
