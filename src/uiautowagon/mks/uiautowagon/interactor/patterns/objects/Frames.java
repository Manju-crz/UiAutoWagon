package mks.uiautowagon.interactor.patterns.objects;

import org.openqa.selenium.WebElement;

public class Frames {

	private String attributeStr = null;

	public String getAttributeStr() {
		return attributeStr;
	}

	public void setAttributeStr(String attributeStr) {
		this.attributeStr = attributeStr;
	}

	private WebElement element = null;

	public WebElement getElement() {
		return element;
	}

	public void setElement(WebElement frame) {
		this.element = frame;
	}

	public WebElement compare(String elementText) {
		
		return null;
	}
	
	public String toString() {

		return "iFrame attributes" + attributeStr;
	}
	
}
