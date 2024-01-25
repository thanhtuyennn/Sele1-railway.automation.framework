package pageObjects;

import java.util.Set;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.ForgotPasswordPageUI;
import pageUIs.GeneralPageUI;
import pageUIs.HomePageUI;
import pageUIs.registrationConfirmPageUI;

public class ForgotPasswordPageObject extends BasePage {
	private WebDriver driver;
	
	public ForgotPasswordPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void enterEmailToPasswordResetForm(String email) {
		waitForElementCLickable(driver, ForgotPasswordPageUI.EMAIL_TEXTBOX);
		sendkeysToElement(driver, ForgotPasswordPageUI.EMAIL_TEXTBOX, email);
		waitForElementCLickable(driver, ForgotPasswordPageUI.SEND_BUTTON);
		scrollIntoView(driver, ForgotPasswordPageUI.SEND_BUTTON);
		clickToElement(driver, ForgotPasswordPageUI.SEND_BUTTON);
	}
	
	public void switchToMailPage(String expectedPageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String currentPageTitle = driver.getTitle();
			if(currentPageTitle.equals(expectedPageTitle)) {
				break;
			}
		}
		sleepInSecond(3);
	
	}
	
	
	
	
}
