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
	
	private String paperButtonTxt = null;
	private String paperButtonArialLabel = null;
	private String paperButtonInnerYtFormattedString = null;
	
	
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

	enum PaperButton {
		PaperButtonHavingTextOrArialLabel,
		PaperButtonHavingInnerYtFormattedString;
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
	
	private boolean isPaperButton() {
		String tagName = cElement.getTagName();
		return tagName.equalsIgnoreCase("paper-button");
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
	
	private boolean isPaperButtonHavingTextOrArialLabel() {
		paperButtonTxt = cElement.getElementText();
		paperButtonArialLabel = cElement.getElement().getAttribute("aria-label");
		if ((paperButtonTxt != null) || (paperButtonArialLabel != null))
			return true;
		return false;
	}

	private boolean isPaperButtonInnerYtFormattedString() {
		List<WebElement> ytFormatedStringElements = new TagsFinder().childYtFormatedStrings(cElement.getElement());
		for (WebElement element : ytFormatedStringElements) {
			paperButtonInnerYtFormattedString = element.getText();
			if ((paperButtonInnerYtFormattedString != null)
					&& (paperButtonInnerYtFormattedString.trim().length() > 0)) {
				return true;
			}
		}
		return false;
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
		} else if (isPaperButton()) {
			Button btn = new Button();
			isPaperButtonHavingTextOrArialLabel();
			isPaperButtonInnerYtFormattedString();
			btn.setPaperButtonTxt(paperButtonTxt);
			btn.setPaperButtonArialLabel(paperButtonArialLabel);
			btn.setPaperButtonInnerYtFormattedString(paperButtonInnerYtFormattedString);
			btn.setcElement(cElement);
			return btn;
		}
		return null;
	}

}
