package mks.uiautowagon.interactor.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.WagonerElements;
import mks.uiautowagon.interactor.store.CheckboxStore;
import mks.uiautowagon.interactor.store.TextFieldsStore;

public class CheckBoxComponent implements WagonerElements {

	WebDriver driver = null;
	public CheckBoxComponent(WebDriver driver) {
		this.driver = driver;
	}

	@Override
	public WebElement get(String label) {

		return new CheckboxStore().find(label);
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
