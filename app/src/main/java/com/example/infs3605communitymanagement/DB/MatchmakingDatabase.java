package com.example.infs3605communitymanagement.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.infs3605communitymanagement.Matchmaking;
import com.example.infs3605communitymanagement.Project;
import com.example.infs3605communitymanagement.User;

@Database(entities = {Matchmaking.class}, version = 3, exportSchema = false)

public abstract class MatchmakingDatabase extends RoomDatabase {

    public abstract MatchmakingDao MatchmakingDao();
}