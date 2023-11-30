package application;

import java.net.URL;

import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.*;

//Controller for the View Task Data page - MJ
public class ViewTaskDataController 
{
	
	//Reference fields, passed in to guide the flow - MJ
	public Employee user;
	public String accessType;
	private ProjectDataBase projects;
	private Project proj;
	private Task task;
	private char flag;
	
	//FXML fields for the Labels, Buttons and TextFields - MJ
	@FXML
	private Text access;
	@FXML
	private Text taskName;
	@FXML
	private Button back;
	@FXML
	private ComboBox<String> storyMenu = new ComboBox<String>();
	@FXML
	private ComboBox<String> logMenu = new ComboBox<String>();

	//Event handler for when the user requests a story - MJ
	public void requestStory(ActionEvent event)
	{
		String desc = (String) storyMenu.getValue();
		storyMenu.getSelectionModel().select(0);
		if(!desc.equals("----------"))
		{
			ArrayList<String> contents = task.returnUserStory(desc);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("User Story:");
	        alert.setHeaderText(contents.get(0));
	        alert.setContentText(contents.get(1) + "\n" + contents.get(2));
	        alert.showAndWait();
			
		}
		
	}
	
	//Event handler for when a user requests an effort log - MJ
	public void requestLog(ActionEvent event)
	{
		String ind = (String) logMenu.getValue();
		logMenu.getSelectionModel().select(0);
		if(!ind.equals("----------"))
		{
			int idx = Integer.parseInt(ind);
			
			ArrayList<String> contents = task.returnEffortLog(user, idx);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Effort Log Contents:");
	        alert.setHeaderText(contents.get(0));
	        alert.setContentText(contents.get(1) + "\n" + contents.get(2));
	        alert.showAndWait();
	
		}
	}
		
	//Setter method to take in the reference to the Project database - MJ
	public void setProjects(ProjectDataBase projects)
	{
		this.projects = projects;
	}
	
	public void setFlag(char flag)
	{
		this.flag = flag;
	}
	
	//Method to set the labels on the page - MJ
	@FXML
	public void setLabel()
	{
		access.setText("Access Type: " + accessType);
		taskName.setText("Task Name: " + task.name);
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("----------");
		for(int i = 0; i < task.storyDesc.size(); i++)
		{
			list.add(task.storyDesc.get(i));
		}
		storyMenu.setItems(FXCollections.observableArrayList(list));
		storyMenu.getSelectionModel().select(0);
		
		
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("----------");
		for(int i = 1; i < task.logSize+1; i++)
		{
			list2.add(Integer.toString(i));
		}
		
		
		logMenu.setItems(FXCollections.observableArrayList(list2));
		logMenu.getSelectionModel().select(0);
		
		
	}
	
	//setter method to initialize the project when controller is opened - MJ
	public void setProj(Project proj)
	{
		this.proj = proj;
	}
	
	//setter method to initialize the task when controller is opened - MJ
	public void setTask(Task task)
	{
		this.task = task;
	}
	
	//Method to go back to the task selection page - MJ
	@FXML
	public void goBack(ActionEvent event)
	{
		try {
			FXMLLoader load = new FXMLLoader(getClass().getResource("SelectTask.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(load.load());
			SelectTaskController ctrl = load.getController();
			ctrl.setAccess(user);
			ctrl.setProjects(projects);
			ctrl.setProj(proj);
			ctrl.setFlag(flag);
			ctrl.setLabel();
			stage.setScene(scene);
			stage.setTitle("Select a task to view");
			stage.show();
			
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
	//Setter method for the accessType of employee - MJ
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