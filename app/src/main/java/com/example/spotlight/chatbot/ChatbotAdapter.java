package com.example.spotlight.chatbot;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.spotlight.R;
import com.example.spotlight.network.DTO.FeedRecommendationDTO;
import com.example.spotlight.network.DTO.StudentRecommendationDTO;
import com.example.spotlight.network.Util.TokenManager;
import com.example.spotlight.posting.ItemDetailMemberGeneralActivity;
import com.example.spotlight.posting.ItemDetailMemberRecruiterActivity;

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

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ChatMessage.TYPE_USER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_text_q, parent, false);
            return new UserMessageViewHolder(view);
        } else if (viewType == ChatMessage.TYPE_BOT_STUDENT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_student, parent, false);
            return new StudentMessageViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_text, parent, false);
            return new BotMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessage message = messageList.get(position);
        if (holder instanceof StudentMessageViewHolder) {
            ((StudentMessageViewHolder) holder).bind(message.getStudent());
        } else if (holder instanceof UserMessageViewHolder) {
            ((UserMessageViewHolder) holder).bind(message.getMessage());
        } else {
            ((BotMessageViewHolder) holder).bind(message.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
        // return Math.min(messageList.size(), 5);
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

    /*
    // 프로젝트 메시지 ViewHolder
    static class ProjectMessageViewHolder extends RecyclerView.ViewHolder {
        private TextView titleText;
        private ImageView thumbnailImage;

        public ProjectMessageViewHolder(View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.answer_text_project);
            thumbnailImage = itemView.findViewById(R.id.project_thumbnail);
        }

        public void bind(FeedRecommendationDTO project) {
            titleText.setText(project.getTitle());

            if (project.getThumbnailImage() != null) {
                Glide.with(thumbnailImage.getContext()).load(project.getThumbnailImage()).into(thumbnailImage);
            } else {
                thumbnailImage.setImageResource(R.drawable.team_image);
            }
        }
    }

     */

    // 학생 메시지 ViewHolder
    static class StudentMessageViewHolder extends RecyclerView.ViewHolder {
        private TextView nameText;
        private TextView categoryText;

        public StudentMessageViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.answer_text_student_name);
            categoryText = itemView.findViewById(R.id.answer_text_student_category);
        }

        public void bind(StudentRecommendationDTO student) {
            nameText.setText(student.getStudentName());
            categoryText.setText(student.getFeedCategory());

            // 말풍선 클릭 리스너
            itemView.setOnClickListener(v -> {
                // 사용자 역할 가져오기
                String userType = TokenManager.getRole();
                Intent intent;

                // 사용자 유형에 따라 적절한 액티비티 선택
                if (userType.equals("RECRUITER")) {
                    intent = new Intent(itemView.getContext(), ItemDetailMemberRecruiterActivity.class);
                } else {
                    intent = new Intent(itemView.getContext(), ItemDetailMemberGeneralActivity.class);
                }

                /*
                // Intent에 필요한 데이터 전달
                intent.putExtra("feedId", student.getFeedId()); // feedId 전달
                intent.putExtra("studentId", student.getStudentId()); // studentId 전달

                 */

                // 액티비티 시작
                itemView.getContext().startActivity(intent);
            });
        }
    }
}