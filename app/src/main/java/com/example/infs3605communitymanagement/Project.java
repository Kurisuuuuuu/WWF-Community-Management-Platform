package com.example.infs3605communitymanagement;

import java.util.ArrayList;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Project{

  @PrimaryKey
  @NonNull
  private String projectID;
  private String projectTitle;
  private String projectSummary;
  private String theme;
  private String supportNeeded;
  private String imageUrl;
  private int curatorAssigned;

  public Project(String projectID, String projectTitle, String projectSummary, String theme, String supportNeeded, String imageUrl, int curatorAssigned) {
    this.projectID = projectID;
    this.projectTitle = projectTitle;
    this.projectSummary = projectSummary;
    this.theme = theme;
    this.supportNeeded = supportNeeded;
    this.imageUrl = imageUrl;
    this.curatorAssigned = curatorAssigned;
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

  public int getCuratorAssigned() {
    return curatorAssigned;
  }

  public void setCuratorAssigned(int curatorAssigned) {
    this.curatorAssigned = curatorAssigned;
  }

  //data

  public static ArrayList<Project> getProjects() {
    ArrayList<Project> projects = new ArrayList<>();
    projects.add(new Project("ID10","Wayfairer - Building community wealth through tourism",
            "Wayfairer is a community-owned accommodation booking platform, where 50% of booking fees are reinvested back into local communities.",
            "Wealth sustainability","Community building, engagement and participation",
            "https://storage.googleapis.com/eu-impactio-prod.appspot.com/impactio-v4/projects/0x587b39a9d3d0462fa6c38470aa3dc2e8?1648607395716",0));
    projects.add(new Project("ID11","The Great Fashion Decarbonisation Opportunity",
            "The Great Fashion Decarbonisation challenge seeks to find solutions that can help enable a net-zero fashion industry by 2050.",
            "Pollution reduction","Financial sustainability, modelling and growth",
            "https://media.istockphoto.com/photos/clothes-shop-costume-dress-fashion-store-style-concept-picture-id955641488?k=20&m=955641488&s=612x612&w=0&h=g7i-RIy3EeBiURdNr9cxI2Llt9s4UdobLMq5c738_TY=",0));
    projects.add(new Project("ID12","Innovate to Regenerate",
            " Innovate to Regenerate is a program that aims to support regenerative, community-led solutions that encourage impact investment in priority areas such as food, biodiversity and climate.",
            "Conservation, Nature and Oceans","Community building, engagement and participation",
            "https://images.pexels.com/photos/974314/pexels-photo-974314.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",0));
    projects.add(new Project("ID13","Cities of Tomorrow",
            "To achieve the Paris Agreement targets, infrastructure projects need to reduce embodied (or upfront) emissions by at least 50% by 2030 (and 30% by 2025).",
            "Sustainable development","Technical skills and entrepreneurial mindset",
            "https://thumbs.dreamstime.com/b/green-city-future-harmony-nature-sunny-day-big-85359650.jpg",0));
    projects.add(new Project("ID14","Bushfire Regeneration Challenge",
            "Bushfire Regeneration Challenge look for solutions that should cover at least one of these areas: fire risk management, regenerative land use, species recovery and building ecological, economic and social resilience to climate change..",
            "Conservation, Nature and Oceans","Indigenous Knowledge and leadership",
            "https://i.dailymail.co.uk/1s/2019/01/25/03/8965060-0-image-a-37_1548388123423.jpg",0));
    projects.add(new Project("ID15","Sustainability Advantage Impact Challenge",
            "Our challenge will mobilise the collective action of innovators, researchers, corporates, NGOs, financiers and government to seed ideas and quicken the adoption of replicable, scalable solutions.",
            "Sustainable development","Technical skills and entrepreneurial mindset",
            "https://pic-bstarstatic.akamaized.net/ugc/953957ace1d3183e57dc3ca419e5b513.jpg@960w_540h_1e_1c_1f.webp",0));
    projects.add(new Project("ID16","Innovate for Wildlife & People Challenge",
            "WThe experiment involved fifty-five highly skilled subject matter experts from around the world working together virtually to co-curate the portfolio and ensuring the projects were the best they could be before being presented for funding.",
            "Sustainable development","Community building, engagement and participation",
            "https://ak9.picdn.net/shutterstock/videos/2758379/thumb/1.jpg?ip=x480",0));


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