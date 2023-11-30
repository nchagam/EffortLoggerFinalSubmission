package application;
// User Story class to be used by Planning Poker - KW
class UserStory {
   // Basic fields of a user story - KW
   private String story;
   private String description; // New field for the description
   private int points;
   // Updated constructor to include the story and the description - KW
   public UserStory(String story, String description, int points) {
       this.story = story;
       this.description = description;
       this.points = points;
   }
   // Methods to return a story, its description, and its points so it can be referenced by Planning Poker - KW
   public String getTitle() {
       return story;
   }
   public String getDescription() {
       return description; // Getter for the description
   }
   public int getPoints() {
       return points;
   }
   // Method to initialize the points of the user story - KW
   public void setPoints(int points) {
       if (this.points == -1) {
           this.points = points;
       } else {
           // Optionally: throw an exception or log a warning
           System.out.println("Points already assigned for this user story.");
       }
   }
   // Overriding the toString method to return the story, description, and points - KW
   @Override
   public String toString() {
       String pointsString = points == -1 ? "Not estimated" : "Estimated Points: " + points;
       return story + (description.isBlank() ? "" : "\n    " + description) + "\n    " + pointsString;
   }
}
