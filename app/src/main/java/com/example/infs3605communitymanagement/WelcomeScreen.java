package com.example.infs3605communitymanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        getWindow().setStatusBarColor(ContextCompat.getColor(WelcomeScreen.this,R.color.black));
        setTitle("WWF");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.dark_green)));

        Button startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(WelcomeScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}