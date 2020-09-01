package mks.test.clickables;

import org.testng.annotations.Test;

import mks.driver.base.DriverBase;
import mks.java.util.Sleep;
import mks.uiautowagon.interactor.WagonerFacade;

public class LinkTest extends DriverBase {

	@Test
	public void testSBI() {

		String url = "https://account.bbc.com/register?action=sign-in&context=homepage&isCasso=false&nonce=JcGlHvwS-wbWOpMOphTldDYU_GijSDzyTvlE&ptrt=https%3A%2F%2Fwww.bbc.com%2F&redirectUri=https%3A%2F%2Fsession.bbc.com%2Fsession%2Fcallback&service=IdSignInService&userOrigin=HOMEPAGE_GNL";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.getCount();
		wagoner.link.get("Under 16").click();
		Sleep.for5Seconds();
		
	}
	
}
