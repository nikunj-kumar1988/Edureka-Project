package com.mindtree.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.AfterSuite;

import com.mindtree.utility.EmailAttachmentSender;
import com.mindtree.utility.TestUtil;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(TestUtil.PROJECT_ROOT_PATH + "\\src\\com\\mindtree\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization() {
		String browserName = prop.getProperty("browser");
		switch (browserName) {
		case "chrome":
			System.out.println("Chrome browser detected ");
			System.setProperty("webdriver.chrome.driver",TestUtil.PROJECT_ROOT_PATH + "\\src\\com\\mindtree\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.out.println("Firefox browser detected ");
			System.setProperty("webdriver.gecko.driver",TestUtil.PROJECT_ROOT_PATH + "\\src\\com\\mindtree\\drivers\\geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			options.setCapability("marionette", true);
			driver = new FirefoxDriver(options);
			break;
		case "edge":
			System.out.println("Edge browser detected ");
			/*
			 * Note: No need of webdriver , it is already present in windows
			 * System.setProperty("webdriver.edge.driver", TestUtil.PROJECT_ROOT_PATH +
			 * "\\src\\com\\mindtree\\drivers\\MicrosoftWebDriver.exe");
			 */

			driver = new EdgeDriver();
			break;
		case "opera":
			System.out.println("Opera browser detected ");
			System.setProperty("webdriver.opera.driver",TestUtil.PROJECT_ROOT_PATH + "\\src\\com\\mindtree\\drivers\\operadriver.exe");
			driver = new OperaDriver();
			break;
		default:
			System.out.println(
					"browser : " + browserName + " is invalid, Launching internet explorer as browser of choice..");
			System.setProperty("webdriver.ie.driver",TestUtil.PROJECT_ROOT_PATH + "\\src\\com\\mindtree\\drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		System.out.println("*** URL is launched ***");
	}

	public void takeScreenShot(String methodName) {
		System.out.println("Inside take screenshot");
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		System.out.println("The filepath is : -- " + TestUtil.SCREENSHOT_FILEPATH);
		try {
			FileUtils.copyFile(scrFile,new File(TestUtil.SCREENSHOT_FILEPATH + timestamp() + "__" + "__" + methodName + ".jpg"));
			System.out.println("*** Placed screen shot in " + TestUtil.SCREENSHOT_FILEPATH + " *** ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String timestamp() {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd__HH-mm-ss").format(Calendar.getInstance().getTime());
		return timeStamp;
	}

	public static void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@AfterSuite
	public void sendReport() throws InterruptedException, FileNotFoundException {
		Thread.sleep(5000);
		System.out.println("Inside send report method");
		File folder = new File("C:\\pdfngreport");
		File[] files = folder.listFiles();
		try {
			EmailAttachmentSender.sendEmailWithAttachments(TestUtil.host, TestUtil.port, TestUtil.mailFrom,TestUtil.password, TestUtil.mailTo, TestUtil.subject, TestUtil.message, files);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("This is finally block and will always execute");
		}
	}
}
