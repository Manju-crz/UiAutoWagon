package mks.base.driver;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import mks.driver.base.DriverBase;
import mks.java.util.Sleep;
import mks.uiautowagon.interactor.WagonerFacade;

public class CsvDataIndividualTest extends DriverBase{

	//@Test
	public void testNetflix() {

		String url = "https://www.netflix.com/in/";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.clickElement.get("Sign In").click();
		Sleep.for2Seconds();
		wagoner.textField.get("Email or phone number").sendKeys("45645654653");
		wagoner.textField.get("Password").sendKeys("sefrsfsdrfs");
		wagoner.checkBox.get("Remember Me").click();
		Sleep.for2Seconds();
		wagoner.clickElement.get("Sign In").click();
		Sleep.for2Seconds();
		
	}
	
	//@Test
	public void testMarketo() {

		String url = "https://app-sjqe.marketo.com/homepage/login";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.getCount();
		wagoner.textField.get("Email").sendKeys("45645654653");
		wagoner.textField.get("Password").sendKeys("sefrsfsdrfs");
		wagoner.checkBox.get("Remember Email").click();
		Sleep.for2Seconds();
		wagoner.clickElement.get("LOGIN").click();
		Sleep.for2Seconds();
		
		wagoner = new WagonerFacade(driver);
		wagoner.getCount();
		wagoner.textField.get("Email").sendKeys("45645654653");
		wagoner.textField.get("Password").sendKeys("sefrsfsdrfs");
		wagoner.checkBox.get("Remember Email").click();
		Sleep.for2Seconds();
		wagoner.clickElement.get("LOGIN").click();
		Sleep.for2Seconds();
		
	}

	//@Test
	public void testFlipkart() {

		String url = "https://www.flipkart.com/";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.textField.get("Enter EMail/Mobile Number").sendKeys("45645654653");
		wagoner.textField.get("Enter Password").sendKeys("sefrsfsdrfs");
		Sleep.for2Seconds();
		wagoner.clickElement.get("Login").click();
		Sleep.for2Seconds();
		wagoner.clickElement.get("New to Flipkart? Create an account").click();
		Sleep.for5Seconds();
		wagoner.textField.get("Enter Mobile Number").sendKeys("45645654653");
		Sleep.for2Seconds();
		wagoner.clickElement.get("CONTINUE").click();
		Sleep.for5Seconds();
		
	}
	
	
	//@Test
	public void finologyTest() {
		String url = "https://www.finology.in/signup.aspx?smode=mobile&ReturnUrl=https://ticker.finology.in";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.getCount();
		wagoner.textField.get("Your Full Name: *").sendKeys("sdfsdfsfsfsd");
		wagoner.textField.get("Email ID: *").sendKeys("sefrsfsdrfs@sd.dfv");
		Sleep.for2Seconds();
		wagoner.textField.get("Mobile Number: *").sendKeys("9875556788");
		Sleep.for2Seconds();
		wagoner.button.get("Signup & Get Started").click();
		Sleep.for2Seconds();
		
		
	}
	
	//@Test
	public void testStackOverflow() {

		String url = "https://stackoverflow.com/";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.link.get("Log in").click();
		Sleep.for2Seconds();
		wagoner.textField.get("Email").sendKeys("45645654653");
		Sleep.for2Seconds();
		wagoner.textField.get("Password").sendKeys("sefrsfsdrfs");
		Sleep.for2Seconds();
		wagoner.clickElement.get("Log in").click();
		Sleep.for2Seconds();
	}
	
	//@Test
	public void youtubeSignInTest() {

		String url = "https://www.youtube.com/";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.getCount();
		wagoner.button.get("SIGN IN").click();
		Sleep.for2Seconds();
		wagoner = wagoner.reload();
		wagoner.getCount();
		wagoner.textField.get("Email or phone").sendKeys("9864567558");
		Sleep.for2Seconds();
		wagoner.button.get("Next").click();
		Sleep.for2Seconds();
		wagoner.textField.get("Enter your password").sendKeys("adasdsadsad");
		Sleep.for2Seconds();
		wagoner.button.get("Next").click();
		Sleep.for2Seconds();
	}

	//@Test
	public void yahooTest() {

		String url = "https://in.yahoo.com/";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.getCount();
		wagoner.link.get("Sign in").click();
		Sleep.for2Seconds();
		wagoner.textField.get("Username, email address or mobile number").sendKeys("9453453454");
		wagoner.checkBox.get("Stay signed in").click();
		Sleep.for2Seconds();
		wagoner.button.get("Next").click();
		Sleep.for2Seconds();
		wagoner.link.get("Forgotten username?").click();
		Sleep.for2Seconds();
		wagoner.link.get("Create an account").click();
		Sleep.for2Seconds();
	}

	//@Test
	public void amazonTest() {

		String url = "https://www.amazon.in/";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.getCount();
		System.out.println("Trying to find the link element");
		wagoner.link.get("Hello, Sign in").click();
		Sleep.for2Seconds();
		wagoner.textField.get("Email or mobile phone number").sendKeys("9876567876");
		Sleep.for2Seconds();
		wagoner.button.get("Continue").click();
		Sleep.for2Seconds();
		wagoner.reload();
		wagoner.link.get("Create your Amazon account").click();
		Sleep.for2Seconds();
		wagoner.textField.get("Mobile number").sendKeys("aasfdafdsed");
		wagoner.textField.get("Email (optional)").sendKeys("aasfdafdsed");
		wagoner.textField.get("Password").sendKeys("aasfdafdsed");
		Sleep.for2Seconds();
		wagoner.link.get("Sign in").click();
		Sleep.for2Seconds();
	}

	@Test
	public void testEbay() {

		String url = "https://www.ebay.com/";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.link.get("Sign in").click();
		Sleep.for2Seconds();
		wagoner.radioButton.get("Click to verify").click();
		Sleep.for5Seconds();
	}
	
}
