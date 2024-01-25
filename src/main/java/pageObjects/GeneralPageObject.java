package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.GeneralPageUI;

public class GeneralPageObject extends BasePage {
	private WebDriver driver;
	
	public GeneralPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public LoginPageObject openLoginPage() {
		waitForElementCLickable(driver, GeneralPageUI.MENU_BUTTON, "Login");
		clickToElement(driver, GeneralPageUI.MENU_BUTTON, "Login");
		return PageGeneratorManager.getLoginPage(driver);
		
	}
	
	public HomePageObject clickToLogoutButton() {
		waitForElementCLickable(driver, GeneralPageUI.MENU_BUTTON, "Log out");
		clickToElement(driver, GeneralPageUI.MENU_BUTTON, "Log out");
		return PageGeneratorManager.getHomePage(driver);
		
	}
	
	public FAQPageObject openFAQTab() {
		waitForElementCLickable(driver, GeneralPageUI.MENU_BUTTON, "FAQ");
		clickToElement(driver, GeneralPageUI.MENU_BUTTON, "FAQ");
		sleepInSecond(3);
		return PageGeneratorManager.getFAQPage(driver);
		
	}
	
	public BookTicketPageObject openBookTicketTab() {
		waitForElementCLickable(driver, GeneralPageUI.MENU_BUTTON, "Book ticket");
		clickToElement(driver, GeneralPageUI.MENU_BUTTON, "Book ticket");
		return PageGeneratorManager.getBookTicketPage(driver);
		
	}
	
//	public boolean LogoutTabDisappeared() {
//		waitForElementInvisible(driver, GeneralPageUI.MENU_BUTTON, "Log out");
//		return true;
//	}
	
//	public boolean LogoutTabDisappeared() {
//		isElementDisplay(driver, GeneralPageUI.LOGOUT_LINK);
//		return true;
//	}
	
	public boolean isLogoutTabUndisplayed() {
		
		return isElementUndisplayed(driver, GeneralPageUI.LOGOUT_LINK);
		
	}
	
	
	
	
	public HomePageObject openHomePage() {
		waitForElementCLickable(driver, GeneralPageUI.MENU_BUTTON, "Home");
		clickToElement(driver, GeneralPageUI.MENU_BUTTON, "Home");
		return PageGeneratorManager.getHomePage(driver);	
	}
	
	public RegisterPageObject openRegisterPage() {
		waitForElementCLickable(driver, GeneralPageUI.MENU_BUTTON, "Register");
		clickToElement(driver, GeneralPageUI.MENU_BUTTON, "Register");
		return PageGeneratorManager.getRegisterPage(driver);	
	}
	
	public RegisterPageObject clickCreateAnAccountLink() {
		scrollIntoView(driver, GeneralPageUI.CREATE_ACCOUNT_LINK);
		waitForElementCLickable(driver, GeneralPageUI.CREATE_ACCOUNT_LINK);
		clickToElement(driver, GeneralPageUI.CREATE_ACCOUNT_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
	}

	public TimetablePageObject openTimetablePage() {
		waitForElementCLickable(driver, GeneralPageUI.MENU_BUTTON, "Timetable");
		clickToElement(driver, GeneralPageUI.MENU_BUTTON, "Timetable");
		return PageGeneratorManager.getTimetablePage(driver);
	}

	public MyTicketPageObject openMyTicketPage() {
		waitForElementCLickable(driver, GeneralPageUI.MENU_BUTTON, "My ticket");
		clickToElement(driver, GeneralPageUI.MENU_BUTTON, "My ticket");
		return PageGeneratorManager.getMyTicketPage(driver);
	}
	
	
	
	
	
	
}
