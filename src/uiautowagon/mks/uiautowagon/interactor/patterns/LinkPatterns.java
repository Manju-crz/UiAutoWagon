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
	
	private String childImgTagHavingAltText = null;
	
	CurrentElement cElement = null;
	
	
	public LinkPatterns(CurrentElement cElement) {
		this.cElement = cElement;
	}

	enum WithinAnchorTag {
		AnchorTagWithText,
		AnchorTagChildDivText,
		ChildImgTagHavingAltText;
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
		if (!isLinkTextValid()) {
			List<WebElement> childDiv = new TagsFinder().childDivs(cElement.getElement());
			for (WebElement div : childDiv) {
				linkChildDivText = div.getText();
				if ((linkChildDivText != null) && linkChildDivText.trim().length() > 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isChildImgTagHavingAltText() {
		if (!isLinkTextValid()) {
			List<WebElement> childImg = new TagsFinder().childImgs(cElement.getElement());
			System.out.println("childImg size s : " + childImg.size());
			for (WebElement img : childImg) {
				String altTxt = img.getAttribute("alt");
				System.out.println("altTxt ss : " + altTxt);
				if ((altTxt != null) && altTxt.trim().length() > 0) {
					childImgTagHavingAltText = altTxt;
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
			isChildImgTagHavingAltText();
			
			lnk.setLinkText(linkText);
			lnk.setLinkChildDivText(linkChildDivText);
			lnk.setChildImgTagHavingAltText(childImgTagHavingAltText);
			lnk.setcElement(cElement);
			return lnk;
		}
		return null;
	}
	
}
