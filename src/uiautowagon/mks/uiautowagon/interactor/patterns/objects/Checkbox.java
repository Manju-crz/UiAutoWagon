package mks.uiautowagon.interactor.patterns.objects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.CurrentElement;

public class Checkbox {

	private String checkBoxLabel = null;
	private List<String> spanTexts = new ArrayList<String>();
	private boolean isParentElementAnchor = false;

	private String parentLabelText = null;
	private String grandParentLabelText = null;

	private WebElement siblingLabel = null;
	
	private String upto4LevelParentsHavingClassWithCheckboxTxtAndContainsInnerSpan = null;
	private String parentPHavingText = null;
	
	public WebElement getSiblingLabel() {
		return siblingLabel;
	}

	public void setSiblingLabel(WebElement siblingLabel) {
		this.siblingLabel = siblingLabel;
	}

	public WebElement getParentLabel() {
		return parentLabel;
	}

	public void setParentLabel(WebElement parentLabel) {
		this.parentLabel = parentLabel;
	}

	private WebElement parentLabel = null;

	private CurrentElement cElement = null;
	
	public CurrentElement getcElement() {
		return cElement;
	}

	public void setcElement(CurrentElement cElement) {
		this.cElement = cElement;
	}

	public String getParentLabelText() {
		return parentLabelText;
	}

	public void setParentLabelText(String parentLabelText) {
		this.parentLabelText = parentLabelText;
	}

	public String getGrandParentLabelText() {
		return grandParentLabelText;
	}

	public void setGrandParentLabelText(String grandParentLabelText) {
		this.grandParentLabelText = grandParentLabelText;
	}

	public String getCheckBoxLabel() {
		return checkBoxLabel;
	}

	public void setCheckBoxLabel(String checkBoxLabel) {
		this.checkBoxLabel = checkBoxLabel;
	}

	public List<String> getSpanTexts() {
		return spanTexts;
	}

	public void setSpanTexts(List<String> spanTexts) {
		this.spanTexts = spanTexts;
	}

	public String getUpto4LevelParentsHavingClassWithCheckboxTxtAndContainsInnerSpan() {
		return upto4LevelParentsHavingClassWithCheckboxTxtAndContainsInnerSpan;
	}

	public void setUpto4LevelParentsHavingClassWithCheckboxTxtAndContainsInnerSpan(
			String upto4LevelParentsHavingClassWithCheckboxTxtAndContainsInnerSpan) {
		this.upto4LevelParentsHavingClassWithCheckboxTxtAndContainsInnerSpan = upto4LevelParentsHavingClassWithCheckboxTxtAndContainsInnerSpan;
	}

	public boolean isParentElementAnchor() {
		return isParentElementAnchor;
	}

	public String getParentPHavingText() {
		return parentPHavingText;
	}

	public void setParentPHavingText(String parentPHavingText) {
		this.parentPHavingText = parentPHavingText;
	}

	public void setParentElementAnchor(boolean isParentElementAnchor) {
		this.isParentElementAnchor = isParentElementAnchor;
	}

	public WebElement compare(String elementText) {

		if ((checkBoxLabel != null) && checkBoxLabel.equalsIgnoreCase(elementText))
			return siblingLabel;
		else if (spanTexts.contains(elementText))
			return cElement.getElement();
		else if ((parentLabelText != null) && parentLabelText.equalsIgnoreCase(elementText))
			return parentLabel;
		else if ((grandParentLabelText != null) && grandParentLabelText.equalsIgnoreCase(elementText))
			return cElement.getElement();
		else if ((parentPHavingText != null) && parentPHavingText.equalsIgnoreCase(elementText))
			return cElement.getElement();
		
		if ((upto4LevelParentsHavingClassWithCheckboxTxtAndContainsInnerSpan != null) && upto4LevelParentsHavingClassWithCheckboxTxtAndContainsInnerSpan.equalsIgnoreCase(elementText)) {
			System.out.println("upto4LevelParentsHavingClassWithCheckboxTxtAndContainsInnerSpan returned with tx t : " + upto4LevelParentsHavingClassWithCheckboxTxtAndContainsInnerSpan);
			return cElement.getElement();
		}
		
		return null;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		if (checkBoxLabel != null) {
			str.append("CheckBox Label : " + checkBoxLabel + ";");
		}
		if (!spanTexts.isEmpty()) {
			str.append("CheckBox SpanTexts : " + spanTexts + ";");
		}
		if (upto4LevelParentsHavingClassWithCheckboxTxtAndContainsInnerSpan != null) {
			str.append("upto4LevelParentsHavingClassWithCheckboxTxtAndContainsInnerSpan Txt : " + upto4LevelParentsHavingClassWithCheckboxTxtAndContainsInnerSpan + ";");
		}
		if (cElement.getElement() != null) {
			str.append("Element tag : " + cElement.getTagName());
		}
		return str.toString();
	}
	
}
