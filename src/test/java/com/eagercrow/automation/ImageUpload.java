package com.eagercrow.automation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


// https://stackoverflow.com/questions/16896685/how-to-upload-file-using-selenium-webdriver-in-java 

public class ImageUpload {
	WebDriver driver = null;

	@BeforeMethod
	public void init() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
	}

	@Test
	public void login() throws AWTException {
		driver.get("http://www.resizeimage.net/");
		WebElement uploadButton = driver.findElement(By.xpath("//div[@id='browse']"));
		uploadButton.click();
		
		 //put path to your image in a clipboard
	    StringSelection ss = new StringSelection("C:\\Hemant\\whatsapp.jpg");
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

	    //imitate mouse events like ENTER, CTRL+C, CTRL+V
	    Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_V);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	    
	     
	    
	}

	@AfterMethod
	public void exit() {

		// driver.close();
		// driver.quit();
	}

}
