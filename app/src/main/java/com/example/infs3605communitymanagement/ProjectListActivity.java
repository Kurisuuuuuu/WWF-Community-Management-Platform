package com.example.infs3605communitymanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.infs3605communitymanagement.DB.ProjectDatabase;

import java.util.ArrayList;
import java.util.concurrent.Executors;

public class ProjectListActivity extends AppCompatActivity {
    private ProjectAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ProjectDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);
        setTitle("WWF Community Management");
        mRecyclerView = findViewById(R.id.tvList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ProjectAdapter.RecyclerviewClickListener listener = new ProjectAdapter.RecyclerviewClickListener() {
            @Override
            public void onClick(View view, String projectID) {
                launchDetailActivity(projectID);
            }
        };
        mAdapter = new ProjectAdapter(new ArrayList<Project>(), listener);

        //implementation of RoomDatabase
        mDb = Room.databaseBuilder(getApplicationContext(), ProjectDatabase.class, "project")
                .fallbackToDestructiveMigration()
                .build();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {

                mDb.projectDao().deleteProjects(mDb.projectDao().getProjects().toArray(new Project[0])); //Project Database 1 to 1 relationship
                mDb.projectDao().insertProjects(Project.getProjects().toArray(new Project[0]));
                ArrayList<Project> project = (ArrayList<Project>) mDb.projectDao().getProjects();
                mAdapter.setData(project);
            }
        });

        mRecyclerView.setAdapter(mAdapter);
    }
    //search & sort
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.project_list_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                Log.d("Filter", query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                Log.d("Filter", newText);
                return false;
            }
        });
        return true;
    }

    //menu buttons (sort & save note)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filterNameIncreasing:
                // sort by country name (A to Z)
                mAdapter.sort(1);
                return true;
            case R.id.filterNameDecreasing:
                // sort by country name (Z to A)
                mAdapter.sort(2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void launchDetailActivity(String message){
        Intent intent = new Intent (this, ProjectDetailActivity.class);
        intent.putExtra(ProjectDetailActivity.projectID, message);
        startActivity(intent);
        Log.d("Test", message);
    }
}