package application;
import java.util.*;

//class for collection of logs/stories for a task - MJ
public class Task
{
	//Basic Fields of a Task - MJ
	ArrayList<EffortLog> logs;
	public ArrayList<UserStory> stories;
	ArrayList<String> storyDesc;
	String name;
	int logSize;
	int storySize;
	int estimate;
	
	//Default constructor for LogCollection - MJ
	public Task()
	{
		logs = new ArrayList<EffortLog>();
		stories = new ArrayList<UserStory>();
		storyDesc = new ArrayList<String>();
		logSize = 0;
		name = "";
		storySize = 0;
	}
	
	//Method to add a log, taking in its contents as parameters - MJ
	public void addLog(String time, int defects, String name)
	{
		EffortLog log = new EffortLog();
		log.setData(time, defects, name);
		logs.add(log);
		logSize++;
	}
	
	//Method to add a story, taking in its contents as parameters - MJ
	public void addStory(String description, String story, int points)
	{
		UserStory newStory = new UserStory(description, story, -1);
		newStory.setPoints(points);
		stories.add(newStory);
		storyDesc.add(newStory.getDescription());
		storySize++;
	}
	
	public int getStorySize()
	{
		return storySize;
	}
	
	//setter method for the name of a task
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setEstimate(int estimate)
	{
		this.estimate = estimate;
	}
	
	//method to retrieve the name of a task
	public String getName()
	{
		return name;
	}
	
	//Method to return  log data to the user, taking in their employee type and
	//log index, using this to determine whether or not the data is redacted
	//and give them the proper data - MJ
	public ArrayList<String> returnEffortLog(Employee user, int idx)
	{	
		if(idx <= logSize && idx > 0) 
		{
			ArrayList<String> data = new ArrayList<String>();
			if(user.getClass() == SoftwareEngineer.class)
			{
				data.add("Name: " + logs.get(idx-1).name);
			}
			else
			{
				EncryptionPrototype encrypt = new EncryptionPrototype();
				data.add("Name : redacted");
			}
			data.add("Time : " + logs.get(idx-1).time);
			data.add("Defects : " + logs.get(idx-1).defects);
			return data;
		}
		return null;
	}
	
	//Method to return user story data to the user, taking the description
	//as a parameter and finding the story based on this information - MJ
	public ArrayList<String> returnUserStory(String description)
	{
		ArrayList<String> storyData = new ArrayList<String>();
		UserStory chosen = stories.get(storyDesc.indexOf(description));
		storyData.add("Description: " + chosen.getDescription());
		storyData.add("Story: " + chosen.getTitle());
		storyData.add("Points Assigned: " + Integer.toString(chosen.getPoints()));
		return storyData;
	}
	
	//Boolean method to check if a story exists in the task - MJ
	public boolean containsStory(String description)
	{
		for(int i = 0; i < storySize; i++)
		{
			if(stories.get(i).getDescription().equals(description))
			{
				return true;
			}
		}
		return false;
	}
}