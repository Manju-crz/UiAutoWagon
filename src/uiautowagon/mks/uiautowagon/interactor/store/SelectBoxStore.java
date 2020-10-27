package mks.uiautowagon.interactor.store;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.patterns.objects.SelectBox;

public class SelectBoxStore {
	
	
	public static List<SelectBox> selectBoxList = new ArrayList<>();
	
	
	public void add(SelectBox selectBox) {
		selectBoxList.add(selectBox);
	}
	
	public WebElement find(String elementText) {

		SelectBox tf = new SelectBox();
		for (int i = 0; i < selectBoxList.size(); i++) {
			tf = selectBoxList.get(i);
			WebElement foundElement = tf.compare(elementText);
			if (foundElement != null) {
				return foundElement;
			}
		}
		return null;
	}
	
}
