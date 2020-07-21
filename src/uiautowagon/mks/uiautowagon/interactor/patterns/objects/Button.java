package mks.uiautowagon.interactor.patterns.objects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

public class Button {

	private WebElement element = null;
	private String buttonText = null;
	private List<String> buttonInnerSpanTexts = new ArrayList<>();
	private String inputValueText = null;
	private List<String> siblingSpanTexts = new ArrayList<>();

	
	public String getButtonText() {
		return buttonText;
	}

	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}

	public WebElement getElement() {
		return element;
	}

	public void setElement(WebElement element) {
		this.element = element;
	}

	public String getInputValueText() {
		return inputValueText;
	}

	public void setInputValueText(String inputValueText) {
		this.inputValueText = inputValueText;
	}

	public List<String> getButtonInnerSpanTexts() {
		return buttonInnerSpanTexts;
	}

	public void setButtonInnerSpanTexts(List<String> buttonInnerSpanTexts) {
		this.buttonInnerSpanTexts = buttonInnerSpanTexts;
	}

	public List<String> getSiblingSpanTexts() {
		return siblingSpanTexts;
	}

	public void setSiblingSpanTexts(List<String> siblingSpanTexts) {
		this.siblingSpanTexts = siblingSpanTexts;
	}

	public WebElement compare(String elementText) {

		if ((buttonText != null) && buttonText.trim().equalsIgnoreCase(elementText))
			return element;
		else if (buttonInnerSpanTexts.contains(elementText))
			return element;
		else if ((inputValueText != null) && inputValueText.trim().equalsIgnoreCase(elementText))
			return element;
		else if (siblingSpanTexts.contains(elementText))
			return element;
		return null;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		if (buttonText != null) {
			str.append("Button Text : " + buttonText);
		}
		if (!buttonInnerSpanTexts.isEmpty()) {
			str.append("Button Inner Span Texts : " + buttonInnerSpanTexts);
		}
		if (!inputValueText.isEmpty()) {
			str.append("Input Value Text" + inputValueText);
		}
		if (!siblingSpanTexts.isEmpty()) {
			str.append("Sibling SpanT exts" + siblingSpanTexts);
		}
		if (element != null) {
			str.append("Element tag : " + element.getTagName());
		}
		return str.toString();
	}
	
}
