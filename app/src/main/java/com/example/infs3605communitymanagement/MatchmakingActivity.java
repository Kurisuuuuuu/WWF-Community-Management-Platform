package com.example.infs3605communitymanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.infs3605communitymanagement.DB.MatchmakingDatabase;
import com.example.infs3605communitymanagement.DB.ProjectDatabase;
import com.example.infs3605communitymanagement.DB.UserDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;

public class MatchmakingActivity extends AppCompatActivity {
    private ProjectAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ProjectDatabase mProjectDb;
    private UserDatabase mUserDb;
    private MatchmakingDatabase mMatchmakingDb;
    SharedPreferences sharedPrefUser;
    public String user;
    public String superPower;
    public String theme;
    public int projectsCanBeAssigned;
    public int challengesNumber;
    public User currentUser;
    public String superPowerCategory;
    public String themeCategory;
    public static String username;
    public String userID;
    public int count=0;
    public String id1 = " ";
    public String id2 = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);
        setTitle("For You");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.dark_green)));
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

        // Back button
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        mMatchmakingDb = Room.databaseBuilder(getApplicationContext(), MatchmakingDatabase.class, "matchmaking")
                .fallbackToDestructiveMigration()
                .build();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                //get info from user db
                //mUserDb.userDao().deleteUsers(mUserDb.userDao().getUsers().toArray(new User[0])); //Project Database 1 to 1 relationship
                //mUserDb.userDao().insertUsers(User.getUsers().toArray(new User[0]));
                //mProjectDb.projectDao().deleteProjects(mProjectDb.projectDao().getProjects().toArray(new Project[0])); //Project Database 1 to 1 relationship
                //mProjectDb.projectDao().insertProjects(Project.getProjects().toArray(new Project[0]));
                //mMatchmakingDb.MatchmakingDao().deleteMatchmaking(mMatchmakingDb.MatchmakingDao().getMatchmakings().toArray(new Matchmaking[0])); //Project Database 1 to 1 relationship
                //mMatchmakingDb.MatchmakingDao().insertMatchmaking(Matchmaking.getMatchmakings().toArray(new Matchmaking[0]));
                currentUser = mUserDb.userDao().getUserByUsername(user);
                Log.d("current user", String.valueOf(currentUser));
                userID = currentUser.getUserID();
                Log.d("matchmaking", String.valueOf(mMatchmakingDb.MatchmakingDao().getMatchmakingByID(userID).size()));
                if (mMatchmakingDb.MatchmakingDao().getMatchmakingByID(userID).size() < currentUser.getProjectsCanBeAssigned()){
                    superPower = currentUser.getSuperPower();
                    if (superPower.contains("Technical skills and entrepreneurial mindset") ){
                        superPowerCategory = "technical";
                    } else if (superPower.contains("Indigenous Knowledge and leadership")){
                        superPowerCategory = "knowledge";
                    } else if (superPower.contains("Community building, engagement and participation")){
                        superPowerCategory = "community";
                    } else if (superPower.contains("Financial sustainability, modelling and growth")){
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
                    } else if (theme.contains("Wealth sustainability")){
                        themeCategory = "Wealth sustainability";
                    } else if (theme.contains("Food and Agriculture")){
                    themeCategory = "Food and Agriculture";
                    } else if (theme.contains("Pollution reduction")){
                        themeCategory = "Pollution reduction";
                    } else if (theme.contains("Sustainable development")){
                        themeCategory = "Sustainable development";
                }
                    Log.d("theme",themeCategory);
                    projectsCanBeAssigned = currentUser.getProjectsCanBeAssigned();
                    challengesNumber = currentUser.getChallengesNumber();

                    ArrayList<Project> project = (ArrayList<Project>) mProjectDb.projectDao().getProjectMatchCurators(superPowerCategory, themeCategory);
                    ArrayList<Project> projectNew = new ArrayList<Project>();
                    Log.d("project",project.toString());
                    // display only 3 projects
                    int projectsCanBeAssignedThisRound = 3;
                    if (mMatchmakingDb.MatchmakingDao().getMatchmakingByID(userID).size()>0){
                        projectsCanBeAssignedThisRound = projectsCanBeAssignedThisRound-mMatchmakingDb.MatchmakingDao().getMatchmakingByID(userID).size();
                        ArrayList<Matchmaking> matchmakingList = (ArrayList<Matchmaking>) mMatchmakingDb.MatchmakingDao().getMatchmakingByID(userID);
                        switch (mMatchmakingDb.MatchmakingDao().getMatchmakingByID(userID).size()){
                            case 1:
                                id1 = matchmakingList.get(0).getProjectID();
                                id2 = null;
                                Log.d("switch", "1");
                                break;
                            case 2:
                                id1 = matchmakingList.get(0).getProjectID();
                                id2 = matchmakingList.get(1).getProjectID();
                                Log.d("switch", "2");
                                break;
                        }
                    }
                    if (project.size()>projectsCanBeAssignedThisRound){
                        for(int i=0;i<projectsCanBeAssignedThisRound;i++){
                            if (id1 != project.get(i).getProjectID() && id2 != project.get(i).getProjectID()){
                                projectNew.add(project.get(i));
                            }
                        }
                    } else if(project.size()==0){
                        Log.d("project", "Does not match");
                    } else {
                        for (int i = 0; i < project.size(); i++) {
                            if (id1 != project.get(i).getProjectID() && id2 != project.get(i).getProjectID()){
                                projectNew.add(project.get(i));
                            }
                        }
                    }
                    Log.d("projectNew",projectNew.toString());

                    if (projectNew != null){
                        List<Project> projectList = projectNew;
                        Log.d("userid", userID);
                        //set to matchmaking database
                        for (int ii=0;ii<projectNew.size();ii++){
                            Log.d("projectID", projectNew.get(ii).getProjectID());
                            if (mMatchmakingDb.MatchmakingDao().getMatchmakingByID(userID).size()>0){
                                Log.d("id1",id1);
                                if(id2 != null) {
                                    Log.d("id2", id2);
                                }
                            }
                            if (projectNew.get(ii).getProjectID().contains(id1) || projectNew.get(ii).getProjectID().contains(id2)){

                            } else {
                                mMatchmakingDb.MatchmakingDao().insertMatchmaking(new Matchmaking(UUID.randomUUID().toString(),userID, projectNew.get(ii).getProjectID(),"Recommended"));
                                Project projectData = projectNew.get(ii);
                                Project updateProject = new Project(projectData.getProjectID(),projectData.getProjectTitle(),projectData.getProjectSummary(),projectData.getTheme(),projectData.getSupportNeeded(),projectData.getSdg(),projectData.getImageUrl(),projectData.getCuratorAssigned()+1);
                                mProjectDb.projectDao().updateProjects(updateProject);
                                User updateUser = new User(currentUser.getUserID(), currentUser.getUsername(),currentUser.getFullName(),currentUser.getUserType(),currentUser.getBio(),currentUser.getPreferredSDGs(),currentUser.getImpactTheme(),currentUser.getLastLogin(),currentUser.getAvailability(),currentUser.getProjectsCanBeAssigned(),currentUser.getCommentsNumber(),currentUser.getChallengesNumber()+1,currentUser.getPassword(),currentUser.getSuperPower(),currentUser.getIndustry(),currentUser.getExperience());
                                mUserDb.userDao().updateUsers(updateUser);
                            }
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter.setData(projectList);
                            }
                        });

                    }
                } else {
                    ArrayList<Project> projectNew = new ArrayList<Project>();
                    projectNew.clear();
                    ArrayList<Matchmaking> matchmakingList = (ArrayList<Matchmaking>) mMatchmakingDb.MatchmakingDao().getMatchmakingByID(userID);
                    ArrayList<String> matchmakeProjectList = new ArrayList<String>();
                    for (int iii=0;iii<matchmakingList.size();iii++){
                        matchmakeProjectList.add(matchmakingList.get(iii).getProjectID());
                        Log.d("projectID", matchmakingList.get(iii).getProjectID());
                        projectNew.add(mProjectDb.projectDao().getProjectByID(matchmakeProjectList.get(iii)));
                    }
                    List<Project> projectList = projectNew;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.setData(projectList);
                        }
                    });
                }

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
    //Back button
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}