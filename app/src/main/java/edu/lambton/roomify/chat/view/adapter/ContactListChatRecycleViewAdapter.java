package edu.lambton.roomify.chat.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import edu.lambton.roomify.R;
import edu.lambton.roomify.chat.dto.Contact;

public class ContactListChatRecycleViewAdapter extends RecyclerView.Adapter<ContactListChatRecycleViewAdapter.ContactListViewHolder> {

    private List<Contact> contactList;
    private OnContactClickListener onContactClickListener;


    public ContactListChatRecycleViewAdapter(List<Contact> contactList, OnContactClickListener onContactClickListener) {
        this.contactList = contactList;
        this.onContactClickListener = onContactClickListener;
    }

    @NonNull
    @Override
    public ContactListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_row, parent, false);
        return new ContactListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactListViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.bind(contact);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    class ContactListViewHolder extends RecyclerView.ViewHolder {

        private final CircleImageView contactProfilePicture;
        private final TextView contactNameTextView;

        public ContactListViewHolder(@NonNull View itemView) {
            super(itemView);
            contactProfilePicture = itemView.findViewById(R.id.contactProfilePicture);
            contactNameTextView = itemView.findViewById(R.id.contactNameTextView);
        }

        // Bind data to the views
        public void bind(@NonNull Contact contact) {

            Picasso.get()
                    .load(contact.getProfilePictureUrl())
                    .fit()
                    .into(contactProfilePicture);

            contactNameTextView.setText(contact.getDisplayName());

            // Set the click listener on the whole item
            itemView.setOnClickListener(v -> {

                onContactClickListener.onContactClick(contact);
            });
        }
    }

    // Interface to define the contact click listener
    public interface OnContactClickListener {
        void onContactClick(Contact contact);
    }
}
