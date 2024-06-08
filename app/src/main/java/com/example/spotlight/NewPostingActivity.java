package com.example.spotlight;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class NewPostingActivity extends AppCompatActivity {
    private Spinner bigCategorySpinner, smallCategorySpinner;
    private ArrayAdapter<CharSequence> smallCategoryAdapter;
    private ImageView dynamicImage;
    private EditText dynamicText;

    private RecyclerView recyclerView;
    private InviteMemberAdapter adapter;
    private List<Member> memberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_posting);

        bigCategorySpinner = findViewById(R.id.big_category_spinner);
        smallCategorySpinner = findViewById(R.id.small_category_spinner);
        dynamicImage = findViewById(R.id.new_posting_description_box);
        dynamicText = findViewById(R.id.new_posting_description_text);
        recyclerView = findViewById(R.id.recyclerView_invite_member);

        setupSpinners();
        setupDynamicImage();
        setupRecyclerView();

        dynamicText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) dynamicImage.getLayoutParams();
                // Set the height of the ImageView based on text length
                params.height = 200 + charSequence.length() * 5; // Basic calculation, adjust as needed
                dynamicImage.setLayoutParams(params);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private void setupSpinners() {
        ArrayAdapter<CharSequence> bigCategoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.big_categories, android.R.layout.simple_spinner_item);
        bigCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bigCategorySpinner.setAdapter(bigCategoryAdapter);
        bigCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSmallCategories(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        updateSmallCategories(0); // Initialize with the first big category
    }

    private void setupDynamicImage() {
        // You can set up more configurations here if needed
    }

    private void setupRecyclerView() {
        memberList = new ArrayList<>();
        memberList.add(new Member(R.drawable.member_image, "김이름"));
        memberList.add(new Member(R.drawable.member_image, "이이름"));
        memberList.add(new Member(R.drawable.member_image, "박이름"));

        adapter = new InviteMemberAdapter(this, memberList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }

    private void updateSmallCategories(int position) {
        int arrayId = R.array.small_categories_humanities; // Default value

        switch (position) {
            case 0:
                arrayId = R.array.small_categories_humanities;
                break;
            case 1:
                arrayId = R.array.small_categories_social;
                break;
            case 2:
                arrayId = R.array.small_categories_education;
                break;
            case 3:
                arrayId = R.array.small_categories_nature;
                break;
            case 4:
                arrayId = R.array.small_categories_engineering;
                break;
            case 5:
                arrayId = R.array.small_categories_medical;
                break;
            case 6:
                arrayId = R.array.small_categories_entertainment;
                break;
        }

        smallCategoryAdapter = ArrayAdapter.createFromResource(this,
                arrayId, android.R.layout.simple_spinner_item);
        smallCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        smallCategorySpinner.setAdapter(smallCategoryAdapter);
    }

    public void onBackClicked(View view) {
        finish();
    }

    public void onMemberPlusClicked(View view) {
        Intent intent = new Intent(this, NewPostingMemberActivity.class);
        startActivity(intent);
    }

    public void onExhibitionPlusClicked(View view) {
        Intent intent = new Intent(this, NewPostingExhibitionActivity.class);
        startActivity(intent);
    }


}
