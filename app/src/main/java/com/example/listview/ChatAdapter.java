package com.example.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private List<ChatMessage> messages;




    private Context context;

    public ChatAdapter(Context context) {
        messages = new ArrayList<>();
        this.context = context;

    }



    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            // inflate sent message layout
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_message, parent, false);
        } else {
            // inflate received message layout
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_message, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatMessage message = messages.get(position);
        holder.messageTextView.setText(message.getText());
        holder.timestampTextView.setText(message.getTimestamp().toString());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (messages.get(position).isSentByMe()) {
            return 0; // sent message
        } else {
            return 1; // received message
        }
    }

    public void addMessage(ChatMessage message) {
        messages.add(message);

    }

    public void clearMessages() {
        messages.clear();

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView messageTextView;
        TextView timestampTextView;

        ViewHolder(View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.chatmessage);
            timestampTextView = itemView.findViewById(R.id.time);
        }
        void bind(ChatMessage message) {
            messageTextView.setText(message.getText());
            timestampTextView.setText(message.getTimestamp());
        }
    }
}



