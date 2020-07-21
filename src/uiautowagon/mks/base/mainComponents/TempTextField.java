package mks.base.mainComponents;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import mks.base.driver.Connector;

public class TempTextField {

	WebDriver driver = null;

	public TempTextField(Connector connector) {
		this.driver = connector.driver;
	}
	
	private WebElement identifyParentElement(WebElement element) {
		WebElement parent = element.findElement(By.xpath("./.."));
		return parent;
	}
	
	
	public void setText(String componentLabel, String textToSet) {
		boolean existance = false;
		List<WebElement> inputElements = driver.findElements(By.xpath("//input"));
		Object elementAttributes = null;
		for (WebElement element : inputElements) {
			try {
				element.isDisplayed();
				existance = true;
			} catch (NoSuchElementException e) {

			}				
			if(existance) {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				elementAttributes = executor.executeScript(
						"var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;",
						element);
				System.out.println("Attributes list: " + elementAttributes.toString());
				if (elementAttributes.toString().contains("placeholder")) {
					System.out.println("Found placeholder....");
					System.out.println("elementAttributes.toString().split()[1] is : " + elementAttributes.toString().split("placeholder=")[1]);
					System.out.println("elementAttributes.toString().split(\"placeholder\")[1].split(\", \")[0] : " + elementAttributes.toString().split("placeholder")[1].split(", ")[0]);
					if (elementAttributes.toString().split("placeholder=")[1].split(", ")[0]
							.equalsIgnoreCase(componentLabel)) {
						element.sendKeys(textToSet);
						break;
					}
				}
				existance = false;
			}
		}
	}
	
	
}
