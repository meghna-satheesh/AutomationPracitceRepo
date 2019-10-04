package com.automationpractice.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automationpractice.lib.DriverInitialization;
import com.automationpractice.utility.BasicUtilityClass;

public class SearchResultTest extends DriverInitialization{
	
	By checkOut = By.xpath("//a[@title='Proceed to checkout']");
	public void addToCart(String itemToSelect) {
		
		By imageHover= By.xpath("//*[@id=\"center_column\"]/ul/li["+itemToSelect+"]/div/div[1]/div/a[1]/img");
		BasicUtilityClass.javaScriptExecuterScroll();
		BasicUtilityClass.moveToElementAction(imageHover);		
		
				
		By addTocartHover = By.xpath("//*[@id=\"center_column\"]/ul/li["+itemToSelect+"]/div/div[2]/div[2]/a[1]");
		BasicUtilityClass.javaScriptExecuterClick(addTocartHover);
		
		BasicUtilityClass.moveToElementAction(checkOut);
		BasicUtilityClass.javaScriptExecuterClick(checkOut);
		BasicUtilityClass.implicitWait();
		String itemsNoExpected = driver.findElement(By.id("summary_products_quantity")).getText();
		Assert.assertTrue(itemsNoExpected.contains("1"));
	}

}
