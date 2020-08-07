package mks.uiautowagon.interactor.patterns.objects;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.CurrentElement;

public class Frames {

	private String attributeStr = null;
	private CurrentElement cElement = null;
	
	public CurrentElement getcElement() {
		return cElement;
	}

	public void setcElement(CurrentElement cElement) {
		this.cElement = cElement;
	}

	public String getAttributeStr() {
		return attributeStr;
	}

	public void setAttributeStr(String attributeStr) {
		this.attributeStr = attributeStr;
	}

	public WebElement compare(String elementText) {
		
		return null;
	}
	
	public String toString() {

		return "iFrame attributes" + attributeStr;
	}
	
}
