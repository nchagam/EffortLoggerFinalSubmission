package application;

import javafx.stage.*;
import javafx.scene.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;

import javafx.scene.control.Button;

import java.io.IOException;

public class FirstPageController{
	// Stage, Scene, and Logs are passed along between controllers - MJ
	private Scene scene;
	private Stage stage;
	public ProjectDataBase projects;
	private PasswordDatabase passData;
	private EmployeeDataBase employees;
	
	//Setter for the various databases we need - MJ
	public void setPassData(PasswordDatabase dat, EmployeeDataBase emp)
	{
		passData = dat;
		employees = emp;
	}
	
	//Setter method to take in the reference to the project database - MJ
	public void setProjects(ProjectDataBase projects)
	{
		this.projects = projects;
	}
	
	
	//Fields for the 3 buttons on the main menu
	@FXML
	private Button softEng;
	
	@FXML
	private Button admin;
	
	@FXML
	private Button qa;
	
	//Setter method to pass along reference to the database of logs - MJ
	
	//EventHandler to take the user to the software engineer password verification page - MJ
	public void softEngView(ActionEvent event) throws IOException
	{
		try {
			//set stage and scene - MJ
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Password.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(fxmlLoader.load());
			stage.setTitle("Input Password");
			//set fields in the controller for the next page - MJ
			passController control = fxmlLoader.getController();
			control.setPassData(passData, employees);
			control.setProjects(projects);
			SoftwareEngineer user = new SoftwareEngineer();
			control.setUser(user);
			control.setType('s');
			//show the new stage - MJ
			stage.setScene(scene);
			stage.show();
			
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	//EventHandler to take the user to the administrator password verification page - MJ
	public void adminView(ActionEvent event) throws IOException
	{
		//same logic as softEngView method - MJ
		try {
			//set stage and scene - MJ
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Password.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(fxmlLoader.load());
			stage.setTitle("Input Password");
			//set fields in the controller for the next page - MJ
			passController control = fxmlLoader.getController();
			control.setPassData(passData, employees);
			Administrator user = new Administrator();
			control.setUser(user);
			control.setProjects(projects);
			control.setType('a');
			//show the new stage - MJ
			stage.setScene(scene);
			stage.show();
			
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	//EventHandler to take the user to the qa engineer password verification page - MJ
	public void qaEngineerView(ActionEvent event) throws IOException
	{
		//same logic as softEngView method - MJ
		try {
			//set stage and scene - MJ
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Password.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(fxmlLoader.load());
			stage.setTitle("Input Password");
			//set fields in the controller for the next page - MJ
			passController control = fxmlLoader.getController();
			control.setPassData(passData, employees);
			QAEngineer user = new QAEngineer();
			control.setUser(user);
			control.setProjects(projects);
			control.setType('q');
			//show the new stage - MJ
			stage.setScene(scene);
			stage.show();
			
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
}
