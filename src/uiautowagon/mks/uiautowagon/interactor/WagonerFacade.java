package mks.uiautowagon.interactor;

import org.openqa.selenium.WebDriver;

import mks.uiautowagon.interactor.components.ButtonComponent;
import mks.uiautowagon.interactor.components.CheckBoxComponent;
import mks.uiautowagon.interactor.components.LinkComponent;
import mks.uiautowagon.interactor.components.RadioButtonComponent;
import mks.uiautowagon.interactor.components.TextFieldComponent;
import mks.uiautowagon.interactor.patterns.TextFieldPatterns;
import mks.uiautowagon.interactor.patterns.objects.RadioButton;
import mks.uiautowagon.interactor.store.ButtonStore;
import mks.uiautowagon.interactor.store.CheckboxStore;
import mks.uiautowagon.interactor.store.ElementsStore;
import mks.uiautowagon.interactor.store.LinkStore;
import mks.uiautowagon.interactor.store.RadioButtonStore;
import mks.uiautowagon.interactor.store.TextFieldsStore;

public class WagonerFacade extends MyDriver{

	public TextFieldComponent textField = null;
	public CheckBoxComponent checkBox = null;
	public ButtonComponent button = null;
	public LinkComponent link = null;
	public RadioButtonComponent radioButton = null;
	
	public WagonerFacade(WebDriver driver) {
		this.driver = driver;
		reload();
	}

	public WagonerFacade reload() {
		clearLocators();
		new DomReader(driver).distribute();
		initiateElements();
		return this;
	}
	
	
	private void clearLocators() {
		ElementsStore.allElements.clear();
		TextFieldsStore.textFieldsList.clear();
		ButtonStore.buttonList.clear();
		CheckboxStore.checkboxList.clear();
		LinkStore.linkList.clear();
		RadioButtonStore.rdoList.clear();
	}
	
	
	private void initiateElements() {
		textField = new TextFieldComponent(driver);
		checkBox = new CheckBoxComponent(driver);
		button = new ButtonComponent(driver);
		link = new LinkComponent(driver);
		radioButton = new RadioButtonComponent(driver);
	}
	

	public void getCount() {
		ElementsStore es = new ElementsStore();
		System.out.println("es.allElements.size() is : " + es.allElements.size());
		
		ButtonStore btnStore = new ButtonStore();
		System.out.println("btnStore.buttonList.size() is : " + btnStore.buttonList.size());
		
		LinkStore lnkStore = new LinkStore();
		System.out.println("lnkStore.linkList.size() is : " + lnkStore.linkList.size());
		
		RadioButtonStore radioButtons = new RadioButtonStore();
		System.out.println("radioButtons.rdoList.size() is : " + radioButtons.rdoList.size());
		for(RadioButton radioButton: radioButtons.rdoList) {
			System.out.println("radioButtons.rdoList : " + radioButton.toString());
		}
		
	}

}
