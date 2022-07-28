package com.example.infs3605communitymanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.infs3605communitymanagement.DB.MatchmakingDatabase;
import com.example.infs3605communitymanagement.DB.ProjectDatabase;
import com.example.infs3605communitymanagement.DB.UserDatabase;

import java.util.ArrayList;
import java.util.UUID;
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
    public Matchmaking checkCurrentMatchmaking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        setTitle("Project Details");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.dark_green)));
        getWindow().setStatusBarColor(ContextCompat.getColor(ProjectDetailActivity.this,R.color.black));

        //Get Intent and get message
        Intent newIntent = getIntent();
        output = newIntent.getStringExtra(projectID);
        Log.d("Type", output);

        // Back button
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sharedPrefUser = getSharedPreferences(user, MODE_PRIVATE);
        user=sharedPrefUser.getString("current user", "");

        mDb = Room.databaseBuilder(getApplicationContext(), ProjectDatabase.class, "project")
                .build();
        mMatchmakingDb = Room.databaseBuilder(getApplicationContext(), MatchmakingDatabase.class, "matchmaking")
                .fallbackToDestructiveMigration()
                .build();
        mUserDb = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "user")
                .fallbackToDestructiveMigration()
                .build();
        Button btnApply = findViewById(R.id.btnApply);

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
                            TextView projectTheme = findViewById(R.id.tvProjectDetailTheme);
                            projectTheme.setText("Project Theme: "+project1.getTheme());
                            TextView areaOfExpertise = findViewById(R.id.tvAreaofExpertise);
                            areaOfExpertise.setText("Area of Expertise Required: "+project1.getSupportNeeded());
                            TextView sdg = findViewById(R.id.tvSDG);
                            sdg.setText("SDGs: "+project1.getSdg());
                            ImageView projectImage = findViewById(R.id.ivProjectImage);
                            Glide.with(projectImage)
                                    .load(project1.getImageUrl())
                                    .into(projectImage);
                        }
                    });
                }
            });
        }
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                Project project = mDb.projectDao().getProjectByID(output);
                project1 = project;
                //Apply status
                currentUser = mUserDb.userDao().getUserByUsername(user);
                Log.d("current user", String.valueOf(currentUser));
                userID = currentUser.getUserID();
                ArrayList<Matchmaking> matchmakingList = (ArrayList<Matchmaking>) mMatchmakingDb.MatchmakingDao().getMatchmakingByID(userID);
                for (int i=0;i<matchmakingList.size();i++){
                    if (matchmakingList.get(i).getProjectID().contains(project1.getProjectID())){
                        checkCurrentMatchmaking = matchmakingList.get(i);
                    }
                }
                if (checkCurrentMatchmaking != null){
                    Log.d("check", "not null");
                    String status = checkCurrentMatchmaking.getStatus();
                    if(status.contains("Applied")){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                btnApply.setText("Applied");
                                btnApply.setClickable(false);
                            }
                        });
                    }
                }
            }
        });
        btnApply.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(ProjectDetailActivity.this, "End of demo",Toast.LENGTH_LONG).show();
                /*
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        currentUser = mUserDb.userDao().getUserByUsername(user);
                        Log.d("current user", String.valueOf(currentUser));
                        userID = currentUser.getUserID();
                        ArrayList<Matchmaking> matchmakingList = (ArrayList<Matchmaking>) mMatchmakingDb.MatchmakingDao().getMatchmakingByID(userID);
                        Log.d("current ProjectID", project1.getProjectID());
                        int size = matchmakingList.size();
                        Log.d("matchmakingList", String.valueOf(size));
                        for (int i=0;i<matchmakingList.size();i++){
                            Log.d("loop","entered");
                            Log.d("matchmakingList Project",matchmakingList.get(i).getProjectID());
                            if (project1.getProjectID().contains(matchmakingList.get(i).getProjectID())){
                                Matchmaking oldRecord = mMatchmakingDb.MatchmakingDao().getMatchmakingByIDs(userID,project1.getProjectID());
                                Matchmaking newRecord = new Matchmaking(oldRecord.getMatchmakeID(), oldRecord.getUserID(), oldRecord.getProjectID(), "Applied");
                                mMatchmakingDb.MatchmakingDao().updateMatchmaking(newRecord);
                                Log.d("Status", "Applied");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        btnApply.setText("Applied");
                                        btnApply.setClickable(false);
                                    }
                                });
                            } else {
                                Log.d ("Status","Failed");
                                Matchmaking newRecord = new Matchmaking(UUID.randomUUID().toString(), userID, project1.getProjectID(), "Applied");
                                mMatchmakingDb.MatchmakingDao().insertMatchmaking(newRecord);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        btnApply.setText("Applied");
                                        btnApply.setClickable(false);
                                    }
                                });
                            }
                        }

                    }
                });*/
            }
        });
    }
    //Back button
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


}
