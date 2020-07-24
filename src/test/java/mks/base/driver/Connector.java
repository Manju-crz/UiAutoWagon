package mks.base.driver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Connector {
	
	public WebDriver driver = null;
	
	public Connector(WebDriver driver) {
		this.driver = driver;
	}
	
	
	
	protected List<WebElement> findDriverElements(By locator) {
		return driver.findElements(locator);
	}
	
	
	
	
}
