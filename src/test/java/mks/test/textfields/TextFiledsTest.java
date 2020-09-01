package mks.test.textfields;

import org.testng.annotations.Test;

import mks.driver.base.DriverBase;
import mks.java.util.Sleep;
import mks.uiautowagon.interactor.WagonerFacade;

public class TextFiledsTest extends DriverBase {

	@Test
	public void testMarketo() {

		String url = "https://www.onlinesbi.com/";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.getCount();
		wagoner.link.get("Login").click();
		Sleep.for2Seconds();
		wagoner.link.get("Continue to Login").click();
		Sleep.for2Seconds();
		wagoner.textField.get("Username*").sendKeys("sdfsdcfsdf");
		Sleep.for2Seconds();
		wagoner.textField.get("Password*").sendKeys("sfsdfsdfdsf");
		Sleep.for2Seconds();
		
	}
	
}
