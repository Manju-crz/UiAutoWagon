package mks.uiautowagon.interactor.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.MyDriver;
import mks.uiautowagon.interactor.WagonerElements;
import mks.uiautowagon.interactor.WagonerFacade;
import mks.uiautowagon.interactor.store.CheckboxStore;
import mks.uiautowagon.interactor.store.RadioButtonStore;
import mks.uiautowagon.interactor.store.TextFieldsStore;

public class CheckBoxComponent implements WagonerElements {

	WebDriver driver = null;
	public CheckBoxComponent() {
		this.driver = MyDriver.getDriver();
	}

	@Override
	public WebElement get(String label) {
		WebElement element = new CheckboxStore().find(label);
		if (element == null) {
			new WagonerFacade().reload();
			element = new CheckboxStore().find(label);
		}
		return element;
	}

	@Override
	public WebElement get(String label, int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement get(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement get() {
		// TODO Auto-generated method stub
		return null;
	}

}
