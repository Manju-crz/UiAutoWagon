package mks.uiautowagon.interactor.store;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.patterns.objects.Link;

public class LinkStore {

	public static List<Link> linkList = new ArrayList<>();

	public void add(Link lnk) {
		linkList.add(lnk);
	}

	public WebElement find(String elementText) {

		Link lnk = new Link();
		for (int i = 0; i < linkList.size(); i++) {
			lnk = linkList.get(i);
			WebElement foundElement = lnk.compare(elementText);
			if (foundElement != null) {
				return foundElement;
			}
		}
		return null;
	}
}
