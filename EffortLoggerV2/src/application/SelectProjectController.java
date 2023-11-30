package application;

import java.net.URL;
import java.util.ResourceBundle;

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

//Controller for the Select Project page - MJ
public class SelectProjectController 
{
	//Reference fields, passed in to guide the flow - MJ
	private Employee user;
	public String accessType;
	private ProjectDataBase projects;
	private char flag;
	private boolean sideWindow;
	
	//FXML fields for the Labels, Buttons and TextFields - MJ
	@FXML
	private Label accessLabel;
	@FXML
	private ComboBox nameMenu = new ComboBox<String>();
	@FXML
	private Button back;
	@FXML
	private Button submit;
	@FXML
	private TextField newProj;

	
	//Setter method to take in the reference to the project database - MJ
	public void setProjects(ProjectDataBase projects)
	{
		this.projects = projects;
	}
	
	public void setFlag(char flag)
	{
		this.flag = flag;
	}
	
	//Method to initialize the label and combo box- MJ
	@FXML
	public void setLabel()
	{
		newProj.setVisible(flag == 'p');
		accessLabel.setText("Access Type: " + accessType);
		ArrayList<String> list = new ArrayList<String>();
		list.add("no selection made");
		for(int i = 0; i < projects.projectNames.size(); i++)
		{
			list.add(projects.projectNames.get(i));
		}
		nameMenu.setItems(FXCollections.observableArrayList(list));
		nameMenu.getSelectionModel().select(0);
		sideWindow = false;
	}
	
	//Event handler for a User's query for a project, it opens the SelectTask
	//page and sets the appropriate fields so the user can search for a task- MJ
	@FXML
	public void nameEntered(ActionEvent event)
	{
		String name = "";
		if(newProj.getText().length() != 0)
		{
			
			name = (String) newProj.getText();
			Project newProject = new Project();
			newProject.setName(name);
			projects.addProject(newProject);
			
			if(flag != 'p')
			{
			
				try {
					FXMLLoader load = new FXMLLoader(getClass().getResource("SelectTask.fxml"));
					Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					Scene scene = new Scene(load.load());
					SelectTaskController ctrl = load.getController();
					ctrl.setAccess(user);
					ctrl.setProjects(projects);
					ctrl.setProj(projects.getProject(name));
					ctrl.setFlag(flag);
					ctrl.setLabel();
					stage.setScene(scene);
					stage.setTitle("Select a task to view");
					stage.show();
					
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			}
			else
			{
				try {
					FXMLLoader load = new FXMLLoader(getClass().getResource("PlanPokerPane.fxml"));
					Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					Scene scene = new Scene(load.load());
					PlanningPokerController ctrl = load.getController();
					ctrl.setAccess(user);
					ctrl.setProjects(projects);
					ctrl.setProject(projects.getProject(name));
					//ctrl.setTask(proj.getTask(name));
					ctrl.setLabel();
					stage.setScene(scene);
					stage.setTitle("Planning Poker!");
					stage.show();
					
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			}
		}
		else
		{
			name = (String) nameMenu.getValue();
		
			if(projects.containsName(name))
			{
				if(flag != 'p')
				{
				
					try {
						FXMLLoader load = new FXMLLoader(getClass().getResource("SelectTask.fxml"));
						Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
						Scene scene = new Scene(load.load());
						SelectTaskController ctrl = load.getController();
						ctrl.setAccess(user);
						ctrl.setProjects(projects);
						ctrl.setProj(projects.getProject(name));
						ctrl.setFlag(flag);
						ctrl.setLabel();
						stage.setScene(scene);
						stage.setTitle("Select a task to view");
						stage.show();
						
				    } catch (Exception e) {
				        e.printStackTrace();
				    }
				}
				else
				{
					try {
						FXMLLoader load = new FXMLLoader(getClass().getResource("PlanPokerPane.fxml"));
						Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
						Scene scene = new Scene(load.load());
						PlanningPokerController ctrl = load.getController();
						ctrl.setAccess(user);
						ctrl.setProjects(projects);
						ctrl.setProject(projects.getProject(name));
						//ctrl.setTask(proj.getTask(name));
						ctrl.setLabel();
						stage.setScene(scene);
						stage.setTitle("Planning Poker!");
						stage.show();
						
				    } catch (Exception e) {
				        e.printStackTrace();
				    }
				}
			}
		}
	}
	
	//Method to return the user to the Main Menu if they hit the back button, this changes
	//the fxml file and controllers being loaded - MJ
	public void goBack(ActionEvent event)
	{
		if(!sideWindow)
		{
			try {
				FXMLLoader load = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene scene = new Scene(load.load());
				MainMenuController ctrl = load.getController();
				ctrl.setAccess(user);
				ctrl.setProjects(projects);
				stage.setScene(scene);
				stage.setTitle("Main Menu of Effort Logger");
				stage.show();
			} catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		else
		{
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		    stage.close();
		}
	}
	
	public void isSideWindow()
	{
		sideWindow = true;
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