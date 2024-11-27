package com.example.spotlight.chatbot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotlight.R;

import java.util.List;

public class ChatbotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ChatMessage> messageList;

    public ChatbotAdapter(List<ChatMessage> messageList) {
        this.messageList = messageList;
    }

    @Override
    public int getItemViewType(int position) {
        return messageList.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ChatMessage.TYPE_USER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_text_q, parent, false);
            return new UserMessageViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_text, parent, false);
            return new BotMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessage message = messageList.get(position);
        if (holder instanceof UserMessageViewHolder) {
            ((UserMessageViewHolder) holder).bind(message.getMessage());
        } else if (holder instanceof BotMessageViewHolder) {
            ((BotMessageViewHolder) holder).bind(message.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    // ViewHolder: 사용자 메시지
    static class UserMessageViewHolder extends RecyclerView.ViewHolder {
        private TextView messageText;

        public UserMessageViewHolder(View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.question_text);
        }

        public void bind(String message) {
            messageText.setText(message);
        }
    }

    // ViewHolder: 챗봇 메시지
    static class BotMessageViewHolder extends RecyclerView.ViewHolder {
        private TextView messageText;

        public BotMessageViewHolder(View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.answer_text);
        }

        public void bind(String message) {
            messageText.setText(message);
        }
    }
}