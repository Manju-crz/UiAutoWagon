package mks.uiautowagon.interactor;

import org.openqa.selenium.WebDriver;

import mks.uiautowagon.interactor.components.ButtonComponent;
import mks.uiautowagon.interactor.components.CheckBoxComponent;
import mks.uiautowagon.interactor.components.ClickElementComponent;
import mks.uiautowagon.interactor.components.LinkComponent;
import mks.uiautowagon.interactor.components.RadioButtonComponent;
import mks.uiautowagon.interactor.components.TextFieldComponent;
import mks.uiautowagon.interactor.store.ButtonStore;
import mks.uiautowagon.interactor.store.ClickElementStore;
import mks.uiautowagon.interactor.store.LinkStore;

public class WagonerFacade {

	static WebDriver driver = null;
	public TextFieldComponent textField = null;
	public CheckBoxComponent checkBox = null;
	public ButtonComponent button = null;
	public LinkComponent link = null;
	public ClickElementComponent clickElement = null;
	public RadioButtonComponent radioButton = null;
	
	public WagonerFacade(WebDriver driver) {
		this.driver = driver;
		System.out.println("WagonerFacade constructor ...");
		reload();

	}

	public WagonerFacade reload() {
		System.out.println("Wagoner dom reloading..");
		new DomReader(driver).distribute();
		textField = new TextFieldComponent(driver);
		checkBox = new CheckBoxComponent(driver);
		button = new ButtonComponent(driver);
		link = new LinkComponent(driver);
		clickElement = new ClickElementComponent(driver);
		radioButton = new RadioButtonComponent(driver);
		return this;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public void getCount() {
		ElementsStorage es = new ElementsStorage();
		System.out.println("es.allElements.size() is : " + es.allElements.size());
		
		ButtonStore btnStore = new ButtonStore();
		System.out.println("btnStore.buttonList.size() is : " + btnStore.buttonList.size());
		
		LinkStore lnkStore = new LinkStore();
		System.out.println("lnkStore.linkList.size() is : " + lnkStore.linkList.size());
		
		ClickElementStore clkStore = new ClickElementStore();
		System.out.println("clkStore.clickELementsList.size() is : " + clkStore.clickELementsList.size());
		
	}

}
