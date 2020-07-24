package mks.test.textfields;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import mks.driver.base.DriverBase;
import mks.driver.webutils.BrowserUtil;
import mks.java.util.Sleep;
import mks.uiautowagon.interactor.WagonerFacade;
import mks.uiautowagon.interactor.store.TextFieldsStore;

public class TestWithFacade extends DriverBase {

	//@Test
	public void testEbay() {
		
		String url = "https://www.ebay.com/";
		driver.get(url);
		Sleep.for2Seconds();
	}


	@Test
	public void testEbay1() {
		
		String url = "https://www.ebay.com/";
		driver.get(url);
		
		WagonerFacade wagoner = new WagonerFacade(driver);
		//wagoner.textField.get("Search for anything").sendKeys("qweqweqwe");
		//wagoner.textField.get1();
		Sleep.for2Seconds();
		System.out.println("TextFieldsStore.textFieldsList is : " + TextFieldsStore.textFieldsList.size());
		TextFieldsStore.textFieldsList.clear();
		
		wagoner = new WagonerFacade(driver);
		//wagoner.textField.get1();
		Sleep.for2Seconds();
		System.out.println("TextFieldsStore.textFieldsList is : " + TextFieldsStore.textFieldsList.size());
		

		WagonerFacade wagoner1 = new WagonerFacade(driver);
		//wagoner.textField.get("Search for anything").sendKeys("qweqweqwe");
		//wagoner1.textField.get1();
		Sleep.for2Seconds();
		System.out.println("TextFieldsStore.textFieldsList is : " + TextFieldsStore.textFieldsList.size());
		TextFieldsStore.textFieldsList.clear();
		
		wagoner1 = new WagonerFacade(driver);
		//wagoner1.textField.get1();
		Sleep.for2Seconds();
		System.out.println("TextFieldsStore.textFieldsList is : " + TextFieldsStore.textFieldsList.size());
		
		
		
		
		List<WebElement> inputElements = driver.findElements(By.xpath("//body//*"));
		System.out.println("inputElements size is : " + inputElements.size());
		/*
		url = "https://app-sjqe.marketo.com/homepage/login";
		driver.get(url);
		wagoner = wagoner.reload();
		wagoner.textField.get("Email").sendKeys("qwdfdfgvsdfds@feqweqwe.com");
		Sleep.for2Seconds();
		
		url = "https://www.ebay.com/";
		driver.get(url);
		wagoner = wagoner.reload();
		wagoner.textField.get("Search for anything").sendKeys("qweqweqwe");
		Sleep.for2Seconds();
		*/
		
		/*
		WebDriver secondDriver = new BrowserUtil().launchChrome();
		url = "https://app-sjqe.marketo.com/homepage/login";
		secondDriver.get(url);
		wagoner = new WagonerFacade(secondDriver);
		wagoner.textField.get("Email").sendKeys("qwdfdfgvsdfds@feqweqwe.com");
		Sleep.for2Seconds();*/
		
	}
	
	
	
	
	
}
