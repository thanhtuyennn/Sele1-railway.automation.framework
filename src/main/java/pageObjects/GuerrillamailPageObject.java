package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.HomePageUI;
import pageUIs.ResetPasswordMailDetailPageUI;
import pageUIs.guerrillamailPageUI;

public class GuerrillamailPageObject extends BasePage {
	private WebDriver driver;
	
	public GuerrillamailPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickToUserNameEmailAddress() {
		waitForElementCLickable(driver, guerrillamailPageUI.USER_NAME_EMAIL_BUTTON);
		clickToElement(driver, guerrillamailPageUI.USER_NAME_EMAIL_BUTTON);
	}
	
	public void inputUserNameEmail(String userName) {
		waitForElementVisible(driver, guerrillamailPageUI.USER_NAME_EMAIL_TEXTBOX);
		sendkeysToElement(driver, guerrillamailPageUI.USER_NAME_EMAIL_TEXTBOX, userName);
	}
	
	public void clickToSetButton() {
		waitForElementCLickable(driver, guerrillamailPageUI.SET_BUTTON);
		clickToElement(driver, guerrillamailPageUI.SET_BUTTON);
	}
	
	public void waitForEmailBoxUpdated() {
		waitForElementVisible(driver, guerrillamailPageUI.UPDATE_DONE_MSG);
	}
	
	public void clickToDetailEmail() {
		waitForElementCLickable(driver, guerrillamailPageUI.CONFIRM_EMAIL);
		clickToElement(driver, guerrillamailPageUI.CONFIRM_EMAIL);

	}
	
	
	public ResetPasswordMailDetailPageObject openDetailEmail(String userName) {
	    for (int attempt = 0; attempt < 5; attempt++) {
	        clickToUserNameEmailAddress();
	        System.out.println("clickToUserNameEmailAddress");
	        inputUserNameEmail(userName);
	        System.out.println("inputUserNameEmail");
	        clickToSetButton();
	        System.out.println("clickToSetButton");
	        if (isElementDisplay(driver, guerrillamailPageUI.CONFIRM_EMAIL)) {
	        	clickToDetailEmail();
	            return PageGeneratorManager.getResetPasswordMailDetailPage(driver);
	        }
	        
	    }

	    throw new RuntimeException("Confirmation email not found after 5 attempts.");
	}
	
	
	
	
	public RegistrationConfirmPageObject switchToConfirmPage() {
		sleepInSecond(5);
		switchToWindowByTitle("Safe Railway - Registration Confirmation Page");
		return PageGeneratorManager.getRegistrationConfirmPage(driver);
	}
	
	public void clickToResetEmail() {
		waitForElementCLickable(driver, guerrillamailPageUI.RESET_PASSWORD_MAIL);
		clickToElement(driver, guerrillamailPageUI.RESET_PASSWORD_MAIL);

	}

	public ResetPasswordMailDetailPageObject xopenResetPasswordMail(String userName) {
		clickToUserNameEmailAddress();
		inputUserNameEmail(userName);
		clickToSetButton();
		driver.navigate().refresh();
		clickToResetEmail();
		return PageGeneratorManager.getResetPasswordMailDetailPage(driver);
	}
	
	public ResetPasswordMailDetailPageObject openResetPasswordMail(String userName) {
	    for (int attempt = 0; attempt < 5; attempt++) {
	        clickToUserNameEmailAddress();
	        inputUserNameEmail(userName);
	        clickToSetButton();

	        if (isElementDisplay(driver, guerrillamailPageUI.RESET_PASSWORD_MAIL)) {
	        	clickToResetEmail();
	            return PageGeneratorManager.getResetPasswordMailDetailPage(driver);
	        }
	        
	    }

	    throw new RuntimeException("Confirmation email not found after 5 attempts.");
	}

	
	
	public String getCurrentTitle() {
		return driver.getTitle();
	}
	
	public void closeAddPopup() {
		closePopup(driver, guerrillamailPageUI.CLOSE_BUTTON);
		System.out.println("close popup success");
	}
	
	
	
}
