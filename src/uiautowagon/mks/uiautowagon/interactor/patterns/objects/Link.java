package mks.uiautowagon.interactor.patterns.objects;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.CurrentElement;

public class Link {

	private String linkText = null;
	
	private CurrentElement cElement = null;
	
	public CurrentElement getcElement() {
		return cElement;
	}
	public void setcElement(CurrentElement cElement) {
		this.cElement = cElement;
	}
	public String getLinkText() {
		return linkText;
	}
	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}
	

	public WebElement compare(String elementText) {

		if ((linkText != null) && linkText.trim().equalsIgnoreCase(elementText))
			return cElement.getElement();
		return null;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		if (linkText != null) {
			str.append("Link Text : " + linkText);
		}
		if (cElement.getElement() != null) {
			str.append("Element tag : " + cElement.getTagName());
		}
		return str.toString();
	}
	
}
