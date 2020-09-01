package mks.uiautowagon.interactor.patterns.objects;

import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.WebElement;

public class RadioButton {


	private WebElement element = null;
	private String siblingLabel = null;
	private String parentLabelText = null;
	
	private String parentDivText = null;
	
	private String divParentingToInputWithParentSiblingText = null;

	private String parentLabelsInnerSpanText = null;
	
	private String grandParentLabelsInnerSpansParentDivText = null;
	
	private String parentFontHavingText = null;
	private String inputHavingAriaLabel = null;
	private String inputHavingValue = null;
	
	public WebElement getElement() {
		return element;
	}

	public void setElement(WebElement element) {
		this.element = element;
	}

	public String getSiblingLabel() {
		return siblingLabel;
	}

	public void setSiblingLabel(String siblingLabel) {
		this.siblingLabel = siblingLabel;
	}

	public String getParentLabelText() {
		return parentLabelText;
	}

	public void setParentLabelText(String parentLabelText) {
		this.parentLabelText = parentLabelText;
	}

	public String getParentDivText() {
		return parentDivText;
	}

	public void setParentDivText(String parentDivText) {
		this.parentDivText = parentDivText;
	}

	public String getDivParentingToInputWithParentSiblingText() {
		return divParentingToInputWithParentSiblingText;
	}

	public void setDivParentingToInputWithParentSiblingText(String divParentingToInputWithParentSiblingText) {
		this.divParentingToInputWithParentSiblingText = divParentingToInputWithParentSiblingText;
	}

	public String getParentLabelsInnerSpanText() {
		return parentLabelsInnerSpanText;
	}

	public void setParentLabelsInnerSpanText(String parentLabelsInnerSpanText) {
		this.parentLabelsInnerSpanText = parentLabelsInnerSpanText;
	}

	public String getGrandParentLabelsInnerSpansParentDivText() {
		return grandParentLabelsInnerSpansParentDivText;
	}

	public void setGrandParentLabelsInnerSpansParentDivText(String grandParentLabelsInnerSpansParentDivText) {
		this.grandParentLabelsInnerSpansParentDivText = grandParentLabelsInnerSpansParentDivText;
	}

	public String getParentFontHavingText() {
		return parentFontHavingText;
	}

	public String getInputHavingAriaLabel() {
		return inputHavingAriaLabel;
	}

	public void setInputHavingAriaLabel(String inputHavingAriaLabel) {
		this.inputHavingAriaLabel = inputHavingAriaLabel;
	}
	
	public void setParentFontHavingText(String parentFontHavingText) {
		this.parentFontHavingText = parentFontHavingText;
	}

	public String getInputHavingValue() {
		return inputHavingValue;
	}

	public void setInputHavingValue(String inputHavingValue) {
		this.inputHavingValue = inputHavingValue;
	}

	public WebElement compare(String elementText) {

		if ((siblingLabel != null) && siblingLabel.equalsIgnoreCase(elementText))
			return element;
		else if ((parentLabelText != null) && parentLabelText.equalsIgnoreCase(elementText))
			return element;
		else if ((parentDivText != null) && parentDivText.equalsIgnoreCase(elementText))
			return element;
		else if ((divParentingToInputWithParentSiblingText != null) && divParentingToInputWithParentSiblingText.equalsIgnoreCase(elementText))
			return element;
		else if ((parentLabelsInnerSpanText != null) && parentLabelsInnerSpanText.equalsIgnoreCase(elementText))
			return element;
		else if ((grandParentLabelsInnerSpansParentDivText != null) && grandParentLabelsInnerSpansParentDivText.equalsIgnoreCase(elementText))
			return element;
		else if ((parentFontHavingText != null) && parentFontHavingText.equalsIgnoreCase(elementText))
			return element;
		else if ((inputHavingAriaLabel != null) && inputHavingAriaLabel.equalsIgnoreCase(elementText))
			return element;
		else if ((inputHavingValue != null) && inputHavingValue.equalsIgnoreCase(elementText))
			return element;
		

		if ((parentLabelsInnerSpanText != null) && parentLabelsInnerSpanText.trim().length() > 0) {
			if (new ArrayList<>(Arrays.asList(parentLabelsInnerSpanText.split("\n"))).contains(elementText))
				return element;
		}
		
		return null;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		if (siblingLabel != null) {
			str.append("Radiobutton Sibling Label : " + siblingLabel);
		}
		if (parentLabelText != null) {
			str.append("Radiobutton Parent Label Text : " + parentLabelText);
		}
		if (parentDivText != null) {
			str.append("Radiobutton Parent Div Text : " + parentDivText);
		}
		if (divParentingToInputWithParentSiblingText != null) {
			str.append("Radiobutton DivParenting To Input WithParent SiblingText : " + divParentingToInputWithParentSiblingText);
		}
		if (element != null) {
			str.append("Element tag : " + element.getTagName());
		}
		return str.toString();
	}
	
}
