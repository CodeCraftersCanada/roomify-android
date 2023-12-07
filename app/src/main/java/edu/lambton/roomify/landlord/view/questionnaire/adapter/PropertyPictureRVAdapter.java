package edu.lambton.roomify.landlord.view.questionnaire.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.lambton.roomify.R;
import edu.lambton.roomify.landlord.model.Picture;

public class PropertyPictureRVAdapter extends RecyclerView.Adapter<PropertyPictureRVAdapter.PropertyPictureViewHolder> {

    private final List<Picture> pictures;

    public PropertyPictureRVAdapter(List<Picture> pictures) {
        this.pictures = pictures;
    }

    @NonNull
    @Override
    public PropertyPictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.thumbnail_picture, parent, false);

        return new PropertyPictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyPictureViewHolder holder, int position) {
        Picasso.get()
                .load(pictures.get(position).getPath())
                .resize(300, 300)
                .centerInside()
                .into(holder.thumbnailImageView);
    }

    @Override
    public int getItemCount() {
        return this.pictures.size();
    }

    static class PropertyPictureViewHolder extends RecyclerView.ViewHolder {
        //private final ImageView pictureImageView;
        //private final ImageButton overflowMenu;
        private final ImageView thumbnailImageView;

        public PropertyPictureViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnailImageView = itemView.findViewById(R.id.pictureImage);
            //pictureImageView = itemView.findViewById(R.id.pictureImage);
            //overflowMenu = itemView.findViewById(R.id.pictureOverflowMenu);
        }
    }

    public interface OnPictureNoteCallback {
        void onDeletePicture(View view, int position);
    }
}
