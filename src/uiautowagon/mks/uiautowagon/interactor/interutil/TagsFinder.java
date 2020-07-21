package mks.uiautowagon.interactor.interutil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TagsFinder {


	public WebElement parentElement(WebElement element) {
		System.out.println("Finding parent..");
		return element.findElement(By.xpath(".."));
	}

	public List<WebElement> childElements(WebElement element) {
		return element.findElements(By.xpath("./*"));
	}

	public List<WebElement> siblings(WebElement element) {
		WebElement parentElement = parentElement(element);
		System.out.println("Found parent tag name is : " + parentElement.getTagName());
		List<WebElement> siblings = parentElement.findElements(By.xpath("./*"));
		System.out.println("Found siblings count is : " + siblings.size());
		return siblings;
	}

	public WebElement siblingLabel(WebElement element) {
		System.out.println("Trying to find the sibling label");
		List<WebElement> siblings = siblings(element);

		for (WebElement tempElement : siblings) {
			System.out.println("for loop for " + tempElement.getTagName());
			if (tempElement.getTagName().equalsIgnoreCase("label")) {
				return tempElement;
			}
		}
		return null;
	}

	public List<WebElement> siblingSpans(WebElement element) {
		List<WebElement> siblings = siblings(element);
		List<WebElement> siblingSpanElements = new ArrayList<>();
		if (!(siblings.isEmpty())) {
			for (WebElement tempElement : siblings) {
				if (tempElement.getTagName().equalsIgnoreCase("span")) {
					siblingSpanElements.add(tempElement);
				}
			}
		}
		return siblingSpanElements;
	}

	public List<WebElement> siblingDivs(WebElement element) {
		WebElement parent = parentElement(element);
		return parent.findElements(By.xpath("./div"));
	}
	
	public List<WebElement> siblingLabels(WebElement element) {
		WebElement parent = parentElement(element);
		return parent.findElements(By.xpath("./label"));
	}

	public List<WebElement> siblingInputs(WebElement element) {
		WebElement parent = parentElement(element);
		return parent.findElements(By.xpath("./input"));
	}

	public List<WebElement> innerSpanElements(WebElement element) {
		List<WebElement> innerSpanElements = element.findElements(By.xpath(".//span"));
		return innerSpanElements;
	}

	public List<WebElement> innerElements(WebElement element) {
		List<WebElement> innerSpanElements = element.findElements(By.xpath(".//*"));
		return innerSpanElements;
	}

	public List<WebElement> innerInputElements(WebElement element) {
		List<WebElement> innerSpanElements = element.findElements(By.xpath(".//input"));
		return innerSpanElements;
	}

	public WebElement parentTD(WebElement element) {
		WebElement parent = parentElement(element);
		System.out.println("parentTD one is: " + parent.getTagName());
		if (parent.getTagName().equalsIgnoreCase("td")) {
			return parent;
		}
		return null;
	}
	
	public WebElement parentTR(WebElement element) {
		WebElement parent = parentElement(element);
		System.out.println("parentTD one is: " + parent.getTagName());
		if (parent.getTagName().equalsIgnoreCase("tr")) {
			return parent;
		}
		return null;
	}

	public WebElement parentTBody(WebElement element) {
		WebElement parent = parentElement(element);
		System.out.println("parentTD one is: " + parent.getTagName());
		if (parent.getTagName().equalsIgnoreCase("tbody")) {
			return parent;
		}
		return null;
	}

	public WebElement parentDiv(WebElement element) {
		WebElement parent = parentElement(element);
		System.out.println("parentTD one is: " + parent.getTagName());
		if (parent.getTagName().equalsIgnoreCase("div")) {
			return parent;
		}
		return null;
	}

	public List<WebElement> childTRs(WebElement element) {
		return element.findElements(By.xpath("./tr"));
	}

	public List<WebElement> childTDs(WebElement element) {
		return element.findElements(By.xpath("./td"));
	}

	public List<WebElement> innerInputs_ChildToTD(WebElement element) {
		return element.findElements(By.xpath(".//td/input"));
	}

	public List<WebElement> innerLabels_ChildToTD(WebElement element) {
		return element.findElements(By.xpath(".//td/label"));
	}
	
}
