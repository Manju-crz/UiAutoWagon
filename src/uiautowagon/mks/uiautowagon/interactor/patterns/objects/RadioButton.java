package mks.uiautowagon.interactor.patterns.objects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

public class RadioButton {


	private WebElement element = null;
	private String siblingLabel = null;
	private String parentLabelText = null;
	
	private String parentDivText = null;
	

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
	
	public WebElement compare(String elementText) {

		if ((siblingLabel != null) && siblingLabel.equalsIgnoreCase(elementText))
			return element;
		else if ((parentLabelText != null) && parentLabelText.equalsIgnoreCase(elementText))
			return element;
		else if ((parentDivText != null) && parentDivText.equalsIgnoreCase(elementText))
			return element;
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
		if (element != null) {
			str.append("Element tag : " + element.getTagName());
		}
		return str.toString();
	}
	
}
