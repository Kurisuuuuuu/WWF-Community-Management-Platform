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

import androidx.annotation.LongDef;
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
    public String superPower;
    public String theme;
    public int projectsCanBeAssigned;
    public int challengesNumber;
    public User currentUser;
    public String superPowerCategory;
    public String themeCategory;

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
                mUserDb.userDao().deleteUsers(mUserDb.userDao().getUsers().toArray(new User[0])); //Project Database 1 to 1 relationship
                mUserDb.userDao().insertUsers(User.getUsers().toArray(new User[0]));
                mProjectDb.projectDao().deleteProjects(mProjectDb.projectDao().getProjects().toArray(new Project[0])); //Project Database 1 to 1 relationship
                mProjectDb.projectDao().insertProjects(Project.getProjects().toArray(new Project[0]));
                currentUser = mUserDb.userDao().getUserByID(user);
                Log.d("current user", String.valueOf(currentUser));
                superPower = currentUser.getSuperPower();
                if (superPower.contains("Technical skills and entrepreneurial mindset")||superPower.contains("Indigenous Knowledge and leadership") ){
                    superPowerCategory = "technical / knowledge";
                } else if (superPower.contains("Community building, engagement and participation")){
                    superPowerCategory = "community";
                } else if (superPower.contains("Financial sustinability, modelling and growth")){
                    superPowerCategory = "Financial";
                } else if(superPower.contains("Environmental impact [based on the impact challenge themes]")){
                    superPowerCategory = "Environment";
                }
                Log.d("superpower",superPower);
                Log.d("superpower",superPowerCategory);
                theme = currentUser.getImpactTheme();
                if (theme.contains("Conservation, Nature and Oceans")) {
                    themeCategory = "Conservation, Nature and Oceans";
                } else if (theme.contains("Climate and Energy")){
                    themeCategory = "Climate and Energy";
                } else if (theme.contains("Food and Agriculture")){
                    themeCategory = "Food and Agriculture";
                }
                Log.d("theme",themeCategory);
                projectsCanBeAssigned = currentUser.getProjectsCanBeAssigned();
                challengesNumber = currentUser.getChallengesNumber();

                ArrayList<Project> project = (ArrayList<Project>) mProjectDb.projectDao().getProjectMatchCurators(superPowerCategory, themeCategory);
                Log.d("project",project.toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setData(project);
                    }
                });
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