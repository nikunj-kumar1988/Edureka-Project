package com.mindtree.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mindtree.base.TestBase;

public class FlightConfirmationPage extends TestBase {

	@FindBy(xpath = "//font[@size='+1']")
	@CacheLookup
	private WebElement iternaryBooked;

	public FlightConfirmationPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String getIternaryTextFromPage() {
		return iternaryBooked.getText();
	}

	public String flightConfirmationPageTitle() {
		return driver.getTitle();
	}
}
