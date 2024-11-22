package com.example.spotlight.main;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

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

    private LinearLayout messageContainer;
    private EditText userInput;
    private ImageButton sendButton;
    private ScrollView scrollView;
    private android.graphics.Typeface customFont;

    private ApiService apiService; // Retrofit 인터페이스

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        // UI 요소 초기화
        messageContainer = findViewById(R.id.chatbot_message_container);
        userInput = findViewById(R.id.chatbot_user_input);
        sendButton = findViewById(R.id.chatbot_send_button);
        scrollView = findViewById(R.id.chatbot_scroll_view);

        // res/font에서 폰트 로드
        customFont = ResourcesCompat.getFont(this, R.font.pretendard_medium);

        // Retrofit 초기화
        apiService = ApiClient.getClientWithToken().create(ApiService.class);

        // 초기 메시지 추가
        showInitialChatbotMessage();

        // 전송 버튼 클릭 리스너
        sendButton.setOnClickListener(v -> {
            String message = userInput.getText().toString().trim();
            if (!message.isEmpty()) {
                // 사용자 메시지 추가
                addUserMessage(message);

                // 입력 필드 초기화
                userInput.setText("");

                // 챗봇 응답 요청
                sendMessageToChatbot(message);
            }
        });
    }

    // 초기 챗봇 메시지 표시
    private void showInitialChatbotMessage() {
        Handler handler = new Handler();

        // 첫 번째 메시지
        handler.postDelayed(() -> {
            String greetingMessage = "안녕하세요! 무엇을 도와드릴까요?";
            addChatbotMessage(greetingMessage);
        }, 1000);

        // 두 번째 메시지
        handler.postDelayed(() -> {
            String exampleQuestions = "예시 질문:\n1. Python 프로젝트를 추천해줘.\n2. 컴퓨터공학 분야의 학생을 추천해줘.";
            addChatbotMessage(exampleQuestions);
        }, 1500);
    }

    private void addUserMessage(String message) {
        TextView textView = createMessageTextView(message, R.drawable.speech_bubble_question, false);
        messageContainer.addView(textView);
        scrollToBottom();
    }

    private void addChatbotMessage(String message) {
        TextView textView = createMessageTextView(message, R.drawable.speech_bubble_answer, true);
        messageContainer.addView(textView);
        scrollToBottom();
    }

    private TextView createMessageTextView(String message, int backgroundResId, boolean isChatbot) {
        TextView textView = new TextView(this);
        textView.setText(message);
        textView.setBackgroundResource(backgroundResId);
        textView.setTextSize(16);
        textView.setPadding(32, 32, 32, 32);
        textView.setTypeface(customFont); // 커스텀 폰트 적용
        textView.setTextColor(getResources().getColor(android.R.color.black));

        // 줄간격 조정
        textView.setLineSpacing(10f, 1.3f);

        textView.setMaxWidth((int) (getResources().getDisplayMetrics().widthPixels * 0.8));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(20, 20, 20, 20);

        if (isChatbot) {
            params.gravity = android.view.Gravity.START;
        } else {
            params.gravity = android.view.Gravity.END;
        }

        textView.setLayoutParams(params);
        return textView;
    }

    private void scrollToBottom() {
        scrollView.post(() -> scrollView.fullScroll(ScrollView.FOCUS_DOWN));
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
                    // 응답 처리 (일단은 첫 번째 응답 메시지 표시)
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
}