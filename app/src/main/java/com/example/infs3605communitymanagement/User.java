package com.example.infs3605communitymanagement;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.DateFormat;

@Entity
public class User{

  @PrimaryKey
  @NonNull
  private String userID;
  private String fullName;
  private String userType;
  private String bio;
  private String areasOfExpertise;
  private String preferredSDGs;
  private String impactTheme;
  private String lastLogin;
  private String availability;
  private int projectsCanBeAssigned;
  private int commentsNumber;
  private int challengesNumber;
  private String password;


  public User(String userID, String fullName, String userType, String bio, String areasOfExpertise,
              String preferredSDGs, String impactTheme, String lastLogin, String availability,
              int projectsCanBeAssigned, int commentsNumber, int challengesNumber, String password) {
    this.userID = userID;
    this.fullName = fullName;
    this.userType = userType;
    this.bio = bio;
    this.areasOfExpertise = areasOfExpertise;
    this.availability = availability;
    this.preferredSDGs = preferredSDGs;
    this.impactTheme = impactTheme;
    this.lastLogin = lastLogin;
    this.projectsCanBeAssigned = projectsCanBeAssigned;
    this.commentsNumber = commentsNumber;
    this.challengesNumber = challengesNumber;
    this.password = password;
  }

  //getters & setters
  public String getUserID() {
    return userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) { this.fullName = fullName; }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public String getBio() { return bio; }

  public void setBio(String bio) {
    this.bio=bio;
  }

  public String getAreasOfExpertise() {
    return areasOfExpertise;
  }

  public void setAreasOfExpertise(String areasOfExpertise) {
    this.areasOfExpertise = areasOfExpertise;
  }
  public String getPreferredSDGs(){ return preferredSDGs;}

  public void setPreferredSDGs(String preferredSDGs){
    this.preferredSDGs = preferredSDGs;
  }

  public String getImpactTheme(){ return impactTheme;}

  public void setImpactTheme(String impactTheme){
    this.impactTheme = impactTheme;
  }

  public String getLastLogin(){ return lastLogin;}

  public void setLastLogin(String lastLogin){
    this.lastLogin = lastLogin;
  }

  public String getAvailability(){ return availability;}

  public void setAvailability(String availability){
    this.availability = availability;
  }

  public int getProjectsCanBeAssigned(){ return projectsCanBeAssigned;}

  public void setProjectsCanBeAssigned(int projectsCanBeAssigned){
    this.projectsCanBeAssigned = projectsCanBeAssigned;
  }

  public int getCommentsNumber(){ return commentsNumber;}

  public void setCommentsNumber(int commentsNumber){
    this.commentsNumber = commentsNumber;
  }

  public int getChallengesNumber(){ return challengesNumber;}

  public void setChallengesNumber(int challengesNumber){
    this.challengesNumber = challengesNumber;
  }

  public String getPassword(){ return password;}

  public void setPassword(String password){
    this.password = password;
  }

  //data
  /*public static ArrayList<Project> getDestinations() {
    ArrayList<Project> projects = new ArrayList<>();

    return destinations;
  }

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