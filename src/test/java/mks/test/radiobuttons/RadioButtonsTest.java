package mks.test.radiobuttons;

import org.testng.annotations.Test;

import mks.driver.base.DriverBase;
import mks.java.util.Sleep;
import mks.uiautowagon.interactor.WagonerFacade;

public class RadioButtonsTest extends DriverBase {

	//@Test
	public void testMarketo() {

		String url = "https://bookpipeline.com/shop/unpublished-contest?gclid=EAIaIQobChMIrdTlwNGd6wIVogrVCh3coQIrEAEYASAAEgL_O_D_BwE,FAILED";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.getCount();
		wagoner.radioButton.get("Fiction – YA").click();
		Sleep.for2Seconds();
		
	}
	
	@Test
	public void testMagicBricks() {

		String url = "https://www.magicbricks.com/bricks/User-Registration1";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.getCount();
		wagoner.radioButton.get("Buyer/Owner").click();
		wagoner.radioButton.get("Builder").click();
		Sleep.for2Seconds();
		wagoner.textField.get("Name").sendKeys("sfsddsdf");
		wagoner.textField.get("Email").sendKeys("sfsddsdf");
		wagoner.textField.get("Password").sendKeys("sfsddsdf");
		wagoner.textField.get("Mobile Number").sendKeys("4545656532");
		wagoner.button.get("Sign Up").click();
		Sleep.for2Seconds();
		wagoner.link.get("Login now").click();
		Sleep.for2Seconds();
		wagoner.textField.get("Enter Email ID or Mobile No.").sendKeys("4545656532");
		wagoner.button.get("Next").click();
		Sleep.for2Seconds();
		
		
	}
}
