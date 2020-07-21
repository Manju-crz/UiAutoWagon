package mks.test.textfields;


import org.testng.annotations.Test;

import mks.base.driver.Connector;
import mks.base.mainComponents.TempTextField;
import mks.driver.base.DriverBase;
import mks.java.util.Sleep;

public class TextboxTest extends DriverBase{
	
	
	@Test
	public void testEbay() {
		
		String url = "https://www.ebay.com/";
		driver.get(url);
		Connector connector = new Connector(driver);
		
		new TempTextField(connector).setText("Search for anything", "qweqweqwe");
		Sleep.for2Seconds();
	}
	
	
	//@Test
	public void testMarketo() {

		String url = "https://app-sjqe.marketo.com/";
		driver.get(url);
		Connector connector = new Connector(driver);
		TempTextField txtField = new TempTextField(connector);
		txtField.setText("Email", "mks@marketo.com");
		txtField.setText("Password", "asfsdfsdf");
		Sleep.for2Seconds();
	}
	
	
	
	@Test
	public void testIRCTC() {

		String url = "https://www.irctc.co.in/nget/train-search";
		driver.get(url);
		Connector connector = new Connector(driver);
		TempTextField txtField = new TempTextField(connector);
		txtField.setText("From*", "mks@marketo.com");
		txtField.setText("To*", "asfsdfsdf");
		Sleep.for2Seconds();
	}
	
	
}
