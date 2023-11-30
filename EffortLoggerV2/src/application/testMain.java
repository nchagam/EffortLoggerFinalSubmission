package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
//import javafx.scene.layout.*;
//import javafx.scene.control.Button;


public class testMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		try {
			//Initializing the scene for the first page - MJ
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SelectProject.fxml"));
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			SelectProjectController ctrl = fxmlLoader.getController();
			
			//Initialize database - MJ
			ProjectDataBase database = new ProjectDataBase();
			//Initialize project - MJ
			Project proj = new Project();
			
			//Initialize and add tasks with stories and logs to the project - MJ
			Task task = new Task();
			task.addLog("1:15:30", 3, "Marko");
			task.addLog("2:30:0", 5, "Marko");
			task.addLog("0:45:30", 1, "Marko");
			task.addStory("Story 1.1.1", "Example body for Story 1.1.1", 5);
			task.addStory("Story 1.1.2", "Example body for Story 1.1.2", 4);
			task.addStory("Story 1.1.3", "Example body for Story 1.1.3", 6);
			task.setName("Test Task 1.1");
			proj.addTask(task);
			
			task = new Task();
			task.addLog("2:13:45", 3, "Kyndra");
			task.addLog("1:30:15", 2, "Kyndra");
			task.addLog("4:15:20", 5, "Kyndra");
			task.addStory("Story 1.2.1", "Example body for Story 1.2.1", 2);
			task.addStory("Story 1.2.2", "Example body for Story 1.2.2", 9);
			task.setName("Test Task 1.2");
			proj.addTask(task);
			proj.setName("Test Project 1");
			database.addProject(proj);
			
			//initialize and add another project - MJ
			proj = new Project();
			task = new Task();
			task.addLog("3:45:10", 5, "Nikhil");
			task.addLog("1:15:30", 5, "Nikhil");
			task.addLog("0:32:25", 1, "Nikhil");
			task.addStory("Story 2.1.1", "Example body for Story 2.1.1", 8);
			task.addStory("Story 2.1.2", "Example body for Story 2.1.2", 7);
			task.addStory("Story 2.1.3", "Example body for Story 2.1.3", 3);
			task.setName("Test Task 2.1");
			proj.addTask(task);
			
			task = new Task();
			task.addLog("2:13:45", 3, "Finn");
			task.addLog("1:30:15", 2, "Finn");
			task.addLog("4:15:20", 5, "Finn");
			task.addStory("Story 2.2.1", "Example body for Story 2.2.1", 5);
			task.addStory("Story 2.2.2", "Example body for Story 2.2.2", 4);
			task.addStory("Story 2.2.3", "Example body for Story 2.2.3", 6);
			task.setName("Test Task 2.2");
			proj.addTask(task);
			proj.setName("Test Project 2");
			database.addProject(proj);
			
			//Administrator test = new Administrator();
			//QAEngineer test = new QAEngineer();
			SoftwareEngineer test = new SoftwareEngineer();
			test.name = "Marko";
			
			
			//Invoke the setter methods in the first controller to pass in the
			//Database and employee reference MJ
			ctrl.setProjects(database);
			ctrl.setAccess(test);
			ctrl.setLabel();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
