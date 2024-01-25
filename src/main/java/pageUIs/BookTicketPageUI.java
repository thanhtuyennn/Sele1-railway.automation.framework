package pageUIs;

public class BookTicketPageUI {
	public static final String DEPART_DATE = "xpath=//select[@name='Date']";
	public static final String DEPART_FROM = "xpath=//select[@name='DepartStation']";
	public static final String ARRIVE_AT = "xpath=//span[@id='ArriveStation']//select";
	public static final String ARRIVE_AT_SAIGON = "xpath=//select[@name='ArriveStation']//option[text()='Sài Gòn']";
	public static final String SEAT_TYPE = "xpath=//select[@name='SeatType']";
	public static final String TICKET_AMOUNT = "xpath=//select[@name='TicketAmount']";
	public static final String BOOK_TICKET_BUTTON = "xpath=//input[@type='submit']";
}
