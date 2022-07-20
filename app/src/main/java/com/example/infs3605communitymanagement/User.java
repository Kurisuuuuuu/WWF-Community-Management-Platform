package com.example.infs3605communitymanagement;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.UUID;

@Entity
public class User{

  @PrimaryKey
  @NonNull
  private String userID;
  private String username;
  private String fullName;
  private String userType;
  private String bio;
  private String preferredSDGs;
  private String impactTheme;
  private String lastLogin;
  private String availability;
  private int projectsCanBeAssigned;
  private int commentsNumber;
  private int challengesNumber;
  private String password;
  private String superPower;
  private String industry;
  private String experience;


  public User(String userID, String username, String fullName, String userType, String bio, String preferredSDGs, String impactTheme, String lastLogin, String availability,
              int projectsCanBeAssigned, int commentsNumber, int challengesNumber, String password, String superPower, String industry, String experience) {
    this.userID = userID;
    this.username = username;
    this.fullName = fullName;
    this.userType = userType;
    this.bio = bio;
    this.availability = availability;
    this.preferredSDGs = preferredSDGs;
    this.impactTheme = impactTheme;
    this.lastLogin = lastLogin;
    this.projectsCanBeAssigned = projectsCanBeAssigned;
    this.commentsNumber = commentsNumber;
    this.challengesNumber = challengesNumber;
    this.password = password;
    this.superPower = superPower;
    this.industry = industry;
    this.experience = experience;
  }

  //getters & setters
  public String getUserID() {
    return userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }

  public String getUsername() {
    return username;
  }

  public void setUserName(String username) {
    this.username = username;
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

  public String getSuperPower(){ return superPower;}

  public void setSuperPower(String superPower){
    this.superPower = superPower;
  }

  public String getIndustry() {
    return industry;
  }

  public void setIndustry(String industry) {
    this.industry = industry;
  }

  public String getExperience() {
    return experience;
  }

  public void setExperience(String experience) {
    this.experience = experience;
  }

  //data
  public static ArrayList<User> getUsers() {
    ArrayList<User> users = new ArrayList<>();
    users.add(new User(UUID.randomUUID().toString(),"Anorah","Anorah Test","Curator","Josephine Sample opens the line of communication between clients, customers, and businesses to get projects done. With over 20 years in both public and private sectors, Melanie has experience in management consultation, team building, professional development, strategic implementation, and company collaboration. Melanie has managed projects at TechPoint International, Cyberry, and Induster, where she was a finalist for the PMI® Project of the Year. Melanie holds an MBA from Dartmouth University and a current PMP® certification.", "Sustainable Cities And Communities, Life On Land, Life Below Water, Partnerships For The Goals, Climate Action, Industry Innovation And Infrastructure", "Climate and Energy, Conservation, Nature and Ocean","5 June 2022, 10 pm GMT","1-hour a week",1,0,0,"abc123","[Environmental impact [based on impact challenge themes],Community building, engagement and participation]","Professional and business services","0-1 years"));
    users.add(new User(UUID.randomUUID().toString(),"Marcie23","Marcie Whitehead","Project Leader","Marketing manager with 4+ years of experience in a corporate environment. Good eye for design, with experience in creating marketing materials with Canva. Intermediate copywriting skills, having worked on the company website, flyers, and several other content pieces.”", "Zero Hunger, Good Health and Well-Being, Affordable and Clean Energy", "Climate and Energy, Conservation","6 June 2022, 8 am GMT","2-hour a week",1,0,0,"abc123","[Indigenous Knowledge and leadership, Regenerative design and development, Financial sustainability, modelling and growth]","Transport equipment manufacturing","10+ years"));
    users.add(new User(UUID.randomUUID().toString(),"Christi56","Christi Eaton","Curator","Results-driven computer science student from UNSW passionate about developing user-friendly software applications. Excellent problem-solving skills and ability to perform well in a team. Seeking to help WWF develop their product as a software engineer, as well as grow and develop my own skills as a coder.", "Climate Action, Reduced Inequalities", "Nature and Ocean","10 June 2022, 10 pm GMT","5-hour a week",1,0,0,"abc123","[Technical skills and entrepreneurial mindset, Regenerative design and development, Community building, engagement and participation]","Health services ","4-6 years"));
    users.add(new User(UUID.randomUUID().toString(),"Gena34","Gena Zhang","Supporter","Josephine Sample opens the line of communication between clients, customers, and businesses to get projects done. With over 20 years in both public and private sectors, Melanie has experience in management consultation, team building, professional development, strategic implementation, and company collaboration. Melanie has managed projects at TechPoint International, Cyberry, and Induster, where she was a finalist for the PMI® Project of the Year. Melanie holds an MBA from Dartmouth University and a current PMP® certification.", "Clean Water and Sanitation, Reduced Inequalities, Responsible Consumption and Production, Partnerships for the Goals", "Climate and Energy, Nature and Ocean","5 June 2022, 11 pm GMT","1-hour a week",1,0,0,"abc123","[Environmental impact [based on impact challenge themes], Regenerative design and development]","Construction ","2-4 years"));
    users.add(new User(UUID.randomUUID().toString(),"Aurelio7","Aurelio Torres","Supporter","Josephine Sample opens the line of communication between clients, customers, and businesses to get projects done. With over 20 years in both public and private sectors, Melanie has experience in management consultation, team building, professional development, strategic implementation, and company collaboration. Melanie has managed projects at TechPoint International, Cyberry, and Induster, where she was a finalist for the PMI® Project of the Year. Melanie holds an MBA from Dartmouth University and a current PMP® certification.", "Industry Innovation and Infrastructure, Affordable and Clean Energy, Life Below Water", "Climate and Energy","15 June 2022, 10 am GMT","7-hour a week",1,0,0,"abc123","[Financial sustainability, modelling and growth, Technical skills and entrepreneurial mindset, Indigenous Knowledge and leadership]","Commerce ","6-8 years"));
    users.add(new User(UUID.randomUUID().toString(),"Dante8","Dante Bradford","Curator","Josephine Sample opens the line of communication between clients, customers, and businesses to get projects done. With over 20 years in both public and private sectors, Melanie has experience in management consultation, team building, professional development, strategic implementation, and company collaboration. Melanie has managed projects at TechPoint International, Cyberry, and Induster, where she was a finalist for the PMI® Project of the Year. Melanie holds an MBA from Dartmouth University and a current PMP® certification.", "Gender Equality, Responsible Consumption and Production, Affordable and Clean Energy,", "Climate and Energy, Conservation, Nature and Ocean","26 June 2022, 12 pm GMT","10-hour a week",1,0,0,"abc123","[Financial sustainability modelling and growth, Technical skills and entrepreneurial mindset]","Agriculture; plantations;other rural sectors","1-2 years"));
    users.add(new User(UUID.randomUUID().toString(),"Lea5","Lea Werner","Supporter","Josephine Sample opens the line of communication between clients, customers, and businesses to get projects done. With over 20 years in both public and private sectors, Melanie has experience in management consultation, team building, professional development, strategic implementation, and company collaboration. Melanie has managed projects at TechPoint International, Cyberry, and Induster, where she was a finalist for the PMI® Project of the Year. Melanie holds an MBA from Dartmouth University and a current PMP® certification.", "Climate Action, Decent Work and Economic Growth, Affordable and Clean Energy, Peace, Justice and Strong Institutions", "Climate and Energy, Conservation","15 June 2022, 1 am GMT","2-hour a week",1,0,0,"abc123","[Indigenous Knowledge and leadership, Regenerative design and development]","Education","0-1 years"));
    users.add(new User(UUID.randomUUID().toString(),"Aisha7","Aisha Vasquez","Project Leader","Josephine Sample opens the line of communication between clients, customers, and businesses to get projects done. With over 20 years in both public and private sectors, Melanie has experience in management consultation, team building, professional development, strategic implementation, and company collaboration. Melanie has managed projects at TechPoint International, Cyberry, and Induster, where she was a finalist for the PMI® Project of the Year. Melanie holds an MBA from Dartmouth University and a current PMP® certification.", "Industry Innovation and Infrastructure, Life on Land, Zero Hunger, No Poverty", "Nature and Ocean","4 June 2022, 10 pm GMT","1-hour a week",1,0,0,"abc123","[Environmental impact [based on impact challenge themes],Community building, engagement and participation]","Hotels; tourism; catering","1-2 years"));
    users.add(new User(UUID.randomUUID().toString(),"Shon5","Shon Peterson","Curator","Josephine Sample opens the line of communication between clients, customers, and businesses to get projects done. With over 20 years in both public and private sectors, Melanie has experience in management consultation, team building, professional development, strategic implementation, and company collaboration. Melanie has managed projects at TechPoint International, Cyberry, and Induster, where she was a finalist for the PMI® Project of the Year. Melanie holds an MBA from Dartmouth University and a current PMP® certification.", "Affordable and Clean Energy, Industry Innovation and Infrastructure, Sustainable Cities and Communities, Life on Land", "Climate and Energy, Nature and Ocean","8 June 2022, 6 pm GMT","5-hour a week",1,0,0,"abc123","[Financial sustainability modelling and growth,]","Transportation services","2-4 years"));
    users.add(new User(UUID.randomUUID().toString(),"Christy6","Christy Owen","Project Leader","Josephine Sample opens the line of communication between clients, customers, and businesses to get projects done. With over 20 years in both public and private sectors, Melanie has experience in management consultation, team building, professional development, strategic implementation, and company collaboration. Melanie has managed projects at TechPoint International, Cyberry, and Induster, where she was a finalist for the PMI® Project of the Year. Melanie holds an MBA from Dartmouth University and a current PMP® certification.", "", "Climate and Energy, Conservation","6 June 2022, 10 pm GMT","3-hour a week",1,0,0,"abc123","[Environmental impact, Financial sustainability, modelling and growth]","Professional and business services","2-4 years"));
    users.add(new User(UUID.randomUUID().toString(),"Dixie76","Dixie Bray","Curator","Josephine Sample opens the line of communication between clients, customers, and businesses to get projects done. With over 20 years in both public and private sectors, Melanie has experience in management consultation, team building, professional development, strategic implementation, and company collaboration. Melanie has managed projects at TechPoint International, Cyberry, and Induster, where she was a finalist for the PMI® Project of the Year. Melanie holds an MBA from Dartmouth University and a current PMP® certification.", "Good Health and Well-Being, Gender Equality, Affordable and Clean Energy, Industry Innovation and Infrastructure", "Climate and Energy, Nature and Ocean","26 June 2022, 5 pm GMT","5-hour a week",1,0,0,"abc123","[Environmental impact [based on impact challenge themes],Community building, engagement and participation]","Commerce","6-8 years"));
    users.add(new User(UUID.randomUUID().toString(),"Della42","Della Bennett","Curator","Customer service representative with 5+ years of experience in telephone customer service, tech support, and customer care. Familiar with Intercom, Drift, and several other customer service software solutions. Very organized and meticulous with organizing customer interaction logs. Handled up to 100 calls daily, with duties including helping customers sign up and retrieving customer data.", "Gender Equality, Decent Work and Economic Growth, Climate Action, Life on Land", "Conservation, Nature and Ocean","13 June 2022, 5 am GMT","2-hour a week",1,0,0,"abc123","[Regenerative design and development, Technical skills and entrepreneurial mindset]","Professional and business services","2-4 years"));
    users.add(new User(UUID.randomUUID().toString(),"Alice23","Alice Mora","Project Leader","Josephine Sample opens the line of communication between clients, customers, and businesses to get projects done. With over 20 years in both public and private sectors, Melanie has experience in management consultation, team building, professional development, strategic implementation, and company collaboration. Melanie has managed projects at TechPoint International, Cyberry, and Induster, where she was a finalist for the PMI® Project of the Year. Melanie holds an MBA from Dartmouth University and a current PMP® certification.", "Climate Action, Life Below Water, Life on Land, Partnerships for the Goals", "Climate and Energy, Conservation","18 June 2022, 11 pm GMT","1-hour a week",1,0,0,"abc123","[Community building, engagement and participation]","Health services","0-1 years"));
    users.add(new User(UUID.randomUUID().toString(),"Noel65","Noel Pierce","Supporter","Josephine Sample opens the line of communication between clients, customers, and businesses to get projects done. With over 20 years in both public and private sectors, Melanie has experience in management consultation, team building, professional development, strategic implementation, and company collaboration. Melanie has managed projects at TechPoint International, Cyberry, and Induster, where she was a finalist for the PMI® Project of the Year. Melanie holds an MBA from Dartmouth University and a current PMP® certification.", "Affordable and Clean Energy, Decent Work and Economic Growth, Industry Innovation and Infrastructure, Reduced Inequalities", "Climate and Energy, Conservation, Nature and Ocean","19 June 2022, 10 am GMT","6-hour a week",1,0,0,"abc123","[Environmental impact [based on impact challenge themes],Community building, engagement and participation]","Agriculture; plantations;other rural sectors","0-1 years"));


    return users;
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