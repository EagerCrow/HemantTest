package com.eagercrow.automation;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.eagercrow.automation.constants.Constants;
import com.eagercrow.automation.data.SignData;

public class EagerCrow extends Assert {

	private WebDriver driver = null;
	private static final Logger logger = LogManager.getLogger(EagerCrow.class);

	EagerCrow() {

		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	}

	@BeforeMethod
	public void initializeDriver() {

		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

		if (logger.isDebugEnabled()) {
			logger.debug("WebDriver has been initialized with implicite wait of 10 sec...!!!");
		} else if (logger.isInfoEnabled()) {
			logger.info("WebDriver has been initialized...!!!");
		}
	}

	@Test(dataProvider = "signInUsers", dataProviderClass = SignData.class)
	// @Parameters({ "username", "password" })
	public void signIn(String username, String password) throws InterruptedException {

		if (logger.isInfoEnabled()) {
			logger.info("Opening the Website Home Page");
		}

		driver.get(Constants.webSiteAddress);

		String xPathSignIn = "//div[@class='header-icons']//a[@routerlink='/SignIn']";

		WebElement signIn = driver.findElement(By.xpath(xPathSignIn));
		signIn.click();

		if (logger.isDebugEnabled()) {
			logger.info("Clicked on Sign In from Home page using xPath : " + xPathSignIn);
		}

		WebElement signInWithGoogle = driver.findElement(By.xpath(
				"//span[@class='firebaseui-idp-text firebaseui-idp-text-long'][contains(text(),'Sign in with Google')]"));

		signInWithGoogle.click();

		Thread.sleep(5000);

		String parentWindow = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();

		// Now we will iterate using Iterator
		Iterator<String> I1 = s1.iterator();

		while (I1.hasNext()) {

			String child_window = I1.next();

			// Here we will compare if parent window is not equal to child window then we
			// will close

			if (!parentWindow.equals(child_window)) {
				driver.switchTo().window(child_window);

				System.out.println(driver.switchTo().window(child_window).getTitle());

			}
		}

		WebElement googleSignIn = driver.findElement(By.xpath("//Input[@name='identifier']"));
		googleSignIn.sendKeys(username);

		WebElement next = driver.findElement(By.xpath("//*[@id='identifierNext']/content"));
		next.click();

		WebElement pass = driver.findElement(By.xpath("//Input[@name='password']"));
		pass.sendKeys(password);

		Thread.sleep(5000);

		WebElement next1 = driver.findElement(By.xpath("//*[@id='passwordNext']/content"));
		next1.click();

		driver.switchTo().window(parentWindow);

		// signOut(parentWindow);

	}

	public void placeOrder() {

	}

	public void signOut() {

		if (logger.isDebugEnabled()) {
			logger.debug("Signing Out...!!!");
		} else if (logger.isInfoEnabled()) {
			logger.info("Signing Out...!!!");
		}

		WebElement myCart = driver.findElement(By.xpath(
				"//div[@class='header-icons']//div[@class='wrap_menu']//nav[@class='menu']//ul[@class='main_menu']//li[@style='padding-top : 0px;padding-right : 0px']//a[@class='header-wrapicon1 dis-block']//div[@class='chip']"));
		myCart.click();

		WebElement signOut = driver.findElement(By.xpath("//html//div[@class='header-icons']//li[3]"));
		signOut.click();

	}

	@AfterMethod
	public void closeDriver() {
		if (logger.isDebugEnabled()) {
			logger.debug("Closing the Driver...!!!");
		} else if (logger.isInfoEnabled()) {
			logger.info("Closing the Driver...!!!");
		}

		driver.close();
	}
}
