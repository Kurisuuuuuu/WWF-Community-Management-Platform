package com.example.infs3605communitymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("WWF");

        //All Challenges Button
        ImageButton btnAllChallenges = findViewById(R.id.iBtnAllChallenges);
        btnAllChallenges.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View webView) {
                Intent intent = new Intent(MainActivity.this,ProjectListActivity.class);
                startActivity(intent);
            }
        });
    }
}