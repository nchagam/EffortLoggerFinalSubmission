package application;
//Nikhil Chagam - TU46

import java.util.HashMap;
import java.util.Map;

public class PasswordDatabase {
	
	//Create a hashmap to store passwords - NC
	Map<String, String> employeePasswords = new HashMap<>();
	
	public PasswordDatabase()
	{
		
	}
	
	//Void method to store a password and employee id using the hash map - NC
	public void storePass(String enteredPassword ,String employeeNumber) {
		String newPass = enteredPassword.concat(employeeNumber);
		employeePasswords.put(employeeNumber, newPass);
	}
	
	//Validate password by checking to see if the key exists and that it
	//is tied to the correct data - NC
	public boolean validate(String enteredPassword, String employeeNumber)
	{
		enteredPassword = enteredPassword.concat(employeeNumber);
		return employeePasswords.containsKey(employeeNumber) && employeePasswords.get(employeeNumber).equals(enteredPassword);
	}
	
	
}