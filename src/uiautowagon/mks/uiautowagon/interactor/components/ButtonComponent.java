package mks.uiautowagon.interactor.components;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import mks.uiautowagon.exceptions.NoSuchElementPositionException;
import mks.uiautowagon.interactor.CurrentElement;
import mks.uiautowagon.interactor.MyDriver;
import mks.uiautowagon.interactor.WagonerElements;
import mks.uiautowagon.interactor.WagonerFacade;
import mks.uiautowagon.interactor.patterns.objects.Button;
import mks.uiautowagon.interactor.patterns.objects.SelectBox;
import mks.uiautowagon.interactor.store.ButtonStore;
import mks.uiautowagon.interactor.store.CheckboxStore;
import mks.uiautowagon.interactor.store.SelectBoxStore;
import mks.uiautowagon.interactor.store.TextFieldsStore;

public class ButtonComponent implements WagonerElements {

	WebDriver driver = null;
	public ButtonComponent() {
		this.driver = MyDriver.getDriver();
	}
	@Override
	public WebElement get(String label) {
		
		WebElement element = new ButtonStore().find(label);
		if (element == null) {
			new WagonerFacade().reload();
			element = new ButtonStore().find(label);
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
		List<Button> elements = ButtonStore.buttonList;
		if (position < 1 || position > elements.size())
			throw new NoSuchElementPositionException("No button found in the given position " + position);
		Button sb = elements.get(position - 1);
		CurrentElement ce = sb.getcElement();
		return ce.getElement();
	}
	@Override
	public WebElement get() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
