package application;

import java.util.*;

//Class to represent the collection of projects for the client - MJ
public class ProjectDataBase
{
	ArrayList<Project> projects;
	ArrayList<String> projectNames;
	int size;
	
	//Default constructor for the class - MJ
	public ProjectDataBase()
	{
		projects = new ArrayList<Project>();
		projectNames = new ArrayList<String>();
		size = 0;
	}
	
	//Method to add a new project to the database - MJ
	public void addProject(Project project)
	{
		projects.add(project);
		projectNames.add(project.getName());
		size ++;
	}
	
	//Method to retrieve a project from the database - MJ
	public Project getProject(String name)
	{
		return projects.get(projectNames.indexOf(name));
	}
	
	public void updateProject(Project project)
	{
		int idx = projectNames.indexOf(project.getName());
		projects.set(idx, project);
	}
	
	//Method to check if the database contains a project by name - MJ
	public boolean containsName(String name)
	{
		for(int i = 0; i < size; i ++)
		{
			if(projectNames.get(i).equals(name))
			{
				return true;
			}
		}
		return false;
	}
	
	public int getSize()
	{
		return size;
	}
	
}

