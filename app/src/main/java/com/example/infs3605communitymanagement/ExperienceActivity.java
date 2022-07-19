package com.example.infs3605communitymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ExperienceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
        setTitle("Enter Experience");

        Intent intent = getIntent();
        String username = intent.getStringExtra("INTENT_USERNAME");
        String password = intent.getStringExtra("INTENT_PASSWORD");
        String usertype = intent.getStringExtra("INTENT_USERTYPE");

        Button enterButton = findViewById(R.id.enterButton);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchMainActivity();
            }
        });
    }

    private void launchMainActivity(){

        Spinner industrySpinner = findViewById(R.id.industrySpinner);
        Spinner expertiseSpinner = findViewById(R.id.expertiseSpinner);
        Spinner experienceSpinner = findViewById(R.id.experienceSpinner);

        String industry = industrySpinner.getSelectedItem().toString();
        String expertise = expertiseSpinner.getSelectedItem().toString();
        String experience = experienceSpinner.getSelectedItem().toString();



        Intent intent = new Intent(ExperienceActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
