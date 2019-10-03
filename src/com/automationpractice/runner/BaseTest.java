package com.automationpractice.runner;

import org.testng.annotations.Test;

import com.automationpractice.lib.DriverInitialization;
import com.automationpractice.pages.LoadUserDetailsTest;
import com.automationpractice.pages.SearchResultTest;
import com.automationpractice.pages.UserAuthenticationTest;
import com.automationpractice.pages.UserLandingPageTest;

import org.testng.annotations.BeforeClass;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;

public class BaseTest extends DriverInitialization{
	DriverInitialization propGet = new DriverInitialization();
	UserAuthenticationTest authUser = new UserAuthenticationTest();
	LoadUserDetailsTest loadUser = new LoadUserDetailsTest();
	UserLandingPageTest user = new UserLandingPageTest();
	SearchResultTest search = new SearchResultTest();
	
	
	@BeforeClass
	public void beforeClass() throws IOException {
	  String usedBrowser = propGet.getPropertyValue("Browser");
	  String URL = propGet.getPropertyValue("WebpageURL");
	  String expectedTitle = propGet.getPropertyValue("ExpcetedTitleOnPageLoad");
	  propGet.initializeAndLaunch(usedBrowser, URL, expectedTitle);
	  
	}	
	
	@Test(priority = 1)
	public void registerUser() throws InterruptedException, IOException {
		String registerExpectedTitle = propGet.getPropertyValue("RegistrationURLPagetitle");
		String successfulRegisterExpected = propGet.getPropertyValue("SuccessfulRegisterTitle");
		authUser.signInLinkSelection(registerExpectedTitle);
		authUser.registerUserEmail();
		loadUser.userDetailLoad(successfulRegisterExpected);
	}
	
	@Test(priority = 2)
	public void existingUserLogin() throws InterruptedException, IOException {
		String registerExpectedTitle = propGet.getPropertyValue("RegistrationURLPagetitle");
		String successfulRegisterExpected = propGet.getPropertyValue("SuccessfulRegisterTitle");
		authUser.signInLinkSelection(registerExpectedTitle);
		authUser.existingUserLogin(successfulRegisterExpected);
		
	}
	
	@Test(priority = 3)
	public void buyProduct() throws IOException {
		String item = propGet.getPropertyValue("itemPurchased");
		String expectedSearchTitle = propGet.getPropertyValue("SearchPageTitle");
		String itemtoSelect = propGet.getPropertyValue("ItemToBeSelected");
		user.searchItem(item,expectedSearchTitle);
		search.addToCart(itemtoSelect);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
