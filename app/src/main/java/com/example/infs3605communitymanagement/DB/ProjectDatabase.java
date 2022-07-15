package com.example.infs3605communitymanagement.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.infs3605communitymanagement.Project;

@Database(entities = {Project.class}, version = 2, exportSchema = false)

public abstract class ProjectDatabase extends RoomDatabase {

    public abstract ProjectDao projectDao();
}
