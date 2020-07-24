package mks.test.clickables;

import org.testng.annotations.Test;

import mks.base.driver.Connector;
import mks.driver.base.DriverBase;
import mks.java.util.Sleep;

public class CheckboxTest extends DriverBase {

	@Test
	public void testMarketo() {
		String url = "https://app-sjqe.marketo.com/";
		driver.get(url);
		Connector connector = new Connector(driver);
		Sleep.for2Seconds();
	}
}
