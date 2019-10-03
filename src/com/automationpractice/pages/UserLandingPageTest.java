package com.automationpractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import com.automationpractice.lib.DriverInitialization;
import com.automationpractice.utility.BasicUtilityClass;

public class UserLandingPageTest extends DriverInitialization {
	
	By searchTextBox = By.id("search_query_top");
	
	
	public void searchItem(String item,String expectedtitle) {
		driver.findElement(searchTextBox).sendKeys(item);
		driver.findElement(searchTextBox).sendKeys(Keys.ENTER);
		
		BasicUtilityClass.pageLoadWait();
		Assert.assertEquals(driver.getTitle(), expectedtitle,"Search not loaded successfully");
	}

}
