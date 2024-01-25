package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.GeneralPageUI;
import pageUIs.HomePageUI;
import pageUIs.PasswordChangePageUI;
import pageUIs.registrationConfirmPageUI;

public class PasswordChangePageObject extends BasePage {
	private WebDriver driver;
	
	public PasswordChangePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void fillInPasswordChangeForm(String newPassword, String newConfirmPassword) {
		waitForElementCLickable(driver, PasswordChangePageUI.RESET_PASSWORD_BUTTON);
		scrollIntoView(driver, PasswordChangePageUI.RESET_PASSWORD_BUTTON);
		waitForElementCLickable(driver, PasswordChangePageUI.NEW_PASSWORD_TEXTBOX);
		sendkeysToElement(driver, PasswordChangePageUI.NEW_PASSWORD_TEXTBOX, newPassword);
		waitForElementCLickable(driver, PasswordChangePageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendkeysToElement(driver, PasswordChangePageUI.CONFIRM_PASSWORD_TEXTBOX, newConfirmPassword);
		scrollIntoView(driver, PasswordChangePageUI.RESET_PASSWORD_BUTTON);
		clickToElement(driver, PasswordChangePageUI.RESET_PASSWORD_BUTTON);
	}

	public String getPasswordChangeMessage() {
		waitForElementVisible(driver, PasswordChangePageUI.PASSWORD_CHANGE_MSG);
		return getElementText(driver, PasswordChangePageUI.PASSWORD_CHANGE_MSG);
	}

	public String getConfirmationPasswordErrorMessage() {
		waitForElementVisible(driver, PasswordChangePageUI.CONFIRM_PASSWORD_VALIDATION_ERROR);
		return getElementText(driver, PasswordChangePageUI.CONFIRM_PASSWORD_VALIDATION_ERROR);
	}
	
	public boolean passwordChangeFormDisplays() {
		isElementDisplay(driver, PasswordChangePageUI.PASSWORD_CHANGE_FORM);
		return true;
	}
	
	public boolean passwordResetTokenAttributeDisplays() {
		String resetToken = getElementAttribute(driver, PasswordChangePageUI.RESET_TOKEN);
		System.out.println("token "+ resetToken);
		if(resetToken.isEmpty()) {
			return false;
		}
		return true;
	}
	
	
}
