package com.mindtree.utility;

public class TestUtil {

	public static final long PAGE_LOAD_TIMEOUT = 20;
	public static final long IMPLICIT_WAIT = 20;
	public static final String PROJECT_ROOT_PATH = System.getProperty("user.dir");
	public static final String SCREENSHOT_FILEPATH = PROJECT_ROOT_PATH + "\\screenshots\\";
	public static final String EXCELFILEPATH = PROJECT_ROOT_PATH + "\\src\\com\\mindtree\\testdata\\Mercury_Tours_Data.xlsx";
	public static final String LOGINSHEET = "login";
	public static final String FLIGHTFINDERSHEET = "flight_finder";
	public static final String SELECTFLIGHTSHEET = "select_flight";
	public static final String BOOKFLIGHTSHEET = "book_flight";
	public static final String FLIGHTCONFIRMATIONSHEET = "flight_confirm";

	public static final String host = "smtp.gmail.com";
	public static final String port = "587";
	public static final String mailFrom = "senders_email_id";
	public static final String password = "senders_password";

	public static final String mailTo = "receivers email id";
	public static final String subject = "New email with attachments";
	public static final String message = "I have some attachments for you.";

}
