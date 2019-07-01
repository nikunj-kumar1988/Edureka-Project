package com.mindtree.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mindtree.base.TestBase;

public class BookAflightPage extends TestBase {

	@FindBy(name = "passFirst0")
	private WebElement firstName;

	@FindBy(name = "passLast0")
	private WebElement lastName;

	@FindBy(name = "creditnumber")
	private WebElement cardNum;

	@FindBy(name = "buyFlights")
	private WebElement securePurchase;

	public BookAflightPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void setFirstName(String first) {
		firstName.clear();
		firstName.sendKeys(first);
	}

	public void setLastName(String last) {
		lastName.clear();
		lastName.sendKeys(last);
	}

	public void setNumber(String num) {
		cardNum.clear();
		cardNum.sendKeys(num);
	}

	public FlightConfirmationPage clickSecurePurchase() {
		securePurchase.click();
		return new FlightConfirmationPage();
	}

	public String getBookAFlightPageTitle() {
		return driver.getTitle();
	}

}
