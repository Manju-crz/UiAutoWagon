package mks.uiautowagon.interactor.patterns;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.CurrentElement;
import mks.uiautowagon.interactor.interutil.SupportUtil;
import mks.uiautowagon.interactor.interutil.TagsFinder;
import mks.uiautowagon.interactor.patterns.objects.RadioButton;

public class RadioButtonPatterns {

	private String siblingLabel = null;
	private String parentLabelText = null;
	private String parentDivText = null;
	private String divParentingToInputWithParentSiblingText = null;
	
	private String parentLabelsInnerSpanText = null;
	private String grandParentLabelsInnerSpansParentDivText = null;
	
	private String parentFontHavingText = null;
	private String inputHavingAriaLabel = null;
	private String inputHavingValue = null;
	
	CurrentElement cElement = null;
	
	public RadioButtonPatterns(CurrentElement cElement) {
		this.cElement = cElement;
	}
	

	enum InputTagPreference {
		SiblingedInputaAndLabels,
		LabelParentingToInput,
		DivParentingToInput,
		DivParentingToInputWithParentSiblingText,
		ParentLabelsInnerSpanText,
		GrandParentLabelsInnerSpansParentDivText,
		InputTagWithParentFontHavingText,
		InputHavingAriaLabel,
		InputHavingValueAttribute;
	}
	
	private boolean isRadioButton() {
		String attributeType = cElement.getTypeAttribute();
		if ((attributeType != null) && (attributeType.equalsIgnoreCase("radio")))
			return true;
		return false;
	}

	private boolean isSiblingedInputAndLabels() {
		String attributes = cElement.getAttributes();
		List<WebElement> siblingInputs = new TagsFinder().siblingInputs(cElement.getElement());
		List<WebElement> siblingLabels = new TagsFinder().siblingLabels(cElement.getElement());
		for (int i = 0; i < siblingInputs.size(); i++) {
			if (new SupportUtil().getAttributes(siblingInputs.get(i)).equalsIgnoreCase(attributes)) {
				if (i < siblingLabels.size()) {
					siblingLabel = siblingLabels.get(i).getText();
					return true;
				} else {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean isLabelParentingToInput() {
		WebElement parentElement = new TagsFinder().parentElement(cElement.getElement());
		if (parentElement.getTagName().equalsIgnoreCase("label")) {
			parentLabelText = parentElement.getText().trim();
			return true;
		}
		return false;
	}
	
	private boolean isDivParentingToInput() {
		if ((!isSiblingedInputAndLabels()) && (!isLabelParentingToInput())) {
			WebElement parentDiv = new TagsFinder().parentDiv(cElement.getElement());
			List<String> divStrList = new ArrayList<>();
			if (parentDiv != null) {
				String divStr = parentDiv.getText();
				if ((divStr != null) && divStr.trim().length() > 0) {
					String[] divStrArr = divStr.trim().split("   ");
					for (String str : divStrArr) {
						divStrList.add(str);
					}
				}
				if (divStrList.size() > 0) {
					String attributes = cElement.getAttributes().trim();
					List<WebElement> siblingInputs = new TagsFinder().siblingInputs(cElement.getElement());
					for (int i = 0; i < siblingInputs.size(); i++) {
						if (attributes.equalsIgnoreCase(new SupportUtil().getAttributes(siblingInputs.get(i)))) {
							try {
								parentDivText = divStrList.get(i);
							} catch (IndexOutOfBoundsException e) {
							}
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private boolean isDivParentingToInputWithParentSiblingText() {

		if ((!isSiblingedInputAndLabels()) && (!isLabelParentingToInput()) && (!isDivParentingToInput())) {
			List<WebElement> siblings = new TagsFinder().siblings(cElement.getElement());
			if (siblings.size() > 1) {
				return false;
			} else {
				int ipcount = -1, finalIdx = -1;
				List<String> allTexts = new ArrayList<>();
				WebElement grandParent = new TagsFinder().parentDiv(new TagsFinder().parentDiv(siblings.get(0)));

				if (grandParent != null) {
					List<WebElement> parentSiblings = new TagsFinder().childElements(grandParent);
					String attributes = cElement.getAttributes();
					for (int i = 0; i < parentSiblings.size(); i++) {
						List<WebElement> child = new TagsFinder().childElements(parentSiblings.get(i));
						if (child.size() == 1) {
							if (child.get(0).getTagName().equalsIgnoreCase("span")) {
								allTexts.add(parentSiblings.get(i).getText());
							} else if (child.get(0).getTagName().equalsIgnoreCase("input")) {
								ipcount++;
								if (new SupportUtil().getAttributes(child.get(0)).equalsIgnoreCase(attributes)) {
									finalIdx = ipcount;
								}
							}
						} else if (child.size() == 0) {
							String txt = parentSiblings.get(i).getText();
							if ((txt != null) && txt.trim().length() > 0) {
								allTexts.add(parentSiblings.get(i).getText().trim());
							}
						}
					}
				} else {
					return false;
				}
				try {
				} catch (IndexOutOfBoundsException e) {

				}
			}
		}
		return false;
	}

	private boolean isparentLabelsInnerSpanText() {
			WebElement parentLabel = new TagsFinder().parentLabel(cElement.getElement());
			if (parentLabel != null) {
				List<WebElement> childSpans = new TagsFinder().childSpans(parentLabel);
				if (childSpans.size() > 0) {
					parentLabelsInnerSpanText = childSpans.get(0).getText();
					cElement.setElement(parentLabel);
					return true;
				}
			}
		return false;
	}
	
	private boolean isGrandParentLabelsInnerSpansParentDivText() {
		WebElement parentDiv = new TagsFinder().parentDiv(cElement.getElement());
		if (parentDiv != null) {
			WebElement grandParentLabel = new TagsFinder().parentLabel(parentDiv);
			if (grandParentLabel != null) {
				List<WebElement> spanElements = new TagsFinder().innerSpanElements(grandParentLabel);
				if (spanElements.size() > 0) {
					for (WebElement spnElement : spanElements) {
						WebElement pDiv = new TagsFinder().parentDiv(spnElement);
						if (pDiv != null) {
							String divTxt = pDiv.getText();
							if (divTxt != null && divTxt.trim().length() > 0) {
								grandParentLabelsInnerSpansParentDivText = divTxt.trim();
								cElement.setElement(grandParentLabel);
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	private boolean isInputTagWithParentFontHavingText() {
		WebElement parentElement = new TagsFinder().parentFont(cElement.getElement());
		if (parentElement != null) {
			String txt = parentElement.getText();
			if (txt != null && txt.trim().length() > 0) {
				parentFontHavingText = txt;
				return true;
			}
		}
		return false;
	}
	
	private boolean isInputHavingAriaLabel() {
		inputHavingAriaLabel = cElement.getElement().getAttribute("aria-label");
		if (inputHavingAriaLabel != null && inputHavingAriaLabel.trim().length() > 0)
			return true;
		return false;
	}

	private boolean isInputHavingValueAttribute() {
		inputHavingValue = cElement.getElement().getAttribute("value");
		if (inputHavingValue != null && inputHavingValue.trim().length() > 0)
			return true;
		return false;
	}
	
	public RadioButton findPattern() {
		if (isRadioButton()) {
			RadioButton rdo = new RadioButton();
			isSiblingedInputAndLabels();
			isLabelParentingToInput();
			isDivParentingToInput();
			isDivParentingToInputWithParentSiblingText();
			isparentLabelsInnerSpanText();
			isGrandParentLabelsInnerSpansParentDivText();
			isInputTagWithParentFontHavingText();
			isInputHavingAriaLabel();
			isInputHavingValueAttribute();
			
			rdo.setSiblingLabel(siblingLabel);
			rdo.setParentLabelText(parentLabelText);
			rdo.setParentDivText(parentDivText);
			rdo.setDivParentingToInputWithParentSiblingText(divParentingToInputWithParentSiblingText);
			rdo.setParentLabelsInnerSpanText(parentLabelsInnerSpanText);
			rdo.setGrandParentLabelsInnerSpansParentDivText(grandParentLabelsInnerSpansParentDivText);
			rdo.setParentFontHavingText(parentFontHavingText);
			rdo.setInputHavingAriaLabel(inputHavingAriaLabel);
			rdo.setInputHavingValue(inputHavingValue);
			
			rdo.setElement(cElement.getElement());
			return rdo;
		}
		return null;
	}
	
}
