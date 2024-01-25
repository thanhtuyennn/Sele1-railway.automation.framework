package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.BookTicketPageUI;
import pageUIs.GeneralPageUI;
import pageUIs.HomePageUI;
import pageUIs.MyTicketPageUI;

public class MyTicketPageObject extends BasePage {
	private WebDriver driver;
	
	public MyTicketPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void selectDepartDate(String value ) {
		scrollIntoView(driver, BookTicketPageUI.DEPART_DATE);
		waitForElementCLickable(driver, BookTicketPageUI.DEPART_DATE);
		selectItemInDropdownByValue(driver, BookTicketPageUI.DEPART_DATE,value );
	}
	
	public void selectDepartFrom(String value ) {
		scrollIntoView(driver, BookTicketPageUI.DEPART_FROM);
		waitForElementCLickable(driver, BookTicketPageUI.DEPART_FROM);
		selectItemInDropdownByVisibleText(driver, BookTicketPageUI.DEPART_FROM,value );
		sleepInSecond(2);
	}
	
	public void selectArriveAt(String value ) {
		waitForElementCLickable(driver, BookTicketPageUI.ARRIVE_AT_SAIGON);
		selectItemInDropdownByVisibleText(driver, BookTicketPageUI.ARRIVE_AT,value );
	}
	
	public void selectSeatType(String value ) {
		scrollIntoView(driver, BookTicketPageUI.SEAT_TYPE);
		waitForElementCLickable(driver, BookTicketPageUI.SEAT_TYPE);
		selectItemInDropdownByVisibleText(driver, BookTicketPageUI.SEAT_TYPE,value );
	}
	
	public void selectTicketAmount(String value ) {
		scrollIntoView(driver, BookTicketPageUI.TICKET_AMOUNT);
		waitForElementCLickable(driver, BookTicketPageUI.TICKET_AMOUNT);
		selectItemInDropdownByVisibleText(driver, BookTicketPageUI.TICKET_AMOUNT,value );
	}
	
	public BookTicketSuccessPageObject clickBookButton() {
		waitForElementCLickable(driver, BookTicketPageUI.BOOK_TICKET_BUTTON);
		clickToElement(driver, BookTicketPageUI.BOOK_TICKET_BUTTON);
		return PageGeneratorManager.getBookTicketSuccessPage(driver);
	}

	public String getDepartFromValue() {
		scrollIntoView(driver, BookTicketPageUI.DEPART_FROM);
		waitForElementVisible(driver, BookTicketPageUI.DEPART_FROM);
		return getSelectedItemDefaultDropdown(driver, BookTicketPageUI.DEPART_FROM);
	}

	public String getArriveAtValue() {
		scrollIntoView(driver, BookTicketPageUI.ARRIVE_AT);
		waitForElementVisible(driver, BookTicketPageUI.ARRIVE_AT);
		return getSelectedItemDefaultDropdown(driver, BookTicketPageUI.ARRIVE_AT);
	}

	public void clickCancelButton() {
		scrollIntoView(driver, MyTicketPageUI.CANCEL_BUTTON);
		waitForElementCLickable(driver, MyTicketPageUI.CANCEL_BUTTON);
		clickToElement(driver, MyTicketPageUI.CANCEL_BUTTON);
	}

	public void clickOkButtonOnPopup() {
		driver.switchTo().alert().accept();
		System.out.println("handle alert success");
		
	}

	public boolean CancelledTicketIsUnDisplayed() {
		return isElementUndisplayed(driver, MyTicketPageUI.CANCELLED_TICKET);
	}
	
	
	
}
