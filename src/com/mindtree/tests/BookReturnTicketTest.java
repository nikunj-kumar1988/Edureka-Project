package com.mindtree.tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mindtree.base.TestBase;
import com.mindtree.pages.BookAflightPage;
import com.mindtree.pages.FlightConfirmationPage;
import com.mindtree.pages.FlightFinderPage;
import com.mindtree.pages.LoginPage;
import com.mindtree.pages.SelectFlightPage;
import com.mindtree.utility.DataproviderClass;
import com.mindtree.utility.TestUtil;

public class BookReturnTicketTest extends TestBase {

	LoginPage loginPage;
	FlightFinderPage flightFinderPage;
	SelectFlightPage selectFlightPage;
	BookAflightPage bookAFlightPage;
	FlightConfirmationPage flightConfirmationPage;

	public BookReturnTicketTest() {
		super();
	}

	@BeforeTest
	public void setup() {
		initialization();
		loginPage = new LoginPage();
		flightFinderPage = new FlightFinderPage();
		selectFlightPage = new SelectFlightPage();
		bookAFlightPage = new BookAflightPage();
		flightConfirmationPage = new FlightConfirmationPage();
	}

	@Test(priority = 1)
	public void loginPageTitleTest() throws InterruptedException {
		String title = loginPage.validateLoginPageTitle();
		Thread.sleep(2000);
		Assert.assertEquals(title, "Welcome: Mercury Tours");
	}

	@Test(priority = 2, dataProvider = "mtLoginData")
	public void loginMethod(String username, String password) {
		try {
			loginPage.setUsername(username);
			loginPage.setPassword(password);
			loginPage.clickLoginbtn();
			System.out.println("Login button is clicked");
			Thread.sleep(5000);
			String flightfinderTitle = flightFinderPage.validateFlightFinderPageTitle();
			Assert.assertEquals(flightfinderTitle, "Find a Flight: Mercury Tours:");
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	@Test(priority = 3, dataProvider = "mtFlightFinderData", dependsOnMethods = "loginMethod")
	public void flightFinderMethod(String type, String passengers, String departingfrom, String onMonth, String onDate,
			String arrivingIn, String returningMonth, String returningDate, String serviceClass, String airline)
			throws InterruptedException {
		flightFinderPage.selectRoundTrip(type);
		flightFinderPage.selectPassengers(passengers);
		flightFinderPage.selectFrom(departingfrom);
		flightFinderPage.selectOnMonth(onMonth);
		flightFinderPage.selectOnDate(onDate);
		flightFinderPage.selectArriving(arrivingIn);
		flightFinderPage.selectReturningMonth(returningMonth);
		flightFinderPage.selectReturningDate(returningDate);
		flightFinderPage.selectServiceClassEconomy(serviceClass);
		flightFinderPage.selectAirline(airline);
		flightFinderPage.clickContinue();
		Thread.sleep(5000);
		String selectFlightTitle = selectFlightPage.selectFlightPageTitle();
		Assert.assertEquals(selectFlightTitle, "Select a Flight: Mercury Tours");
	}

	@Test(priority = 4, dataProvider = "mtSelectFlightData", dependsOnMethods = "flightFinderMethod")
	public void mtSelectFlightDataMethod(String departureFlight, String returnFlight) throws InterruptedException {
		System.out.println("departureFlight passed is : " + departureFlight);
		System.out.println("returnFlight passed is : " + returnFlight);
		selectFlightPage.selectParisToLondon(departureFlight);
		selectFlightPage.selectLondonToParis(returnFlight);
		selectFlightPage.clickContinue();
		Thread.sleep(2000);
		String bookAflightPageTitle = bookAFlightPage.getBookAFlightPageTitle();
		Assert.assertEquals(bookAflightPageTitle, "Book a Flight: Mercury Tours");
	}

	@Test(priority = 5, dataProvider = "mtBookFlightData", dependsOnMethods = "mtSelectFlightDataMethod")
	public void mtBookFlightDataMethod(String fn, String ln, String ccn) throws InterruptedException {
		bookAFlightPage.setFirstName(fn);
		bookAFlightPage.setLastName(ln);
		bookAFlightPage.setNumber(ccn);
		bookAFlightPage.clickSecurePurchase();
		Thread.sleep(2000);
		String flightConfirmPageTitle = flightConfirmationPage.flightConfirmationPageTitle();
		Assert.assertEquals(flightConfirmPageTitle, "Flight Confirmation: Mercury Tours");
	}

	@Test(priority = 6, dataProvider = "mtFlightConfirmationData", dependsOnMethods = "mtBookFlightDataMethod")
	public void mtFlightConfirmationDataMethod(String txt) {
		String txtValue = flightConfirmationPage.getIternaryTextFromPage();
		Assert.assertEquals(txtValue, "Your itinerary has been booked!");
	}

	@AfterTest
	public void closeBrowser() {
		tearDown();
	}

	@DataProvider
	public Object[][] mtLoginData() throws Exception {
		Object[][] fbdata = DataproviderClass.getDataFromExcel(TestUtil.EXCELFILEPATH, TestUtil.LOGINSHEET);
		return fbdata;
	}

	@DataProvider
	public Object[][] mtFlightFinderData() throws Exception {
		Object[][] fbdata = DataproviderClass.getDataFromExcel(TestUtil.EXCELFILEPATH, TestUtil.FLIGHTFINDERSHEET);
		return fbdata;
	}

	@DataProvider
	public Object[][] mtSelectFlightData() throws Exception {
		Object[][] fbdata = DataproviderClass.getDataFromExcel(TestUtil.EXCELFILEPATH, TestUtil.SELECTFLIGHTSHEET);
		return fbdata;
	}

	@DataProvider
	public Object[][] mtBookFlightData() throws Exception {
		Object[][] fbdata = DataproviderClass.getDataFromExcel(TestUtil.EXCELFILEPATH, TestUtil.BOOKFLIGHTSHEET);
		return fbdata;
	}

	@DataProvider
	public Object[][] mtFlightConfirmationData() throws Exception {
		Object[][] fbdata = DataproviderClass.getDataFromExcel(TestUtil.EXCELFILEPATH,TestUtil.FLIGHTCONFIRMATIONSHEET);
		return fbdata;
	}
}