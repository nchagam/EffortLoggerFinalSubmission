package application;
import java.util.ArrayList;

//Software Engineer is a type of Engineer - MJ
public class SoftwareEngineer extends Engineer
{
	//Contains the same fields as Engineer/Employee
	protected String employeeNumber;
	public String name;
	protected String password;
	protected ArrayList<Integer> groups;
	protected ArrayList<String> permissions;
	
	//Default constructor for SoftwareEngineer
	//Software Engineer has the most permissions - MJ
	public SoftwareEngineer()
	{
		super();
		name = "Bob";
		permissions = new ArrayList<String>();
		permissions.add("PlanningPoker");
		permissions.add("EffortLoggerClock");
		permissions.add("ViewData");
	}
	
	
	
}