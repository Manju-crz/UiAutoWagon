package mks.test.clickables;

import org.testng.annotations.Test;

import mks.base.driver.Connector;
import mks.base.mainComponents.TempCheckbox;
import mks.base.mainComponents.TempTextField;
import mks.driver.base.DriverBase;
import mks.java.util.Sleep;

public class CheckboxTest extends DriverBase {

	@Test
	public void testMarketo() {
		String url = "https://app-sjqe.marketo.com/";
		driver.get(url);
		Connector connector = new Connector(driver);
		TempTextField txtField = new TempTextField(connector);
		txtField.setText("Email", "mks@marketo.com");
		txtField.setText("Password", "asfsdfsdf");
		TempCheckbox checkbx = new TempCheckbox(connector);
		checkbx.clickOnCheckbox("Remember Email");
		Sleep.for2Seconds();
	}
}
