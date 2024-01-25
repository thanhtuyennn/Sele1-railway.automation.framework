package pageObjects;

import java.util.Set;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.GeneralPageUI;
import pageUIs.HomePageUI;
import pageUIs.ResetPasswordMailDetailPageUI;
import pageUIs.registrationConfirmPageUI;

public class ResetPasswordMailDetailPageObject extends BasePage {
	private WebDriver driver;
	
	public ResetPasswordMailDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public PasswordChangePageObject openResetLink() {
		waitForElementCLickable(driver, ResetPasswordMailDetailPageUI.RESET_LINK);
		clickToElement(driver, ResetPasswordMailDetailPageUI.RESET_LINK);
		return PageGeneratorManager.getPasswordChangePage(driver);
	}
	
	public RegistrationConfirmPageObject activeAccount() {
		waitForElementCLickable(driver, ResetPasswordMailDetailPageUI.RESET_LINK);
		clickToElement(driver, ResetPasswordMailDetailPageUI.RESET_LINK);
		driver.close();
		return PageGeneratorManager.getRegistrationConfirmPage(driver);
	}
	
	public void switchToConfirmPage(String expectedPageTitle) {
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
	
	public void closeWindow(String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}
	
	
	
}
