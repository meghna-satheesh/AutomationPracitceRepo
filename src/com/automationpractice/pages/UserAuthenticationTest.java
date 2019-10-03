package com.automationpractice.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.automationpractice.lib.DriverInitialization;
import com.automationpractice.runner.BaseTest;
import com.automationpractice.utility.BasicUtilityClass;

public class UserAuthenticationTest extends DriverInitialization{	
	
	SoftAssert softAssert = new SoftAssert();

	By signinLink = By.linkText("Sign in");
	By emailCreate = By.id("email_create");
	By submitCreate = By.id("SubmitCreate");
	By formId = By.id("account-creation_form");
	By existingEmail = By.id("email");
	By password = By.id("passwd");
	By submitLogin = By.id("SubmitLogin");
	
	public void signInLinkSelection(String expectedTitle) throws InterruptedException {
		WebElement signIn = BasicUtilityClass.explicitWait(signinLink);
		signIn.click();
		BasicUtilityClass.pageLoadWait();
		String actualtitle = driver.getTitle();
		Assert.assertEquals(actualtitle, expectedTitle,"Authentication pageload failed");		
	}
	
	public void registerUserEmail() {
		WebElement email = BasicUtilityClass.explicitWait(emailCreate);
		String emailUser = BasicUtilityClass.excelReaderObj.getCellData("RegisterUser", "email", 2);
		email.sendKeys(emailUser);
		driver.findElement(submitCreate).click();
		
		BasicUtilityClass.implicitWait();
		BasicUtilityClass.moveToElementAction(formId);
		Boolean flag = driver.findElement(formId).isDisplayed();
		Assert.assertTrue(flag, "register User details page not displayed");
	}
	
	
	public void existingUserLogin(String successfullRegExpected) {
		String emailId = BasicUtilityClass.excelReaderObj.getCellData("Existing User", "Email", 2);
		String passwordExisting = BasicUtilityClass.excelReaderObj.getCellData("Existing User", "Password", 2);
		driver.findElement(existingEmail).sendKeys(emailId);
		driver.findElement(password).sendKeys(passwordExisting);
		driver.findElement(submitLogin).click();
		
		BasicUtilityClass.pageLoadWait();
		Assert.assertEquals(driver.getTitle(), successfullRegExpected,"Login Failed");
	}

}
