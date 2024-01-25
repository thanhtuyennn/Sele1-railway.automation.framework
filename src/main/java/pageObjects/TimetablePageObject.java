package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.BookTicketPageUI;
import pageUIs.GeneralPageUI;
import pageUIs.HomePageUI;
import pageUIs.TimetablePageUI;

public class TimetablePageObject extends BasePage {
	private WebDriver driver;
	
	public TimetablePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public BookTicketPageObject clickOnRouteQuangNgaiToHue() {
		scrollIntoView(driver, TimetablePageUI.QUANGNGAI_HUE);
		waitForElementCLickable(driver, TimetablePageUI.QUANGNGAI_HUE);
		clickToElement(driver, TimetablePageUI.QUANGNGAI_HUE);
		return PageGeneratorManager.getBookTicketPage(driver);
	}
	
	public TicketPricePageObject clickOnCheckPriceDaNangToSaiGon() {
		scrollIntoView(driver, TimetablePageUI.PRICE_DANANG_SAIGON);
		waitForElementCLickable(driver, TimetablePageUI.PRICE_DANANG_SAIGON);
		clickToElement(driver, TimetablePageUI.PRICE_DANANG_SAIGON);
		return PageGeneratorManager.getTicketTicketPricePage(driver);
	}
	
	
	
}
