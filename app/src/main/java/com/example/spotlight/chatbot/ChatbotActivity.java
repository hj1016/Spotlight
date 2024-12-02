package com.example.spotlight.chatbot;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatbotActivity extends AppCompatActivity {

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
        addChatbotMessage("안녕하세요! 무엇을 도와드릴까요?");
        addChatbotMessage("예시 질문:\n1. Python 프로젝트를 추천해줘.\n2. 컴퓨터공학 분야의 학생을 추천해줘.");
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

    private void sendMessageToChatbot(String userMessage) {
        // ChatbotRequest 생성
        List<ChatbotRequest.Message> messages = new ArrayList<>();
        messages.add(new ChatbotRequest.Message("user", userMessage));
        ChatbotRequest request = new ChatbotRequest("gpt-3.5-turbo", messages);

        // API 호출
        Call<List<Object>> call = apiService.askChatbot(request);
        call.enqueue(new Callback<List<Object>>() {
            @Override
            public void onResponse(Call<List<Object>> call, Response<List<Object>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    addChatbotMessage(response.body().toString());
                } else {
                    addChatbotMessage("챗봇 응답을 불러오는 데 실패했습니다.");
                }
            }

            @Override
            public void onFailure(Call<List<Object>> call, Throwable t) {
                Log.e("ChatbotActivity", "API 호출 실패: " + t.getMessage());
                addChatbotMessage("서버 연결에 실패했습니다.");
            }
        });
    }

    public void onBackClicked(View view) {
        finish();
    }
}