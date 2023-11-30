package application;

//Generic class for Effort Log = MJ
public class EffortLog
{
	public String time;
	public int defects;
	public String name;
	
	//Default constructor for a log with null fields - MJ
	public EffortLog()
	{
		
	}
	
	//Constructor to create an effort log, taking in all necessary fields to
	//populate the log - MJ
	public EffortLog(String time, int defects, String name)
	{
		this.time = time;
		this.defects = defects;
		this.name = name;
	}
	
	//Setter method to initialize the fields of a log - MJ
	public void setData(String time, int defects, String name)
	{
		this.time = time;
		this.defects = defects;
		this.name = name;
	}
}