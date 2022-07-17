package com.example.infs3605communitymanagement.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.infs3605communitymanagement.Project;
import com.example.infs3605communitymanagement.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getUsers();

    @Query("SELECT * FROM user WHERE challengesNumber < projectsCanBeAssigned")
    User getUsersCanBeAssigned();

    @Query("SELECT * FROM user WHERE userID == :userID")
    User getUserByID(String userID);

    @Insert
    void insertUsers (User... users);

    @Update
    void updateUsers (User... users);

    @Delete
    void deleteUsers(User... users);
}
