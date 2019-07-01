package com.mindtree.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.mindtree.base.TestBase;

public class FlightFinderPage extends TestBase {

	@FindBy(xpath = "//input[@name='tripType']")
	private List<WebElement> roundTrip;

	@FindBy(name = "passCount")
	private WebElement passengers;

	@FindBy(name = "fromPort")
	private WebElement departingFrom;

	@FindBy(name = "fromMonth")
	private WebElement frMon;

	@FindBy(name = "fromDay")
	private WebElement frday;

	@FindBy(name = "toPort")
	private WebElement arrivingIn;

	@FindBy(name = "toMonth")
	private WebElement returnMonth;

	@FindBy(name = "toDay")
	private WebElement returnDate;

	@FindBy(xpath = "//input[@name='servClass']")
	private List<WebElement> serviceClassBusiness;

	@FindBy(name = "airline")
	private WebElement air;

	@FindBy(xpath = "//input[@name='findFlights']")
	private WebElement continue_btn;

	public FlightFinderPage() {
		PageFactory.initElements(driver, this);
	}

	
	public void selectRoundTrip(String type) {
		System.out.println("Round trip in excel is : " + type);
		for (int i = 0; i < roundTrip.size(); i++) {
			if (roundTrip.get(i).getAttribute("value").equalsIgnoreCase(type)) {
				roundTrip.get(i).click();
				System.out.println(type + " is selected");
			}
		}
	}

	public void selectPassengers(String countPass) {
		System.out.println("No's of passengers in excel is : " + countPass);
		new Select(passengers).selectByVisibleText(countPass);
		System.out.println(countPass + " Passenger is selected");
	}

	public void selectFrom(String depart) {
		System.out.println("Flight is departing from : " + depart);
		new Select(departingFrom).selectByVisibleText(depart);
		System.out.println("Departing from " + depart + " is selected");
	}

	public void selectOnMonth(String onMon) {
		System.out.println("Flight is departed on month : " + onMon);
		new Select(frMon).selectByVisibleText(onMon);
		System.out.println("Departing month " + onMon + " is selected");
	}

	public void selectOnDate(String onDat) {
		System.out.println("Flight is departed on date : " + onDat);
		new Select(frday).selectByVisibleText(onDat);
		System.out.println("Departing date " + onDat + " is selected");
	}

	public void selectArriving(String arr) {
		System.out.println("Flight is Arriving in : " + arr);
		new Select(arrivingIn).selectByVisibleText(arr);
		System.out.println("Arriving to " + arr + " is selected");
	}

	public void selectReturningMonth(String reMon) {
		System.out.println("Flight is returning on month : " + reMon);
		new Select(returnMonth).selectByVisibleText(reMon);
		System.out.println("Returning month " + reMon + " is selected");
	}

	public void selectReturningDate(String retDat) {
		System.out.println("Flight is returning on date : " + retDat);
		new Select(returnDate).selectByVisibleText(retDat);
		System.out.println("Returning date " + retDat + " is selected");
	}

	public void selectServiceClassEconomy(String serviceClass) {
		System.out.println("Service class in excel is : " + serviceClass);
		for (int i = 0; i < serviceClassBusiness.size(); i++) {
			if (serviceClassBusiness.get(i).getAttribute("value").equalsIgnoreCase(serviceClass)) {
				serviceClassBusiness.get(i).click();
				System.out.println("Service class " + serviceClass + " is selected");
			}
		}
	}

	public void selectAirline(String airLine) {
		System.out.println("Airline present in excel is : " + airLine);
		new Select(air).selectByVisibleText(airLine);
		System.out.println("Airline " + airLine + " is selected");
	}

	public SelectFlightPage clickContinue() {
		continue_btn.click();
		return new SelectFlightPage();
	}

	public String validateFlightFinderPageTitle() {
		return driver.getTitle();
	}
}
