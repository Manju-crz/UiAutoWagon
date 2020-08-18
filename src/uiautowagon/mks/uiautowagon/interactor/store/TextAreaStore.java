package mks.uiautowagon.interactor.store;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.patterns.objects.TextArea;

public class TextAreaStore {

	public static List<TextArea> textAreaList = new ArrayList<>();

	public void add(TextArea textArea) {
		textAreaList.add(textArea);
	}

	public WebElement find(String elementText) {

		TextArea tf = new TextArea();
		for (int i = 0; i < textAreaList.size(); i++) {
			tf = textAreaList.get(i);
			WebElement foundElement = tf.compare(elementText);
			if (foundElement != null) {
				return foundElement;
			}
		}
		return null;
	}

}
