package mks.base.driver;

import mks.uiautowagon.interactor.WagonerFacade;

public class CsvTestPerformer {

	private WagonerFacade wagoner = null;
	private String elementType = null;
	private String elementLabel = null;

	public boolean foundException = false;
	public String exceptionMessage = null;
	
	private String setTextStr = "sdvdfsdfgdsdfdf";

	public CsvTestPerformer(WagonerFacade wagoner, String elementType, String elementLabel) {
		this.wagoner = wagoner;
		this.elementType = elementType.trim();
		this.elementLabel = elementLabel.trim();
		validateElementType();
	}

	private boolean isButton() {
		return elementType.equalsIgnoreCase("Button");
	}

	private boolean isLink() {
		return elementType.equalsIgnoreCase("link");
	}

	private boolean isCheckbox() {
		return elementType.equalsIgnoreCase("Checkbox");
	}

	private boolean isRadio() {
		return elementType.equalsIgnoreCase("Radio");
	}

	private boolean isTextfield() {
		return elementType.equalsIgnoreCase("Textfield");
	}

	private void validateElementType() {
		if ((!isButton()) && (!isLink()) && (!isCheckbox()) && (!isRadio()) && (!isTextfield())) {
			foundException = true;
			exceptionMessage = "The given element type '" + elementType + "' is unidentified!";
		}
	}

	private void setTextStr() {
		if (elementLabel.toLowerCase().contains("mobile") || elementLabel.toLowerCase().contains("phone"))
			setTextStr = "9876543487";
		else if (elementLabel.toLowerCase().contains("mail"))
			setTextStr = "sdgfg@sdvdf.com";
		else if (elementLabel.toLowerCase().contains("age"))
			setTextStr = "56";
	}
	
	
	public void performAction() {

		if (!foundException) {
			try {
				if (isButton()) {
					wagoner.button.get(elementLabel).click();
				} else if (isLink()) {
					wagoner.link.get(elementLabel).click();
				} else if (isCheckbox()) {
					wagoner.checkBox.get(elementLabel).click();
				} else if (isRadio()) {
					wagoner.radioButton.get(elementLabel).click();
				} else if (isTextfield()) {
					setTextStr();
					wagoner.textField.get(elementLabel).sendKeys(setTextStr);
				}
			} catch (NullPointerException e) {
				foundException = true;
				exceptionMessage = "Found NullPointerException while performing action on element, and the exception is: "
						+ e.getMessage();
				e.printStackTrace();
			} catch (Exception e) {
				foundException = true;
				exceptionMessage = "Found Exception while performing action on element, and the exception is: "
						+ e.getMessage();
				e.printStackTrace();
			}
		}
	}
	
	
	
}
