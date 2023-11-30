package application;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;

//Controller for the Planning Poker page - MJ
public class PlanningPokerController   
{
	//Same fields being stored as in Main Menu controller - MJ
	private Employee user;
	private String accessType;
	private ProjectDataBase projects;
	private Project project;
	public String projName;
	private Task task;
	private ArrayList<Task> taskList;
	private ArrayList<String> taskNames;
	private ArrayList<Integer> estimates;
	
	
	//FXML fields for button and label - MJ
	@FXML
	private Button addStory;
	@FXML
	private Label accessLabel;
	@FXML
	private Button back;
	@FXML
	private ComboBox<String> tasks = new ComboBox<String>();
	@FXML
	private TextField newTask;
	@FXML
	private Button submit;
	

	
	//Setter method to take in the reference to the project database - MJ
	public void setProjects(ProjectDataBase projects)
	{
		this.projects = projects;
		taskNames = new ArrayList<String>();
		taskList = new ArrayList<Task>();
		estimates = new ArrayList<Integer>();
	}
	public void setEstimates(ArrayList<Integer> estimates)
	{
		this.estimates = estimates;
	}
	public void setTask(Task task)
	{
		this.task = task;
	}
	
	public void setProject(Project project)
	{
		this.project = project;
	}
	
	public void setTasks(ArrayList<String> taskNames, ArrayList<Task> tasks)
	{
		this.taskNames = taskNames;
		this.taskList = tasks;
	}
	
	@FXML
	public void nextPage(ActionEvent event)
	{
		try {
			FXMLLoader load = new FXMLLoader(getClass().getResource("AddUserStories.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(load.load());
			AddUserStoriesController ctrl = load.getController();
			ctrl.setAccess(user);
			ctrl.setProjects(projects);
			Platform.runLater(() -> {
			    ctrl.setProject(project);
			});
			ctrl.setTask(task);
			ctrl.setTasks(taskNames, taskList);
			stage.setScene(scene);
			stage.setTitle("Add User Stories!");
			stage.show();
			
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void check()
	{
		System.out.println(this.project == null);
	}
	
	//Setter method for the label displaying employee access type - MJ
	@FXML
	public void setLabel()
	{
		accessLabel.setText("Access Type: " + accessType);
		
	}
	
	//EventHandler for the back button to take user to main - MJ
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
			stage.setTitle("Main Menu Effort Logger");
			stage.show();
			
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@FXML
	public void addTask(ActionEvent event)
	{
		String taskChosen = newTask.getText();
		if(!taskChosen.equals(""))
		{
			if(!taskNames.contains(taskChosen))
			{
				taskNames.add(taskChosen);
				Task task = new Task();
				task.setName(taskChosen);
				taskList.add(task);
				estimates.add(-1);
				tasks.getItems().add(taskChosen + " - Estimate: -1");
			}
			else
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setTitle("Error!");
		        alert.setHeaderText("Unable to add task name.");
		        alert.setContentText("Please choose a name you haven't picked yet!");
		        alert.showAndWait();
			}
		}
		newTask.setText("");
		
	}
	
	
	public void updateTask(Task task, int estimate)
	{
		
		int idx = taskNames.indexOf(task.getName());
		taskList.set(idx, task);
		estimates.set(idx, estimate);
		ArrayList<String> menuItems = new ArrayList<String>();
		for(int i = 0; i < taskList.size(); i++)
		{
			menuItems.add(taskNames.get(i) + " - Estimate: " + estimates.get(i));
		}
		tasks.getItems().clear();
		tasks.setItems(FXCollections.observableArrayList(menuItems));
		tasks.getSelectionModel().select(0);
		tasks.getSelectionModel().clearSelection();
		
		//projects.getProject(project.getName()).getTask(task.getName()).setEstimate(estimate);
		
		
		
	}
	
	@FXML
	public void chooseTask(ActionEvent event)
	{
		String name = tasks.getValue();
		int end = name.indexOf('-');
		name = name.substring(0, end-1);
		Task task = taskList.get(taskNames.indexOf(name));
		try {
			FXMLLoader load = new FXMLLoader(getClass().getResource("AddUserStories.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(load.load());
			AddUserStoriesController ctrl = load.getController();
			ctrl.setAccess(user);
			ctrl.setProjects(projects);
			ctrl.setProject(project);
			ctrl.setTask(task);
			ctrl.setTasks(taskNames, taskList);
			ctrl.setEstimates(estimates);
			ctrl.setTask(task);
			stage.setScene(scene);
			stage.setTitle("Add User Stories");
			stage.show();
			
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void endGame(ActionEvent event)
	{
		if(estimates.size() != 0 && !estimates.contains(-1))
		{
			try {
				FXMLLoader load = new FXMLLoader(getClass().getResource("PokerEndScreen.fxml"));
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene scene = new Scene(load.load());
				PokerEndScreenController ctrl = load.getController();
				ctrl.setAccess(user);
				ctrl.setProjects(projects);
				ctrl.setProject(project);
				ctrl.setTasks(taskNames, taskList);
				ctrl.setEstimates(estimates);
				ctrl.setTask(task);
				ctrl.setLabel();
				stage.setScene(scene);
				stage.setTitle("Add User Stories");
				stage.show();
				
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		else
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Error!");
	        alert.setHeaderText("Cannot end the game yet!");
	        alert.setContentText("Please make sure you have added and completed estimating all tasks.");
	        alert.showAndWait();
		}
	}
	
	
	//Setter method for the accessType field used in the
	//access label - MJ
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