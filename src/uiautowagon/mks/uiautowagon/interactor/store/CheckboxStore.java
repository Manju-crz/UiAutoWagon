package mks.uiautowagon.interactor.store;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.patterns.objects.Checkbox;
import mks.uiautowagon.interactor.patterns.objects.TextField;

public class CheckboxStore {

	public static List<Checkbox> checkboxList = new ArrayList<>();
	
	
	public void add(Checkbox checkbox) {
		checkboxList.add(checkbox);
	}
	

	public WebElement find(String elementText) {
		System.out.println("checkboxList.size() is :  " + checkboxList.size());
		Checkbox tf = new Checkbox();
		for (int i = 0; i < checkboxList.size(); i++) {
			tf = checkboxList.get(i);
			WebElement foundElement = tf.compare(elementText);
			if (foundElement != null) {
				return foundElement;
			}
		}
		return null;
	}
	
}
