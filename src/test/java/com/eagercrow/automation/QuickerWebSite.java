package com.eagercrow.automation;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class QuickerWebSite {

	WebDriver driver = null;
	Logger logger =LogManager.getLogger(QuickerWebSite.class);

	@BeforeMethod
	public void init() {
		driver = new ChromeDriver();
		logger.info("Initialized Driver");
		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		logger.info("Applied Implicite timer of 10sec to driver");
	}

	@Test
	public void login() {
		driver.get("https://www.quikr.com/");

		WebElement loginButton = driver.findElement(By.xpath("//li[@class='login-link']"));
		logger.info("Clicking main page login");
		loginButton.click();

		/*String parentWindow = driver.getWindowHandle();

		Set<String> windowList = new HashSet<String>();

		windowList = driver.getWindowHandles();
		
		Iterator<String> iterator = windowList.iterator();
	
		
		while(iterator.hasNext()){
			
			String window = iterator.next();
			System.out.println("WindowList  : "+ window);
			
			if(!parentWindow.equals(window)){
				driver.switchTo().window(window);
				System.out.println("Current Window : "+ driver.getTitle());
			}
*/			
		
		WebElement userName = driver.findElement(By.xpath("//input[@id='FirstEmailOrMobile']"));
		userName.sendKeys("9822066637");
		logger.info("Entered Phone Number");
		
		WebElement next = driver.findElement(By.xpath("//button[@type='button'][contains(text(),'Continue')]"));
		next.click();
		logger.info("Clicked Continue button");
		
		WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
		password.sendKeys("123456");
		logger.info("Entered Password");
		

		WebElement login = driver.findElement(By.xpath("//button[@id='reCaptchaLoginPassword']"));
		login.click();
		logger.info("Clicked Login Button");
		

	}

	@AfterMethod
	public void exit() {

		driver.close();
		driver.quit();
	}

}
 