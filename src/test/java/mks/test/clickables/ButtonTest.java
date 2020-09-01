package mks.test.clickables;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import mks.driver.base.DriverBase;
import mks.java.util.Sleep;
import mks.uiautowagon.interactor.WagonerFacade;

public class ButtonTest extends DriverBase {

	@Test
	public void testSBI() {

		String url = "https://www.hdfcbank.com/personal/ways-to-bank/online-banking/credit-card-netbanking";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.getCount();
		/*wagoner.textField.get("Search").sendKeys("sfdsfsfdf");
		wagoner.button.get("LOGIN").click();
		Sleep.for2Seconds();
		wagoner.radioButton.get("Credit Card Login").click();
		Sleep.for2Seconds();
		wagoner.reload();
		wagoner.link.get("Know more").click();*/
		Sleep.for2Seconds();
		wagoner.link.get("Forgot password / IPIN").click();
		Sleep.for2Seconds();
		driver.findElement(By.linkText("Reset Pin")).click();
		Sleep.for2Seconds();
		wagoner.textField.get("User ID / Customer ID :").sendKeys("sfdsfsfdf");
		
		wagoner.link.get("GO").click();
		Sleep.for2Seconds();
		wagoner.reload();
		wagoner.radioButton.get("Authentication with One Time Passwords (OTPs) sent on your registered Mobile No., and Email ID.").click();
		Sleep.for2Seconds();
	}
	
}
