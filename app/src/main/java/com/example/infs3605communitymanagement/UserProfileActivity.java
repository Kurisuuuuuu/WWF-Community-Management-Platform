package com.example.infs3605communitymanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.infs3605communitymanagement.DB.UserDatabase;

import java.util.ArrayList;
import java.util.concurrent.Executors;

public class UserProfileActivity extends AppCompatActivity {
    private UserDatabase mDb;
    public User oldUser;
    public User newUser;
    SharedPreferences sharedPrefUser;
    public String user;
    EditText username;
    EditText fullname;
    EditText bio;
    Spinner themeSpinner;
    Spinner expertiseSpinner;
    EditText password;
    SharedPreferences sharedPref;
    public String savedLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        setTitle("User Profile");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.dark_green)));
        getWindow().setStatusBarColor(ContextCompat.getColor(UserProfileActivity.this,R.color.black));

        // Back button
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //implementation of RoomDatabase
        mDb = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "user")
                .fallbackToDestructiveMigration()
                .build();

        //current user info
        sharedPrefUser = getSharedPreferences(user, MODE_PRIVATE);
        user=sharedPrefUser.getString("current user", "");

        //setup screen items
        username = findViewById(R.id.etUserName);
        fullname = findViewById(R.id.etFullName);
        bio = findViewById(R.id.etBio);
        themeSpinner = findViewById(R.id.themeSpinner2);
        expertiseSpinner = findViewById(R.id.expertiseSpinner2);
        password = findViewById(R.id.etPassword);

        //spinner stuff
        ArrayAdapter<CharSequence> themeAdapter = ArrayAdapter.createFromResource(this,
                R.array.theme_array, android.R.layout.simple_spinner_item);
        themeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        themeSpinner.setAdapter(themeAdapter);
        ArrayAdapter<CharSequence> expertiseAdapter = ArrayAdapter.createFromResource(this,
                R.array.expertise_array, android.R.layout.simple_spinner_item);
        expertiseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        expertiseSpinner.setAdapter(expertiseAdapter);

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
               oldUser = mDb.userDao().getUserByUsername(user);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        username.setText(oldUser.getUsername());
                        fullname.setText(oldUser.getFullName());
                        bio.setText(oldUser.getBio());
                        themeSpinner.setSelection(themeAdapter.getPosition(oldUser.getImpactTheme()));
                        expertiseSpinner.setSelection(expertiseAdapter.getPosition(oldUser.getSuperPower()));
                        password.setText(oldUser.getPassword());
                    }
                });
            }
        });
        //Navigation bar
        ImageButton forYouPageButton = findViewById(R.id.foryoupagebutton2);
        forYouPageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View webView) {
                Intent intent = new Intent(UserProfileActivity.this,MatchmakingActivity.class);
                startActivity(intent);
            }
        });
        ImageButton homePageButton = findViewById(R.id.homeButton);
        homePageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View webView) {
                Intent intent = new Intent(UserProfileActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    //save button
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_profile_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnSave: {
                saveUser();
                return true;
            }
                default:
                    return super.onOptionsItemSelected(item);
        }
    }
    private void saveUser(){
        //implementation of RoomDatabase
        mDb = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "user")
                .fallbackToDestructiveMigration()
                .build();
        if (username.getText().toString().length()>10){
            Toast.makeText(UserProfileActivity.this, "Username must not be over 10 digits", Toast.LENGTH_SHORT).show();
            return;
        }else {
            newUser = new User(oldUser.getUserID(),username.getText().toString(),fullname.getText().toString(), oldUser.getUserType(), bio.getText().toString(), oldUser.getPreferredSDGs(),themeSpinner.getSelectedItem().toString(),oldUser.getLastLogin(), oldUser.getAvailability(), oldUser.getProjectsCanBeAssigned(),oldUser.getCommentsNumber(), oldUser.getChallengesNumber(), password.getText().toString(),expertiseSpinner.getSelectedItem().toString(), oldUser.getIndustry(), oldUser.getExperience());
            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    mDb.userDao().updateUsers(newUser);
                }
            });
            Toast.makeText(UserProfileActivity.this, "User details updated", Toast.LENGTH_SHORT).show();
            sharedPref = getSharedPreferences(savedLogin, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.remove(oldUser.getUsername());
            editor.putString(username.getText().toString(),password.getText().toString());
            editor.commit();
            Intent intent = new Intent(UserProfileActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }
    //Back button
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}