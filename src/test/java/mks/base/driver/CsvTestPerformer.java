package mks.base.driver;

import org.openqa.selenium.StaleElementReferenceException;

import mks.java.util.Sleep;
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

	public boolean isButton() {
		return elementType.equalsIgnoreCase("Button");
	}

	public boolean isLink() {
		return elementType.equalsIgnoreCase("link");
	}

	public boolean isCheckbox() {
		return elementType.equalsIgnoreCase("Checkbox");
	}

	public boolean isRadio() {
		return elementType.equalsIgnoreCase("Radio");
	}

	public boolean isTextfield() {
		return elementType.equalsIgnoreCase("Textfield");
	}

	public boolean isTextArea() {
		return elementType.equalsIgnoreCase("TextArea");
	}

	private void validateElementType() {
		if ((!isButton()) && (!isLink()) && (!isCheckbox()) && (!isRadio()) && (!isTextfield()) && (!isTextArea())) {
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
				try {
					perform();
				} catch (StaleElementReferenceException e) {
					wagoner.reload();
					perform();
				}
			} catch (NullPointerException e) {
				foundException = true;
				exceptionMessage = String.format(
						"NullPointerException: Element Type- %s; Element Label- %s; Message- %s", elementType,
						elementLabel, e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				foundException = true;
				exceptionMessage = String.format("Exception: Element Type- %s; Element Label- %s; Message- %s",
						elementType, elementLabel, e.getMessage());
				e.printStackTrace();
			}
		}
	}

	private void perform() {
		if (isButton()) {
			wagoner.button.get(elementLabel).click();
			Sleep.for5Seconds();
		} else if (isLink()) {
			wagoner.link.get(elementLabel).click();
			Sleep.for5Seconds();
		} else if (isCheckbox()) {
			wagoner.checkBox.get(elementLabel).click();
		} else if (isRadio()) {
			wagoner.radioButton.get(elementLabel).click();
		} else if (isTextfield()) {
			setTextStr();
			wagoner.textField.get(elementLabel).sendKeys(setTextStr);
		} else if (isTextArea()) {
			setTextStr();
			wagoner.textArea.get(elementLabel).sendKeys(setTextStr);
		}
	}
	
}
