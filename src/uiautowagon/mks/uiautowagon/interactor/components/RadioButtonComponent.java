package mks.uiautowagon.interactor.components;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import mks.uiautowagon.exceptions.NoSuchElementPositionException;
import mks.uiautowagon.interactor.CurrentElement;
import mks.uiautowagon.interactor.MyDriver;
import mks.uiautowagon.interactor.WagonerElements;
import mks.uiautowagon.interactor.WagonerFacade;
import mks.uiautowagon.interactor.patterns.objects.Link;
import mks.uiautowagon.interactor.patterns.objects.RadioButton;
import mks.uiautowagon.interactor.store.LinkStore;
import mks.uiautowagon.interactor.store.RadioButtonStore;
import mks.uiautowagon.interactor.store.TextFieldsStore;

public class RadioButtonComponent implements WagonerElements{

	WebDriver driver = null;
	public RadioButtonComponent() {
		this.driver = MyDriver.getDriver();
	}
	@Override
	public WebElement get(String label) {
		
		WebElement element = new RadioButtonStore().find(label);
		if (element == null) {
			new WagonerFacade().reload();
			element = new RadioButtonStore().find(label);
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

		List<RadioButton> elements = RadioButtonStore.rdoList;
		if (position < 1 || position > elements.size())
			throw new NoSuchElementPositionException("No RadioButton found in the given position " + position);
		RadioButton sb = elements.get(position - 1);
		return sb.getElement();
	}
	@Override
	public WebElement get() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
