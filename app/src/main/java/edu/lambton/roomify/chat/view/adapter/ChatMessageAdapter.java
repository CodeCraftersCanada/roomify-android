package edu.lambton.roomify.chat.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import edu.lambton.roomify.R;
import edu.lambton.roomify.chat.dto.Message;

public class ChatMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_CURRENT_USER = 1;
    private static final int VIEW_TYPE_OTHER_USER = 2;
    private List<Message> messages;
    private String currentUserId;

    public ChatMessageAdapter(List<Message> messages, String currentUserId) {
        this.messages = messages;
        this.currentUserId = currentUserId;
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);

        if (message.getSenderId().equals(currentUserId)) {
            return VIEW_TYPE_CURRENT_USER;
        } else {
            return VIEW_TYPE_OTHER_USER;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == VIEW_TYPE_CURRENT_USER) {
            View view = inflater.inflate(R.layout.message_text, parent, false);
            return new CurrentUserViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.message_text_other_user, parent, false);
            return new OtherUserViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);

        if (holder instanceof CurrentUserViewHolder) {
            bindCurrentUserViewHolder((CurrentUserViewHolder) holder, message);
        } else if (holder instanceof OtherUserViewHolder) {
            bindOtherUserViewHolder((OtherUserViewHolder) holder, message);
        }
    }

    private void bindCurrentUserViewHolder(@NonNull CurrentUserViewHolder holder, @NonNull Message message) {
        holder.messageTextView.setText(message.getText());

        if (!Objects.equals(message.getImagePathRecipientId(), "")) {
            Picasso.get()
                    .load(message.getImagePathSenderId())
                    .fit()
                    .into(holder.circleImageView);
        }

    }

    private void bindOtherUserViewHolder(@NonNull OtherUserViewHolder holder, @NonNull Message message) {
        holder.messageTextView.setText(message.getText());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    static class CurrentUserViewHolder extends RecyclerView.ViewHolder {
        private final TextView messageTextView;
        private final CircleImageView circleImageView;

        public CurrentUserViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
            circleImageView = itemView.findViewById(R.id.profileIconChat);
        }
    }


    static class OtherUserViewHolder extends RecyclerView.ViewHolder {
        private final TextView messageTextView;


        public OtherUserViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.messageTextView);

        }
    }


}
