package mks.uiautowagon.interactor;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.patterns.ButtonPatterns;
import mks.uiautowagon.interactor.patterns.CheckboxPatterns;
import mks.uiautowagon.interactor.patterns.FramePatterns;
import mks.uiautowagon.interactor.patterns.LinkPatterns;
import mks.uiautowagon.interactor.patterns.OtherPatterns;
import mks.uiautowagon.interactor.patterns.RadioButtonPatterns;
import mks.uiautowagon.interactor.patterns.SelectBoxPatterns;
import mks.uiautowagon.interactor.patterns.TextAreaPatterns;
import mks.uiautowagon.interactor.patterns.TextFieldPatterns;
import mks.uiautowagon.interactor.patterns.objects.Button;
import mks.uiautowagon.interactor.patterns.objects.Checkbox;
import mks.uiautowagon.interactor.patterns.objects.Frames;
import mks.uiautowagon.interactor.patterns.objects.Link;
import mks.uiautowagon.interactor.patterns.objects.Other;
import mks.uiautowagon.interactor.patterns.objects.RadioButton;
import mks.uiautowagon.interactor.patterns.objects.SelectBox;
import mks.uiautowagon.interactor.patterns.objects.TextArea;
import mks.uiautowagon.interactor.patterns.objects.TextField;
import mks.uiautowagon.interactor.store.ButtonStore;
import mks.uiautowagon.interactor.store.CheckboxStore;
import mks.uiautowagon.interactor.store.ClickElementsStore;
import mks.uiautowagon.interactor.store.ElementsStore;
import mks.uiautowagon.interactor.store.FramesStore;
import mks.uiautowagon.interactor.store.LinkStore;
import mks.uiautowagon.interactor.store.OtherStore;
import mks.uiautowagon.interactor.store.RadioButtonStore;
import mks.uiautowagon.interactor.store.SelectBoxStore;
import mks.uiautowagon.interactor.store.TextAreaStore;
import mks.uiautowagon.interactor.store.TextFieldsStore;

public class DomReader {

	private WebDriver driver = null;
	
	DomReader(WebDriver driver) {
		this.driver = MyDriver.getDriver();
	}
	
	
	void distribute() {

		List<WebElement> allElements = driver.findElements(By.xpath("//body//input|//body//a|//body//button|//body//paper-button|//body//textarea|//body//select")); //[not(contains(@style,'display:none'))]		//body//*
		System.out.println("inputElements size is : " + allElements.size());
		ElementsStore elementsStore = new ElementsStore();
		ClickElementsStore clickElementsStore = new ClickElementsStore();
		
		int textfieldCount = 1;
		int checkboxCount = 1;
		int buttonCount = 1;
		int lnkCount = 1;
		int rdoCount = 1;
		int textareaCount = 1;
		int otherCount = 1;
		int selectboxCount = 1;
		
		TextFieldsStore tfs = new TextFieldsStore();
		CheckboxStore cbs = new CheckboxStore();
		ButtonStore bs = new ButtonStore();
		OtherStore os = new OtherStore();
		FramesStore fs = new FramesStore();
		LinkStore lk = new LinkStore();
		RadioButtonStore rdos = new RadioButtonStore();
		TextAreaStore tas = new TextAreaStore();
		SelectBoxStore sbs = new SelectBoxStore();
		
		for (WebElement element : allElements) {
			
			CurrentElement cElement = new CurrentElement();
			cElement.setElement(element);
			String allAttributes = null;
			try {
				element.isDisplayed();
				allAttributes = cElement.getAttributes();
			} catch (StaleElementReferenceException | NoSuchElementException e) {
				continue;
			}
			
			System.out.println(">>>>>>>>>>>>>>>> Started identifying for : " + cElement.getTagName());
			System.out.println("allAttributes are : " + allAttributes);

			if ((allAttributes != null) && allAttributes.contains("=") && (!cElement.isStyleNonDisplayed())) {

				Frames frame = new FramePatterns(cElement).findPattern();
				if (frame != null) {
					System.out.println("Found iframe and storing it..");
					fs.add(frame);
					continue;
				}

				SelectBox selectboxEement = new SelectBoxPatterns(cElement).findPattern();
				if (selectboxEement != null) {
					elementsStore.add("SelectBox" + selectboxCount, selectboxEement);
					sbs.add(selectboxEement);
					selectboxCount++;
					continue;
				}
				
				TextField textfieldEement = new TextFieldPatterns(cElement).findPattern();
				if (textfieldEement != null) {
					elementsStore.add("Textfield" + textfieldCount, textfieldEement);
					tfs.add(textfieldEement);
					textfieldCount++;
					continue;
				}

				TextArea textareaEement = new TextAreaPatterns(cElement).findPattern();
				if (textareaEement != null) {
					elementsStore.add("TextArea" + textareaCount, textareaEement);
					tas.add(textareaEement);
					textareaCount++;
					continue;
				}
				
				Checkbox checkboxElement = new CheckboxPatterns(cElement).findPattern();
				if (checkboxElement != null) {
					elementsStore.add("Checkbox" + checkboxCount, checkboxElement);
					cbs.add(checkboxElement);
					checkboxCount++;
					continue;
				}
				
				ButtonPatterns buttonPatterns = new ButtonPatterns(cElement);
				Button buttonElement = buttonPatterns.findPattern();
				if (buttonElement != null) {
					elementsStore.add("Button" + buttonCount, buttonElement);
					clickElementsStore.add("Button" + buttonCount, buttonElement);
					bs.add(buttonElement);
					buttonCount++;
					continue;
				}

				LinkPatterns linkPatterns = new LinkPatterns(cElement); 
				Link lnkElement = linkPatterns.findPattern();
				if (lnkElement != null) {
					elementsStore.add("Link" + lnkCount, lnkElement);
					System.out.println("Adding clickElementsStore Link : " + "Link" + lnkCount);
					System.out.println("=== " + lnkElement.getLinkText() );
					clickElementsStore.add("Link" + lnkCount, lnkElement);
					lk.add(lnkElement);
					lnkCount++;
					continue;
				}

				RadioButton rdoElement = new RadioButtonPatterns(cElement).findPattern();
				if (rdoElement != null) {
					elementsStore.add("Radio" + rdoCount, rdoElement);
					rdos.add(rdoElement);
					rdoCount++;
					continue;
				}

				Other otherElement = new OtherPatterns(cElement).findPattern();
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
