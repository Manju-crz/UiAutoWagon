package mks.uiautowagon.interactor.patterns;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.CurrentElement;
import mks.uiautowagon.interactor.interutil.SupportUtil;
import mks.uiautowagon.interactor.interutil.TagsFinder;
import mks.uiautowagon.interactor.patterns.objects.Button;
import mks.uiautowagon.interactor.patterns.objects.Checkbox;

public class ButtonPatterns {
	
	CurrentElement cElement = null;
	private String buttonText = null;
	private String inputValueText = null;
	private List<String> buttonInnerSpanTexts = new ArrayList<>();
	private List<String> siblingSpanTexts = new ArrayList<>();
	
	public ButtonPatterns(CurrentElement cElement) {
		this.cElement = cElement;
	}
	
	enum HavingButtonTag {
		ButtonTagWithText,
		ButtonTagWithInnerTagsSpanText;
	}
	
	enum WithInputTag {
		InputTagHavingValueTextWithTypeSubmit,
		InputTypeSubmitWithSiblingTagSpanText;
	}
	
	
	private boolean isButton() {
		String tagName = cElement.getTagName();
		if (tagName.equalsIgnoreCase("button")) {
			buttonText = cElement.getElementText();
			return true;
		} else if (tagName.equalsIgnoreCase("input") && cElement.getTypeAttribute().equalsIgnoreCase("submit")) {
			return true;
		}
		return false;
	}
	

	private boolean isButtonTagWithInnerTagsSpanText() {
		siblingSpanTexts = new SupportUtil().getElementsText(new TagsFinder().siblingSpans(cElement.getElement()));
		return siblingSpanTexts.isEmpty();
	}
	
	private boolean isInputTagHavingValueTextWithTypeSubmit() {
		inputValueText = cElement.getElement().getAttribute("value");
		if (inputValueText != null) {
			return true;
		}
		return false;
	}

	private boolean isInputTypeSubmitWithSiblingTagSpanText() {
		buttonInnerSpanTexts = new SupportUtil().getElementsText(new TagsFinder().innerSpanElements(cElement.getElement()));
		return buttonInnerSpanTexts.isEmpty();
	}
	

	public Button findPattern() {
		if (isButton()) {
			Button btn = new Button();
			isButtonTagWithInnerTagsSpanText();
			isInputTagHavingValueTextWithTypeSubmit();
			isInputTypeSubmitWithSiblingTagSpanText();
			btn.setButtonText(buttonText);
			btn.setInputValueText(inputValueText);
			btn.setButtonInnerSpanTexts(buttonInnerSpanTexts);
			btn.setSiblingSpanTexts(siblingSpanTexts);
			btn.setcElement(cElement);
			return btn;
		}
		return null;
	}

}
