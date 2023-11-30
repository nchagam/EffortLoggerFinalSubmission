package application;
import java.util.ArrayList;

//import javafx.event.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
//import javafx.application.Application;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.*;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.BorderPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.scene.control.*;

//import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

//import java.io.IOException;

//Controller for the Effort Logger Clock - MJ
public class PickCardController  
{
	private Employee user;
	
	private ProjectDataBase projects;
	private Project project;
	//Fields for the text that will be displayed - MJ
	private String accessType;
	private int defects;
	//Boolean to determine clock on/clock off - MJ
	private boolean clockOn;
	private Task task;
	private ArrayList<String> taskNames;
	private ArrayList<Task> tasks;
	private ArrayList<Integer> estimates;
	private ArrayList<Integer> cardValues;
	private int estimate;
	
	
	
	//Setter method to take in the reference to the project database - MJ
	public void setProjects(ProjectDataBase projects)
	{
		this.projects = projects;
	}
	
	public void setProject(Project project)
	{
		this.project = project;
	}
	
	//Setter method to take in the reference to the task - MJ
	public void setTask(Task task)
	{
		this.task = task;
	}
	public void setEstimates(ArrayList<Integer> estimates)
	{
		this.estimates = estimates;
	}
	
	public void setTasks(ArrayList<String> taskNames, ArrayList<Task> tasks)
	{
		this.taskNames = taskNames;
		this.tasks = tasks;
	}
	
	//Fields for the 4 buttons, 2 labels, and 1 text 
	//that will be displayed - MJ
	@FXML
	private Label accessLabel;
	@FXML
	private Label cardChoices;
	@FXML
	private Button back;
	@FXML
	private Button calculateEstimate;

	
	@FXML
	private RadioButton b1;
	@FXML
	private RadioButton b2;
	@FXML
	private RadioButton b3;
	@FXML
	private RadioButton b5;
	@FXML
	private RadioButton b8;
	@FXML
	private RadioButton b13;
	@FXML
	private Button add;
	
	//Method to set label to display the user's accessType -MJ
	@FXML
	public void setLabel()
	{
		accessLabel.setText("Access Type: " + accessType);
		cardValues = new ArrayList<Integer>();
		
	}
	
	@FXML
	public void addCard(ActionEvent event)
	{
		int choice = 0;
		if(b1.isSelected())
		{
			cardValues.add(1);
			choice = 1;
			b1.setSelected(false);
		}
		else if(b2.isSelected())
		{
			cardValues.add(2);
			choice = 2;
			b2.setSelected(false);
		}
		else if(b3.isSelected())
		{
			cardValues.add(3);
			choice = 3;
			b3.setSelected(false);
		}
		else if(b5.isSelected())
		{
			cardValues.add(5);
			choice = 5;
			b5.setSelected(false);
		}
		else if(b8.isSelected())
		{
			cardValues.add(8);
			choice = 8;
			b8.setSelected(false);
		}
		else if(b13.isSelected())
		{
			cardValues.add(13);
			choice = 13;
			b13.setSelected(false);
		}
		if(choice != 0) {
			cardChoices.setText("Cards Selected: " + cardValues.size());
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Success!");
	        alert.setHeaderText("Card Added: " + choice);
	        alert.setContentText("If there are any remaining group members that have not selected a card, repeat the process.");
	        alert.showAndWait();
		}
	}
	
	
	
	@FXML
	public void calcEstimate(ActionEvent event)
	{
		if(cardValues.size() != 0)
		{
			int sum = 0;
			for(int i = 0; i < cardValues.size(); i++)
			{
				sum += cardValues.get(i);
			}
			estimate = sum/(cardValues.size());
			try {
				FXMLLoader load = new FXMLLoader(getClass().getResource("PlanPokerPane.fxml"));
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene scene = new Scene(load.load());
				PlanningPokerController ctrl = load.getController();
				ctrl.setAccess(user);
				ctrl.setProjects(projects);
				ctrl.setProject(project);
				ctrl.setEstimates(estimates);
				ctrl.setTasks(taskNames, tasks);
				ctrl.updateTask(task, estimate);
				ctrl.setLabel();
				stage.setScene(scene);
				stage.setTitle("Planning Poker!");
				stage.show();
				
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		else
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Error!");
	        alert.setHeaderText("Failed to Calculate Estimate");
	        alert.setContentText("Please make sure everybody in the group picks a card first!");
	        alert.showAndWait();
		}
	}
	
	@FXML
	public void press1(ActionEvent event)
	{
		b2.setSelected(false);
		b3.setSelected(false);
		b5.setSelected(false);
		b8.setSelected(false);
		b13.setSelected(false);
	}
	@FXML
	public void press2(ActionEvent event)
	{
		b1.setSelected(false);
		b3.setSelected(false);
		b5.setSelected(false);
		b8.setSelected(false);
		b13.setSelected(false);
	}
	@FXML
	public void press3(ActionEvent event)
	{
		b1.setSelected(false);
		b2.setSelected(false);
		b5.setSelected(false);
		b8.setSelected(false);
		b13.setSelected(false);
	}
	@FXML
	public void press5(ActionEvent event)
	{
		b1.setSelected(false);
		b2.setSelected(false);
		b3.setSelected(false);
		b8.setSelected(false);
		b13.setSelected(false);
	}
	@FXML
	public void press8(ActionEvent event)
	{
		b1.setSelected(false);
		b2.setSelected(false);
		b3.setSelected(false);
		b5.setSelected(false);
		b13.setSelected(false);
	}
	@FXML
	public void press13(ActionEvent event)
	{
		b1.setSelected(false);
		b2.setSelected(false);
		b3.setSelected(false);
		b5.setSelected(false);
		b8.setSelected(false);
	}
	
	
	//Method to set the field of accessType based on user
	//class type - MJ
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