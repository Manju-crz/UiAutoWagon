package mks.test.textarea;

import org.testng.annotations.Test;

import mks.driver.base.DriverBase;
import mks.java.util.Sleep;
import mks.uiautowagon.interactor.WagonerFacade;

public class TextAreaTest extends DriverBase {

	@Test
	public void testMarketo() {

		String url = "https://www.mediawiki.org/wiki/User_talk:49.37.196.48";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.getCount();
		wagoner.textField.get("Start a new topic").sendKeys("wedwedewdwf");
		Sleep.for2Seconds();
		wagoner.textArea.get("Post a new message to \"User talk:49.37.196.48\"").sendKeys("adsfsdfsdfsdfsdfsdfsdf");
		Sleep.for5Seconds();
		
	}
	
}
