package application;

import java.util.*;

//Class for storing the tasks and employees associated with a project -MJ
public class Project
{
	String name;
	ArrayList<Task> tasks;
	ArrayList<String> taskNames;
	ArrayList<String> employeeNames;
	int size;
	
	//Default constructor for a project - MJ
	public Project()
	{
		name = "";
		tasks = new ArrayList<Task>();
		taskNames = new ArrayList<String>();
		size = 0;
	}
	
	//Setter method for the name of a project - MJ
	public void setName(String name)
	{
		this.name = name;
	}
	
	
	
	//Method to add a task to the project - MJ
	public void addTask(Task task)
	{
		tasks.add(task);
		taskNames.add(task.getName());
		size++;
	}
	
	public void addLog(Task task, String time, int defects, String name)
	{
		int idx = taskNames.indexOf(task.getName());
		task.addLog(time, defects, name);
		tasks.set(idx, task);
	}
	
	public void addUserStory(Task task, String name, String description, int points)
	{
		int idx = taskNames.indexOf(task.getName());
		task.addStory(name, description, points);
		tasks.set(idx, task);
	}
	
	
	//Getter method to return the name of the project - MJ
	public String getName()
	{
		return name;
	}
	
	public int getSize()
	{
		return size;
	}
	
	//Method to check if the project contains a task - MJ
	public boolean containsName(String name)
	{
		for(int i = 0; i < size; i ++)
		{
			if(taskNames.get(i).equals(name))
			{
				return true;
			}
		}
		return false;
	}
	
	//Getter method to return a task based on its name - MJ
	public Task getTask(String name)
	{
		return tasks.get(taskNames.indexOf(name));
	}
	public Task getTask(int idx)
	{
		return tasks.get(idx);
	}
	

}

