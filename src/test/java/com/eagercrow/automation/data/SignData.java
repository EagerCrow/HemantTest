package com.eagercrow.automation.data;

import org.testng.annotations.DataProvider;

public class SignData {
	@DataProvider(name="signInUsers")
	public static Object[][] signInData(){
		
		Object users[][] = new Object[2][2];
		
		users[0][0]= "tumbare27@gmail.com";

		users[0][1]= "Vidyapeeth27!";
		
		users[1][0]= "eagercrowofficial@gmail.com";

		users[1][1]= "Password27!";
		
		return users;
	}

}
