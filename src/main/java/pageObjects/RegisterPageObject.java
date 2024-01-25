package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.HomePageUI;
import pageUIs.LoginPageUI;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	private WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}
	

	public void sendkeyToEmailTextbox(String email) {
		scrollIntoView(driver, RegisterPageUI.REGISTER_BUTTON);
		waitForElementCLickable(driver, RegisterPageUI.REGISTER_TEXTBOX, "email");
		sendkeysToElement(driver, RegisterPageUI.REGISTER_TEXTBOX, email, "email");
	}

	public void sendkeyToPasswordTextbox(String password) {
		waitForElementVisible(driver, RegisterPageUI.REGISTER_TEXTBOX, "password");
		sendkeysToElement(driver, RegisterPageUI.REGISTER_TEXTBOX, password, "password");
	}

	public void sendkeyToConfirmPasswordTextbox(String confimrPassword) {
		waitForElementVisible(driver, RegisterPageUI.REGISTER_TEXTBOX, "confirmPassword");
		sendkeysToElement(driver, RegisterPageUI.REGISTER_TEXTBOX, confimrPassword, "confirmPassword");
	}
	
	public void sendkeyToConfirmPIDTextbox(String PID) {
		waitForElementVisible(driver, RegisterPageUI.REGISTER_TEXTBOX, "pid");
		sendkeysToElement(driver, RegisterPageUI.REGISTER_TEXTBOX, PID, "pid");
	}
	
	public void clickToRegisterButton() {
		scrollIntoView(driver, RegisterPageUI.REGISTER_BUTTON);
		waitForElementCLickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}
	
	public RegisterSuccessPageObject createNewAccount(String email, String password,String confimrPassword,String PID) {
		
		sendkeyToEmailTextbox(email);
		sendkeyToPasswordTextbox(password);
		sendkeyToConfirmPasswordTextbox(confimrPassword);
		sendkeyToConfirmPIDTextbox(PID);
		clickToRegisterButton();
		return PageGeneratorManager.getRegisterSuccessPage(driver);	
	}
	
	public String getExistingAccountErrorMessage() {
		waitForElementVisible(driver, RegisterPageUI.EXISTING_ACCOUNT_ERROR);
		return getElementText(driver, RegisterPageUI.EXISTING_ACCOUNT_ERROR);
	}
	
	public String getInvalidPassordErrorMessage() {
		waitForElementVisible(driver, RegisterPageUI.INVALID_PASSWORD_ERROR);
		return getElementText(driver, RegisterPageUI.INVALID_PASSWORD_ERROR);
	}

	public String getInvalidIDErrorMessage() {
		waitForElementVisible(driver, RegisterPageUI.INVALID_ID_ERROR);
		return getElementText(driver, RegisterPageUI.INVALID_ID_ERROR);
	}
	
	public String getRegisterPageTitle() {
		return driver.getTitle();
	}
	


}
