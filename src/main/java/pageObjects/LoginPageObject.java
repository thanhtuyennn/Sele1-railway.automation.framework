package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.GeneralPageUI;
import pageUIs.HomePageUI;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void sendkeyToEmailTextbox(String email) {
		waitForElementVisible(driver, LoginPageUI.LOGIN_TEXTBOX, "username");
		sendkeysToElement(driver, LoginPageUI.LOGIN_TEXTBOX, email, "username");;
	}

	public void sendkeyToPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUI.LOGIN_TEXTBOX, "password");
		sendkeysToElement(driver, LoginPageUI.LOGIN_TEXTBOX, password, "password" );
	}

	public void clickToLoginButton() {
		scrollIntoView(driver, LoginPageUI.LOGIN_BUTTON);
		waitForElementCLickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
	}

	public String getErrorMessage() {
		waitForElementVisible(driver, LoginPageUI.ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.ERROR_MESSAGE);
	}
	
	public void enterValidInformationExceptionPassword(String email, String password) {
		sendkeyToEmailTextbox(email);
		sendkeyToPasswordTextbox(password);
		clickToLoginButton();
	}
	
	public void enterValidInformationExceptionPasswordSeveralTimes(String email, String password, int times) {
		for (int i = 1; i <= times; i++) {
			sendkeyToEmailTextbox(email);
			sendkeyToPasswordTextbox(password);
			clickToLoginButton();
		}
	}
	

	public HomePageObject loginToSystem(String email, String password) {
		
		sendkeyToEmailTextbox(email);
		sendkeyToPasswordTextbox(password);
		clickToLoginButton();
		return PageGeneratorManager.getHomePage(driver);
		
	}

	public ForgotPasswordPageObject openForgotPasswordLink() {
		scrollIntoView(driver, LoginPageUI.FORGOT_PASSWORD_LINK);
		waitForElementCLickable(driver, LoginPageUI.FORGOT_PASSWORD_LINK);
		clickToElement(driver, LoginPageUI.FORGOT_PASSWORD_LINK);
		return PageGeneratorManager.getForgotPasswordPage(driver);
	}

}
