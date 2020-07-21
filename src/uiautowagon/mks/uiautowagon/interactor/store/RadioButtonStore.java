package mks.uiautowagon.interactor.store;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.patterns.objects.Button;
import mks.uiautowagon.interactor.patterns.objects.RadioButton;

public class RadioButtonStore {

	public static List<RadioButton> rdoList = new ArrayList<>();
	
	
	public void add(RadioButton rdo) {
		rdoList.add(rdo);
	}

	public WebElement find(String elementText) {

		RadioButton tf = new RadioButton();
		for (int i = 0; i < rdoList.size(); i++) {
			tf = rdoList.get(i);
			WebElement foundElement = tf.compare(elementText);
			if (foundElement != null) {
				return foundElement;
			}
		}
		return null;
	}
	
}
