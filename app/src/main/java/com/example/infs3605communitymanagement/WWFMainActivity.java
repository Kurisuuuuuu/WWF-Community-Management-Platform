package com.example.infs3605communitymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class WWFMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("WWF");

        //All Buttons
        ImageButton btnUserList = findViewById(R.id.iBtnAllChallenges);
        btnUserList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View webView) {
                Intent intent = new Intent(WWFMainActivity.this,UserListActivity.class);
                startActivity(intent);
            }
        });
        ImageButton btnMatchmakingList = findViewById(R.id.iBtnForYou);
        btnMatchmakingList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View webView) {
                Intent intent = new Intent(WWFMainActivity.this,MatchmakingListActivity.class);
                startActivity(intent);
            }
        });
    }
}