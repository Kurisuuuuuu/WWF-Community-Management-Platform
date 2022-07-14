package com.example.infs3605communitymanagement.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.infs3605communitymanagement.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)

public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}