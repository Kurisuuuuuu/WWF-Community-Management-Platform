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

import com.example.infs3605communitymanagement.DB.UserDatabase;

import java.util.ArrayList;
import java.util.concurrent.Executors;

public class UserListActivity extends AppCompatActivity {
    private UserAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private UserDatabase mDb;
    public ArrayList<User> user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);
        setTitle("User list");
        mRecyclerView = findViewById(R.id.tvList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserAdapter.RecyclerviewClickListener listener = new UserAdapter.RecyclerviewClickListener() {
            @Override
            public void onClick(View view, String projectID) {
                //launchDetailActivity(projectID);
            }
        };
        mAdapter = new UserAdapter(new ArrayList<User>(), listener);

        //implementation of RoomDatabase
        mDb = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "user")
                .fallbackToDestructiveMigration()
                .build();
/*
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                mDb.userDao().deleteUsers(mDb.userDao().getUsers().toArray(new User[0])); //Project Database 1 to 1 relationship
                mDb.userDao().insertUsers(User.getUsers().toArray(new User[0]));

            }
        });
*/

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                user = (ArrayList<User>) mDb.userDao().getUsers();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setData(user);
                        if (user == null){
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