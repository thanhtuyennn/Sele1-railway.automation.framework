package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getSuccessMessage() {
		waitForElementVisible(driver, HomePageUI.SUCCESS_MESSAGE);
		return getElementText(driver, HomePageUI.SUCCESS_MESSAGE);
	}
	
	public boolean isCreateAccountLinkDisplay() {
		waitForElementVisible(driver, HomePageUI.CREATE_ACCOUNT_LINK);
		return isElementDisplay(driver, HomePageUI.CREATE_ACCOUNT_LINK);
		
	}
	
}
