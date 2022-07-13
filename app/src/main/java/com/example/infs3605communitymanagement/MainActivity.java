package com.example.infs3605communitymanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private ProjectAdapter mAdapter;
    private RecyclerView mRecyclerView;

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
        mAdapter = new ProjectAdapter(Project.getProjects(), listener);
        mRecyclerView.setAdapter(mAdapter);
    }
}