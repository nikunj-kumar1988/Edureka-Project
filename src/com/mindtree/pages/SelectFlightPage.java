package com.mindtree.pages;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mindtree.base.TestBase;

public class SelectFlightPage extends TestBase {

	
	@FindBy(xpath = "//input[@name='outFlight']")
	private List<WebElement> parisToLondon;

	@FindBy(xpath = "//input[@name='inFlight']")
	private List<WebElement> londonToParis;

	@FindBy(name = "reserveFlights")
	private WebElement continueBtn;
	
	public SelectFlightPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void selectParisToLondon(String dpl) {
		System.out.println("paris to london flight in excel is : " + dpl);
		Iterator<WebElement> iter = parisToLondon.iterator();
		while(iter.hasNext()) {
		    WebElement element = iter.next();
			/*
			 * System.out.println("element value is >>>>>>>>>>>>>" + element);
			 * System.out.println("Element value is >>>>>>>>>>>>>" + element.getText());
			 * System.out.println("attribute value is >>>>>>>>>>>>>" +
			 * element.getAttribute("value"));
			 * System.out.println("truth value is >>>>>>>>>>>>>" +
			 * element.getAttribute("value").equalsIgnoreCase(dpl));
			 */
		    if (element.getAttribute("value").equalsIgnoreCase(dpl)) {
		    	element.click();
		    }
		}
		
		/*
		 * for (int i = 0; i < parisToLondon.size(); i++) { if
		 * (parisToLondon.get(i).getAttribute("value").equalsIgnoreCase(dpl)) {
		 * System.out.println("the value of radio button is >>>>>>>>>> " +
		 * parisToLondon.get(i).getAttribute("value")); parisToLondon.get(i).click();
		 * System.out.println("paris to london flight " + dpl + " is now selected"); } }
		 */
	}

	public void selectLondonToParis(String ret) {
		System.out.println("london to paris flight in excel is : " + ret);
		Iterator<WebElement> iter = londonToParis.iterator();
		while(iter.hasNext()) {
		    WebElement element = iter.next();
		    if (element.getAttribute("value").equalsIgnoreCase(ret)) {
		    	element.click();
		    }
		}
		
		/*
		 * for (int i = 0; i < londonToParis.size(); i++) { if
		 * (londonToParis.get(i).getAttribute("value").equalsIgnoreCase(ret)) {
		 * System.out.println("the value of radio button is >>>>>>>>>> " +
		 * londonToParis.get(i).getAttribute("value")); londonToParis.get(i).click();
		 * System.out.println("london to paris flight " + ret + " is now selected"); }
		 */
		}
	

	public BookAflightPage clickContinue() {
		continueBtn.click();
		return new BookAflightPage();
	}

	public String selectFlightPageTitle() {
		return driver.getTitle();
	}
}
