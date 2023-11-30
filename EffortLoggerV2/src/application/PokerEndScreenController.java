package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;

//import java.net.URL;
//import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class PokerEndScreenController   
{
	//Maintains reference to the user and stored logs - MJ
	private Employee user;
	//Fields for accessType and neededAccess which will be
	//initialized dependent on the user
	private String accessType;
	private ProjectDataBase projects;
	private Project project;
	private Task task;
	private ArrayList<Task> taskList;
	private ArrayList<String> taskNames;
	private ArrayList<Integer> estimates;
	private int finalEstimate;
	
	
	
	//Fields for the 2 labels and 1 button in the stage - MJ
	@FXML
	private Label accessLabel;
	
	@FXML
	private Label estimateLabel;
	
	@FXML
	private ListView taskBreakdown;
	
	@FXML
	private Button back;
	
	@FXML
	private Button save;
	
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

	//EventHandler for the back button, will take the user back to the
	//main menu and pass back the references to the employee and logs - MJ
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
			ctrl.setEstimates(estimates);
			ctrl.setTasks(taskNames, taskList);
			ctrl.updateTask(taskList.get(0), estimates.get(0));
			ctrl.setLabel();
			stage.setScene(scene);
			stage.setTitle("Planning Poker!");
			stage.show();
			
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void saveSubmit(ActionEvent event)
	{
		for(int i = 0; i < taskList.size(); i++)
		{
			projects.getProject(project.getName()).addTask(taskList.get(i));
		}
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
	
	//Setter method for the label on the access denied page 
	//this is dependent on access type and needed access- MJ
	@FXML
	public void setLabel()
	{
		accessLabel.setText("Access Type: " + accessType);
		
		ArrayList<String> menuItems = new ArrayList<String>();
		for(int i = 0; i < taskList.size(); i++)
		{
			menuItems.add("Story: " + taskNames.get(i) + " - Estimate: " + estimates.get(i));
		}
		taskBreakdown.setItems(FXCollections.observableArrayList(menuItems));
		taskBreakdown.getSelectionModel().select(0);
		taskBreakdown.getSelectionModel().clearSelection();
		
		finalEstimate = 0;
		for(int i = 0; i < estimates.size(); i++)
		{
			finalEstimate += estimates.get(i);
		}
		finalEstimate = finalEstimate/estimates.size();
		estimateLabel.setText("Final Effort Estimate: " + finalEstimate);
	}

	//Setter method for the accessType and neededAccess
	//for this page - MJ
	public void setAccess(String access)
	{
		accessType = access;
	}
	
	//Determine accessType based on the type of Employee using
	//the software - MJ
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