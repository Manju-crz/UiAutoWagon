package mks.base.driver;

import org.testng.annotations.Test;

import mks.driver.base.DriverBase;
import mks.java.util.Sleep;
import mks.uiautowagon.interactor.WagonerFacade;

public class ReloaderTest extends DriverBase {

	// @Test
	public void testMultiPages() {

		String url = "https://www.facebook.com/";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.getCount();
		wagoner.textField.get("First name").sendKeys("gdfgdgdh");
		Sleep.for2Seconds();
		wagoner.clickElement.get("Log In").click();
		Sleep.for5Seconds();
		// wagoner.getCount();

		url = "https://app-sjqe.marketo.com/";
		driver.get(url);
		wagoner.reload();
		wagoner.textField.get("Email").sendKeys("sadsads");
		Sleep.for2Seconds();
		wagoner.button.get("LOGIN").click();
		Sleep.for5Seconds();
		// wagoner.getCount();

		url = "https://www.facebook.com/";
		driver.get(url);
		WagonerFacade wagoner1 = new WagonerFacade(driver);
		wagoner1.textField.get("First name").sendKeys("gdfgdgdh");
		Sleep.for2Seconds();
		// wagoner1.getCount();

		url = "https://app-sjqe.marketo.com/";
		driver.get(url);
		wagoner1 = new WagonerFacade(driver);
		wagoner1.textField.get("Email").sendKeys("sadsads@sd.com");
		Sleep.for2Seconds();
		wagoner1.getCount();
		wagoner1.clickElement.get("LOGIN").click();
		Sleep.for5Seconds();
		// wagoner1.getCount();
	}

	//@Test
	public void testMarketo() {

		String url = "https://app-sjqe.marketo.com/";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.textField.get("Email").sendKeys("sadsads");
		Sleep.for2Seconds();
		wagoner.getCount();
		System.out.println("Going to find LOGIN");
		wagoner.clickElement.get("LOGIN").click();
		Sleep.for5Seconds();
		
		
		wagoner.textField.get("Email").sendKeys("sadsads");
		Sleep.for2Seconds();
		wagoner.getCount();
		System.out.println("Going to find LOGIN");
		wagoner.clickElement.get("LOGIN").click();
		Sleep.for5Seconds();
	}

	
	
	
	@Test
	public void testFacebook() {

		String url = "https://www.facebook.com/";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.textField.get("First name").sendKeys("gdfgdgdh");
		wagoner.textField.get("Surname").sendKeys("gdfgdgdh");
		wagoner.textField.get("Mobile number or email address").sendKeys("gdfgdgdh@sdfds.com");
		wagoner.textField.get("New password").sendKeys("gdfgdgdh");
		wagoner.radioButton.get("Male").click();
		wagoner.button.get("Sign Up").click();
		Sleep.for5Seconds();
		wagoner.textField.get("Re-enter email address").sendKeys("gdfgdgdh@sdfds.com");
		Sleep.for2Seconds();
		
		wagoner.clickElement.get("Sign Up").click();
		Sleep.for5Seconds();
	}

	// @Test
	public void testMarketoClickElementTest() {

		String url = "https://app-sjqe.marketo.com/";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.textField.get("Email").sendKeys("sadsads");
		Sleep.for2Seconds();

	}

}
