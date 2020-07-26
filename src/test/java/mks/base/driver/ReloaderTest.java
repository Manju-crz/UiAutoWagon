package mks.base.driver;

import org.testng.annotations.Test;

import mks.driver.base.DriverBase;
import mks.java.util.Sleep;
import mks.uiautowagon.interactor.WagonerFacade;

public class ReloaderTest extends DriverBase{

	//@Test
	public void testMultiPages() {
		
		String url = "https://app-sjqe.marketo.com/";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.textField.get("Email").sendKeys("sadsads");
		Sleep.for2Seconds();
		
		url = "https://www.flipkart.com/";
		driver.get(url);
		wagoner = new WagonerFacade(driver);
		wagoner.textField.get("Enter Password").sendKeys("gdfgdgdh");
		Sleep.for5Seconds();
	}
	
	
	//@Test
	public void testMarketo() {
		
		String url = "https://app-sjqe.marketo.com/";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.textField.get("Email").sendKeys("sadsads");
		Sleep.for2Seconds();
		
	}
	

	@Test
	public void testFl() {
		
		String url = "https://www.flipkart.com/";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.textField.get("Enter Password").sendKeys("gdfgdgdh");
		Sleep.for5Seconds();
	}
	
}
