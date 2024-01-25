package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.BookTicketPageObject;
import pageObjects.BookTicketSuccessPageObject;
import pageObjects.FAQPageObject;
import pageObjects.ForgotPasswordPageObject;
import pageObjects.GeneralPageObject;
import pageObjects.GuerrillamailPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyTicketPageObject;
import pageObjects.PasswordChangePageObject;
import pageObjects.RegisterPageObject;
import pageObjects.RegisterSuccessPageObject;
import pageObjects.RegistrationConfirmPageObject;
import pageObjects.ResetPasswordMailDetailPageObject;
import pageObjects.TicketPricePageObject;
import pageObjects.TimetablePageObject;

public class PageGeneratorManager {
	public static GeneralPageObject getGeneralPage(WebDriver driver) {
		return new GeneralPageObject(driver);
	}
	
	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static FAQPageObject getFAQPage(WebDriver driver) {
		return new FAQPageObject(driver);
	}
	
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	
	public static GuerrillamailPageObject getGuerrillamailPage(WebDriver driver) {
		return new GuerrillamailPageObject(driver);
	}
	
	public static RegisterSuccessPageObject getRegisterSuccessPage(WebDriver driver) {
		return new RegisterSuccessPageObject(driver);
	}
	
	public static RegistrationConfirmPageObject getRegistrationConfirmPage(WebDriver driver) {
		return new RegistrationConfirmPageObject(driver);
	}

	public static ForgotPasswordPageObject getForgotPasswordPage(WebDriver driver) {
		return new ForgotPasswordPageObject(driver);
	}

	public static ResetPasswordMailDetailPageObject getResetPasswordMailDetailPage(WebDriver driver) {
		return new ResetPasswordMailDetailPageObject(driver);
	}

	public static PasswordChangePageObject getPasswordChangePage(WebDriver driver) {
		return new PasswordChangePageObject(driver);
	}

	public static BookTicketPageObject getBookTicketPage(WebDriver driver) {
		return new BookTicketPageObject(driver);
	}
	
	public static BookTicketSuccessPageObject getBookTicketSuccessPage(WebDriver driver) {
		return new BookTicketSuccessPageObject(driver);
	}

	public static TimetablePageObject getTimetablePage(WebDriver driver) {
		return new TimetablePageObject(driver);
	}

	public static MyTicketPageObject getMyTicketPage(WebDriver driver) {
		return new MyTicketPageObject(driver);
	}

	public static TicketPricePageObject getTicketTicketPricePage(WebDriver driver) {
		return new TicketPricePageObject(driver);
	}

}
