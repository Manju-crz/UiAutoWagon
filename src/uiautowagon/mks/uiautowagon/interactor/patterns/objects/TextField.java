package mks.uiautowagon.interactor.patterns.objects;


import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.CurrentElement;

public class TextField {

	private String placeholder = null;
	private String labelText = null;
	private String labelFor = null;
	private String trParallelLabel = null;

	private String divNeighbourPlaceholder = null;
	private String ariaLabel = null;
	
	
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
		return null;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		if (labelText != null) {
			str.append("Label : " + labelText);
		}
		if (placeholder != null) {
			str.append("Placeholder : " + placeholder);
		}
		if (labelFor != null) {
			str.append("LabelFor : " + labelFor);
		}
		if (cElement.getElement() != null) {
			str.append("Element tag : " + cElement.getTagName());
		}
		return str.toString();
	}
	
}
