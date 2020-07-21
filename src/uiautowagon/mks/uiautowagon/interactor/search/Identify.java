package mks.uiautowagon.interactor.search;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;

public abstract class Identify {
	
	WebElement element = null;
	
	protected boolean isElementDisplayed() {
		try{
			element.isDisplayed();
			return true;
		}catch(NoSuchElementException e) {
			return false;
		}
	}
	
	
}
