package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.HomePageUI;
import pageUIs.LoginPageUI;
import pageUIs.RegisterPageUI;
import pageUIs.RegisterSuccessPageUI;

public class RegisterSuccessPageObject extends BasePage {
	private WebDriver driver;

	public RegisterSuccessPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getSuccessMessage() {
		waitForElementVisible(driver, RegisterSuccessPageUI.REGISTER_SUCCESS_MSG);
		return getElementText(driver, RegisterSuccessPageUI.REGISTER_SUCCESS_MSG);
	}
	

	



}
