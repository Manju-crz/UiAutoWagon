package mks.uiautowagon.interactor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
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
import mks.uiautowagon.interactor.patterns.objects.ClickElement;
import mks.uiautowagon.interactor.patterns.objects.Frames;
import mks.uiautowagon.interactor.patterns.objects.Link;
import mks.uiautowagon.interactor.patterns.objects.Other;
import mks.uiautowagon.interactor.patterns.objects.RadioButton;
import mks.uiautowagon.interactor.patterns.objects.TextField;
import mks.uiautowagon.interactor.store.ButtonStore;
import mks.uiautowagon.interactor.store.CheckboxStore;
import mks.uiautowagon.interactor.store.ClickElementStore;
import mks.uiautowagon.interactor.store.FramesStore;
import mks.uiautowagon.interactor.store.LinkStore;
import mks.uiautowagon.interactor.store.OtherStore;
import mks.uiautowagon.interactor.store.RadioButtonStore;
import mks.uiautowagon.interactor.store.TextFieldsStore;

public class DomReader {

	private WebDriver driver = null;

	DomReader(WebDriver driver) {
		this.driver = driver;
	}
	
	
	void distribute() {

		List<WebElement> allElements = driver.findElements(By.xpath("//body//*"));
		System.out.println("inputElements size is : " + allElements.size());
		ElementsStorage elementsStore = new ElementsStorage();

		int textfieldCount = 1;
		int checkboxCount = 1;
		int buttonCount = 1;
		int lnkCount = 1;
		int clickCount = 1;
		int rdoCount = 1;
		int otherCount = 1;

		TextFieldsStore tfs = new TextFieldsStore();
		CheckboxStore cbs = new CheckboxStore();
		ButtonStore bs = new ButtonStore();
		OtherStore os = new OtherStore();
		FramesStore fs = new FramesStore();
		LinkStore lk = new LinkStore();
		ClickElementStore ces = new ClickElementStore();
		RadioButtonStore rdos = new RadioButtonStore();
		
		int runningCount = 1;
		for (WebElement element : allElements) {
			System.out.println("runningCount s: " + (runningCount++));
			String allAttributes = new SupportUtil(driver).getAttributes(element);
			System.out.println(">>>>>>>>>>>>>>>> Started identifying for : " + element.getTagName());
			System.out.println("allAttributes are : " + allAttributes);
			if ((allAttributes != null) && allAttributes.contains("=")) {

				Frames frame = new FramePatterns(element).findPattern();
				if (frame != null) {
					System.out.println("Found iframe and storing it..");
					fs.add(frame);
					continue;
				}

				TextField textfieldEement = new TextFieldPatterns(element).findPattern();
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

				Button buttonElement = new ButtonPatterns(element).findPattern();
				if (buttonElement != null) {
					elementsStore.add("Button" + buttonCount, buttonElement);
					bs.add(buttonElement);
					buttonCount++;
					
					Set<String> clickTxts = new HashSet<>();
					clickTxts.add(buttonElement.getButtonText());
					clickTxts.addAll(buttonElement.getButtonInnerSpanTexts());
					clickTxts.add(buttonElement.getInputValueText());
					clickTxts.addAll(buttonElement.getSiblingSpanTexts());
					ClickElement clk = new ClickElement();
					clk.setElementText(clickTxts);
					clk.setElement(element);
					ces.add(clk);
					clickCount++;
					continue;
				}

				Link lnkElement = new LinkPatterns(element).findPattern();
				if (lnkElement != null) {
					elementsStore.add("Link" + lnkCount, lnkElement);
					lk.add(lnkElement);
					lnkCount++;
					
					Set<String> clickTxts = new HashSet<>();
					clickTxts.add(lnkElement.getLinkText());
					ClickElement clk = new ClickElement();
					clk.setElementText(clickTxts);
					clk.setElement(element);
					ces.add(clk);
					clickCount++;
					
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
