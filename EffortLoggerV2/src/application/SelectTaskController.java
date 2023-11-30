package application;

import java.net.URL;

import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.*;

//Controller for the Select Task page - MJ
public class SelectTaskController 
{
	//Reference fields, passed in to guide the flow - MJ
	private Employee user;
	public String accessType;
	private ProjectDataBase projects;
	private Project proj;
	private char flag;
	
	//FXML fields for the Labels, Buttons and TextFields - MJ
	@FXML
	private ComboBox taskMenu;
	@FXML
	private Label accessLabel;
	@FXML
	private Label rejectLabel;
	@FXML
	private TextField taskName;
	@FXML
	private Button back;
	@FXML
	private Button submit;
	@FXML
	private Label taskNames;
	@FXML
	private TextField newTask;
	
	//Setter method to take in the reference to the Project Database - MJ
	public void setProjects(ProjectDataBase projects)
	{
		this.projects = projects;
	}
	
	public void setFlag(char flag)
	{
		this.flag = flag;
	}
	
	//Method to set the labels - MJ
	@FXML
	public void setLabel()
	{
		newTask.setVisible(flag == 'p');
		accessLabel.setText("Access Type: " + accessType);
		ArrayList<String> list = new ArrayList<String>();
		list.add("no selection made");
		for(int i = 0; i < proj.taskNames.size(); i++)
		{
			list.add(proj.taskNames.get(i));
		}
		taskMenu.setItems(FXCollections.observableArrayList(list));
		taskMenu.getSelectionModel().select(0);
		
	}
	
	//setter method to initialize the project when controller is opened - MJ
	public void setProj(Project proj)
	{
		this.proj = proj;
	}
	
	//Event Handler to go back to the Select Project Page - MJ
	@FXML
	public void goBack(ActionEvent event)
	{
		try {
			FXMLLoader load = new FXMLLoader(getClass().getResource("SelectProject.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(load.load());
			SelectProjectController ctrl = load.getController();
			ctrl.setAccess(user);
			ctrl.setProjects(projects);
			ctrl.setFlag(flag);
			ctrl.setLabel();
			stage.setScene(scene);
			stage.setTitle("Select a Project to View");
			stage.show();
			
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	//Event Handler for when the user makes a selection from the combo box,
	//it takes them to the view task data page and initializes the task so they can
	//extract data from it - MJ
	@FXML
	public void nameEntered(ActionEvent event)
	{
		String name = (String) taskMenu.getValue();
		if(newTask.getText().length() != 0)
		{
			name = newTask.getText();
			Task addedTask = new Task();
			addedTask.setName(name);
			projects.getProject(proj.getName()).addTask(addedTask);
		}
		if(proj.containsName(name))
		{
			if(flag == 'v')
			{
				try {
					FXMLLoader load = new FXMLLoader(getClass().getResource("ViewTaskData.fxml"));
					Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					Scene scene = new Scene(load.load());
					ViewTaskDataController ctrl = load.getController();
					ctrl.setAccess(user);
					ctrl.setProjects(projects);
					ctrl.setProj(proj);
					ctrl.setTask(proj.getTask(name));
					ctrl.setFlag(flag);
					ctrl.setLabel();
					stage.setScene(scene);
					stage.setTitle("Task Data Breakdown!");
					stage.show();
					
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			}
			else if(flag == 'p')
			{
				
				try {
					FXMLLoader load = new FXMLLoader(getClass().getResource("PlanPokerPane.fxml"));
					Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					Scene scene = new Scene(load.load());
					PlanningPokerController ctrl = load.getController();
					ctrl.setAccess(user);
					ctrl.setProjects(projects);
					ctrl.setProject(proj);
					ctrl.setTask(proj.getTask(name));
					ctrl.setLabel();
					stage.setScene(scene);
					stage.setTitle("Planning Poker!");
					stage.show();
					
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			}
			else if(flag == 'c')
			{
				try {
					FXMLLoader load = new FXMLLoader(getClass().getResource("Clock.fxml"));
					Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					Scene scene = new Scene(load.load());
					ClockController ctrl = load.getController();
					ctrl.setAccess(user);
					ctrl.setDefects();
					ctrl.setProjects(projects);
					ctrl.setProject(proj);
					ctrl.setTask(proj.getTask(name));
					ctrl.setLabel();
					stage.setScene(scene);
					stage.setTitle("Effort Logger Clock!");
					stage.show();
					
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			}
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