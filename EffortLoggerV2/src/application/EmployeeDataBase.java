package application;

import java.util.ArrayList;

//class to store the database of all employees by type - TD
class EmployeeDataBase
{
	//ArrayLists for each type of employee
	ArrayList<String> seNames = new ArrayList<String>();
	ArrayList<String> qaNames = new ArrayList<String>();
	ArrayList<String> adminNames = new ArrayList<String>();
	
	public EmployeeDataBase()
	{
		
	}
	
	//Method to check if employee is Software Engineer - TD
	public boolean isSE(String name)
	{
		if(seNames.contains(name))
		{
			return true;
		}
		return false;
	}
	
	//Method to check if employee is QA Engineer - TD
	public boolean isQA(String name)
	{
		if(qaNames.contains(name))
		{
			return true;
		}
		return false;
	}
	
	//Method to check if employee is Administrator- TD
	public boolean isAdmin(String name)
	{
		if(adminNames.contains(name))
		{
			return true;
		}
		return false;
	}
}