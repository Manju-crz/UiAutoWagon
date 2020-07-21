package mks.uiautowagon.interactor.search;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.WagonerFacade;
import mks.uiautowagon.interactor.interutil.SupportUtil;
import mks.uiautowagon.interactor.patterns.TextFieldPatterns;
import mks.uiautowagon.interactor.patterns.objects.TextField;
import mks.uiautowagon.interactor.store.TextFieldsStore;

public class InputIdentification extends Identify{
	
	WebDriver driver = null;
	
	public InputIdentification() {
		driver = WagonerFacade.getDriver();
	}
	
	
	public WebElement identify(String text) {

		List<WebElement> inputElements = driver.findElements(By.xpath("//input"));
		String elementAttributes = null;
		for (WebElement tempElement : inputElements) {
			element = tempElement;
			if (isElementDisplayed()) {
				
				
				/*if(tempElement.getAttribute("type").equalsIgnoreCase("text")) {
				}
				elementAttributes = new SupportUtil(driver).getAttributes(tempElement);
				System.out.println("elementAttributes : " + elementAttributes);
				if (elementAttributes.toString().contains("placeholder")) {
					System.out.println("Found placeholder....");
					System.out.println("elementAttributes.toString().split()[1] is : "
							+ elementAttributes.toString().split("placeholder=")[1]);
					System.out.println("elementAttributes.toString().split(\"placeholder\")[1].split(\", \")[0] : "
							+ elementAttributes.toString().split("placeholder")[1].split(", ")[0]);
					if (elementAttributes.toString().split("placeholder=")[1].split(", ")[0].equalsIgnoreCase(text)) {
						return tempElement;
					}
				}*/
				
				System.out.println("tempElement picked up is : " + new SupportUtil(driver).getAttributes(tempElement));
				
				TextFieldPatterns tfp = new TextFieldPatterns(tempElement);
				TextField tf = tfp.findPattern();
				if (tf != null) {
					System.out.println("tf.toString() is : " + tf.toString());
					if ((tf.getPlaceholder() != null) && (tf.getPlaceholder().equalsIgnoreCase(text)))
						return tf.getElement();
				}
			}

		}
		return null;
	}
	
	
	
	
	public void store() {

		List<WebElement> inputElements = driver.findElements(By.xpath("//input"));
		String elementAttributes = null;
		for (WebElement tempElement : inputElements) {
			element = tempElement;
			if (isElementDisplayed()) {
				
				System.out.println("tempElement picked up is : " + new SupportUtil(driver).getAttributes(tempElement));
				
				TextFieldPatterns tfp = new TextFieldPatterns(tempElement);
				TextField tf = tfp.findPattern();
				if (tf != null) {
					new TextFieldsStore().add(tf);
				}
			}
		}
	}
	
	
	
}
