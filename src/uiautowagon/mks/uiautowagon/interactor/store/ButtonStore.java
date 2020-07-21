package mks.uiautowagon.interactor.store;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.patterns.objects.Button;
import mks.uiautowagon.interactor.patterns.objects.TextField;

public class ButtonStore {

	public static List<Button> buttonList = new ArrayList<>();
	
	
	public void add(Button button) {
		buttonList.add(button);
	}

	public WebElement find(String elementText) {

		Button tf = new Button();
		for (int i = 0; i < buttonList.size(); i++) {
			tf = buttonList.get(i);
			WebElement foundElement = tf.compare(elementText);
			if (foundElement != null) {
				return foundElement;
			}
		}
		return null;
	}
	
}
