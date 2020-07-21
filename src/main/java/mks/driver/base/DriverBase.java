package mks.driver.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import mks.driver.webutils.BrowserUtil;

public class DriverBase {
	
	protected WebDriver driver = null;
	
	@BeforeClass
	public void getBrowser() {
		driver = new BrowserUtil().launchChrome();
	}
	
	@AfterClass
	public void quitBrowser() {
		driver.quit();
	}
	
}
