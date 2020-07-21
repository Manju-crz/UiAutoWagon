package mks.uiautowagon.interactor.store;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.patterns.objects.TextField;

public class TextFieldsStore {
	
	
	public static List<TextField> textFieldsList = new ArrayList<>();
	
	
	public void add(TextField textfield) {
		textFieldsList.add(textfield);
	}
	
	public WebElement find(String elementText) {

		TextField tf = new TextField();
		for (int i = 0; i < textFieldsList.size(); i++) {
			tf = textFieldsList.get(i);
			WebElement foundElement = tf.compare(elementText);
			if (foundElement != null) {
				return foundElement;
			}
		}
		return null;
	}
	
}
