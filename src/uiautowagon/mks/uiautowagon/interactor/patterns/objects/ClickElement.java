package mks.uiautowagon.interactor.patterns.objects;

import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.WebElement;

public class ClickElement {

	private Set<String> elementText = new HashSet<>();
	private WebElement element = null;
	
	public Set<String> getElementText() {
		return elementText;
	}

	public void setElementText(Set<String> elementText) {
		this.elementText = elementText;
	}

	public WebElement getElement() {
		return element;
	}

	public void setElement(WebElement element) {
		this.element = element;
	}
	
	public WebElement compare(String elementText) {
		if (elementText.contains(elementText))
			return element;
		return null;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Click element " + element.getTagName());
		str.append("Element text " + elementText.toString());
		return str.toString();
	}
	
}
