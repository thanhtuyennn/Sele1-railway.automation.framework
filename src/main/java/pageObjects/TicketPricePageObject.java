package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.BookTicketPageUI;
import pageUIs.GeneralPageUI;
import pageUIs.HomePageUI;
import pageUIs.TicketPricePageUI;

public class TicketPricePageObject extends BasePage {
	private WebDriver driver;
	
	public TicketPricePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getTicketPricePageTitle() {	
		waitForElementVisible(driver, TicketPricePageUI.TICKET_PRICE_TITLE);
		return getElementText(driver, TicketPricePageUI.TICKET_PRICE_TITLE);
	}

	public String getTicketTableTitle() {
		waitForElementVisible(driver, TicketPricePageUI.TABLE_TITLE);
		return getElementText(driver, TicketPricePageUI.TABLE_TITLE);
	}

	public String getHSPrice() {
		waitForElementVisible(driver, TicketPricePageUI.TICKET_PRICE, "1");
		return getElementText(driver, TicketPricePageUI.TICKET_PRICE, "1");
	}
	
	public String getSSPrice() {
		waitForElementVisible(driver, TicketPricePageUI.TICKET_PRICE, "2");
		return getElementText(driver, TicketPricePageUI.TICKET_PRICE, "2");
	}
	
	public String getSSCPrice() {
		waitForElementVisible(driver, TicketPricePageUI.TICKET_PRICE, "3");
		return getElementText(driver, TicketPricePageUI.TICKET_PRICE, "3");
	}
	
	public String getHBPrice() {
		waitForElementVisible(driver, TicketPricePageUI.TICKET_PRICE, "4");
		return getElementText(driver, TicketPricePageUI.TICKET_PRICE, "4");
	}
	
	public String getSBPrice() {
		waitForElementVisible(driver, TicketPricePageUI.TICKET_PRICE, "5");
		return getElementText(driver, TicketPricePageUI.TICKET_PRICE, "5");
	}
	
	public String getSBCPrice() {
		waitForElementVisible(driver, TicketPricePageUI.TICKET_PRICE, "6");
		return getElementText(driver, TicketPricePageUI.TICKET_PRICE, "6");
	}
	
	
	
	
}
