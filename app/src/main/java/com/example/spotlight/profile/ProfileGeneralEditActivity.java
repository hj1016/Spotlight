package com.example.spotlight.profile;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.spotlight.R;
import com.example.spotlight.network.API.ApiClient;
import com.example.spotlight.network.API.ApiService;
import com.example.spotlight.network.Response.UserProfileResponse;
import com.example.spotlight.network.Util.TokenManager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileGeneralEditActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int STORAGE_PERMISSION_CODE = 100;
    private EditText editTextUsername;
    private TextView textViewId;
    private ImageView imageViewProfile;
    private String username;
    private String id;
    private String profileImg;
    private Uri imageUri;
    private ApiService apiService;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_profile_general_edit);
        editTextUsername = findViewById(R.id.profile_general_edit_user_name);
        textViewId = findViewById(R.id.profile_general_edit_ID_text);
        imageViewProfile = findViewById(R.id.profile_general_edit_user_image);

        id = TokenManager.getId();
        profileImg = TokenManager.getProfileImage();
        username = TokenManager.getUsername();

        editTextUsername.setText(username);
        textViewId.setText(id);
        if(profileImg != null && !profileImg.isEmpty()) {
            Glide.with(this)
                    .load(profileImg)
                    .circleCrop()
                    .into(imageViewProfile);
        }
        apiService = ApiClient.getClientWithToken().create(ApiService.class);

        checkStoragePermission();
    }

    private void checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_MEDIA_IMAGES}, STORAGE_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "권한이 허용되었습니다.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "권한이 거절되었습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onBackClicked(View view) {
        Intent intent = new Intent(this, ProfileGeneralActivity.class);
        startActivity(intent);
    }
    public void onGeneralEditCompleteClicked(View view) {
        Log.d("copleteCliked", "help");
        String username = editTextUsername.getText().toString();
        uploadImageAndData(username, imageUri);
    }

    private void uploadImageAndData(String username, Uri imageUri) {
        Log.d("uploadImageAndData", username);
        File file = new File(getPathFromUri(imageUri));
        Log.d("uploadImageAndData", file.getName());
        RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(imageUri)), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("profileImage", file.getName(), requestFile);
        Map<String, RequestBody> userProfileUpdateRequest = new HashMap<>();
        userProfileUpdateRequest.put("username", RequestBody.create(MediaType.parse("text/plain"), username));
        Log.d("uploadImageAndData", imageUri.getPath());
        Call<UserProfileResponse> call = apiService.updateProfile(userProfileUpdateRequest, body);
        call.enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {

                if (response.isSuccessful()) {
                    UserProfileResponse userProfileResponse = response.body();
                    String username = userProfileResponse.getUsername();
                    String profileImg = userProfileResponse.getProfileImage();
                    TokenManager.setUsername(username);
                    TokenManager.setProfileImage(profileImg);
                    Toast.makeText(ProfileGeneralEditActivity.this, "프로필이 성공적으로 업데이트되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProfileGeneralEditActivity.this, ProfileRecruiterActivity.class);
                    startActivity(intent);
                    finish(); // 완료 후 액티비티 종료 또는 다른 화면으로 전환
                } else {
                    Log.d("onResponse", "Response Code: " + response.code());
                    Log.d("onResponse", "Response Message: " + response.message());
                    if (response.errorBody() != null) {
                        try {
                            Log.d("onResponse", "Error Body: " + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Toast.makeText(ProfileGeneralEditActivity.this, "프로필 업데이트에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserProfileResponse> call, Throwable t) {
                Toast.makeText(ProfileGeneralEditActivity.this, "서버 요청 실패: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onImageButtonClicked(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED) {
            openFileChooser();
        } else {
            Toast.makeText(this, "스토리지 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
            checkStoragePermission();
        }
    }

    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            Glide.with(this)
                    .load(imageUri)
                    .circleCrop()
                    .into(imageViewProfile);
        }
    }

    private String getPathFromUri(Uri uri) {
        String[] projection = { android.provider.MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndexOrThrow(android.provider.MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(columnIndex);
            cursor.close();
            return path;
        } else {
            return uri.getPath();
        }
    }

}
