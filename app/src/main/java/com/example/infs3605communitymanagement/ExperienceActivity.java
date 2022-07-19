package com.example.infs3605communitymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.infs3605communitymanagement.DB.UserDatabase;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.Executors;

public class ExperienceActivity extends AppCompatActivity {

    private UserDatabase mDb;
    private User newUser;

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

        Spinner themeSpinner = findViewById(R.id.themeSpinner);
        Spinner expertiseSpinner = findViewById(R.id.expertiseSpinner);
        Spinner experienceSpinner = findViewById(R.id.experienceSpinner);

        String theme = themeSpinner.getSelectedItem().toString();
        String expertise = expertiseSpinner.getSelectedItem().toString();
        String experience = experienceSpinner.getSelectedItem().toString();

        mDb = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "user")
                .fallbackToDestructiveMigration()
                .build();

        newUser = new User(UUID.randomUUID().toString(),username, "test", userType, "null", "null", theme, "null", "null", 2,
                0, 0, password, expertise, "null", experience);
        ArrayList<User> users = new ArrayList<>();
        users.add(newUser);
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                mDb.userDao().insertUsers(users.toArray(new User[0]));
                Log.d("register",username);
            }
        });

        Intent mainActivityIntent = new Intent(ExperienceActivity.this, MainActivity.class);
        mainActivityIntent.putExtra(MainActivity.username, username);
        startActivity(mainActivityIntent);
    }
}
