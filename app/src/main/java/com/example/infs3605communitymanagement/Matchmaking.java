package com.example.infs3605communitymanagement;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
@Entity(
        foreignKeys = {
                @ForeignKey(entity = User.class,parentColumns = "userID",
                        childColumns = "UserID",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Project.class,parentColumns = "projectID",
                        childColumns = "projectID",
                        onDelete = ForeignKey.CASCADE)
        }
)
public class Matchmaking {

  @PrimaryKey
  @NonNull
  private String matchmakeID;
  private String userID;
  private String projectID;
  private String status;


  public Matchmaking(String matchmakeID, String userID, String projectID, String status) {
    this.matchmakeID = matchmakeID;
    this.userID = userID;
    this.projectID = projectID;
    this.status = status;
  }

  //getters & setters
  public String getUserID() {
    return userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }

  public String getMatchmakeID(String matchmakeID) {
    return matchmakeID;
  }

  public void setMatchmakeID(String matchmakeID) { this.matchmakeID = matchmakeID; }

  public String getProjectID() {
    return projectID;
  }

  public void setProjectID(String projectID) {
    this.projectID = projectID;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  //data
  public static ArrayList<Matchmaking> getMatchmakings() {
    ArrayList<Matchmaking> matchmakings = new ArrayList<>();
    return matchmakings;
  }
/*
  //return destination
  public static Destination findDestination(String name) {
    ArrayList<Destination> destinations = Destination.getDestinations();
    for (Destination destination : destinations) {
      if (destination.getName().equals(name)) {
        return destination;
      }
    }
      return null;
    }*/
}