package mks.uiautowagon.interactor.patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.CurrentElement;
import mks.uiautowagon.interactor.interutil.SupportUtil;
import mks.uiautowagon.interactor.interutil.TagsFinder;
import mks.uiautowagon.interactor.patterns.objects.Link;
import mks.uiautowagon.interactor.patterns.objects.TextField;

public class LinkPatterns {
	
	private String linkText = null;

	CurrentElement cElement = null;
	
	
	public LinkPatterns(CurrentElement cElement) {
		this.cElement = cElement;
	}

	enum WithinAnchorTag {
		AnchorTagWithText;
	}

	private boolean isLink() {
		if (cElement.getTagName().equalsIgnoreCase("a")) {
			List<WebElement> innerElements = new TagsFinder().innerInputElements(cElement.getElement());
			System.out.println("innerElements are : " + innerElements.size());
			if (innerElements.size() != 0) {
				return false;
			}
			return true;
		}
		return false;
	}

	private boolean isLinkTextFound() {
		String linkTxt = cElement.getElementText();
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
			lnk.setcElement(cElement);
			return lnk;
		}
		return null;
	}
	
}
