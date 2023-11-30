package application;

//import java.net.URL;
//import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class AddUserStoriesController   
{
	// Maintains reference to the user and stored logs - KW
	private Employee user;
	
	private Task task;
	private ProjectDataBase projects;
	private ArrayList<String> taskNames;
	private ArrayList<Task> tasks;
	private ArrayList<Integer> estimates;
	public Project project;
	// Fields for accessType and neededAccess which will be
	// Initialized dependent on the user - KW
	// private String accessType;
	//private String neededAccess;
	 
	//Setter methods to collect previous reference to ProjectDataBase/Projects/Tasks - MJ
	public void setProjects(ProjectDataBase projects)
	{
		this.projects = projects;
	}
	
	public void setTask(Task task)
	{
		this.task = task;
		//check();
	}
	public void setProject(Project project)
	{
		this.project = project; 
	}
	public void setProject(String name)
	{
		System.out.println(name + " was the name");
		//this.project = projects.getProject(name);
	}
	
	public void setEstimates(ArrayList<Integer> estimates)
	{
		this.estimates = estimates;
	}
	
	public void setTasks(ArrayList<String> taskNames, ArrayList<Task> tasks)
	{
		this.taskNames = taskNames;
		this.tasks = tasks;
	}
	
	public void check()
	{
		System.out.println(this.project == null);
	}
	
	// Fields for the text fields and buttons in the add story stage - KW
	@FXML
    private TextField userInputStoryField;
    @FXML
    private Button addUserStoryButton;
    @FXML
    private ListView<UserStory> userStoryListView;
    @FXML
    private ComboBox<Integer> fibonacciComboBox;
    @FXML
    private Button estimateButton;
    @FXML
    private CheckBox referenceCheckBox;
	@FXML
    private TextField userInputStoryDescription;
    @FXML
    private Button submitButton;
    @FXML
    private Button viewDataButton;
    
    private ArrayList<UserStory> userStories = new ArrayList<>();

    private final Integer[] fibonacciNumbers = {1, 2, 3, 5, 8, 13, 21, 34, 55, 89};
	
	@FXML
	private Button back;

	// EventHandler for the back button, will take the user back to the
	// main menu and pass back the references to the employee and logs - MJ
	@FXML
	public void goBack(ActionEvent event)
	{
		try {
			FXMLLoader load = new FXMLLoader(getClass().getResource("PlanPokerPane.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(load.load());
			PlanningPokerController ctrl = load.getController();
			ctrl.setAccess(user);
			ctrl.setProjects(projects);
			ctrl.setTask(task);
			ctrl.setProject(project);
			ctrl.setEstimates(estimates);
			ctrl.setTasks(taskNames, tasks);
			ctrl.updateTask(task, estimates.get(taskNames.indexOf(task.name)));
			ctrl.setLabel();
			stage.setScene(scene);
			stage.setTitle("Planning Poker!");
			stage.show();
			
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	//Event handler for when the user presses the View Data button, this opens the Select Project
	//page in a new window, to not remove the user from what they are doing - MJ
	@FXML
	public void viewData(ActionEvent event)
	{
		try {
			FXMLLoader load = new FXMLLoader(getClass().getResource("SelectProject.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(load.load());
			SelectProjectController ctrl = load.getController();
			ctrl.setAccess(user);
			ctrl.setProjects(projects);
			ctrl.setFlag('v');
			ctrl.setLabel();
			ctrl.isSideWindow();
			stage.setScene(scene);
			stage.setTitle("Project Selection Page!");
			stage.show();
			
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	// Initializes the controller, setting up the ComboBox with Fibonacci numbers - KW
	@FXML
    public void initialize() {
        fibonacciComboBox.setItems(FXCollections.observableArrayList(fibonacciNumbers));
    }

	// Handles adding a new user story from user input - KW
	@FXML
	public void handleAddUserStory() {
	    String userStoryText = userInputStoryField.getText();
	    String userStoryDescription = userInputStoryDescription.getText();

	    // Checks if the user story text is not blank before proceeding
	    if (!userStoryText.isBlank() && !userStoryDescription.isBlank()) {
	        // Create a UserStory object with both the story, description, and default points
	        UserStory newUserStory = new UserStory(userStoryText, userStoryDescription, -1);

	        // Add the UserStory object to the ListView
	        userStoryListView.getItems().add(newUserStory);

	        // Clear both input fields for the next entry
	        userInputStoryField.clear();
	        userInputStoryDescription.clear();
	    } else {
	        // Handle error: Alert the user that the story field is required
	    	Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText(null);
	        alert.setContentText("Cannot add User Story. Please enter the Story and Description first.");
	        alert.showAndWait();
	    }
	}

    // Sets the estimated points for a selected user story based on Fibonacci numbers - KW
    @FXML
    public void handleEstimate() {
        UserStory selectedStory = userStoryListView.getSelectionModel().getSelectedItem();
        if (selectedStory != null && fibonacciComboBox.getValue() != null) {
            selectedStory.setPoints(fibonacciComboBox.getValue());
            userStoryListView.refresh();
            showConfirmationMessage("Estimated story points set to: " + fibonacciComboBox.getValue());
        } else {
            showErrorMessage("Please select a user story and a Fibonacci number first.");
        }
    }


    // Displays confirmation message when user input handled properly - KW
    private void showConfirmationMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Displays error message when the user makes a mistake - KW
    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    // Handles the submission of user stories, saving them to a CSV file - KW
    @FXML
    public void handleSubmitButtonAction(ActionEvent event) {
        
    	boolean readySubmit = true;
    	for(int i = 0; i < userStoryListView.getItems().size(); i++)
    	{
    		UserStory curr = userStoryListView.getItems().get(i);
    		if(curr.getPoints() == -1)
    		{
    			readySubmit = false;
    		}
    	}
    	
    	if(readySubmit)
    	{
	        userStories.clear(); // Clears the existing user stories list to avoid duplicates
	        userStories.addAll(userStoryListView.getItems());
	        task.stories = userStories;
	        saveUserStoriesToCSV(userStories, "userStories.csv"); // Save to CSV
	        showConfirmationMessage("User stories have been saved.");
	        
	        int len = userStories.size();
	        int idx = tasks.indexOf(task);
	        for(int i = 0; i < len; i++)
	        {
	        	String name = userStories.get(i).getTitle();
	        	String description = userStories.get(i).getDescription();
	        	int points = userStories.get(i).getPoints();
	        	task.addStory(name, description, points);
	        }
	        tasks.set(idx, task);
	        
	        try {
				FXMLLoader load = new FXMLLoader(getClass().getResource("PickCard.fxml"));
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene scene = new Scene(load.load());
				PickCardController ctrl = load.getController();
				ctrl.setAccess(user);
				ctrl.setProjects(projects);
				ctrl.setProject(project);
				ctrl.setTask(task);
				ctrl.setEstimates(estimates);
				ctrl.setTasks(taskNames, tasks);
				ctrl.setLabel();
				stage.setScene(scene);
				stage.setTitle("Pick a card!");
				stage.show();
				
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
    	}
    	else
    	{
    		showErrorMessage("Please configure all estimates before submitting!");
    	}
        
    }
    
    
    // Saves a list of UserStory objects to a specified CSV file
    private void saveUserStoriesToCSV(List<UserStory> userStories, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            // Writing header
            writer.write("Title,Description,Points\n");

            for (UserStory story : userStories) {
                writer.write(convertToCSV(story) + "\n");
            }
        } catch (IOException e) {
            showErrorMessage("Error writing to CSV file: " + e.getMessage());
        }
    }

    // Method for to prevent special character errors - KW  
    private String convertToCSV(UserStory story) {
        //  Using the UserStory methods getTitle(), getDescription(), and getPoints()
        // Handles any special characters, like commas in titles or descriptions
        return escapeSpecialCharacters(story.getTitle()) + ","
             + escapeSpecialCharacters(story.getDescription()) + ","
             + story.getPoints();
    }
    // Method for to prevent special character errors - KW
    private String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " "); // Replace newlines with spaces
        if (data.contains(",") || data.contains("\"") || data.contains("\n")) {
            data = data.replace("\"", "\"\""); // Replace quotes with double quotes
            escapedData = "\"" + data + "\""; // Quote the entire field
        }
        return escapedData;
    }

    // Method to establish access to maintain reference to user - MJ
    public void setAccess(Employee empType)
	{
		user = empType;

	}
	
	
	
}
