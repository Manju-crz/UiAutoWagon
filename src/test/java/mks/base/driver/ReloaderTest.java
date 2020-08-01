package mks.base.driver;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import mks.driver.base.DriverBase;
import mks.java.util.Sleep;
import mks.uiautowagon.interactor.WagonerFacade;

public class ReloaderTest extends DriverBase{

	@Test
	public void testMultiPages() {
		
		String url = "https://www.facebook.com/";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.textField.get("First name").sendKeys("gdfgdgdh");
		Sleep.for2Seconds();
		wagoner.getCount();
		
		url = "https://app-sjqe.marketo.com/";
		driver.get(url);
		wagoner.reload();
		wagoner.textField.get("Email").sendKeys("sadsads");
		Sleep.for2Seconds();
		wagoner.button.get("LOGIN").click();
		Sleep.for5Seconds();
		wagoner.getCount();
		
		url = "https://www.facebook.com/";
		driver.get(url);
		WagonerFacade wagoner1 = new WagonerFacade(driver);
		wagoner1.textField.get("First name").sendKeys("gdfgdgdh");
		Sleep.for2Seconds();
		wagoner1.getCount();
		
		url = "https://app-sjqe.marketo.com/";
		driver.get(url);
		wagoner1 = new WagonerFacade(driver);
		wagoner1.textField.get("Email").sendKeys("sadsads");
		Sleep.for2Seconds();
		wagoner1.getCount();
	}
	
	
	//@Test
	public void testMarketo() {
		
		String url = "https://app-sjqe.marketo.com/";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.textField.get("Email").sendKeys("sadsads");
		Sleep.for2Seconds();
		
	}
	

	//@Test
	public void testFl() {
		
		String url = "https://www.flipkart.com/";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.textField.get("Enter Password").sendKeys("gdfgdgdh");
		Sleep.for5Seconds();
	}
	
	

	//@Test
	public void testMarketoClickElementTest() {
		
		String url = "https://app-sjqe.marketo.com/";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.textField.get("Email").sendKeys("sadsads");
		Sleep.for2Seconds();
		
	}
	
	
}
