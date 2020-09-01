package mks.uiautowagon.interactor.patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.CurrentElement;
import mks.uiautowagon.interactor.interutil.SupportUtil;
import mks.uiautowagon.interactor.interutil.TagsFinder;
import mks.uiautowagon.interactor.patterns.objects.TextField;
import mks.uiautowagon.interactor.store.TextFieldsStore;

public class TextFieldPatterns {
	
	private String placeholder = null;
	private String label = null;
	private String trParallelLabel = null;
	
	private String divNeighbourPlaceholder = null;
	private String ariaLabel = null;
	
	private String parentDivsSiblingLabel = null;
	private String parentDivsSiblingInnerLabel = null;
	
	private String inputValue = null;
	private String siblingSpanText = null;
	private String parentBTxt = null;
	
	private String grandParentsSiblingHavingInnerSpanTxt = null;
	private String parentsSiblingH3Label = null;
	private String parentsSiblingH4Label = null;
	private String parentsSiblingH5Label = null;
	
	private String siblingsInnerLabel = null;
	private String grandParentDivsSiblingDivsChildSpan = null;
	
	private String trHavingTwoTdOneForLabelAndOneForField = null;
	
	CurrentElement cElement = null;
	
	private List<String> acceptedAttributeTypes = new ArrayList<>(Arrays.asList("password", "text", "email", "tel", "search"));
	
	public TextFieldPatterns(CurrentElement cElement) {
		this.cElement = cElement;
	}
	
	enum WithinAParentTag {
		InputTagHavingPlaceholder,
		InputTagWithSiblingedLabel,
		InputTagWithSiblingedSpan,
		InputTagWithSiblingedDivPlaceholder,
		InputTagHavingAriaLabelholder,
		InputTagHavingValue;
	}
	
	
	enum OusideParentTag {
		FirstTrHavingLabelsSecondTrHavingInput,
		DivParentsSiblingLabelUnderGrandParentDiv,
		DivParentsSiblingChildLabelUnderGrandParentDiv,
		
		BParentTagHavingTextIncludingInput,
		GrandParentsSiblingHavingInnerSpan,
		ParentsSiblingH3Label,
		ParentsSiblingH4Label,
		ParentsSiblingH5Label,
		SiblingsInnerLabel,
		GrandParentDivsSiblingDivsChildSpan,
		TrHavingTwoTdOneForLabelAndOneForField
		;
	}
	

	private boolean isTextField() {
		String attributeType = cElement.getElement().getAttribute("type");
		if ((attributeType != null) && (acceptedAttributeTypes.contains(attributeType)))
			return true;
		return false;
	}

	private boolean isLabelFound() {
		if (label == null) {
			return false;
		} else if (label.trim().length() == 0) {
			return false;
		}
		return true;
	}

	private boolean isPlaceholderFound() {
		if (placeholder == null) {
			return false;
		} else if (placeholder.trim().length() == 0) {
			return false;
		}
		return true;
	}

	private boolean isInputTagHavingPlaceholder() {
		placeholder = cElement.getElement().getAttribute("placeholder");
		System.out.println("placeholder is : " + placeholder);
		if (placeholder != null) {
			return true;
		}
		return false;
	}
	
	private boolean isInputTagWithSiblingedLabel() {

		WebElement siblingLabel = new TagsFinder().siblingLabel(cElement.getElement());
		if (siblingLabel != null) {
			label = siblingLabel.getText().trim();
			return true;
		}
		return false;
	}

	private boolean isInputTagWithSiblingedSpan() {
		
		System.out.println("cElement.getElement()attrte : " + cElement.getAttributes());
		WebElement siblingSpanTxtElement = new TagsFinder().siblingSpan(cElement.getElement());
		System.out.println("siblingSpanTxtElement s : " + siblingSpanTxtElement);
		if (siblingSpanTxtElement != null) {
			siblingSpanText = siblingSpanTxtElement.getText().trim();
			System.out.println("siblingSpanText -- " + siblingSpanText);
			return true;
		}
		return false;
	}
	
	private boolean isFirstTrHavingLabelsSecondTrHavingInput() {

		if (label == null) {
			String elementAttribute = new SupportUtil().getAttributes(cElement.getElement());
			System.out.println("elementAttribute -- " + elementAttribute);
			WebElement tdParent = new TagsFinder().parentTD(cElement.getElement());
			if (tdParent != null) {
				WebElement trParent = new TagsFinder().parentTR(tdParent);
				if(trParent != null) {
					WebElement parentTbodyElement = new TagsFinder().parentTBody(trParent);
					if (parentTbodyElement != null) {
						List<WebElement> allInnerInputs = new TagsFinder().innerInputs_ChildToTD(parentTbodyElement);
						System.out.println("allInnerInputs found are: " + allInnerInputs.size());
						int i;
						for (i = 0; i < allInnerInputs.size(); i++) {
							System.out.println("new SupportUtil().getAttributes(allInnerInputs.get(i)) : " + new SupportUtil().getAttributes(allInnerInputs.get(i)));
							if (new SupportUtil().getAttributes(allInnerInputs.get(i)).equalsIgnoreCase(elementAttribute)) {
								break;
							}
						}
						List<WebElement> allInnerLabels = new TagsFinder().innerLabels_ChildToTD(parentTbodyElement);
						System.out.println("allInnerLabels found are: " + allInnerLabels.size());
						if (allInnerLabels.size() > i) {
							trParallelLabel = allInnerLabels.get(i).getText().trim();
							System.out.println("allInnerLabels.get(i).getText().trim() : " + trParallelLabel);
						}
					}
				}
			}
		}
		return false;
	}
	
	private boolean isInputTagWithSiblingedDivPlaceholder() {
		
		System.out.println("label at this posiiong is : " + label);
		System.out.println("placeholder at this posiiong is : " + placeholder);

		if ((!isLabelFound()) && (!isPlaceholderFound())) {
			List<WebElement> siblings = new TagsFinder().siblingDivs(cElement.getElement());
			System.out.println("Found siblings divs are : " + siblings.size());

			for (WebElement tempElement : siblings) {
				
				String placeHolderTxt = tempElement.getAttribute("placeholder");
				System.out.println("placeHolderTxt fnd is : " + placeHolderTxt);
				if ((placeHolderTxt != null) && placeHolderTxt.trim().length() > 0) {
					divNeighbourPlaceholder = placeHolderTxt.trim();
					System.out.println("divNeighbourPlaceholder s : " + divNeighbourPlaceholder);
					return true;
				}
				placeHolderTxt = tempElement.getAttribute("class");
				System.out.println("class fnd is : " + placeHolderTxt);
				if ((placeHolderTxt != null) && placeHolderTxt.trim().equalsIgnoreCase("placeholder")) {
					divNeighbourPlaceholder = tempElement.getText().trim();
					return true;
				}
				placeHolderTxt = tempElement.getAttribute("name");
				System.out.println("name fnd is : " + placeHolderTxt);
				if ((placeHolderTxt != null) && placeHolderTxt.trim().equalsIgnoreCase("placeholder")) {
					divNeighbourPlaceholder = tempElement.getText().trim();
					return true;
				}
				
				
			}
		}
		return false;
	}
	
	private boolean isInputTagHavingAriaLabelholder() {
		System.out.println("isInputTagHavingAriaLabelholder label : " + label);
		System.out.println("isInputTagHavingAriaLabelholder placeholder : " + placeholder);
		if ((label == null) && (!isPlaceholderFound())) {
			ariaLabel = cElement.getElement().getAttribute("aria-label");
			System.out.println("ariaLabel is :: " + ariaLabel);
			return true;
		}
		return false;
	}

	private boolean isDivParentsSiblingLabelUnderGrandParentDiv() {
		//if ((label == null) && (trParallelLabel == null) && (ariaLabel == null)) {
			WebElement parentDiv = new TagsFinder().parentDiv(cElement.getElement());
			if (parentDiv != null) {
				System.out.println("cElement's attributes : " + cElement.getAttributes());
				WebElement parentSiblingLabel = new TagsFinder().siblingLabel(parentDiv);
				System.out.println("parentSiblingLabel -- " + parentSiblingLabel);
				if (parentSiblingLabel != null) {
					System.out.println("parentSiblingLabel is not null-- and the text is : " + parentSiblingLabel.getText());
					parentDivsSiblingLabel = parentSiblingLabel.getText();
					return true;
				}
			}
		//}
		return false;
	}

	private boolean isDivParentsSiblingChildLabelUnderGrandParentDiv() {
		if (parentDivsSiblingLabel == null) {
			System.out.println("TextFieldsStore.textFieldsList.size() : " + TextFieldsStore.textFieldsList.size());
			WebElement parentDiv = new TagsFinder().parentDiv(cElement.getElement());
			if (parentDiv != null) {
				List<WebElement> parentSiblingDivs = new TagsFinder().siblingDivs(parentDiv);
				System.out.println("parentSiblingDivs are : " + parentSiblingDivs.size());
				for (WebElement divElement : parentSiblingDivs) {
					List<WebElement> childLabels = new TagsFinder().childLabels(divElement);
					System.out.println("childLabels size : " + childLabels.size());
					for (WebElement label : childLabels) {
						parentDivsSiblingInnerLabel = label.getText();
						System.out.println("parentDivsSiblingInnerLabel is : " + parentDivsSiblingInnerLabel);
						if (parentDivsSiblingInnerLabel != null && parentDivsSiblingInnerLabel.trim().length() > 0) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	private boolean inputTagHavingValue() {
		inputValue = cElement.getElement().getAttribute("value");
		if (inputValue != null)
			return true;
		return false;
	}
	
	private boolean isBParentTagHavingTextIncludingInput() {
		WebElement parentB = new TagsFinder().parentB(cElement.getElement());
		if (parentB != null) {
			parentBTxt = parentB.getText();
			if (parentBTxt != null)
				return true;
		}
		return false;
	}
	
	private boolean isGrandParentsSiblingHavingInnerSpan() {
		if ((label == null) && (trParallelLabel == null) && (ariaLabel == null) && (parentDivsSiblingLabel == null)
				&& (parentDivsSiblingInnerLabel == null) && (siblingSpanText == null) && (parentBTxt == null)) {
			WebElement parentDiv = new TagsFinder().parentDiv(cElement.getElement());
			if (parentDiv != null) {
				WebElement grandParentDiv = new TagsFinder().parentDiv(parentDiv);
				if (grandParentDiv != null) {
					WebElement grandParentsParentDiv = new TagsFinder().parentDiv(grandParentDiv);
					if (grandParentsParentDiv != null) {
						List<WebElement> innerSpans = new TagsFinder().innerSpanElements(grandParentsParentDiv);
						if (innerSpans.size() > 0) {
							grandParentsSiblingHavingInnerSpanTxt = innerSpans.get(0).getText();
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	
	private boolean isParentsSiblingH3Label() {
		WebElement parent = new TagsFinder().parentElement(cElement.getElement());
		WebElement grandParent = new TagsFinder().parentDiv(parent);
		if (grandParent != null) {
			List<WebElement> childH3s = new TagsFinder().childH3s(grandParent);
			for (WebElement h3 : childH3s) {
				String h3Txt = h3.getText();
				if (h3Txt != null && h3Txt.trim().length() > 0) {
					parentsSiblingH3Label = h3Txt;
					return true;
				}
			}
		}
		return false;
	}

	private boolean isParentsSiblingH4Label() {
		WebElement parent = new TagsFinder().parentElement(cElement.getElement());
		WebElement grandParent = new TagsFinder().parentDiv(parent);
		if (grandParent != null) {
			List<WebElement> childH4s = new TagsFinder().childH4s(grandParent);
			for (WebElement h4 : childH4s) {
				String h4Txt = h4.getText();
				if (h4Txt != null && h4Txt.trim().length() > 0) {
					parentsSiblingH4Label = h4Txt;
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isParentsSiblingH5Label() {
		WebElement parent = new TagsFinder().parentElement(cElement.getElement());
		WebElement grandParent = new TagsFinder().parentDiv(parent);
		if (grandParent != null) {
			List<WebElement> childH5s = new TagsFinder().childH5s(grandParent);
			for (WebElement h5 : childH5s) {
				String h5Txt = h5.getText();
				if (h5Txt != null && h5Txt.trim().length() > 0) {
					parentsSiblingH5Label = h5Txt;
					return true;
				}
			}
		}
		return false;
	}
	
	
	private boolean isSiblingsInnerLabel() {
		WebElement parent = new TagsFinder().parentElement(cElement.getElement());
		List<WebElement> innerLabels = new TagsFinder().innerLabelElements(parent);
		for (WebElement labelElement : innerLabels) {
			String lblTxt = labelElement.getText();
			if (lblTxt != null && lblTxt.trim().length() > 0) {
				siblingsInnerLabel = lblTxt;
				return true;
			}
		}
		return false;
	}
	
	private boolean isGrandParentDivsSiblingDivsChildSpan() {
		WebElement parent = new TagsFinder().parentElement(cElement.getElement());
		WebElement grandParentDiv = new TagsFinder().parentDiv(parent);
		if (grandParentDiv != null) {
			List<WebElement> sibingDivs = new TagsFinder().siblingDivs(grandParentDiv);
			for (WebElement div : sibingDivs) {
				List<WebElement> childSpans = new TagsFinder().childSpans(div);
				for (WebElement spn : childSpans) {
					String childSpanTxt = spn.getText();
					if (childSpanTxt != null && childSpanTxt.trim().length() > 0) {
						grandParentDivsSiblingDivsChildSpan = childSpanTxt;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean isTrHavingTwoTdOneForLabelAndOneForField() {
		WebElement parent = new TagsFinder().parentElement(cElement.getElement());
		if (!parent.getTagName().equalsIgnoreCase("td")) {
			parent = new TagsFinder().parentElement(parent);
			if (!parent.getTagName().equalsIgnoreCase("td")) {
				parent = new TagsFinder().parentElement(parent);
				if (!parent.getTagName().equalsIgnoreCase("td")) {
					return false;
				}
			}
		}
		parent = new TagsFinder().parentElement(parent);
		if (!parent.getTagName().equalsIgnoreCase("tr")) {
			return false;
		}
		List<WebElement> innerTds = new TagsFinder().innerTDElements(parent);
		if (innerTds.size() != 2) {
			return false;
		}
		List<WebElement> innerInputs = new TagsFinder().innerInputElements(innerTds.get(0));
		if (innerInputs.size() > 0) {
			trHavingTwoTdOneForLabelAndOneForField = innerTds.get(1).getText();
		} else {
			trHavingTwoTdOneForLabelAndOneForField = innerTds.get(0).getText();
		}
		return true;
	}
	
	public TextField findPattern() {
		if (isTextField()) {
			TextField tf = new TextField();
			isInputTagHavingPlaceholder();
			isInputTagWithSiblingedLabel();
			isFirstTrHavingLabelsSecondTrHavingInput();
			isInputTagWithSiblingedDivPlaceholder();
			isInputTagHavingAriaLabelholder();
			isDivParentsSiblingLabelUnderGrandParentDiv();
			isDivParentsSiblingChildLabelUnderGrandParentDiv();
			inputTagHavingValue();
			isInputTagWithSiblingedSpan();
			isBParentTagHavingTextIncludingInput();
			isGrandParentsSiblingHavingInnerSpan();
			isParentsSiblingH3Label();
			isParentsSiblingH4Label();
			isParentsSiblingH5Label();
			isSiblingsInnerLabel();
			isGrandParentDivsSiblingDivsChildSpan();
			isTrHavingTwoTdOneForLabelAndOneForField();
			
			tf.setPlaceholder(placeholder);
			tf.setLabelText(label);
			tf.setTrParallelLabel(trParallelLabel);
			tf.setDivNeighbourPlaceholder(divNeighbourPlaceholder);
			tf.setAriaLabel(ariaLabel);
			tf.setParentDivsSiblingLabel(parentDivsSiblingLabel);
			tf.setParentDivsSiblingInnerLabel(parentDivsSiblingInnerLabel);
			tf.setInputValue(inputValue);
			tf.setSiblingSpanText(siblingSpanText);
			tf.setParentBTxt(parentBTxt);
			tf.setGrandParentsSiblingHavingInnerSpanTxt(grandParentsSiblingHavingInnerSpanTxt);
			tf.setParentsSiblingH3Label(parentsSiblingH3Label);
			tf.setParentsSiblingH4Label(parentsSiblingH4Label);
			tf.setParentsSiblingH5Label(parentsSiblingH5Label);
			tf.setSiblingsInnerLabel(siblingsInnerLabel);
			tf.setGrandParentDivsSiblingDivsChildSpan(grandParentDivsSiblingDivsChildSpan);
			tf.setTrHavingTwoTdOneForLabelAndOneForField(trHavingTwoTdOneForLabelAndOneForField);
			
			tf.setcElement(cElement);
			return tf;
		}
		return null;
	}
	
	
	
	
}
