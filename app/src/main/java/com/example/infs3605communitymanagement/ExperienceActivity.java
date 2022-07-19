package com.example.infs3605communitymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.infs3605communitymanagement.DB.UserDao;
import com.example.infs3605communitymanagement.DB.UserDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class ExperienceActivity extends AppCompatActivity {

    private UserDatabase mDb;

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

        Intent intent = getIntent();
        String username = intent.getStringExtra("INTENT_USERNAME");
        String password = intent.getStringExtra("INTENT_PASSWORD");
        String userType = intent.getStringExtra("INTENT_USERTYPE");

        Spinner industrySpinner = findViewById(R.id.industrySpinner);
        Spinner expertiseSpinner = findViewById(R.id.expertiseSpinner);
        Spinner experienceSpinner = findViewById(R.id.experienceSpinner);

        String industry = industrySpinner.getSelectedItem().toString();
        String expertise = expertiseSpinner.getSelectedItem().toString();
        String experience = experienceSpinner.getSelectedItem().toString();

        mDb = Room.databaseBuilder(this, UserDatabase.class, "users.db").createFromAsset("users.db").build();

        User newUser = new User("1", "null", userType, "null", "null", "null", "null", "null", 2,
                0, 0, username, password, expertise, industry, experience);

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {

                mDb.userDao().insertUsers(newUser);
            }
        });

        Intent mainActivityIntent = new Intent(ExperienceActivity.this, MainActivity.class);
        startActivity(mainActivityIntent);
    }
}
