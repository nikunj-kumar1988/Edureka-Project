package com.mindtree.tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mindtree.base.TestBase;
import com.mindtree.pages.FlightFinderPage;
import com.mindtree.pages.LoginPage;
import com.mindtree.utility.DataproviderClass;
import com.mindtree.utility.TestUtil;

public class LoginTest extends TestBase {

	public LoginTest() {
		super();
	}

	LoginPage loginPage;
	FlightFinderPage flightFinderPage;

	@BeforeTest
	public void setup() {
		initialization();
		loginPage = new LoginPage();
		flightFinderPage = new FlightFinderPage();
	}

	@Test(priority = 1)
	public void loginPageTitleTest() throws InterruptedException {
		String title = loginPage.validateLoginPageTitle();
		Thread.sleep(2000);
		Assert.assertEquals(title, "Welcome: Mercury Tours");
	}

	@Test(priority = 2)
	public void signInButtonTest() {
		boolean flag = loginPage.validateSignInButton();
		Assert.assertTrue(flag);
	}

	@Test(priority = 3, dataProvider = "mtLoginData")
	public void loginMethod(String username, String password) {
		try {
			loginPage.setUsername(username);
			loginPage.setPassword(password);
			loginPage.clickLoginbtn();
			System.out.println("Login button is clicked");
			Thread.sleep(5000);
			String title = flightFinderPage.validateFlightFinderPageTitle();
			Assert.assertEquals(title, "Find a Flight: Mercury Tours:");
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
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
}
