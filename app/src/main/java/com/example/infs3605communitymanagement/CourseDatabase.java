package com.example.infs3605communitymanagement;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Course.class}, version = 1)
public abstract class CourseDatabase extends RoomDatabase {

    // Bug caused by unnecessary 'class' keyword
    // Changed from 'class' to 'abstract'
    public abstract CourseDao courseDao();
}
