package com.automationpractice.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.automationpractice.lib.DriverInitialization;
import com.automationpractice.utility.BasicUtilityClass;

public class LoadUserDetailsTest extends DriverInitialization{
	
	SoftAssert softAssert =  new SoftAssert();
	By firstName = By.id("customer_firstname");
	By lastName = By.id("customer_lastname");
	By email = By.id("email");
	By password = By.id("passwd");
	By newsLetter = By.id("newsletter");
	By offers = By.id("optin");
	By fnameAddr = By.id("firstname");
	By lnameAddr = By.id("lastname");
	By company = By.id("company");
	By address = By.id("address1");
	By city = By.id("city");
	By postalCode = By.id("postcode");
	By mobile = By.id("phone_mobile");
	By alias = By.id("alias");
	By submitButton = By.id("submitAccount");
	
	public void userDetailLoad(String successfulRegExpected) {
		String title = BasicUtilityClass.excelReaderObj.getCellData("UserDetailsLoad", "Title", 2) ;	
		String fName = BasicUtilityClass.excelReaderObj.getCellData("UserDetailsLoad", "First Name", 2);
		String lName = BasicUtilityClass.excelReaderObj.getCellData("UserDetailsLoad", "Last Name", 2);
		String emailId = BasicUtilityClass.excelReaderObj.getCellData("RegisterUser", "email", 2);
		String passwrd = BasicUtilityClass.excelReaderObj.getCellData("UserDetailsLoad", "Password", 2);
		String date = BasicUtilityClass.excelReaderObj.getCellData("UserDetailsLoad", "Date", 2);
		String month = BasicUtilityClass.excelReaderObj.getCellData("UserDetailsLoad", "Month", 2);
		String year = BasicUtilityClass.excelReaderObj.getCellData("UserDetailsLoad", "Year", 2);
		String newsLetter = BasicUtilityClass.excelReaderObj.getCellData("UserDetailsLoad", "Sign up for our newsletter!", 2);
		String offerPartner = BasicUtilityClass.excelReaderObj.getCellData("UserDetailsLoad", "Receive special offers from our partners!", 2);
		String companyName = BasicUtilityClass.excelReaderObj.getCellData("UserDetailsLoad", "Company", 2);		
		String addressMain = BasicUtilityClass.excelReaderObj.getCellData("UserDetailsLoad", "Address", 2);	
		String citiName = BasicUtilityClass.excelReaderObj.getCellData("UserDetailsLoad", "City", 2);	
		String stateName = BasicUtilityClass.excelReaderObj.getCellData("UserDetailsLoad", "State", 2);
		String zipCode = BasicUtilityClass.excelReaderObj.getCellData("UserDetailsLoad", "Zip Code", 2);
		String mobNumber = BasicUtilityClass.excelReaderObj.getCellData("UserDetailsLoad", "Mobile Number", 2); 
		String addAlias = BasicUtilityClass.excelReaderObj.getCellData("UserDetailsLoad", "Address Alias", 2); 
		selectTitle(title);
		driver.findElement(firstName).sendKeys(fName);
		driver.findElement(lastName).sendKeys(lName);		
		softAssert.assertEquals(driver.findElement(email).getAttribute("value"),emailId,"Email ID not displayed by default");
		driver.findElement(password).sendKeys(passwrd);
		dobSelection(date, month, year);
		checkboxSelection(newsLetter, offerPartner);
		driver.findElement(company).sendKeys(companyName);
		driver.findElement(address).sendKeys(addressMain);
		driver.findElement(city).sendKeys(citiName);
		selectState(stateName);
		driver.findElement(postalCode).sendKeys(zipCode);
		driver.findElement(mobile).sendKeys(mobNumber);
		driver.findElement(alias).sendKeys(addAlias);
		driver.findElement(submitButton).click();
		
		BasicUtilityClass.pageLoadWait();
		softAssert.assertEquals(driver.getTitle(), successfulRegExpected,"Registration successfully completed");
		softAssert.assertAll();
		
	}
	
	
	public void selectTitle(String title) {
		if(title.equalsIgnoreCase("Mr")) {
			driver.findElement(By.id("uniform-id_gender1")).click();
		}
		else {
			driver.findElement(By.id("uniform-id_gender2")).click();
		}
	}
	
	
	public void dobSelection(String date,String month,String year) {
		Select dobDate = new Select(driver.findElement(By.id("days")));
		Select dobMonth = new Select(driver.findElement(By.id("months")));
		Select dobYear = new Select(driver.findElement(By.id("years")));
		dobDate.selectByValue(date);
		dobMonth.selectByValue(month);
		dobYear.selectByValue(year);
	}
	
	public void checkboxSelection(String newsletter,String offer) {
		if(newsletter.equalsIgnoreCase("Yes")) {
			driver.findElement(newsLetter).click();
		}
		if(offer.equalsIgnoreCase("Yes")) {
			driver.findElement(offers).click();
		}
	}

	public void selectState(String stateName) {
		Select stateSelect = new Select(driver.findElement(By.id("id_state")));
		stateSelect.selectByVisibleText(stateName);
	}
	
}
