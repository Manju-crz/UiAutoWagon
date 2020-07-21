package mks.uiautowagon.interactor.search;

import org.openqa.selenium.WebElement;

public class InputTypeText implements InputTypes {

	private String label = null;
	private String placeholder = null;
	private WebElement webElement = null;

	String getLabel() {
		return label;
	}

	void setLabel(String label) {
		this.label = label;
	}

	String getPlaceholder() {
		return placeholder;
	}

	void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	WebElement getWebElement() {
		return webElement;
	}

	void setWebElement(WebElement webElement) {
		this.webElement = webElement;
	}


	@Override
	public InputTypes getType() {
		return this;
	}

}
