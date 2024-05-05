package com.example.spotlight;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    //private Button loginButton;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // 뷰 초기화
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        //loginButton = findViewById(R.id.loginButton); 로그인 버튼 따로 없이 ID/비번 맞으면 바로 넘어가게 하고픔
        signupButton = findViewById(R.id.signupButton);

        // 로그인 버튼 리스너
        //loginButton.setOnClickListener(new View.OnClickListener() {
           // @Override
            //public void onClick(View v) {
                // 로그인 로직 실행
               // performLogin(usernameEditText.getText().toString(), passwordEditText.getText().toString());
           // }
        //});

        // 회원가입 버튼 리스너
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 회원가입 로직 실행
                performSignUp();
            }
        });
    }
    private void performLogin(String username, String password) {
        // TODO: 실제 로그인 로직 구현
        Toast.makeText(this, "Login Attempt with Username: " + username, Toast.LENGTH_SHORT).show();
    }

    private void performSignUp() {
        // TODO: 실제 회원가입 로직 구현
        Toast.makeText(this, "Sign Up Button Clicked", Toast.LENGTH_SHORT).show();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Spotlight");

        // 메뉴 아이콘 추가
        toolbar.setNavigationIcon(R.drawable.ic_menu); // 메뉴 아이콘 리소스
        toolbar.setNavigationOnClickListener(view -> {
            // 메뉴 버튼 클릭 이벤트 처리
        });
    }

        private DrawerLayout drawerLayout;
        private ActionBarDrawerToggle toggle;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            drawerLayout = findViewById(R.id.drawer_layout);
            NavigationView navigationView = findViewById(R.id.nav_view);

            toggle = new ActionBarDrawerToggle(
                    this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if (toggle.onOptionsItemSelected(item)) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

}
