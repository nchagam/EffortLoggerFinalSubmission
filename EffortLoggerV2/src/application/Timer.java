package application;

//Timer class that I made for my EffortLogger Clock - MJ
public class Timer
{
	//3 Fields for hours, minutes, and seconds - MJ
	private int hours;
	private int minutes;
	private int seconds;
	
	//Default constructor sets these fields to 0 - MJ
	public Timer()
	{
		hours = 0;
		minutes = 0;
		seconds = 0;
	}
	
	//Method to return the time in clock format - MJ
	public String getTime()
	{
		String time = Integer.toString(hours) + ":" + Integer.toString(minutes) + ":" + Integer.toString(seconds);
		return time;
	}
	
	//Method to increment time by one second - MJ
	public void oneSecond()
	{
		if(seconds < 59)
		{
			seconds ++;
		}
		else if(minutes < 59)
		{
			seconds = 0;
			minutes++;
			
		}
		else
		{
			seconds = 0;
			minutes = 0;
			hours++;
			
		}
	}
	
}