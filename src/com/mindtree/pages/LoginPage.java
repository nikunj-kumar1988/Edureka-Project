package com.mindtree.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mindtree.base.TestBase;

public class LoginPage extends TestBase {

	@FindBy(name = "userName")
	private WebElement user;

	@FindBy(name = "password")
	private WebElement pass;

	@FindBy(name = "login")
	@CacheLookup
	private WebElement login_btn;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public void setUsername(String username) {
		user.clear();
		user.sendKeys(username);
	}

	public void setPassword(String password) {
		pass.clear();
		pass.sendKeys(password);
	}

	public FlightFinderPage clickLoginbtn() {
		login_btn.click();
		return new FlightFinderPage();
	}

	public String validateLoginPageTitle(){
		return driver.getTitle();
	}

	public boolean validateSignInButton() {
		return login_btn.isDisplayed();
	}
}
