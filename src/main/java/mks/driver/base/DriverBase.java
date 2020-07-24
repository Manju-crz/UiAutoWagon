package mks.driver.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
	
	public void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public void jsSetText(WebElement element, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "arguments[0].value='" + value + "';";
		js.executeScript(script, element);
	}

	public void clickOnLocation(WebElement element) {
		new Actions(driver).moveToElement(element, 0, 0).click().build().perform();
	}
	
	
}
