package mks.uiautowagon.interactor.interutil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.MyDriver;
import mks.uiautowagon.interactor.WagonerFacade;

public class SupportUtil {

	protected WebDriver driver = null;

	public SupportUtil() {
		this.driver = MyDriver.getDriver();
	}
	
	public String getAttributes(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		Object elementAttributes = executor.executeScript(
				"var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;",
				element);
		return elementAttributes.toString();
	}
	
	public String labelTagText(List<WebElement> elements) {

		for (WebElement element : elements) {
			if (element.getTagName().equalsIgnoreCase("label")) {
				return element.getText().trim();
			}
		}
		return null;
	}

	public List<WebElement> readDomElements() {

		List<WebElement> inputElements = driver.findElements(By.xpath("//body//*"));
		System.out.println("inputElements size is : " + inputElements.size());
		return inputElements;
	}

	public List<String> getElementsText(List<WebElement> elements) {
		List<String> allTexts = new ArrayList<>();
		for (WebElement element : elements) {
			allTexts.add(element.getText().trim());
		}
		return allTexts;
	}
	
}
