package mks.uiautowagon.interactor;

import org.openqa.selenium.WebElement;

public class CurrentElement {

	private WebElement element = null;

	private String tagName = null;
	private String attributes = null;

	public WebElement getElement() {
		return element;
	}

	public void setElement(WebElement element) {
		this.element = element;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

}
