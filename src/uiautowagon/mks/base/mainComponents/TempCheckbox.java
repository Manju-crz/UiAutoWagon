package mks.base.mainComponents;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import mks.base.driver.Connector;

public class TempCheckbox {


	WebDriver driver = null;

	public TempCheckbox(Connector connector) {
		this.driver = connector.driver;
	}
	
	private WebElement identifyParentElement(WebElement element) {
		WebElement parent = element.findElement(By.xpath("./.."));
		return parent;
	}
	
	private List<WebElement> getAllChildElements(WebElement element){
		return driver.findElements(By.xpath(".//*"));
	}
	
	private boolean isItRequiredCheckbox(WebElement child, String checkboxLabel) {
		boolean existance = false;
		try {
			child.isDisplayed();
			existance = true;
		} catch (NoSuchElementException e) {

		}
		if(existance) {
			if(child.getText().trim().equalsIgnoreCase(checkboxLabel))
			{
				return true;
			}
		}
		return false;
	}
	
	public void clickOnCheckbox(String checkboxLabel) {
		boolean existance = false;
		List<WebElement> inputElements = driver.findElements(By.xpath("//input[@type='checkbox']"));
		for (WebElement element : inputElements) {
			try {
				element.isDisplayed();
				existance = true;
			} catch (NoSuchElementException e) {
				
			}
			if(existance) {
				WebElement parent = identifyParentElement(element);
				List<WebElement> children = getAllChildElements(parent);
				
				for(WebElement child : children) {
					if(isItRequiredCheckbox(child, checkboxLabel)) {
						child.click();
						break;
					}
				}
				existance = false;
			}
		}
	}
	
}
