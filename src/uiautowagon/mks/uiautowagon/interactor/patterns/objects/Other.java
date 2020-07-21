package mks.uiautowagon.interactor.patterns.objects;

import org.openqa.selenium.WebElement;

public class Other {

	private String tagName = null;
	private String attributesStr = null;
	private WebElement element = null;

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getAttributesStr() {
		return attributesStr;
	}

	public void setAttributesStr(String attributesStr) {
		this.attributesStr = attributesStr;
	}

	public WebElement getElement() {
		return element;
	}

	public void setElement(WebElement element) {
		this.element = element;
	}

}
