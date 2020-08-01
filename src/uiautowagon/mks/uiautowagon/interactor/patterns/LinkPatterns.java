package mks.uiautowagon.interactor.patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.interutil.SupportUtil;
import mks.uiautowagon.interactor.interutil.TagsFinder;
import mks.uiautowagon.interactor.patterns.objects.Link;
import mks.uiautowagon.interactor.patterns.objects.TextField;

public class LinkPatterns {
	
	private WebElement element = null;
	private String linkText = null;
	
	
	public LinkPatterns(WebElement element) {
		System.out.println("Beginned to TextFieldPatterns");
		this.element = element;
	}

	enum WithinAnchorTag {
		AnchorTagWithText;
	}

	private boolean isLink() {
		String tag = element.getTagName();
		System.out.println("attributeType is : " + tag);
		if (tag.equalsIgnoreCase("a")) {
			List<WebElement> innerElements = new TagsFinder().innerInputElements(element);
			System.out.println("innerElements are : " + innerElements.size());
			if (innerElements.size() != 0) {
				return false;
			}
			return true;
		}
		return false;
	}

	private boolean isLinkTextFound() {
		String linkTxt = element.getText();
		if (linkTxt != null) {
			linkText = linkTxt.trim();
			return true;
		}
		return false;
	}

	public Link findPattern() {
		if (isLink()) {
			Link lnk = new Link();
			isLinkTextFound();
			lnk.setLinkText(linkText);
			lnk.setElement(element);
			return lnk;
		}
		return null;
	}
	
}
