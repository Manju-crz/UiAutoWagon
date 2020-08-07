package mks.uiautowagon.interactor;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.interutil.SupportUtil;

public class CurrentElement {

	private WebElement element = null;

	private String tagName = null;
	private String attributes = null;
	private String typeAttribute = null;
	private String elementTxt = null;
	
	public WebElement getElement() {
		return element;
	}

	public void setElement(WebElement element) {
		this.element = element;
	}

	public String getTagName() {
		if(tagName == null) {
			tagName = element.getTagName();
		}
		return tagName;
	}

	public String getAttributes() {
		if (attributes == null)
			attributes = new SupportUtil().getAttributes(element);
		return attributes;
	}

	public String getTypeAttribute() {
		if (typeAttribute == null) {
			typeAttribute = element.getAttribute("type");
		}
		return typeAttribute;
	}

	public String getElementText() {
		if (elementTxt == null) {
			elementTxt = element.getText().trim();
		}
		return elementTxt;
	}

}
