package mks.uiautowagon.interactor.patterns;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.interutil.SupportUtil;
import mks.uiautowagon.interactor.interutil.TagsFinder;
import mks.uiautowagon.interactor.patterns.objects.Button;
import mks.uiautowagon.interactor.patterns.objects.Checkbox;

public class ButtonPatterns {
	

	private WebElement element = null;
	private String buttonText = null;
	private String inputValueText = null;
	private List<String> buttonInnerSpanTexts = new ArrayList<>();
	private List<String> siblingSpanTexts = new ArrayList<>();
	
	public ButtonPatterns(WebElement element) {
		System.out.println("Beginned to ButtonPatterns");
		this.element = element;
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
		String tagName = element.getTagName();
		if (tagName.equalsIgnoreCase("button")) {
			buttonText = element.getText().trim();
			return true;
		} else if (tagName.equalsIgnoreCase("input") && element.getAttribute("type").equalsIgnoreCase("submit")) {
			return true;
		}
		return false;
	}
	

	private boolean isButtonTagWithInnerTagsSpanText() {
		siblingSpanTexts = new SupportUtil().getElementsText(new TagsFinder().siblingSpans(element));
		return siblingSpanTexts.isEmpty();
	}
	
	private boolean isInputTagHavingValueTextWithTypeSubmit() {
		inputValueText = element.getAttribute("value");
		if (inputValueText != null) {
			return true;
		}
		return false;
	}

	private boolean isInputTypeSubmitWithSiblingTagSpanText() {
		buttonInnerSpanTexts = new SupportUtil().getElementsText(new TagsFinder().innerSpanElements(element));
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
			btn.setElement(element);
			return btn;
		}
		return null;
	}
	
	
}
