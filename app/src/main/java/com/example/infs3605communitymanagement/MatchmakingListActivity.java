package com.example.infs3605communitymanagement;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.infs3605communitymanagement.DB.MatchmakingDatabase;
import com.example.infs3605communitymanagement.DB.ProjectDatabase;
import com.example.infs3605communitymanagement.DB.UserDatabase;

import java.util.ArrayList;
import java.util.concurrent.Executors;

public class MatchmakingListActivity extends AppCompatActivity {
    private MatchmakingAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private MatchmakingDatabase mMatchmakingDb;
    private ProjectDatabase mProjectDb;
    private UserDatabase mUserDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);
        setTitle("Matchmaking list");
        mRecyclerView = findViewById(R.id.tvList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MatchmakingAdapter.RecyclerviewClickListener listener = new MatchmakingAdapter.RecyclerviewClickListener() {
            @Override
            public void onClick(View view, String projectID) {
                //launchDetailActivity(projectID);
            }
        };
        mAdapter = new MatchmakingAdapter(new ArrayList<Matchmaking>(), new ArrayList<Project>(),new ArrayList<User>(),listener);

        //implementation of RoomDatabase
        mMatchmakingDb = Room.databaseBuilder(getApplicationContext(), MatchmakingDatabase.class, "matchmaking")
                .fallbackToDestructiveMigration()
                .build();
        mProjectDb = Room.databaseBuilder(getApplicationContext(), ProjectDatabase.class, "project")
                .fallbackToDestructiveMigration()
                .build();
        mUserDb = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "user")
                .fallbackToDestructiveMigration()
                .build();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<Matchmaking> matchmaking = (ArrayList<Matchmaking>) mMatchmakingDb.MatchmakingDao().getMatchmakings();
                ArrayList<Project> projectList = new ArrayList<Project>();
                ArrayList<User> userList = new ArrayList<User>();
                for (int i=0;i<matchmaking.size();i++){
                    projectList.add(mProjectDb.projectDao().getProjectByID(matchmaking.get(i).getProjectID()));
                    userList.add(mUserDb.userDao().getUserByUserID(matchmaking.get(i).getUserID()));
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setData(matchmaking, projectList,userList);
                        if (matchmaking.size()==0){
                            Log.d("user", "null");
                        } else {
                            Log.d("user","normal");
                        }
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
/*
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
 */
}