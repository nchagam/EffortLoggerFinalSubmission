package application;
import java.util.ArrayList;

//QA Engineer is a type of Engineer - MJ
public class QAEngineer extends Engineer
{
	//Contains the same fields as Engineer/Employee
	protected int employeeID;
	protected String password;
	protected ArrayList<Integer> groups;
	protected ArrayList<String> permissions;
	
	//Default constructor for QAEngineer, they have an intermediate level
	//of permissions - MJ
	public QAEngineer()
	{
		permissions = new ArrayList<String>();
		permissions.add("PlanningPoker");
		permissions.add("ViewData");
	}
	
	
	
}