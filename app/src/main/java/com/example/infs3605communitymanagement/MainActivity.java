package com.example.infs3605communitymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    //logout button
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnLogout: {
                launchLogin();
                return true;
            }
            case R.id.btnProfile:{
                launchProfile();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void launchLogin(){
        Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intentLogin);
    }
    private void launchProfile(){
        Intent intentProfile = new Intent(MainActivity.this, UserProfileActivity.class);
        startActivity(intentProfile);
    }
}