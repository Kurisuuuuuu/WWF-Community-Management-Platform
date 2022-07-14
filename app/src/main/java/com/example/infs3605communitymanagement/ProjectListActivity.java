package com.example.infs3605communitymanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.infs3605communitymanagement.DB.ProjectDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class ProjectListActivity extends AppCompatActivity {
    private ProjectAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ProjectDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("WWF Community Management");
        mRecyclerView = findViewById(R.id.tvList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ProjectAdapter.RecyclerviewClickListener listener = new ProjectAdapter.RecyclerviewClickListener() {
            @Override
            public void onClick(View view, String projectID) {
                //launchDetailActivity(destinationName);
            }
        };
        mAdapter = new ProjectAdapter(new ArrayList<Project>(), listener);
        mRecyclerView.setAdapter(mAdapter);

        //implementation of RoomDatabase
        mDb = Room.databaseBuilder(getApplicationContext(), ProjectDatabase.class, "project")
                .build();
        /*Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                mDb.projectDao().deleteProjects(mDb.projectDao().getProjects().toArray(new Project[0])); //Project Database 1 to 1 relationship
                mDb.projectDao().insertProjects(Project.getProjects().toArray(new Project[0]));
            }
        });
*/
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<Project> project = (ArrayList<Project>) mDb.projectDao().getProjects();
                mAdapter.setData(project);
            }
        });
    }
}