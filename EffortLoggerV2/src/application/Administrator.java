package application;
import java.util.ArrayList;

//Class Administrator is a type of Employee - MJ
public class Administrator extends Employee
{
	//Administrator has the same fields as an employee - MJ
	protected String employeeNumber;
	protected String name;
	protected String password;
	protected ArrayList<Integer> groups;
	protected ArrayList<String> permissions;
	
	//Default constructor for Administrator, Admin has
	//the least amount of permissions - MJ
	public Administrator()
	{
		super();
		permissions = new ArrayList<String>();
		permissions.add("ViewData");
	}
	
	
	
	
}