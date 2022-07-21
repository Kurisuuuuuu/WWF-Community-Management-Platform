package com.example.infs3605communitymanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.infs3605communitymanagement.DB.MatchmakingDatabase;
import com.example.infs3605communitymanagement.DB.ProjectDatabase;
import com.example.infs3605communitymanagement.DB.UserDatabase;

import java.util.ArrayList;
import java.util.concurrent.Executors;

public class ProjectDetailActivity extends AppCompatActivity {
    public static String projectID;
    private ProjectDatabase mDb;
    private String output;
    public Project project1;
    private MatchmakingDatabase mMatchmakingDb;
    SharedPreferences sharedPrefUser;
    public String user;
    public static String username;
    private UserDatabase mUserDb;
    public User currentUser;
    public String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        setTitle("Detail Activity");

        //Get Intent and get message
        Intent newIntent = getIntent();
        output = newIntent.getStringExtra(projectID);
        Log.d("Type", output);

        //Get Intent and get message
        Intent intent = getIntent();
        user= intent.getStringExtra(username);

        mDb = Room.databaseBuilder(getApplicationContext(), ProjectDatabase.class, "project")
                .build();
        mMatchmakingDb = Room.databaseBuilder(getApplicationContext(), MatchmakingDatabase.class, "matchmaking")
                .fallbackToDestructiveMigration()
                .build();
        mUserDb = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "user")
                .fallbackToDestructiveMigration()
                .build();

        if (newIntent != null){
            Log.d("Type", output);

            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    Project project = mDb.projectDao().getProjectByID(output);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            project1 = project;
                            Log.d("Type", output);
                            TextView projectTitle = findViewById(R.id.tvProjectDetailTitle);
                            projectTitle.setText(project1.getProjectTitle().toString());
                            TextView projectSummary = findViewById(R.id.tvProjectDetailSummary);
                            projectSummary.setText(project1.getProjectSummary());
                            TextView projectTheme = findViewById(R.id.tvProjectThemeText);
                            projectTheme.setText(project1.getTheme());
                            TextView areaOfExpertise = findViewById(R.id.tvAreaofExpertiseText);
                            areaOfExpertise.setText(project1.getSupportNeeded());
                            ImageView projectImage = findViewById(R.id.ivProjectImage);
                            Glide.with(projectImage)
                                    .load(project1.getImageUrl())
                                    .into(projectImage);
                        }
                    });
                }
            });
        }
        Button btnApply = findViewById(R.id.btnApply);
        btnApply.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                currentUser = mUserDb.userDao().getUserByUsername(user);
                Log.d("current user", String.valueOf(currentUser));
                userID = currentUser.getUserID();
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        Project project = mDb.projectDao().getProjectByID(output);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ArrayList<Matchmaking> matchmakingList = (ArrayList<Matchmaking>) mMatchmakingDb.MatchmakingDao().getMatchmakingByID(userID);
                                if (matchmakingList.contains(project1.getProjectID())){
                                    Matchmaking oldRecord = mMatchmakingDb.MatchmakingDao().getMatchmakingByIDs(userID,project1.getProjectID());
                                    Matchmaking newRecord = new Matchmaking(oldRecord.getMatchmakeID(), oldRecord.getUserID(), oldRecord.getProjectID(), "Applied");
                                    mMatchmakingDb.MatchmakingDao().updateMatchmaking(newRecord);
                                    Log.d("Status", "Applied");
                                }
                            }
                        });
                    }
                });
            }
        });
    }



}
