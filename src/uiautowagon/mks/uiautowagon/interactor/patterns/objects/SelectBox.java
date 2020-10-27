package mks.uiautowagon.interactor.patterns.objects;

import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.CurrentElement;

public class SelectBox {

	private String placeholder = null;
	private String labelText = null;
	private String labelFor = null;
	
	private String inputValue = null;
	private String siblingSpanText = null;
	
	private String trParallelLabel = null;

	private String divNeighbourPlaceholder = null;
	private String ariaLabel = null;
	
	private String parentDivsSiblingLabel = null;
	private String parentDivsSiblingInnerLabel = null;
	private String parentBTxt = null;
	
	private String grandParentsSiblingHavingInnerSpanTxt = null;
	private String parentsSiblingH3Label = null;
	private String parentsSiblingH4Label = null;
	private String parentsSiblingH5Label = null;
	
	private String siblingsInnerLabel = null;
	private String grandParentDivsSiblingDivsChildSpan = null;
	private String trHavingTwoTdOneForLabelAndOneForField = null;
	
	private CurrentElement cElement = null;
	
	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getLabelText() {
		return labelText;
	}

	public void setLabelText(String labelText) {
		this.labelText = labelText;
	}

	public String getTrParallelLabel() {
		return trParallelLabel;
	}

	public void setTrParallelLabel(String trParallelLabel) {
		this.trParallelLabel = trParallelLabel;
	}

	public String getLabelFor() {
		return labelFor;
	}

	public void setLabelFor(String labelFor) {
		this.labelFor = labelFor;
	}

	public String getDivNeighbourPlaceholder() {
		return divNeighbourPlaceholder;
	}

	public void setDivNeighbourPlaceholder(String divNeighbourPlaceholder) {
		this.divNeighbourPlaceholder = divNeighbourPlaceholder;
	}

	public String getAriaLabel() {
		return ariaLabel;
	}

	public void setAriaLabel(String ariaLabel) {
		this.ariaLabel = ariaLabel;
	}

	public String getParentDivsSiblingLabel() {
		return parentDivsSiblingLabel;
	}

	public void setParentDivsSiblingLabel(String parentDivsSiblingLabel) {
		this.parentDivsSiblingLabel = parentDivsSiblingLabel;
	}
	
	public String getParentDivsSiblingInnerLabel() {
		return parentDivsSiblingInnerLabel;
	}

	public void setParentDivsSiblingInnerLabel(String parentDivsSiblingInnerLabel) {
		this.parentDivsSiblingInnerLabel = parentDivsSiblingInnerLabel;
	}

	public String getInputValue() {
		return inputValue;
	}

	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}

	public String getSiblingSpanText() {
		return siblingSpanText;
	}

	public void setSiblingSpanText(String siblingSpanText) {
		this.siblingSpanText = siblingSpanText;
	}

	public String getParentBTxt() {
		return parentBTxt;
	}

	public void setParentBTxt(String parentBTxt) {
		this.parentBTxt = parentBTxt;
	}

	public String getGrandParentsSiblingHavingInnerSpanTxt() {
		return grandParentsSiblingHavingInnerSpanTxt;
	}

	public void setGrandParentsSiblingHavingInnerSpanTxt(String grandParentsSiblingHavingInnerSpanTxt) {
		this.grandParentsSiblingHavingInnerSpanTxt = grandParentsSiblingHavingInnerSpanTxt;
	}

	public String getParentsSiblingH3Label() {
		return parentsSiblingH3Label;
	}

	public void setParentsSiblingH3Label(String parentsSiblingH3Label) {
		this.parentsSiblingH3Label = parentsSiblingH3Label;
	}
	
	public String getParentsSiblingH4Label() {
		return parentsSiblingH4Label;
	}

	public void setParentsSiblingH4Label(String parentsSiblingH4Label) {
		this.parentsSiblingH4Label = parentsSiblingH4Label;
	}

	public String getParentsSiblingH5Label() {
		return parentsSiblingH5Label;
	}

	public void setParentsSiblingH5Label(String parentsSiblingH5Label) {
		this.parentsSiblingH5Label = parentsSiblingH5Label;
	}

	public String getSiblingsInnerLabel() {
		return siblingsInnerLabel;
	}

	public void setSiblingsInnerLabel(String siblingsInnerLabel) {
		this.siblingsInnerLabel = siblingsInnerLabel;
	}

	public String getGrandParentDivsSiblingDivsChildSpan() {
		return grandParentDivsSiblingDivsChildSpan;
	}

	public void setGrandParentDivsSiblingDivsChildSpan(String grandParentDivsSiblingDivsChildSpan) {
		this.grandParentDivsSiblingDivsChildSpan = grandParentDivsSiblingDivsChildSpan;
	}

	public String getTrHavingTwoTdOneForLabelAndOneForField() {
		return trHavingTwoTdOneForLabelAndOneForField;
	}

	public void setTrHavingTwoTdOneForLabelAndOneForField(String trHavingTwoTdOneForLabelAndOneForField) {
		this.trHavingTwoTdOneForLabelAndOneForField = trHavingTwoTdOneForLabelAndOneForField;
	}

	public CurrentElement getcElement() {
		return cElement;
	}

	public void setcElement(CurrentElement cElement) {
		this.cElement = cElement;
	}

	public WebElement compare(String elementText) {

		if ((placeholder != null) && placeholder.trim().equalsIgnoreCase(elementText))
			return cElement.getElement();
		else if ((labelText != null) && labelText.trim().equalsIgnoreCase(elementText))
			return cElement.getElement();
		else if ((labelFor != null) && labelFor.trim().equalsIgnoreCase(elementText))
			return cElement.getElement();
		else if ((trParallelLabel != null) && trParallelLabel.trim().equalsIgnoreCase(elementText))
			return cElement.getElement();
		else if ((divNeighbourPlaceholder != null) && divNeighbourPlaceholder.trim().equalsIgnoreCase(elementText))
			return cElement.getElement();
		else if ((ariaLabel != null) && ariaLabel.trim().equalsIgnoreCase(elementText))
			return cElement.getElement();
		else if ((parentDivsSiblingLabel != null) && parentDivsSiblingLabel.trim().equalsIgnoreCase(elementText))
			return cElement.getElement();
		else if ((parentDivsSiblingInnerLabel != null) && parentDivsSiblingInnerLabel.trim().equalsIgnoreCase(elementText))
			return cElement.getElement();
		else if ((inputValue != null) && inputValue.trim().equalsIgnoreCase(elementText))
			return cElement.getElement();
		else if ((siblingSpanText != null) && siblingSpanText.trim().equalsIgnoreCase(elementText))
			return cElement.getElement();
		else if ((parentBTxt != null) && parentBTxt.trim().equalsIgnoreCase(elementText))
			return cElement.getElement();
		else if ((grandParentsSiblingHavingInnerSpanTxt != null) && grandParentsSiblingHavingInnerSpanTxt.trim().equalsIgnoreCase(elementText))
			return cElement.getElement();
		else if ((parentsSiblingH3Label != null) && parentsSiblingH3Label.trim().equalsIgnoreCase(elementText))
			return cElement.getElement();
		else if ((parentsSiblingH4Label != null) && parentsSiblingH4Label.trim().equalsIgnoreCase(elementText))
			return cElement.getElement();
		else if ((parentsSiblingH5Label != null) && parentsSiblingH5Label.trim().equalsIgnoreCase(elementText))
			return cElement.getElement();
		else if ((siblingsInnerLabel != null) && siblingsInnerLabel.trim().equalsIgnoreCase(elementText))
			return cElement.getElement();
		else if ((grandParentDivsSiblingDivsChildSpan != null) && grandParentDivsSiblingDivsChildSpan.trim().equalsIgnoreCase(elementText))
			return cElement.getElement();
		else if ((trHavingTwoTdOneForLabelAndOneForField != null) && trHavingTwoTdOneForLabelAndOneForField.trim().equalsIgnoreCase(elementText))
			return cElement.getElement();
		
		
		if ((parentDivsSiblingLabel != null) && parentDivsSiblingLabel.trim().length() > 0) {
			if (new ArrayList<>(Arrays.asList(parentDivsSiblingLabel.split("\n"))).contains(elementText))
				return cElement.getElement();
		}
		
		
		return null;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		if (labelText != null) {
			str.append("Label : " + labelText + "; ");
		}
		if (placeholder != null) {
			str.append("Placeholder : " + placeholder + "; ");
		}
		if (labelFor != null) {
			str.append("LabelFor : " + labelFor + "; ");
		}
		if (siblingSpanText != null) {
			str.append("SiblingSpanText : " + siblingSpanText + "; ");
		}
		if (inputValue != null) {
			str.append("InputValue : " + inputValue + "; ");
		}
		if (cElement.getElement() != null) {
			str.append("Element tag : " + cElement.getTagName());
		}
		return str.toString();
	}
	
}
