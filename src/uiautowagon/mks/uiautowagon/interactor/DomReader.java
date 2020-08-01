package mks.uiautowagon.interactor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.interutil.SupportUtil;
import mks.uiautowagon.interactor.patterns.ButtonPatterns;
import mks.uiautowagon.interactor.patterns.CheckboxPatterns;
import mks.uiautowagon.interactor.patterns.FramePatterns;
import mks.uiautowagon.interactor.patterns.LinkPatterns;
import mks.uiautowagon.interactor.patterns.OtherPatterns;
import mks.uiautowagon.interactor.patterns.RadioButtonPatterns;
import mks.uiautowagon.interactor.patterns.TextFieldPatterns;
import mks.uiautowagon.interactor.patterns.objects.Button;
import mks.uiautowagon.interactor.patterns.objects.Checkbox;
import mks.uiautowagon.interactor.patterns.objects.Frames;
import mks.uiautowagon.interactor.patterns.objects.Link;
import mks.uiautowagon.interactor.patterns.objects.Other;
import mks.uiautowagon.interactor.patterns.objects.RadioButton;
import mks.uiautowagon.interactor.patterns.objects.TextField;
import mks.uiautowagon.interactor.store.ButtonStore;
import mks.uiautowagon.interactor.store.CheckboxStore;
import mks.uiautowagon.interactor.store.ElementsStore;
import mks.uiautowagon.interactor.store.FramesStore;
import mks.uiautowagon.interactor.store.LinkStore;
import mks.uiautowagon.interactor.store.OtherStore;
import mks.uiautowagon.interactor.store.RadioButtonStore;
import mks.uiautowagon.interactor.store.TextFieldsStore;

public class DomReader {

	private WebDriver driver = null;
	private List<String> tags = new ArrayList<String>(Arrays.asList("input","a","button"));
	
	
	DomReader(WebDriver driver) {
		this.driver = MyDriver.getDriver();
	}
	
	
	void distribute() {

		List<WebElement> allElements = driver.findElements(By.xpath("//body//input|//body//a|//body//button")); //[not(contains(@style,'display:none'))]		//body//*
		System.out.println("inputElements size is : " + allElements.size());
		ElementsStore elementsStore = new ElementsStore();

		int textfieldCount = 1;
		int checkboxCount = 1;
		int buttonCount = 1;
		int lnkCount = 1;
		int rdoCount = 1;
		int otherCount = 1;

		TextFieldsStore tfs = new TextFieldsStore();
		CheckboxStore cbs = new CheckboxStore();
		ButtonStore bs = new ButtonStore();
		OtherStore os = new OtherStore();
		FramesStore fs = new FramesStore();
		LinkStore lk = new LinkStore();
		RadioButtonStore rdos = new RadioButtonStore();
		
		for (WebElement element : allElements) {
			
			CurrentElement cElement = new CurrentElement();
			cElement.setElement(element);
			
			String allAttributes = null;
			try {
				allAttributes = new SupportUtil().getAttributes(element);
				element.isDisplayed();
			} catch (StaleElementReferenceException | NoSuchElementException e) {
				continue;
			}
			cElement.setAttributes(allAttributes);
			cElement.setTagName(element.getTagName());
			System.out.println(">>>>>>>>>>>>>>>> Started identifying for : " + cElement.getTagName());
			System.out.println("allAttributes are : " + allAttributes);

			if ((allAttributes != null) && allAttributes.contains("=")) {

				Frames frame = new FramePatterns(element).findPattern();
				if (frame != null) {
					System.out.println("Found iframe and storing it..");
					fs.add(frame);
					continue;
				}

				TextField textfieldEement = new TextFieldPatterns(cElement).findPattern();
				if (textfieldEement != null) {
					elementsStore.add("Textfield" + textfieldCount, textfieldEement);
					tfs.add(textfieldEement);
					textfieldCount++;
					continue;
				}

				Checkbox checkboxElement = new CheckboxPatterns(element).findPattern();
				if (checkboxElement != null) {
					elementsStore.add("Checkbox" + checkboxCount, checkboxElement);
					cbs.add(checkboxElement);
					checkboxCount++;
					continue;
				}
				
				ButtonPatterns buttonPatterns = new ButtonPatterns(element);
				Button buttonElement = buttonPatterns.findPattern();
				if (buttonElement != null) {
					elementsStore.add("Button" + buttonCount, buttonElement);
					bs.add(buttonElement);
					buttonCount++;
					continue;
				}

				LinkPatterns linkPatterns = new LinkPatterns(element); 
				Link lnkElement = linkPatterns.findPattern();
				if (lnkElement != null) {
					elementsStore.add("Link" + lnkCount, lnkElement);
					lk.add(lnkElement);
					lnkCount++;
					continue;
				}

				RadioButton rdoElement = new RadioButtonPatterns(element).findPattern();
				if (rdoElement != null) {
					elementsStore.add("Radio" + rdoCount, rdoElement);
					rdos.add(rdoElement);
					rdoCount++;
					continue;
				}

				Other otherElement = new OtherPatterns(element).findPattern();
				if (otherElement != null) {
					elementsStore.add("Other" + otherCount, otherElement);
					os.add(otherElement);
					otherCount++;
					continue;
				}
			}
		}
	}
	
}
