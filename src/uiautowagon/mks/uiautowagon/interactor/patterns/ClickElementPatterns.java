package mks.uiautowagon.interactor.patterns;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.patterns.objects.Checkbox;
import mks.uiautowagon.interactor.patterns.objects.ClickElement;

public class ClickElementPatterns {


	private WebElement element = null;


	enum InputTagPreference {
		UNDEFINED;
	}
	
	private boolean isClickElement() {
		return true;
	}
	
	public ClickElement findPattern() {
		if (isClickElement()) {
			ClickElement chk = new ClickElement();
			chk.setElement(element);
			return chk;
		}
		return null;
	}
	
	
}
