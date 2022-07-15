package com.example.infs3605communitymanagement.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.infs3605communitymanagement.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getUsers();

    @Insert
    void insertUsers (User... users);

    @Delete
    void deleteUsers(User... users);
}
