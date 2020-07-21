package mks.uiautowagon.interactor.store;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.patterns.objects.ClickElement;
import mks.uiautowagon.interactor.patterns.objects.TextField;

public class ClickElementStore {
	
	public static List<ClickElement> clickELementsList = new ArrayList<>();
	
	
	public void add(ClickElement textfield) {
		clickELementsList.add(textfield);
	}
	
	public WebElement find(String elementText) {

		ClickElement tf = new ClickElement();
		for (int i = 0; i < clickELementsList.size(); i++) {
			tf = clickELementsList.get(i);
			WebElement foundElement = tf.compare(elementText);
			if (foundElement != null) {
				return foundElement;
			}
		}
		return null;
	}
	

}
