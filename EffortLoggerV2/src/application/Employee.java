package application;
import java.util.ArrayList;

//Generalized Employee Class - MJ
public class Employee
{
	//Associated string with an employee - MJ
	protected String employeeNumber;
	public String name;
	protected String password;
	protected ArrayList<Integer> groups;
	protected ArrayList<String> permissions;

	
	//Default Constructor for Employee - MJ
	public Employee()
	{
		permissions = new ArrayList<String>();
	}
	
	//Method to return permissions of the employee
	public ArrayList<String> getPermissions()
	{
		return permissions;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	
	
	
}