package mks.uiautowagon.interactor.patterns.objects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.CurrentElement;

public class Button {

	private String buttonText = null;
	private List<String> buttonInnerSpanTexts = new ArrayList<>();
	private String inputValueText = null;
	private List<String> siblingSpanTexts = new ArrayList<>();

	private String paperButtonTxt = null;
	private String paperButtonArialLabel = null;
	private String paperButtonInnerYtFormattedString = null;
	
	private CurrentElement cElement = null;
	
	public CurrentElement getcElement() {
		return cElement;
	}

	public void setcElement(CurrentElement cElement) {
		this.cElement = cElement;
	}

	public String getButtonText() {
		return buttonText;
	}

	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
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
	public String getPaperButtonTxt() {
		return paperButtonTxt;
	}

	public void setPaperButtonTxt(String paperButtonTxt) {
		this.paperButtonTxt = paperButtonTxt;
	}

	public String getPaperButtonArialLabel() {
		return paperButtonArialLabel;
	}

	public void setPaperButtonArialLabel(String paperButtonArialLabel) {
		this.paperButtonArialLabel = paperButtonArialLabel;
	}

	public String getPaperButtonInnerYtFormattedString() {
		return paperButtonInnerYtFormattedString;
	}

	public void setPaperButtonInnerYtFormattedString(String paperButtonInnerYtFormattedString) {
		this.paperButtonInnerYtFormattedString = paperButtonInnerYtFormattedString;
	}
	
	public CurrentElement compare(String elementText) {

		if (cElement.getTagName().equalsIgnoreCase("paper-button")) {
			if ((paperButtonTxt != null) && paperButtonTxt.trim().equalsIgnoreCase(elementText))
				return cElement;
			else if ((paperButtonInnerYtFormattedString != null)
					&& paperButtonInnerYtFormattedString.trim().equalsIgnoreCase(elementText))
				return cElement;
			else if ((paperButtonArialLabel != null) && paperButtonArialLabel.trim().equalsIgnoreCase(elementText))
				return cElement;
		} else {
			if ((buttonText != null) && buttonText.trim().equalsIgnoreCase(elementText))
				return cElement;
			else if (buttonInnerSpanTexts.contains(elementText))
				return cElement;
			else if ((inputValueText != null) && inputValueText.trim().equalsIgnoreCase(elementText))
				return cElement;
			else if (siblingSpanTexts.contains(elementText))
				return cElement;
		}

		return null;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		if (buttonText != null) {
			str.append("Button Text : " + buttonText + ";");
		}
		if (!buttonInnerSpanTexts.isEmpty()) {
			str.append("Button Inner Span Texts : " + buttonInnerSpanTexts + ";");
		}
		if (!inputValueText.isEmpty()) {
			str.append("Input Value Text" + inputValueText + ";");
		}
		if (!siblingSpanTexts.isEmpty()) {
			str.append("Sibling SpanTexts" + siblingSpanTexts + ";");
		}
		if (cElement.getElement() != null) {
			str.append("ElementTag : " + cElement.getTagName());
		}
		return str.toString();
	}
	
}
