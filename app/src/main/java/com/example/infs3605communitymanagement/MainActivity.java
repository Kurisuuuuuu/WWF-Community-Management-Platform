package com.example.infs3605communitymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static String username;
    public String username1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("WWF");


        //Get Intent and get message
        Intent newIntent = getIntent();
        username1= newIntent.getStringExtra(username);
        Log.d("username1", username1);

        //All Challenges Button
        ImageButton btnAllChallenges = findViewById(R.id.iBtnAllChallenges);
        btnAllChallenges.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View webView) {
                Intent intent = new Intent(MainActivity.this,ProjectListActivity.class);
                startActivity(intent);
            }
        });
        ImageButton btnForYou = findViewById(R.id.iBtnForYou);
        btnForYou.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View webView) {
                Intent intent = new Intent(MainActivity.this,MatchmakingActivity.class);
                intent.putExtra(MatchmakingActivity.username, username1);
                startActivity(intent);
            }
        });
    }
}