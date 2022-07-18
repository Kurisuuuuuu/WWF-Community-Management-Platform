package com.example.infs3605communitymanagement.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.infs3605communitymanagement.Project;

import java.util.List;

@Dao
public interface ProjectDao {
    @Query("SELECT * FROM project")
    List<Project> getProjects();

    @Query("SELECT * FROM project WHERE projectID == :projectID")
    Project getProjectByID(String projectID);

    @Query("SELECT * FROM project WHERE curatorAssigned < 4 AND supportNeeded LIKE '%'||:superPower||'%' AND theme LIKE '%'||:theme||'%'")
    List<Project> getProjectMatchCurators(String superPower, String theme);

    @Insert
    void insertProjects (Project... projects);

    @Update
    void updateProjects (Project... projects);

    @Delete
    void deleteProjects(Project... projects);
}
