package com.example.infs3605communitymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.infs3605communitymanagement.DB.ProjectDatabase;

import java.util.concurrent.Executors;

public class ProjectDetailActivity extends AppCompatActivity {
    public static String projectID;
    private ProjectDatabase mDb;
    private String output;
    public Project project1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        setTitle("Detail Activity");

        //Get Intent and get message
        Intent newIntent = getIntent();
        output = newIntent.getStringExtra(projectID);
        Log.d("Type", output);

        if (newIntent != null){
            mDb = Room.databaseBuilder(getApplicationContext(), ProjectDatabase.class, "project")
                    .build();
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
    }


}
