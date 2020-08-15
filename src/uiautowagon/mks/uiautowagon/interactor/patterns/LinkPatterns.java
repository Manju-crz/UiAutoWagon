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
	private String linkChildDivText = null;
	CurrentElement cElement = null;
	
	
	public LinkPatterns(CurrentElement cElement) {
		this.cElement = cElement;
	}

	enum WithinAnchorTag {
		AnchorTagWithText,
		AnchorTagChildDivText;
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
			if (linkTxt.length() > 0)
				return true;
		}
		return false;
	}

	private boolean isLinkTextValid() {
		if (linkText != null) {
			System.out.println("linkText idenfitued is : " + linkText);
			if (linkText.trim().length() > 0)
				return true;
		}
		return false;
	}

	private boolean isLinkChildDivTextFound() {
		System.out.println("isLinkChildDivTextFound beginened");
		if (!isLinkTextValid()) {
			System.out.println("isLinkTextFound is not");
			List<WebElement> childDiv = new TagsFinder().childDivs(cElement.getElement());
			System.out.println("childDiv size is : " + childDiv);
			for (WebElement div : childDiv) {
				linkChildDivText = div.getText();
				System.out.println("linkChildDivText is : " + linkChildDivText);
				if ((linkChildDivText != null) && linkChildDivText.trim().length() > 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	public Link findPattern() {
		if (isLink()) {
			Link lnk = new Link();
			isLinkTextFound();
			isLinkChildDivTextFound();
			lnk.setLinkText(linkText);
			lnk.setLinkChildDivText(linkChildDivText);
			lnk.setcElement(cElement);
			return lnk;
		}
		return null;
	}
	
}
