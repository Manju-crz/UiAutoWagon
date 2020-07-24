package mks.base.driver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import mks.driver.base.DriverBase;
import mks.java.util.Sleep;
import mks.uiautowagon.interactor.WagonerFacade;

public class NewPatternTry extends DriverBase{

	//@Test
	public void testMarketo() {
		
		String url = "https://app-sjqe.marketo.com/";
		driver.get(url);
		
		WagonerFacade wagoner = new WagonerFacade(driver);
		//wagoner.getCount();
		//driver.findElement(By.id("loginUsername")).sendKeys("sadsdsfs@acsdf.com");
		wagoner.textField.get("Email").sendKeys("sadsads");
		Sleep.for2Seconds();
		wagoner.textField.get("Password").sendKeys("sdfsdsgd");
		Sleep.for2Seconds();
		wagoner.checkBox.get("Remember Email").click();
		Sleep.for2Seconds();
		wagoner.button.get("LOGIN").click();
		Sleep.for10Seconds();
		
	}
	
	

	//@Test
	public void testFB() {
		
		String url = "https://www.facebook.com/";
		driver.get(url);
		
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.textField.get("Email or phone").sendKeys("sadsdsfs@acsdf.com");
		Sleep.for2Seconds();
		wagoner.textField.get("Password").sendKeys("sdfsdsgd");
		Sleep.for2Seconds();
		wagoner.button.get("Log In").click();
		Sleep.for10Seconds();
		
	}
	
	//@Test
	public void testFB2() {
		
		String url = "https://www.facebook.com/";
		driver.get(url);
		
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.textField.get("First name").sendKeys("asfsdfsdf");
		Sleep.for2Seconds();
		wagoner.textField.get("Surname").sendKeys("srgbrfgrhb");
		wagoner.textField.get("Mobile number or email address").sendKeys("sdfsdsgd@sdd.com");
		wagoner.textField.get("New password").sendKeys("sdsffdsgd@sdd.com");
		Sleep.for2Seconds();
		wagoner = wagoner.reload();
		wagoner.textField.get("Re-enter email address").sendKeys("sdfjhjyhsdsgd");
		wagoner.radioButton.get("Female").click();
		Sleep.for2Seconds();
		wagoner.button.get("Sign Up").click();
		Sleep.for10Seconds();
		
	}
	
	//@Test
	public void testFB2DirectCode() {

		String url = "https://www.facebook.com/";
		driver.get(url);
		driver.findElement(By.xpath("//input[@aria-label='First name']")).sendKeys("asfsdf");
		Sleep.for2Seconds();

	}
	

	//@Test
	public void testEbaySignInDirectCode() {

		String url = "https://signin.ebay.com/signin/";
		driver.get(url);
		System.out.println("Check box text is : " + driver.findElement(By.xpath("//label[@class='checkbox-label']")).getText());
		Sleep.for5Seconds();
	}
	

	//@Test
	public void testEbaySignInWagoner() {

		String url = "https://www.ebay.com/sch/ebayadvsearch";
		driver.get(url);
		Sleep.for5Seconds();
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.checkBox.get("Completed listings").click();
		Sleep.for5Seconds();
		wagoner.link.get("Clear options").click();
		Sleep.for5Seconds();
		wagoner.getCount();
	}
	

	//@Test
	public void testSCYesNoRadio() {

		String url = "https://apply.standardchartered.co.in/personal-loan?pid=IN_IP_PL_CR&_ga=2.124102095.492274700.1594548540-1994161994.1594548540";
		driver.get(url);
		Sleep.for5Seconds();
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.radioButton.get("No").click();
		Sleep.for5Seconds();
		wagoner.radioButton.get("Yes").click();
		Sleep.for5Seconds();
		wagoner.getCount();
	}
	

	//@Test
	public void testAdidasShopRadio() {

		String url = "https://shop.adidas.co.in/adidas/account/register.do";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.getCount();
		wagoner.radioButton.get("MALE").click();
		Sleep.for2Seconds();
		wagoner.radioButton.get("FEMALE").click();
		Sleep.for2Seconds();
		wagoner.getCount();
	}
	
	

	//@Test
	public void testAdidasShopRadioDriverCode() {

		String url = "https://shop.adidas.co.in/adidas/account/register.do";
		driver.get(url);
		Sleep.for5Seconds();
		clickOnLocation(driver.findElement(By.id("userName")));
		Sleep.for2Seconds();
		jsSetText(driver.findElement(By.id("userName")), "sdfdsfs");
		Sleep.for2Seconds();
	}
	
}
