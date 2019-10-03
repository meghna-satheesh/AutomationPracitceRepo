package com.automationpractice.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.automationpractice.utility.BasicUtilityClass;

public class DriverInitialization {
	
	public static WebDriver driver;
		
	/*
	 * Fetch the property value from the Property file (Keyword Driven)
	 */
	
	public String getPropertyValue(String fieldName) throws IOException {
		Properties propertyValue = new Properties();
		InputStream input = new FileInputStream("Resources\\Global.properties");
		propertyValue.load(input);
		String fieldValue = propertyValue.getProperty(fieldName);
		return fieldValue;
	}
	
	
	/*
	 * Initialize driver and launch URL
	 */
	
	public void initializeAndLaunch(String usedBrowser,String URL,String expectedTitle) {
		 if(usedBrowser.equalsIgnoreCase("Google Chrome")) {
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\lenovo\\eclipse-workspace\\AutomationPractice\\Drivers\\chromedriver_win32\\chromedriver.exe");
				driver = new ChromeDriver();
			}
			else if(usedBrowser.equalsIgnoreCase("ie")|| usedBrowser.equalsIgnoreCase("Internet Explorer")) {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				System.setProperty("webdriver.ie.driver", "C:\\Users\\lenovo\\eclipse-workspace\\AutomationPractice\\Drivers");
				driver = new InternetExplorerDriver();
			}
			else {
				driver = new FirefoxDriver();
			}	 
		 	driver.get(URL);
			driver.manage().window().maximize();
			BasicUtilityClass.pageLoadWait();
			String actualTitle = driver.getTitle();
			Assert.assertEquals(actualTitle, expectedTitle,"Page not loaded as expected");
	}
	
	
	

}
