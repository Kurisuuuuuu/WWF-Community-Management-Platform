package com.example.infs3605communitymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ExperienceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
        setTitle("Enter Experience");

        Button enterButton = findViewById(R.id.enterButton);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchMainActivity();
            }
        });
    }

    private void launchMainActivity(){

        Intent intent = new Intent(ExperienceActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
