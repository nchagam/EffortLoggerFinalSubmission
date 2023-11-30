package application;

//import java.net.URL;
//import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AccessDeniedController   
{
	//Maintains reference to the user and stored logs - MJ
	private Employee user;
	//Fields for accessType and neededAccess which will be
	//initialized dependent on the user
	private String accessType;
	private String neededAccess;
	private ProjectDataBase projects;
	
	
	
	//Fields for the 2 labels and 1 button in the stage - MJ
	@FXML
	private Label accessLabel;
	
	@FXML
	private Label neededLabel;
	
	@FXML
	private Button back;
	
	//Setter method to collect previous reference to the project database - MJ
	public void setProjects(ProjectDataBase projects)
	{
		this.projects = projects;
	}

	//EventHandler for the back button, will take the user back to the
	//main menu and pass back the references to the employee and logs - MJ
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
	
	//Setter method for the label on the access denied page 
	//this is dependent on access type and needed access- MJ
	@FXML
	public void setLabel()
	{
		accessLabel.setText("Access Type: " + accessType);
		neededLabel.setText("You need to be a: " + neededAccess);
	}

	//Setter method for the accessType and neededAccess
	//for this page - MJ
	public void setAccess(String access, String needed)
	{
		accessType = access;
		neededAccess = needed;
	}
	
	//Determine accessType based on the type of Employee using
	//the software - MJ
	public void setAccess(Employee empType, String needed)
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
		neededAccess = needed;
	}
	
}