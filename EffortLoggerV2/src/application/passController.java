package application;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class passController   
{
	Stage stage;
	Scene scene;
	char type;
	//Maintains reference to the user and stored logs - MJ
	private Employee user;

	
	public ProjectDataBase projects;
	private PasswordDatabase passData;
	private EmployeeDataBase employees;
	
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
	
	
	
	public void setType(char s)
	{
		type = s;
	}
	
	public void setUser(Employee user)
	{
		this.user = user;
	}
	
	
	@FXML
	private Button submit;
	
	@FXML
	private TextField userName;
	
	@FXML
	private TextField password;
	
	@FXML
	private Text display;
	
	
	//EventHandler for the submit button to submit a password and go to main - MJ
	@FXML
	public void submit(ActionEvent event)
	{
		String uName = userName.getText();
		String pass = password.getText();
		//If Statement to validate user password - NC
		if(uName != null && pass != null && passData.validate(pass, uName))
		{
			//If statement to validate user type - TD
			if(validateType(uName)) {
				try {
					//set stage and scene - MJ
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
					stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					scene = new Scene(fxmlLoader.load());
					stage.setTitle("Main Menu Effort Logger");
					//set fields in the controller for the next page - MJ
					MainMenuController control = fxmlLoader.getController();
					user.setName(uName);
					control.setAccess(user);
					control.setProjects(projects);
					//show the new stage - MJ
					stage.setScene(scene);
					stage.show();
					
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			}
			else
			{
				display.setText("You are not this type of employee, try again:");
			}
		}
		else
		{
			display.setText("Incorrect UserID or Password, try again:");
		}
	}
	
	//Local method to validate whether the user is logging into the correct view of effort logger
	private boolean validateType(String uID)
	{
		if(user.getClass() == SoftwareEngineer.class)
		{
			return employees.isSE(uID);
		}
		else if(user.getClass() == QAEngineer.class)
		{
			return employees.isQA(uID);
		}
		else
		{
			return employees.isAdmin(uID);
		}
	}

	

	
}