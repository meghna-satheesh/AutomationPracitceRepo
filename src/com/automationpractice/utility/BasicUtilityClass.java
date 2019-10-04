package com.automationpractice.utility;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automationpractice.lib.DriverInitialization;



public class BasicUtilityClass extends DriverInitialization{
	
	
	/*
	 * Time Wait Specifications
	 */
	
	public static void pageLoadWait() {
		driver.manage().timeouts().pageLoadTimeout(70, TimeUnit.SECONDS);
	}
	
	public static WebElement explicitWait(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		WebElement waitElement = wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
		return waitElement;
	}
	
	public static List<WebElement> explicitWaitElements(List<WebElement> locatorElements){
		WebDriverWait wait = new WebDriverWait(driver, 70);
		List<WebElement> waitElements = wait.until(ExpectedConditions.visibilityOfAllElements(locatorElements));
		return waitElements;
	}
	
	public static void implicitWait() {
		driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
	}
	
	
	
	
	
	/*
	 * Actions Class Specification
	 */
	public static void moveToElementAction(By imageHover) {
		Actions builder = new Actions(driver);
		Action movetoElement = builder.moveToElement(driver.findElement(imageHover)).build();
		movetoElement.perform();
	}
	
	
	/*
	 * Javascript executer 
	 */
	
	public static void javaScriptExecuterScroll() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,2000)");
	}
	
	public static void javaScriptExecuterClick(By locator) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", driver.findElement(locator));
	}
	
	
	/*
	 * Initialize Excel Object
	 */
	public static ExcelReader excelReaderObj = new ExcelReader("C:\\Users\\lenovo\\eclipse-workspace\\AutomationPractice\\Resources\\TestData.xlsx");
	
	
	
}
