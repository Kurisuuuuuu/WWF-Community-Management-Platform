package com.example.infs3605communitymanagement;

import java.util.ArrayList;

public class Project<project> {
  private String projectID;
  private String projectTitle;
  private String projectSummary;
  private String theme;
  private String supportNeeded;

  public Project(String projectID, String projectTitle, String projectSummary, String theme, String supportNeeded) {
    this.projectID = projectID;
    this.projectTitle = projectTitle;
    this.projectSummary = projectSummary;
    this.theme = theme;
    this.supportNeeded = supportNeeded;
  }

  //getters & setters
  public String getProjectID() {
    return projectID;
  }

  public void setProjectID(String projectID) {
    this.projectID = projectID;
  }

  public String getProjectTitle() {
    return projectTitle;
  }

  public void setProjectTitle(String projectTitle) {
    this.projectTitle = projectTitle;
  }

  public String getProjectSummary() {
    return projectSummary;
  }

  public void setProjectSummary(String projectSummary) {
    this.projectSummary = projectSummary;
  }

  public String getTheme() {
    return theme;
  }

  public void setTheme(String theme) {
    this.theme=theme;
  }

  public String getSupportNeeded() {
    return supportNeeded;
  }

  public void setSupportNeeded(String supportNeeded) {
    this.supportNeeded = supportNeeded;
  }

  //data
  /*public static ArrayList<Project> getDestinations() {
    ArrayList<Project> projects = new ArrayList<>();
    projects.add(new Destination("Beijing", "China", "+8", 2, "From ancient walled capital to showpiece megacity in barely a century, Beijing (Běijīng, 北京), spins a breathless yarn of triumph, tragedy, endurance and innovation.", "PEK", new LatLng(39.916668,116.383331), "https://www.lonelyplanet.com/china/beijing", "https://www.youtube.com/watch?v=nFtgFb1XCF8"));
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