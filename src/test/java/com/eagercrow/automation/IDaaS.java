package com.eagercrow.automation;

import java.util.Iterator;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.http.HttpResponse;

import com.eagercrow.automation.constants.Constants;

public class IDaaS {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("http://example.com/doc.html");

		WebElement termsAndCondition = driver.findElement(By.xpath("//a[@id='toc-link']"));
		WebElement downloadDoc = driver.findElement(By.xpath("//a[@id='mydocpdf']"));

		// downLoadTermsAndConditions(driver);

	}

	public void acceptTermsAndConditions(WebDriver driver) {

		WebElement termsAndCondition = driver.findElement(By.xpath("//a[@id='toc-link']"));
		WebElement downloadDoc = driver.findElement(By.xpath("//a[@id='mydocpdf']"));

		termsAndCondition.click();

		String parentWindow = driver.getWindowHandle();
		Set<String> windowList = driver.getWindowHandles();

		// switch to new window
		Iterator<String> iterator = windowList.iterator();

		while (iterator.hasNext()) {

			String child_window = iterator.next();

			if (!parentWindow.equals(child_window)) {
				driver.switchTo().window(child_window);

			}
		}
		WebElement accept = driver.findElement(By.xpath("//input[@id='accept']"));
		accept.click();

		driver.switchTo().window(parentWindow);
		downloadDoc.click();

	}

	public void rejectTermsAndConditions(WebDriver driver) {

		WebElement termsAndCondition = driver.findElement(By.xpath("//a[@id='toc-link']"));
		WebElement downloadDoc = driver.findElement(By.xpath("//a[@id='mydocpdf']"));

		termsAndCondition.click();

		String parentWindow = driver.getWindowHandle();
		Set<String> windowList = driver.getWindowHandles();

		// switch to new window
		Iterator<String> iterator = windowList.iterator();

		while (iterator.hasNext()) {

			String child_window = iterator.next();

			if (!parentWindow.equals(child_window)) {
				driver.switchTo().window(child_window);

			}
		}
		WebElement reject = driver.findElement(By.xpath("//input[@id='reject']"));
		reject.click();

		driver.switchTo().window(parentWindow);

		// Don't know how to handle 403 http response

	}

	public void directClickOnDownload(WebDriver driver) {
		WebElement downloadDoc = driver.findElement(By.xpath("//a[@id='mydocpdf']"));
		// Don't know how to handle 403 http response

	}

}
