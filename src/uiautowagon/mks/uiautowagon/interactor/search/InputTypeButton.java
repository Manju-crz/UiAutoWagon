package mks.uiautowagon.interactor.search;

import org.openqa.selenium.WebElement;

public class InputTypeButton implements InputTypes {

	private String buttonText = null;
	private WebElement webElement = null;

	String getButtonText() {
		return buttonText;
	}

	void setButtonText(String buttonText) {
		this.buttonText = buttonText;
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
