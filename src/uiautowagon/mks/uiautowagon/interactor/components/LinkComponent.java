package mks.uiautowagon.interactor.components;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import mks.uiautowagon.exceptions.NoSuchElementPositionException;
import mks.uiautowagon.interactor.CurrentElement;
import mks.uiautowagon.interactor.MyDriver;
import mks.uiautowagon.interactor.WagonerElements;
import mks.uiautowagon.interactor.WagonerFacade;
import mks.uiautowagon.interactor.patterns.objects.Checkbox;
import mks.uiautowagon.interactor.patterns.objects.Link;
import mks.uiautowagon.interactor.store.ButtonStore;
import mks.uiautowagon.interactor.store.CheckboxStore;
import mks.uiautowagon.interactor.store.LinkStore;
import mks.uiautowagon.interactor.store.RadioButtonStore;

public class LinkComponent implements WagonerElements {

	WebDriver driver = null;
	public LinkComponent() {
		this.driver = MyDriver.getDriver();
	}
	@Override
	public WebElement get(String label) {

		WebElement element = new LinkStore().find(label);
		if (element == null) {
			new WagonerFacade().reload();
			element = new LinkStore().find(label);
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

		List<Link> elements = LinkStore.linkList;
		if (position < 1 || position > elements.size())
			throw new NoSuchElementPositionException("No Link found in the given position " + position);
		Link sb = elements.get(position - 1);
		CurrentElement ce = sb.getcElement();
		return ce.getElement();
	
	}
	@Override
	public WebElement get() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
