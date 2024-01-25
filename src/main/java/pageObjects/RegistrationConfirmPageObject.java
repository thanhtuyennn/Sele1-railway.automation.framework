package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.GeneralPageUI;
import pageUIs.HomePageUI;
import pageUIs.registrationConfirmPageUI;

public class RegistrationConfirmPageObject extends BasePage {
	private WebDriver driver;
	
	public RegistrationConfirmPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public LoginPageObject openLoginPage() {
		waitForElementCLickable(driver, registrationConfirmPageUI.LOGIN_BUTTON);
		clickToElement(driver, registrationConfirmPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getLoginPage(driver);
		
	}
	
	public String getRegistrationConfirmMessage() {
		waitForElementVisible(driver, registrationConfirmPageUI.REGISTRATION_CONFIRM_MSG);
		return getElementText(driver, registrationConfirmPageUI.REGISTRATION_CONFIRM_MSG);
	}
	
	
}
