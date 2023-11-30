package application;
import java.util.ArrayList;

//Class Engineer is a type of Employee - MJ
public class Engineer extends Employee
{
	//Engineer has the same fields as an employee - MJ
	protected String employeeNumber;
	protected String name;
	protected String password;
	protected ArrayList<Integer> groups;
	protected ArrayList<String> permissions;
	
	//Default constructor for an Engineer - MJ
	public Engineer()
	{
		super();
		name = "";
		permissions = new ArrayList<String>();
		permissions.add("PlanningPoker");
	}
	
	//Method to return the list of permissions of an employee - MJ
	public ArrayList<String> getPermissions()
	{
		return permissions;
	}
	
	
	
}