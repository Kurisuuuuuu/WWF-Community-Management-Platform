package com.example.infs3605communitymanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.infs3605communitymanagement.DB.ProjectDatabase;
import com.example.infs3605communitymanagement.DB.UserDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class MatchmakingActivity extends AppCompatActivity {
    private ProjectAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ProjectDatabase mProjectDb;
    private UserDatabase mUserDb;
    SharedPreferences sharedPrefUser;
    public String user;
    public String areaOfExpertise;
    public String theme;
    public int projectsCanBeAssigned;
    public int challengesNumber;
    public User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
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

        sharedPrefUser = getSharedPreferences(user, MODE_PRIVATE);
        user=sharedPrefUser.getString("current user", "");
        Log.d("user", user);
        //implementation of RoomDatabase
        mProjectDb = Room.databaseBuilder(getApplicationContext(), ProjectDatabase.class, "project")
                .fallbackToDestructiveMigration()
                .build();
        mUserDb = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "user")
                .fallbackToDestructiveMigration()
                .build();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                //get info from user db
                currentUser = mUserDb.userDao().getUserByID(user);
                areaOfExpertise = currentUser.getAreasOfExpertise();
                theme = currentUser.getImpactTheme();
                projectsCanBeAssigned = currentUser.getProjectsCanBeAssigned();
                challengesNumber = currentUser.getChallengesNumber();

                ArrayList<Project> project = (ArrayList<Project>) mProjectDb.projectDao().getProjectMatchCurators(areaOfExpertise,theme);
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