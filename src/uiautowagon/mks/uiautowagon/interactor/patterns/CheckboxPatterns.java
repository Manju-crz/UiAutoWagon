package mks.uiautowagon.interactor.patterns;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.CurrentElement;
import mks.uiautowagon.interactor.interutil.SupportUtil;
import mks.uiautowagon.interactor.interutil.TagsFinder;
import mks.uiautowagon.interactor.patterns.objects.Checkbox;
import mks.uiautowagon.interactor.patterns.objects.TextField;

public class CheckboxPatterns {


	private String label = null;
	private List<String> spanTexts = new ArrayList<>();
	private boolean isAnchorParent = false;

	private String parentLabelText = null;
	private String grandParentLabelText = null;
	
	private String upto4LevelParentsHavingClassWithCheckboxTxtAndContainsInnerSpan = null;
	
	WebElement siblingLabel = null;
	WebElement parentLabel = null;

	private String parentPHavingText = null;
	
	CurrentElement cElement = null;
	
	public CheckboxPatterns(CurrentElement cElement) {
		this.cElement = cElement;
	}
	

	enum InputTagPreference {
		InputTagWithSiblingedSpanText,
		InputTagWithSiblingedLabel;
	}
	
	
	enum AnchorTagPreference {
		InputTagAndSpanInsideAnchor;
	}

	enum ParentBased {
		InputTagwithParentLabel,
		InputTagwithGrandParentLabel,
		Upto4LevelParentsHavingClassWithCheckboxTxtAndContainsInnerSpan,
		InputTagWithParentPHavingText;
	}
	
	private boolean isCheckbox() {
		String attributeType = cElement.getTypeAttribute();
		if ((attributeType != null) && (attributeType.equalsIgnoreCase("checkbox")))
			return true;
		return false;
	}
	
	private boolean isInputTagWithSiblingedLabel() {
		siblingLabel = new TagsFinder().siblingLabel(cElement.getElement());
		if (siblingLabel == null)
			return false;
		label = siblingLabel.getText();
		if (label == null) {
			return false;
		}
		return true;
	}
	
	private boolean isInputTagWithSiblingedSpanTexts() {
		spanTexts = new SupportUtil().getElementsText(new TagsFinder().siblingSpans(cElement.getElement()));
		return spanTexts.isEmpty();
	}
	
	private boolean isInputTagWithWithAnchorParent() {
		WebElement parentElement = new TagsFinder().parentElement(cElement.getElement());
		return parentElement.getTagName().equalsIgnoreCase("a");
	}

	private boolean isInputTagwithParentLabel() {
		parentLabel = new TagsFinder().parentElement(cElement.getElement());
		if (parentLabel.getTagName().equalsIgnoreCase("label")) {
			parentLabelText = parentLabel.getText().trim();
			return true;
		}
		return false;
	}

	private boolean isInputTagwithGrandParentLabel() {
		WebElement parentElement = new TagsFinder().parentElement(cElement.getElement());
		WebElement grandParent = new TagsFinder().parentElement(parentElement);
		if (grandParent.getTagName().equalsIgnoreCase("label")) {
			grandParentLabelText = grandParent.getText().trim();
			return true;
		}
		return false;
	}
	
	private boolean isUpto4LevelParentsHavingClassWithCheckboxTxtAndContainsInnerSpan() {

		WebElement parent1 = new TagsFinder().parentElement(cElement.getElement());
		int val = utilize(parent1);
		if (val == 1)
			return true;
		else if (val == -1)
			return false;

		parent1 = new TagsFinder().parentElement(parent1);
		val = utilize(parent1);
		if (val == 1)
			return true;
		else if (val == -1)
			return false;

		parent1 = new TagsFinder().parentElement(parent1);
		val = utilize(parent1);
		if (val == 1)
			return true;
		else if (val == -1)
			return false;

		parent1 = new TagsFinder().parentElement(parent1);
		val = utilize(parent1);
		if (val == 1)
			return true;
		else if (val == -1)
			return false;

		return false;
	}

	private int utilize(WebElement parent) {

		String parent1Class = parent.getAttribute("class");
		if ((parent1Class != null) && parent1Class.trim().toLowerCase().contains("checkbox")) {
			List<WebElement> innerSpans = new TagsFinder().innerSpanElements(parent);
			for (WebElement span : innerSpans) {
				String spnTxt = span.getText();
				if (spnTxt != null && spnTxt.trim().length() > 0) {
					upto4LevelParentsHavingClassWithCheckboxTxtAndContainsInnerSpan = spnTxt;
					System.out.println("upto4LevelParentsHavingClassWithCheckboxTxtAndContainsInnerSpan si : " + upto4LevelParentsHavingClassWithCheckboxTxtAndContainsInnerSpan);
					return 1;
				}
			}
		} else {
			return -1;
		}
		return 0;
	}
	
	private boolean isInputTagWithParentPHavingText() {
		WebElement parentElement = new TagsFinder().parentP(cElement.getElement());
		System.out.println("isInputTagWithParentFontHavingText atrtibutes : " + cElement.getAttributes());
		if (parentElement != null) {
			System.out.println("parentElement s not null");
			String txt = parentElement.getText();
			if (txt != null && txt.trim().length() > 0) {
				parentPHavingText = txt.trim();
				System.out.println("txt is --: " + txt.trim());
				return true;
			}
		}
		return false;
	}
	
	
	public Checkbox findPattern() {
		if (isCheckbox()) {
			Checkbox chk = new Checkbox();
			isInputTagWithSiblingedLabel();
			isInputTagWithSiblingedSpanTexts();
			isInputTagWithWithAnchorParent();
			isInputTagwithParentLabel();
			isInputTagwithGrandParentLabel();
			isUpto4LevelParentsHavingClassWithCheckboxTxtAndContainsInnerSpan();
			isInputTagWithParentPHavingText();
			
			chk.setCheckBoxLabel(label);
			chk.setSpanTexts(spanTexts);
			chk.setParentLabelText(parentLabelText);
			chk.setGrandParentLabelText(grandParentLabelText);
			chk.setParentElementAnchor(isAnchorParent);
			chk.setcElement(cElement);
			chk.setSiblingLabel(siblingLabel);
			chk.setParentLabel(parentLabel);
			chk.setUpto4LevelParentsHavingClassWithCheckboxTxtAndContainsInnerSpan(upto4LevelParentsHavingClassWithCheckboxTxtAndContainsInnerSpan);
			chk.setParentPHavingText(parentPHavingText);
			
			return chk;
		}
		return null;
	}
	
}
