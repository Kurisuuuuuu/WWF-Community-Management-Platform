package com.example.infs3605communitymanagement;

import java.util.ArrayList;

public class Project<project> {
  private String projectID;
  private String projectTitle;
  private String solutionName;
  private String projectSummary;
  private String theme;
  private String supportNeeded;

  public Project(String projectID, String projectTitle, String solutionName, String projectSummary, String theme, String supportNeeded) {
    this.projectID = projectID;
    this.projectTitle = projectTitle;
    this.solutionName = solutionName;
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

  public String getSolutionName() {
    return solutionName;
  }

  public void setSolutionName(String solutionName) {
    this.solutionName = solutionName;
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
  public static ArrayList<Destination> getDestinations() {
    ArrayList<Destination> destinations = new ArrayList<>();
    destinations.add(new Destination("Beijing", "China", "+8", 2, "From ancient walled capital to showpiece megacity in barely a century, Beijing (Běijīng, 北京), spins a breathless yarn of triumph, tragedy, endurance and innovation.", "PEK", new LatLng(39.916668,116.383331), "https://www.lonelyplanet.com/china/beijing", "https://www.youtube.com/watch?v=nFtgFb1XCF8"));
    destinations.add(new Destination("Hong Kong", "Hong Kong", "+8", 5, "Hong Kong welcomes with an iconic skyline, a legendary kitchen, and lush, protected nature where rare birds and colorful traditions thrive.", "HKG", new LatLng(22.302711, 114.177216), "https://www.discoverhongkong.com/anz/index.html", "https://www.youtube.com/watch?v=k9vuyLcIxs4"));
    destinations.add(new Destination("London", "United Kingdom", "+0", 3, "One of the world's most visited cities, London has something for everyone: from history and culture to fine food and good times. ", "LHR", new LatLng(51.509865, -0.118092), "https://www.visitbritain.com/gb/", "https://www.youtube.com/watch?v=bqSY4MSvFc8"));
    destinations.add(new Destination("New York", "United States", "-5", 4, "Epicenter of the arts. Architectural darling. Dining and shopping capital. Trendsetter. New York City wears many crowns, and spreads an irresistible feast for all.", "NYC", new LatLng(40.71278, -74.00594), "https://www.nycgo.com/", "https://www.youtube.com/watch?v=MtCMtC50gwY"));
    destinations.add(new Destination("Tokyo", "Japan", "+9", 5, "Yoking past and future, Tokyo dazzles with its traditional culture and passion for everything new. ", "TYO", new LatLng(35.658581, 139.745438), "https://www.japan.travel/en/au/", "https://www.youtube.com/watch?v=_Z_5_BKdGZM"));
    destinations.add(new Destination("Sydney", "Australia", "+10", 5, "Sydney, spectacularly draped around its glorious harbour and beaches, has visual wow factor like few other cities. Scratch the surface and it only gets better.", "SYD", new LatLng(-33.852, 151.211), "https://www.australia.com/en/places/sydney-and-surrounds/guide-to-sydney.html", "https://www.youtube.com/watch?v=5QhKCIt8QCw"));
    destinations.add(new Destination("Singapore", "Singapore", "+8", 4, "Celebrating its melting pot of cultures, Singapore has that spark, and it's fast becoming one of Asia’s hit-list destinations.", "SIN", new LatLng(1.290270,103.851959), "https://www.visitsingapore.com/en/", "https://www.youtube.com/watch?v=P_q3BdrFsLI"));
    destinations.add(new Destination("Dubai", "United Arab Emirates", "+4", 4, "Dubai is a stirring alchemy of profound traditions and ambitious futuristic vision wrapped into starkly evocative desert splendour.", "DXB", new LatLng(25.204849, 55.270783), "https://www.visitdubai.com/en/", "https://www.youtube.com/watch?v=VE75g0frvr4"));
    destinations.add(new Destination("Queenstown", "New Zealand", "+12", 5, "Queenstown is as much a verb as a noun, a place of doing that likes to spruik itself as the 'adventure capital of the world'. It's famously the birthplace of bungy jumping, and the list of adventures you can throw yourself into here is encyclopedic – from alpine heliskiing to zip-lining. It's rare that a visitor leaves without having tried something that ups their heart rate, but to pigeonhole Queenstown as just a playground is to overlook its cosmopolitan dining and arts scene, its fine vineyards, and the diverse range of bars that can make evenings as fun-filled as the days. ", "ZQN",new LatLng(-45.031162, 168.662643), "https://www.queenstownnz.co.nz/", "https://www.youtube.com/watch?v=DpN2sGKCqhw"));
    destinations.add(new Destination("Vancouver", "Canada", "-7", 5, "Explorable neighborhoods, drink-and-dine delights and memorable cultural and outdoor activities framed by striking natural vistas – there's a superfluity of reasons to fall for this ocean-fringed metropolis.", "YVR", new LatLng(49.246292, -123.116226), "https://www.destinationvancouver.com/", "https://www.youtube.com/watch?v=_dIliOvL5aA"));
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
    }
}