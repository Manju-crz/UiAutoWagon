package mks.uiautowagon.interactor.components;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.ElementsStorage;
import mks.uiautowagon.interactor.WagonerElements;
import mks.uiautowagon.interactor.patterns.objects.TextField;
import mks.uiautowagon.interactor.search.InputIdentification;
import mks.uiautowagon.interactor.store.TextFieldsStore;

public class TextFieldComponent implements WagonerElements {

	WebDriver driver = null;

	public TextFieldComponent(WebDriver driver) {
		this.driver = driver;
	}

	@Override
	public WebElement get(String label) {

		return new TextFieldsStore().find(label);
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
