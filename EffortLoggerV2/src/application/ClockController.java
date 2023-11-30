package application;
//import javafx.event.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
//import javafx.application.Application;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.*;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.BorderPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.scene.control.*;

//import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

//import java.io.IOException;

//Controller for the Effort Logger Clock - MJßß
public class ClockController  
{
	//Instance of a timer created for the clock - MJ
	private Timer timer = new Timer();
	//References to user and logs maintained - MJ
	private Employee user;
	
	private ProjectDataBase projects;
	//Fields for the text that will be displayed - MJ
	private String accessType;
	private int defects;
	//Boolean to determine clock on/clock off - MJ
	private boolean clockOn;
	private Task task;
	private Project project;
	
	//Setter method to take in the reference to the 
	//log collection that has been passed around - MJ
	
	
	//Setter method to take in the reference to the project database - MJ
	public void setProjects(ProjectDataBase projects)
	{
		this.projects = projects;
	}
	
	//Fields for the 4 buttons, 2 labels, and 1 text 
	//that will be displayed - MJ
	@FXML
	private Label accessLabel;
	@FXML
	private Label defectsLabel;
	@FXML
	private Text time;
	@FXML
	private Button startClock;
	@FXML
	private Button stopClock;
	@FXML
	private Button back;
	@FXML
	private Button defectCounter;
	@FXML
	private Button viewDataButton;
	@FXML
	private Button saveData;
	
	//Timeline that governs the clock - MJ
	//Every second, if the clock is on, then the timer is
	//incremented using the oneSecond() method I created - MJ
	Timeline timeline = new Timeline(
			new KeyFrame(Duration.seconds(1),
					e ->{
						if(clockOn) {
					timer.oneSecond();
					time.setText(timer.getTime());}
					}
			)
			);
			
	//Method to start the clock using the timeline when
	//the start button is pressed - MJ
	@FXML
	public void startClock()
	{
		clockOn = true;
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}
	
	//Method to stop the clock using the timeline when
	//the stop button is pressed - MJ
	@FXML
	public void stopClock()
	{
		clockOn = false;
		timeline.pause();
	}
	
	//Method to initialize the clock which will be called 
	//when opening the Clock page - MJ
	public void initClock()
	{
		clockOn = false;
		time.setText(timer.getTime());
		
		
	}
	
	//Method to set label to display the user's accessType -MJ
	@FXML
	public void setLabel()
	{
		accessLabel.setText("Access Type: " + accessType);
	}
	
	//Method to save the data from the Clock - FH
	@FXML
	public void saveData(ActionEvent event)
	{
		if(!timer.getTime().equals("0:0:0")) {
			project.addLog(task, timer.getTime(), defects, user.name);
			projects.updateProject(project);
			stopClock();
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Success!");
	        alert.setHeaderText("Effort Data Saved!");
	        alert.setContentText("Time: " + timer.getTime() + "\nDefects: " + defects);
	        alert.showAndWait();
	        timer = new Timer();
			time.setText(timer.getTime());
			setDefects();
		}
		else
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Error!");
	        alert.setHeaderText("There is no Data to save!");
	        alert.setContentText("Press \"start\" to start the clock");
	        alert.showAndWait();
		}
	}
	
	
	//Method to go back to Main Menu on the push of the back button
	//and transport the fields of employee and log collection - MJ
	@FXML
	public void goBack(ActionEvent event)
	{
		
		
		try {
			FXMLLoader load = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(load.load());
			MainMenuController ctrl = load.getController();
			ctrl.setAccess(user);
			ctrl.setProjects(projects);
			stage.setScene(scene);
			stage.setTitle("Main Menu of Effort Logger!");
			stage.show();
			
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	//Event handler to open the view data portal in a new window for the user
	//if they select the View Data button - MJ
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
			ctrl.setLabel();
			ctrl.setFlag('v');
			ctrl.isSideWindow();
			stage.setScene(scene);
			stage.setTitle("Project Selection Page!");
			stage.show();
			
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	//Button to increment the defect counter - MJ
	@FXML
	public void count(ActionEvent event)
	{
		defects += 1;
		defectsLabel.setText(Integer.toString(defects));
		
	}
	
	//Method to set the defects label when the page is
	//first being opened - MJ
	public void setDefects()
	{
		defects = 0;
		defectsLabel.setText(Integer.toString(defects));
		
	}
	
	public void setTask(Task task)
	{
		this.task = task;
	}
	
	public void setProject(Project project)
	{
		this.project = project;
	}
	
	//Method to set the field of accessType based on user
	//class type - MJ
	public void setAccess(Employee empType)
	{
		user = empType;
		if(user.getClass() == SoftwareEngineer.class)
		{
			accessType = "Software Engineer";
		}
		else if(user.getClass() == QAEngineer.class)
		{
			accessType = "Quality Assurance";
		}
		else
		{
			accessType =" Administration";
		}
	}
}