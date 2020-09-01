package mks.uiautowagon.interactor.patterns.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.CurrentElement;

public class Link {

	private String linkText = null;
	private String linkChildDivText = null;
	
	private String childImgTagHavingAltText = null;
	
	private CurrentElement cElement = null;
	
	public CurrentElement getcElement() {
		return cElement;
	}
	public void setcElement(CurrentElement cElement) {
		this.cElement = cElement;
	}
	public String getLinkText() {
		return linkText;
	}
	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}
	
	public String getLinkChildDivText() {
		return linkChildDivText;
	}
	public void setLinkChildDivText(String linkChildDivText) {
		this.linkChildDivText = linkChildDivText;
	}

	public String getChildImgTagHavingAltText() {
		return childImgTagHavingAltText;
	}
	public void setChildImgTagHavingAltText(String childImgTagHavingAltText) {
		this.childImgTagHavingAltText = childImgTagHavingAltText;
	}


	public WebElement compare(String elementText) {
		if ((linkText != null) && linkText.trim().equalsIgnoreCase(elementText))
			return cElement.getElement();
		else if ((linkText != null) && linkText.trim().length() > 0) {
			if (new ArrayList<>(Arrays.asList(linkText.split("\n"))).contains(elementText))
				return cElement.getElement();
		}
		
		if ((linkChildDivText != null) && linkChildDivText.trim().equalsIgnoreCase(elementText))
			return cElement.getElement();
		else if ((linkChildDivText != null) && linkChildDivText.trim().length() > 0) {
			if (new ArrayList<>(Arrays.asList(linkChildDivText.split("\n"))).contains(elementText))
				return cElement.getElement();
		}
		
		if ((childImgTagHavingAltText != null) && childImgTagHavingAltText.trim().equalsIgnoreCase(elementText))
			return cElement.getElement();
		
		return null;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		if (linkText != null) {
			str.append("Link Text : " + linkText + ";");
		}
		if (cElement.getElement() != null) {
			str.append("Element tag : " + cElement.getTagName());
		}
		return str.toString();
	}
	
}
