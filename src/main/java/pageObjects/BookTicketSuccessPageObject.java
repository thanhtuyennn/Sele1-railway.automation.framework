package pageObjects;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.BookTicketPageUI;
import pageUIs.BookTicketSuccessPageUI;
import pageUIs.GeneralPageUI;
import pageUIs.HomePageUI;

public class BookTicketSuccessPageObject extends BasePage {
	private WebDriver driver;
	
	public BookTicketSuccessPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getBookSuccessMessage() {
		waitForElementVisible(driver, BookTicketSuccessPageUI.BOOK_SUCCESS_MSG);
		return getElementText(driver, BookTicketSuccessPageUI.BOOK_SUCCESS_MSG);
	}

	
	public String getDepartStationInfor() {
		waitForElementVisible(driver, BookTicketSuccessPageUI.TICKET_INFOR, "1");
		return getElementText(driver, BookTicketSuccessPageUI.TICKET_INFOR, "1");
	}
	
	public String getArriveStationInfor() {
		waitForElementVisible(driver, BookTicketSuccessPageUI.TICKET_INFOR, "2");
		return getElementText(driver, BookTicketSuccessPageUI.TICKET_INFOR, "2");
	}
	
	public String getSeatTypeInfor() {
		waitForElementVisible(driver, BookTicketSuccessPageUI.TICKET_INFOR, "3");
		return getElementText(driver, BookTicketSuccessPageUI.TICKET_INFOR, "3");
	}
	
	public String getDepartDateInfor() {
		waitForElementVisible(driver, BookTicketSuccessPageUI.TICKET_INFOR, "4");
		return getElementText(driver, BookTicketSuccessPageUI.TICKET_INFOR, "4");
	}
	
	public String getAmountInfor() {
		waitForElementVisible(driver, BookTicketSuccessPageUI.TICKET_INFOR, "7");
		return getElementText(driver, BookTicketSuccessPageUI.TICKET_INFOR, "7");
	}
	
	public String getNext12Days() {
		LocalDate currentDate = LocalDate.now();
		LocalDate next12Days = currentDate.plusDays(12);
		String formattedDate;
		if(next12Days.getMonthValue()<10 && next12Days.getDayOfMonth() < 10) {
			formattedDate = next12Days.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
		} else if (next12Days.getMonthValue()<10 && next12Days.getDayOfMonth() >= 10) {
			formattedDate = next12Days.format(DateTimeFormatter.ofPattern("M/dd/yyyy"));
		} else if (next12Days.getMonthValue()>= 10 && next12Days.getDayOfMonth() < 10){
			formattedDate = next12Days.format(DateTimeFormatter.ofPattern("MM/d/yyyy"));
		} else {
			formattedDate = next12Days.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		}
		return formattedDate;
		
	}
	
	public String getNext25Days() {
		LocalDate currentDate = LocalDate.now();
		LocalDate next12Days = currentDate.plusDays(25);
		String formattedDate;
		if(next12Days.getMonthValue()<10 && next12Days.getDayOfMonth() < 10) {
			formattedDate = next12Days.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
		} else if (next12Days.getMonthValue()<10 && next12Days.getDayOfMonth() >= 10) {
			formattedDate = next12Days.format(DateTimeFormatter.ofPattern("M/dd/yyyy"));
		} else if (next12Days.getMonthValue()>= 10 && next12Days.getDayOfMonth() < 10){
			formattedDate = next12Days.format(DateTimeFormatter.ofPattern("MM/d/yyyy"));
		} else {
			formattedDate = next12Days.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		}
		return formattedDate;
		
	}
	
	public String getNext10Days() {
		LocalDate currentDate = LocalDate.now();
		LocalDate next12Days = currentDate.plusDays(10);
		String formattedDate;
		if(next12Days.getMonthValue()<10 && next12Days.getDayOfMonth() < 10) {
			formattedDate = next12Days.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
		} else if (next12Days.getMonthValue()<10 && next12Days.getDayOfMonth() >= 10) {
			formattedDate = next12Days.format(DateTimeFormatter.ofPattern("M/dd/yyyy"));
		} else if (next12Days.getMonthValue()>= 10 && next12Days.getDayOfMonth() < 10){
			formattedDate = next12Days.format(DateTimeFormatter.ofPattern("MM/d/yyyy"));
		} else {
			formattedDate = next12Days.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		}
		return formattedDate;
		
	}
	
	
	
	
}
