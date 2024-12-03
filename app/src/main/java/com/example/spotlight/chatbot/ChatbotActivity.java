package com.example.spotlight.chatbot;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotlight.R;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Request.ChatbotRequest;
import com.example.spotlight.network.DTO.FeedRecommendationDTO;
import com.example.spotlight.network.DTO.StudentRecommendationDTO;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatbotActivity extends AppCompatActivity {

    private final Gson gson = new Gson();
    private RecyclerView recyclerView;
    private ChatbotAdapter adapter;
    private List<ChatMessage> messageList = new ArrayList<>();
    private EditText userInput;
    private ImageButton sendButton;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        // UI 요소 초기화
        recyclerView = findViewById(R.id.recyclerView);
        userInput = findViewById(R.id.chatbot_user_input);
        sendButton = findViewById(R.id.chatbot_send_button);

        // RecyclerView와 Adapter 설정
        adapter = new ChatbotAdapter(messageList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Retrofit 초기화
        apiService = ApiClient.getClient().create(ApiService.class);

        // 초기 메시지 표시
        showInitialChatbotMessage();

        // 전송 버튼 클릭 리스너
        sendButton.setOnClickListener(v -> {
            String message = userInput.getText().toString().trim();
            if (!message.isEmpty()) {
                // 사용자 메시지 추가
                addUserMessage(message);
                userInput.setText("");

                // 챗봇 응답 요청
                sendMessageToChatbot(message);
            }
        });
    }

    private void showInitialChatbotMessage() {
        Handler handler = new Handler();

        // 첫 번째 메시지를 0초 후에 표시
        handler.postDelayed(() -> addChatbotMessage("안녕하세요! 무엇을 도와드릴까요?"), 700);

        // 두 번째 메시지를 1.5초 후에 표시
        handler.postDelayed(() -> addChatbotMessage("예시 질문) Python 프로젝트를 추천해줘."), 1500);
    }


    private void addUserMessage(String message) {
        messageList.add(new ChatMessage(ChatMessage.TYPE_USER, message));
        adapter.notifyItemInserted(messageList.size() - 1);
        recyclerView.scrollToPosition(messageList.size() - 1);
    }

    private void addChatbotMessage(String message) {
        messageList.add(new ChatMessage(ChatMessage.TYPE_BOT, message));
        adapter.notifyItemInserted(messageList.size() - 1);
        recyclerView.scrollToPosition(messageList.size() - 1);
    }

    /*
    private void addProjectMessage(FeedRecommendationDTO feed) {
        ChatMessage message = new ChatMessage(ChatMessage.TYPE_BOT_PROJECT, null);
        message.setProject(feed);
        messageList.add(message);
        adapter.notifyItemInserted(messageList.size() - 1);
        recyclerView.scrollToPosition(messageList.size() - 1);
    }

     */

    private void addStudentMessage(StudentRecommendationDTO student) {
        ChatMessage message = new ChatMessage(ChatMessage.TYPE_BOT_STUDENT, null);
        message.setStudent(student);
        messageList.add(message);
        adapter.notifyItemInserted(messageList.size() - 1);
        recyclerView.scrollToPosition(messageList.size() - 1);
    }


    private void sendMessageToChatbot(String userMessage) {
        List<ChatbotRequest.Message> messages = new ArrayList<>();
        messages.add(new ChatbotRequest.Message("user", userMessage));
        ChatbotRequest request = new ChatbotRequest("gpt-3.5-turbo", messages);

        apiService.askChatbot(request).enqueue(new Callback<List<Object>>() {
            @Override
            public void onResponse(Call<List<Object>> call, Response<List<Object>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Object> responseBody = response.body();
                    for (Object obj : responseBody) {
                        // JSON 데이터로 변환
                        String json = gson.toJson(obj);

                        // 데이터 타입 확인 및 처리
                        if (json.contains("studentName")) {
                            StudentRecommendationDTO student = gson.fromJson(json, StudentRecommendationDTO.class);
                            addStudentMessage(student);
                        } else {
                            addChatbotMessage("알 수 없는 데이터 형식입니다.");
                        }
                    }
                } else {
                    addChatbotMessage("응답 데이터를 불러오는 데 실패했습니다.");
                }
            }

            @Override
            public void onFailure(Call<List<Object>> call, Throwable t) {
                addChatbotMessage("서버와의 연결에 실패했습니다.");
            }
        });
    }

    public void onBackClicked(View view) {
        finish();
    }
}