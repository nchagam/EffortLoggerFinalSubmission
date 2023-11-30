package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.*;

//Controller for the View Data page - MJ
public class LogDataController 
{
	
	//Same fields being saved as in the Main Menu Controller - MJ
	public Employee user;
	public String accessType;
	private ProjectDataBase projects;
	private Project proj;
	private Task task;
	private EffortLog log;
	private int logIdx;
	
	
	
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
	
	public void setProjects(ProjectDataBase projects)
	{
		this.projects = projects;
	}
	public void setProject(Project proj)
	{
		this.proj = proj;
	}
	public void setTask(Task task)
	{
		this.task = task;
	}
	public void setLog(EffortLog log)
	{
		this.log = log;
	}
	public void setLabel()
	{
		System.out.print(false);
	}
	public void setLogIdx(int idx)
	{
		this.logIdx = idx;
	}
	
}