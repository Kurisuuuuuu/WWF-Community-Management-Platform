package com.example.infs3605communitymanagement;

import java.util.ArrayList;

public class Project<project> {
  private String projectID;
  private String projectTitle;
  private String projectSummary;
  private String theme;
  private String supportNeeded;
  private String imageUrl;

  public Project(String projectID, String projectTitle, String projectSummary, String theme, String supportNeeded, String imageUrl) {
    this.projectID = projectID;
    this.projectTitle = projectTitle;
    this.projectSummary = projectSummary;
    this.theme = theme;
    this.supportNeeded = supportNeeded;
    this.imageUrl = imageUrl;
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

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  //data
  public static ArrayList<Project> getProjects() {
    ArrayList<Project> projects = new ArrayList<>();
    projects.add(new Project("ID10","Wayfairer - Building community wealth through tourism","Wayfairer is a community-owned accommodation booking platform, where 50% of booking fees are reinvested back into local communities.","Food and agriculture, Conservation, Nature and Oceans, Other: Community Wealth Building","Technical / Knowledge, Financial, Community", "https://storage.googleapis.com/eu-impactio-prod.appspot.com/impactio-v4/projects/0x587b39a9d3d0462fa6c38470aa3dc2e8?1648607395716"));
    return projects;
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