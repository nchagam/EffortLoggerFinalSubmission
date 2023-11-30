package application;

import javafx.stage.*;
import javafx.scene.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;

import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenuController {
	//Fields for Employee class and LogCollection that will be
	//passed around through the various controllers - MJ
	private Employee user;
	public ProjectDataBase projects;
	//String for accessType when displayed
	private String accessType;	

	//Setter method to take in the reference to the LogCollection - MJ
	
	//Setter method to take in the reference to the project database - MJ
	public void setProjects(ProjectDataBase projects)
	{
		this.projects = projects;
	}
	
	//FXML fields for the 3 Buttons on the main menu - MJ
	@FXML
	private Button ppButton;
	
	@FXML
	private Button clockButton;
	
	@FXML
	private Button viewDat;
	
	//EventHandler for the Planning Poker button - MJ
	//This checks user access based on employee type and takes them to
	//Planning Poker or to the Access Denied Page - MJ
	@FXML
	public void planningPokerPane(ActionEvent event) throws IOException
	{
		if(accessType.equals("Software Engineer")||accessType.equals("Quality Assurance")) 
		{
			try {
				FXMLLoader load = new FXMLLoader(getClass().getResource("SelectProject.fxml"));
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene scene = new Scene(load.load());
				SelectProjectController ctrl = load.getController();
				ctrl.setAccess(user);
				ctrl.setProjects(projects);
				ctrl.setFlag('p');
				ctrl.setLabel();
				stage.setScene(scene);
				stage.setTitle("Select a Project to View");
				stage.show();

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		else
		{
			try {
				FXMLLoader load = new FXMLLoader(getClass().getResource("AccessDenied.fxml"));
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene scene = new Scene(load.load());
				AccessDeniedController ctrl = load.getController();
				ctrl.setAccess(user, "Software Engineer or Quality Assurance Engineer");
				ctrl.setLabel();
				ctrl.setProjects(projects);
				stage.setScene(scene);
				stage.setTitle("Access Denied!");
				stage.show();
				
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
	}

	//EventHandler for the Effort Logger Clock button - MJ
	//This checks user access based on employee type and takes them to
	//Effort Logger Clock or to the Access Denied Page - MJ
	public void openClockPane(ActionEvent event) throws IOException
	{
		if(accessType.equals("Software Engineer")) 
		{
			try {
				FXMLLoader load = new FXMLLoader(getClass().getResource("SelectProject.fxml"));
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene scene = new Scene(load.load());
				SelectProjectController ctrl = load.getController();
				ctrl.setAccess(user);
				ctrl.setProjects(projects);
				ctrl.setFlag('c');
				ctrl.setLabel();
				stage.setScene(scene);
				stage.setTitle("Select a Project to View");
				stage.show();
				
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		else
		{
			try {
				FXMLLoader load = new FXMLLoader(getClass().getResource("AccessDenied.fxml"));
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene scene = new Scene(load.load());
				AccessDeniedController ctrl = load.getController();
				ctrl.setAccess(user, "Software Engineer");
				ctrl.setLabel();
				ctrl.setProjects(projects);
				stage.setScene(scene);
				stage.setTitle("Access Denied!");
				stage.show();
				
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		
	}
	
	//EventHandler for the viewData button - MJ
	//This passes along the reference to the employee which will be
	//used to determine which data to show- MJ
	public void viewData(ActionEvent event) throws IOException
	{
		try {
			FXMLLoader load = new FXMLLoader(getClass().getResource("SelectProject.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(load.load());
			SelectProjectController ctrl = load.getController();
			ctrl.setAccess(user);
			ctrl.setProjects(projects);
			ctrl.setFlag('v');
			ctrl.setLabel();
			stage.setScene(scene);
			stage.setTitle("Select a Project to View");
			stage.show();
			
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	//Setter method for the accessType field - MJ
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
