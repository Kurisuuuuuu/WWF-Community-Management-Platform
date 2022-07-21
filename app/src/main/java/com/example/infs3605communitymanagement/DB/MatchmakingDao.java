package com.example.infs3605communitymanagement.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.infs3605communitymanagement.Matchmaking;
import com.example.infs3605communitymanagement.User;

import java.util.List;

@Dao
public interface MatchmakingDao {

    @Query("SELECT * FROM matchmaking")
    List<Matchmaking> getMatchmakings();

    @Query("SELECT * FROM matchmaking WHERE userID == :userID AND projectID == :projectID")
    Matchmaking getMatchmakingByIDs(String userID, String projectID);

    @Query("SELECT * FROM matchmaking WHERE userID == :userID")
    List<Matchmaking> getMatchmakingByID(String userID);

    @Insert
    void insertMatchmaking (Matchmaking... matchmakings);

    @Update
    void updateMatchmaking (Matchmaking... matchmakings);

    @Delete
    void deleteMatchmaking (Matchmaking... matchmakings);
}
