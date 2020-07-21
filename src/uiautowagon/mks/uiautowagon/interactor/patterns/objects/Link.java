package mks.uiautowagon.interactor.patterns.objects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

public class Link {

	private String linkText = null;
	private WebElement element = null;
	
	public String getLinkText() {
		return linkText;
	}
	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}
	public WebElement getElement() {
		return element;
	}
	public void setElement(WebElement element) {
		this.element = element;
	}
	

	public WebElement compare(String elementText) {

		if ((linkText != null) && linkText.trim().equalsIgnoreCase(elementText))
			return element;
		return null;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		if (linkText != null) {
			str.append("Link Text : " + linkText);
		}
		if (element != null) {
			str.append("Element tag : " + element.getTagName());
		}
		return str.toString();
	}
	
}
