package safe.railway.user;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import commons.BaseTest;
import commons.PageGeneratorManager;
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

public class User_01_Login_Logout extends BaseTest {
	private WebDriver driver;

	private GeneralPageObject generalPage;
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private FAQPageObject FAQPage;
	private RegisterPageObject registerPage;
	private GuerrillamailPageObject guerrillamailPage;
	private RegisterSuccessPageObject registerSuccessPage;
	private RegistrationConfirmPageObject registrationConfirmPage;
	private ForgotPasswordPageObject forgotPasswordPage;
	private PasswordChangePageObject passwordChangePage;
	private ResetPasswordMailDetailPageObject resetPasswordMailDetailPage;
	private BookTicketPageObject bookTicketPage;
	private BookTicketSuccessPageObject bookTicketSuccessPage;
	private TimetablePageObject timetablePage;
	private MyTicketPageObject myTicketPage;
	private TicketPricePageObject ticketPricePage;
	
	private String projectPath = System.getProperty("user.dir");
	String emailAddress = "selenium1" + generateFakeNumber();
	String emailAddress_2 = "selenium1" + generateFakeNumber();
	String emailAddress_3 = "selenium1" + generateFakeNumber();
	String emailAddress_4 = "selenium1" + generateFakeNumber();
	String emailAddress_5 = "selenium1" + generateFakeNumber();
	String emailAddress_6 = "selenium1" + generateFakeNumber();
	String password = "12345678";
	String confirmPassword = "12345678";
	String PID = "12345678";
	String invalid =  "";
	String wrongPassword =  "dothanhtuyen";
	String notMatchConfirmPassword = "456789";

	@Parameters({"browser"})
	@BeforeClass
	public void beforeClass(String browserName) {
		if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\src/main/resources\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		} else if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\src/main/resources\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		
		generalPage = PageGeneratorManager.getGeneralPage(driver);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://saferailway.somee.com/Page/HomePage.cshtml");
		
		registerPage = generalPage.openRegisterPage();

		registerSuccessPage = registerPage.createNewAccount(emailAddress + "@sharklasers.com", password,
				confirmPassword, PID);

		guerrillamailPage = PageGeneratorManager.getGuerrillamailPage(driver);
		driver.get("https://www.guerrillamail.com/inbox");
		resetPasswordMailDetailPage = guerrillamailPage.openDetailEmail(emailAddress);
		registrationConfirmPage = resetPasswordMailDetailPage.activeAccount();
		resetPasswordMailDetailPage.switchToConfirmPage("Safe Railway - Registration Confirmation Page");
		
	}

	@Test
	public void TC_01_Login_Success() {
		log.info("TC01 - Step 01: Open login page");
		loginPage = generalPage.openLoginPage();

		log.info("TC01 - Step 02: Login page");
		homePage = loginPage.loginToSystem(emailAddress + "@sharklasers.com", password);

		log.info("TC01 - Step 03: Verify register success");
		verifyEquals(homePage.getSuccessMessage(), "Welcome to Safe Railway");

		log.info("TC01 - Step 04: Click to logout button");
		homePage = generalPage.clickToLogoutButton();

	}

	@Test
	public void TC_02_Login_Blank_Username() {
		log.info("TC02 - Step 01: Open login page");
		loginPage = generalPage.openLoginPage();

		log.info("TC02 - Step 02: Enter password");
		loginPage.sendkeyToPasswordTextbox(password);

		log.info("TC02 - Step 03: Click login button");
		loginPage.clickToLoginButton();

		log.info("TC02- Step 04: Verify error message displays");
		verifyEquals(loginPage.getErrorMessage(),
				"There was a problem with your login and/or errors exist in your form.");
	}
	
	@Test
	public void TC_03_Login_Invalid_Password() {
		log.info("TC03 - Step 01: Login page");
		loginPage = generalPage.openLoginPage();
		
		log.info("TC03 - Step 02: enter emailaddress");
		loginPage.sendkeyToEmailTextbox(emailAddress + "@sharklasers.com");
		
		log.info("TC03 - Step 03: Enter password");
		loginPage.sendkeyToPasswordTextbox(invalid);
		
		log.info("TC03 - Step 04: Click login button");
		loginPage.clickToLoginButton();
		
		log.info("TC03 - Step 05: Verify error message");
		verifyEquals(loginPage.getErrorMessage(),
				"There was a problem with your login and/or errors exist in your form.");
	}
	
	@Test
	public void TC_04_Login_Wrong_Password_Many_Times() {
		log.info("TC04 - Step 01: Login page");
		loginPage = generalPage.openLoginPage();
		
		log.info("TC04 - Step 02: Enter valid infor 1 time");
		loginPage.enterValidInformationExceptionPassword(emailAddress + "@sharklasers.com", wrongPassword);
		
		log.info("TC04- Step 03: Verify error");
		verifyEquals(loginPage.getErrorMessage(),
				"Invalid username or password. Please try again.");
		
		log.info("TC04 - Step 04: Enter valid infor many times");
		loginPage.enterValidInformationExceptionPasswordSeveralTimes(emailAddress + "@sharklasers.com", wrongPassword, 3);
		
		log.info("TC04 - Step 05: Verify error");
		verifyEquals(loginPage.getErrorMessage(),
				"You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.");
	}
	
	@Test
	public void TC_05_Login_Not_Activated_Account() {
		log.info("TC05 - Step 01: Open register page");
		registerPage = generalPage.openRegisterPage();
		
		log.info("TC05 - Step 02: Creae new account");
		registerSuccessPage = registerPage.createNewAccount(emailAddress_2 + "@sharklasers.com", password,
				confirmPassword, PID);
		
		log.info("TC05 - Step 03: Open login page");
		loginPage = generalPage.openLoginPage();

		log.info("TC05 - Step 04: Enter email to textbox");
		loginPage.sendkeyToEmailTextbox(emailAddress_2+"@sharklasers.com");

		log.info("TC05 - Step 05: Enter password to textbox");
		loginPage.sendkeyToPasswordTextbox(password);

		log.info("TC05 - Step 06: Click login button");
		loginPage.clickToLoginButton();

		log.info("TC05 - Step 07: Verify error message: Invalid username or password. Please try again.");
		verifyEquals(loginPage.getErrorMessage(),
		"Invalid username or password. Please try again.");
		
		
	}
	
	@Test
	public void TC_06_Logout() {
		log.info("TC06 - Step 01: Open login page");
		loginPage = generalPage.openLoginPage();

		log.info("TC06 - Step 02: Login page");
		homePage = loginPage.loginToSystem(emailAddress + "@sharklasers.com", password);
		
		log.info("TC06 - Step 03: Open FAQ tab");
		FAQPage = generalPage.openFAQTab();

		log.info("TC06 - Step 04: Click log out button");
		homePage = generalPage.clickToLogoutButton();
		
		log.info("TC06 - Step 05: Verify logout button is not displayed");
		verifyTrue(generalPage.isLogoutTabUndisplayed());
		
	}
	
	@Test
	public void TC_07_Register_With_Existing_Account() {
		log.info("TC07 - Step 01: Open home page");
		homePage = PageGeneratorManager.getHomePage(driver);
		
		log.info("TC07 - Step 02: Open register page");
		registerPage = generalPage.openRegisterPage();
		
		log.info("TC07 - Step 03: Create new account");
		registerSuccessPage = registerPage.createNewAccount(emailAddress + "@sharklasers.com", confirmPassword,
				password, PID);
		
		log.info("TC07 - Step 04: Verify error: This email address is already in use. ");
		verifyEquals(registerPage.getExistingAccountErrorMessage(), "This email address is already in use.");	
	}
	
	@Test
	public void TC_08_Register_With_Empty_Fields() {
		log.info("TC08 - Step 01: Open register page");
		registerPage = generalPage.openRegisterPage();
		
		log.info("TC08 - Step 02: Create new account");
		registerSuccessPage = registerPage.createNewAccount(emailAddress + "@sharklasers.com", "",
				"", "");
		
		log.info("TC08 - Step 03: Verify error message");
		verifyEquals(registerPage.getExistingAccountErrorMessage(), "There're errors in the form. Please correct the errors and try again.");	
		
		log.info("TC08 - Step 04: Verify error message: Invalid password length");
		verifyEquals(registerPage.getInvalidPassordErrorMessage(), "Invalid password length");	
		
		log.info("TC08 - Step 05: Verify error message: Invalid ID length");
		verifyEquals(registerPage.getInvalidIDErrorMessage(), "Invalid ID length");	
	}
	
	@Test
	public void TC_09_Create_And_Active_Account() {
		log.info("TC09 - Step 01: Open home page");
		homePage = generalPage.openHomePage();
		
		log.info("TC09 - Step 02: Verify create account link is displayed");
		verifyTrue(homePage.isCreateAccountLinkDisplay());
		
		log.info("TC09 - Step 03: Click create account link");
		registerPage = generalPage.clickCreateAnAccountLink();
		
		log.info("TC09 - Step 04: Verify page tittle");
		verifyEquals(registerPage.getRegisterPageTitle(), "Safe Railway - Register an Account");
		
		log.info("TC09 - Step 05: Create new account");
		registerSuccessPage = registerPage.createNewAccount(emailAddress_3 + "@sharklasers.com", password,
				confirmPassword, PID);
		
		log.info("TC09 - Step 06: Verify success message");
		verifyEquals(registerSuccessPage.getSuccessMessage(), "Thank you for registering your account");
		
		log.info("TC09 - Step 07: Open guerrilla mail Page");
		guerrillamailPage = PageGeneratorManager.getGuerrillamailPage(driver);
		
		log.info("TC09 - Step 08: Get guerrilla link");
		driver.get("https://www.guerrillamail.com/inbox");
		
		log.info("TC09 - Step 09: Open detail email");
		resetPasswordMailDetailPage = guerrillamailPage.openDetailEmail(emailAddress_3);
		
		log.info("TC09 - Step 10: Active account");
		registrationConfirmPage = resetPasswordMailDetailPage.activeAccount();
		
		log.info("TC09 - Step 11: Switch to confirm page");
		resetPasswordMailDetailPage.switchToConfirmPage("Safe Railway - Registration Confirmation Page");
		
		log.info("TC09 - Step 12: Verify message: Registration Confirmed! You can now log in to the site.");
		verifyEquals(registrationConfirmPage.getRegistrationConfirmMessage(), "Registration Confirmed! You can now log in to the site.");
	
	}
	
	@Test
	public void TC_10_Reset_Password_Same_Current() {
		log.info("TC10 - Step 01: Login page");
		loginPage = registrationConfirmPage.openLoginPage();
		
		log.info("TC10 - Step 02: Open forgot password link");
		forgotPasswordPage = loginPage.openForgotPasswordLink();

		log.info("TC10 - Step 03: Enter email to password reset form");
		forgotPasswordPage.enterEmailToPasswordResetForm(emailAddress + "@sharklasers.com");
		
		log.info("TC10 - Step 04: Open guerrilla mail page");
		guerrillamailPage = PageGeneratorManager.getGuerrillamailPage(driver);
		
		log.info("TC10 - Step 05: Login page");
		driver.get("https://www.guerrillamail.com/inbox");
		
		forgotPasswordPage.switchToMailPage("✉ Guerrilla Mail - Disposable Temporary E-Mail Address");
		
		log.info("TC10 - Step 06: open Reset Password Mail");
		resetPasswordMailDetailPage = guerrillamailPage.openResetPasswordMail(emailAddress);
		
		log.info("TC10 - Step 07: open Reset Link");
		passwordChangePage = resetPasswordMailDetailPage.openResetLink();
		
		log.info("TC10 - Step 08: Switch to confirm page");
		resetPasswordMailDetailPage.switchToConfirmPage("Safe Railway - Password Reset");
		
		String parentID = driver.getWindowHandle();	
		resetPasswordMailDetailPage.closeWindow(parentID);
		log.info("TC10 - Step 09: Fill in password change form");
		passwordChangePage.fillInPasswordChangeForm(password, confirmPassword);
		
		log.info("TC10 - Step 10: Verify error message");
		verifyEquals(passwordChangePage.getPasswordChangeMessage(), "The new password cannot be the same with the current password");
		
	}
	
	@Test
	public void TC_11_Reset_Password_Confirm_Password_Not_Match() {
		log.info("TC11 - Step 01: Login page");
		homePage = generalPage.openHomePage();
		
		log.info("TC11 - Step 02: Open register page");
		registerPage = generalPage.openRegisterPage();

		log.info("TC11 - Step 03: Create new account");
		registerSuccessPage = registerPage.createNewAccount(emailAddress_6 + "@sharklasers.com", password,
				confirmPassword, PID);

		log.info("TC11 - Step 04: oOpen guerrilla mail page");
		guerrillamailPage = PageGeneratorManager.getGuerrillamailPage(driver);
		
		log.info("TC11 - Step 05: get guerrillamail link ");
		driver.get("https://www.guerrillamail.com/inbox");
		
		log.info("TC11 - Step 06: open detail mail");
		resetPasswordMailDetailPage = guerrillamailPage.openDetailEmail(emailAddress_6);
		
		log.info("TC11 - Step 07: Active account");
		registrationConfirmPage = resetPasswordMailDetailPage.activeAccount();
		
		log.info("TC11 - Step 08: Switch to confirm page");
		resetPasswordMailDetailPage.switchToConfirmPage("Safe Railway - Registration Confirmation Page");
		
		
		log.info("TC11 - Step 01: Login page");
		loginPage = registrationConfirmPage.openLoginPage();
		
		log.info("TC11 - Step 02: Open forgot password link");
		forgotPasswordPage = loginPage.openForgotPasswordLink();

		log.info("TC11 - Step 03: Enter email to password reset form");
		forgotPasswordPage.enterEmailToPasswordResetForm(emailAddress_6 + "@sharklasers.com");
		
		log.info("TC11 - Step 04: Open guerrilla mail page");
		guerrillamailPage = PageGeneratorManager.getGuerrillamailPage(driver);
		
		log.info("TC11 - Step 05: Login page");
		driver.get("https://www.guerrillamail.com/inbox");
		
		forgotPasswordPage.switchToMailPage("✉ Guerrilla Mail - Disposable Temporary E-Mail Address");
		
		log.info("TC11 - Step 06: open Reset Password Mail");
		resetPasswordMailDetailPage = guerrillamailPage.openResetPasswordMail(emailAddress_6);
		
		log.info("TC11 - Step 07: open Reset Link");
		passwordChangePage = resetPasswordMailDetailPage.openResetLink();
		
		log.info("TC11 - Step 08: Switch to confirm page");
		resetPasswordMailDetailPage.switchToConfirmPage("Safe Railway - Password Reset");
		
		String parentID = driver.getWindowHandle();	
		resetPasswordMailDetailPage.closeWindow(parentID);
		log.info("TC11 - Step 11: Fill in password change form");
		passwordChangePage.fillInPasswordChangeForm(password, notMatchConfirmPassword);
		
		log.info("TC11 - Step 12: Verify error message");
		verifyEquals(passwordChangePage.getPasswordChangeMessage(), "Could not reset password. Please correct the errors and try again.");
		
		log.info("TC11 - Step 13: Verify error message");
		verifyEquals(passwordChangePage.getConfirmationPasswordErrorMessage(), "The password confirmation did not match the new password.");
		
	}
	
	@Test
	public void TC_12_Book_1_Ticket_A_Time() {
		log.info("TC12 - Step 01: Open home page");
		homePage = generalPage.openHomePage();
		
		log.info("TC12 - Step 01: Open login page");
		loginPage = registrationConfirmPage.openLoginPage();
		
		log.info("TC12 - Step 02: Login page");
		homePage = loginPage.loginToSystem(emailAddress+"@sharklasers.com", password);
		
		log.info("TC12 - Step 03: Open book ticket tab");
		bookTicketPage = generalPage.openBookTicketTab();
		
		log.info("TC12 - Step 04: Select depart date");
		bookTicketPage.selectDepartDate("12");	
		
		log.info("TC12 - Step 05: Select departure");
		bookTicketPage.selectDepartFrom("Nha Trang");
		
		log.info("TC12 - Step 06: Select arrival");
		bookTicketPage.selectArriveAt("Huế");
		
		log.info("TC12 - Step 07: Select seat type");
		bookTicketPage.selectSeatType("Soft bed with air conditioner");	
		
		log.info("TC12 - Step 08: Select ticket amount");
		bookTicketPage.selectTicketAmount("1");	
		
		log.info("TC12 - Step 09: Click book button");
		bookTicketSuccessPage = bookTicketPage.clickBookButton();
		
		log.info("TC12 - Step 10: Verify book success MSG");
		verifyEquals(bookTicketSuccessPage.getBookSuccessMessage(), "Ticket booked successfully!");
		
		log.info("TC12 - Step 11: Verify depart station infor");
		verifyEquals(bookTicketSuccessPage.getDepartStationInfor(), "Nha Trang");
		
		log.info("TC12 - Step 12: Verify arrive station infor");
		verifyEquals(bookTicketSuccessPage.getArriveStationInfor(), "Huế");
		
		log.info("TC12 - Step 13: Login page");
		verifyEquals(bookTicketSuccessPage.getSeatTypeInfor(), "Soft bed with air conditioner");
		
		log.info("TC12 - Step 14: Verify depart date infor");
		verifyEquals(bookTicketSuccessPage.getDepartDateInfor(), bookTicketSuccessPage.getNext12Days());
		
		log.info("TC12 - Step 15: Verify amount infor");
		verifyEquals(bookTicketSuccessPage.getAmountInfor(), "1");
		
		log.info("TC12 - Step 16: Click to logout button");
		homePage = generalPage.clickToLogoutButton();
	}
	
	@Test
	public void TC_13_Book_Many_Ticket_A_Time() {
		log.info("TC13 - Step 00: Open home page");
		homePage = generalPage.openHomePage();
		
		log.info("TC13 - Step 01: Open login page");
		loginPage = generalPage.openLoginPage();
		
		log.info("TC13 - Step 02: Login page");
		homePage = loginPage.loginToSystem(emailAddress+"@sharklasers.com", password);
		
		log.info("TC13 - Step 03: Open book ticket tab");
		bookTicketPage = generalPage.openBookTicketTab();
		
		log.info("TC13 - Step 04: Verify depart date");
		bookTicketPage.selectDepartDate("25");	
		
		log.info("TC13 - Step 05: Verify departure");
		bookTicketPage.selectDepartFrom("Nha Trang");	
		
		log.info("TC13 - Step 06: Verify arrival");
		bookTicketPage.selectArriveAt("Sài Gòn");	
		
		log.info("TC13 - Step 07: Select seat type");
		bookTicketPage.selectSeatType("Soft seat with air conditioner");	
		
		log.info("TC13 - Step 08: Select ticket amount");
		bookTicketPage.selectTicketAmount("5");	
		
		log.info("TC13 - Step 09: Click book button");
		bookTicketSuccessPage = bookTicketPage.clickBookButton();
		
		log.info("TC13 - Step 10: Verify book success MSG");
		verifyEquals(bookTicketSuccessPage.getBookSuccessMessage(), "Ticket booked successfully!");
		
		log.info("TC13 - Step 11: Verify depart station infor");
		verifyEquals(bookTicketSuccessPage.getDepartStationInfor(), "Nha Trang");
		
		log.info("TC13 - Step 12: Verify arrive station infor");
		verifyEquals(bookTicketSuccessPage.getArriveStationInfor(), "Sài Gòn");
		
		log.info("TC13 - Step 13: Verify seat type infor");
		verifyEquals(bookTicketSuccessPage.getSeatTypeInfor(), "Soft seat with air conditioner");
		
		log.info("TC13 - Step 14: Get depart date infor");
		verifyEquals(bookTicketSuccessPage.getDepartDateInfor(), bookTicketSuccessPage.getNext25Days());
		
		log.info("TC13 - Step 15: Get ammount infor");
		verifyEquals(bookTicketSuccessPage.getAmountInfor(), "5");
		
		log.info("TC13 - Step 16: Click to logout button");
		homePage = generalPage.clickToLogoutButton();
	}
	
	@Test
	public void TC_14_Check_Price_From_Timetable() {
		log.info("TC14 - Step 00: Open home page");
		homePage = generalPage.openHomePage();
		
		log.info("TC14 - Step 01: Open login page");
		loginPage = generalPage.openLoginPage();
		
		log.info("TC14 - Step 02: Login page");
		homePage = loginPage.loginToSystem(emailAddress+"@sharklasers.com", password);
		
		log.info("TC14 - Step 03: Open timetable");
		timetablePage = generalPage.openTimetablePage();
		
		log.info("TC14 - Step 04: Click on check price Da Nang - SG");
		ticketPricePage = timetablePage.clickOnCheckPriceDaNangToSaiGon();
		
		log.info("TC14 - Step 05: Verify page title");
		verifyEquals(ticketPricePage.getTicketPricePageTitle(), "Ticket Price");
		
		log.info("TC14 - Step 06: Verify Sticket table title");
		verifyEquals(ticketPricePage.getTicketTableTitle(), "Ticket price from Đà Nẵng to Sài Gòn");
		
		log.info("TC14 - Step 07: Verify HSP price");
		verifyEquals(ticketPricePage.getHSPrice(), "310000");
		
		log.info("TC14 - Step 08: Verify SS price");
		verifyEquals(ticketPricePage.getSSPrice(), "335000");
		
		log.info("TC14 - Step 09: Verify SSC price");
		verifyEquals(ticketPricePage.getSSCPrice(), "360000");
		
		log.info("TC14 - Step 10: Verify HBP price");
		verifyEquals(ticketPricePage.getHBPrice(), "410000");
		
		log.info("TC14 - Step 11: Verify SB price");
		verifyEquals(ticketPricePage.getSBPrice(), "460000");
		
		log.info("TC14 - Step 12: Verify SBC price");
		verifyEquals(ticketPricePage.getSBCPrice(), "510000");
		
		log.info("TC14 - Step 13: Click to logout button");
		homePage = generalPage.clickToLogoutButton();
		
	}
	
	@Test
	public void TC_15_Book_ticket_From_Timetable() {
		log.info("TC15 - Step 01: Open home page");
		homePage = generalPage.openHomePage();
		
		log.info("TC15 - Step 02: Open register page");
		registerPage = generalPage.openRegisterPage();
		
		log.info("TC15 - Step 03: Create new account");
		registerSuccessPage = registerPage.createNewAccount(emailAddress_4 + "@sharklasers.com", password,
				confirmPassword, PID);

		log.info("TC15 - Step 04: Open guerrilla mail page ");
		guerrillamailPage = PageGeneratorManager.getGuerrillamailPage(driver);
		
		log.info("TC15 - Step 05: Get guerrillamail link");
		driver.get("https://www.guerrillamail.com/inbox");
		
		log.info("TC15 - Step 06: Open detail mail");
		resetPasswordMailDetailPage = guerrillamailPage.openDetailEmail(emailAddress_4);
		
		log.info("TC15 - Step 07: Active account");
		registrationConfirmPage = resetPasswordMailDetailPage.activeAccount();
		
		log.info("TC15 - Step 08: Switch to confirm page");
		resetPasswordMailDetailPage.switchToConfirmPage("Safe Railway - Registration Confirmation Page");
		
		log.info("TC15 - Step 09: Open login page");
		loginPage = generalPage.openLoginPage();
		
		log.info("TC15 - Step 10: Login page");
		homePage = loginPage.loginToSystem(emailAddress_4+"@sharklasers.com", password);
		
		log.info("TC15 - Step 11: Open time table");
		timetablePage = generalPage.openTimetablePage();
		
		log.info("TC15 - Step 12: Click on route Quang Ngai - Hue");
		bookTicketPage = timetablePage.clickOnRouteQuangNgaiToHue();
		
		log.info("TC15 - Step 13: Select departure");
		verifyEquals(bookTicketPage.getDepartFromValue(), "Quảng Ngãi");
		
		log.info("TC15 - Step 14: Select arrival");
		verifyEquals(bookTicketPage.getArriveAtValue(), "Huế");
		
		log.info("TC15 - Step 15: Select depart date");
		bookTicketPage.selectDepartDate("10");
		
		log.info("TC15 - Step 16: Select ticket amount");
		bookTicketPage.selectTicketAmount("5");
		
		log.info("TC15 - Step 17: Click book button");
		bookTicketSuccessPage = bookTicketPage.clickBookButton();
		
		log.info("TC15 - Step 18: Verify success MSG");
		verifyEquals(bookTicketSuccessPage.getBookSuccessMessage(), "Ticket booked successfully!");
		
		log.info("TC15 - Step 19: Verify depart station infor");
		verifyEquals(bookTicketSuccessPage.getDepartStationInfor(), "Quảng Ngãi");
		
		log.info("TC15 - Step 20: Verify arrive station infor");
		verifyEquals(bookTicketSuccessPage.getArriveStationInfor(), "Huế");
		
		log.info("TC15 - Step 21: Verify depart date infor");
		verifyEquals(bookTicketSuccessPage.getDepartDateInfor(), bookTicketSuccessPage.getNext10Days());
		
		log.info("TC15 - Step 22: Verify amount infor");
		verifyEquals(bookTicketSuccessPage.getAmountInfor(), "5");
		
		log.info("TC15 - Step 23: Click to logout button");
		homePage = generalPage.clickToLogoutButton();
	}
	
	@Test
	public void TC_16_Cancel_Ticket() {
		log.info("TC16 - Step 01: Login page");
		homePage = generalPage.openHomePage();
		
		log.info("TC16 - Step 02: Open register page");
		registerPage = generalPage.openRegisterPage();

		log.info("TC16 - Step 03: Create new account");
		registerSuccessPage = registerPage.createNewAccount(emailAddress_5 + "@sharklasers.com", password,
				confirmPassword, PID);

		log.info("TC16 - Step 04: oOpen guerrilla mail page");
		guerrillamailPage = PageGeneratorManager.getGuerrillamailPage(driver);
		
		log.info("TC16 - Step 05: get guerrillamail link ");
		driver.get("https://www.guerrillamail.com/inbox");
		
		log.info("TC16 - Step 06: open detail mail");
		resetPasswordMailDetailPage = guerrillamailPage.openDetailEmail(emailAddress_5);
		
		log.info("TC16 - Step 07: Active account");
		registrationConfirmPage = resetPasswordMailDetailPage.activeAccount();
		
		log.info("TC16 - Step 08: Switch to confirm page");
		resetPasswordMailDetailPage.switchToConfirmPage("Safe Railway - Registration Confirmation Page");
		
		log.info("TC16 - Step 09: Open login page");
		loginPage = generalPage.openLoginPage();
		
		log.info("TC16 - Step 10: Login page");
		homePage = loginPage.loginToSystem(emailAddress_5+"@sharklasers.com", password);
		
		log.info("TC16 - Step 11: Open book ticket tab");
		bookTicketPage = generalPage.openBookTicketTab();
		
		log.info("TC16 - Step 12: Select depart date");
		bookTicketPage.selectDepartDate("9");	
		
		log.info("TC16 - Step 13: Select departure");
		bookTicketPage.selectDepartFrom("Quảng Ngãi");
		
		log.info("TC16 - Step 14: Select arrival");
		bookTicketPage.selectArriveAt("Huế");
		
		log.info("TC16 - Step 15: Select seat type");
		bookTicketPage.selectSeatType("Soft bed with air conditioner");	
		
		log.info("TC16 - Step 16: Select ticket amount");
		bookTicketPage.selectTicketAmount("1");	
		
		log.info("TC16 - Step 17: Click book button");
		bookTicketSuccessPage = bookTicketPage.clickBookButton();
		
		log.info("TC16 - Step 18: Open my ticket page");
		myTicketPage = generalPage.openMyTicketPage();
		
		log.info("TC16 - Step 19: Click cancel button");
		myTicketPage.clickCancelButton();
		
		log.info("TC16 - Step 20: Click ok button");
		myTicketPage.clickOkButtonOnPopup();
		
		log.info("TC16 - Step 21: Verify cancelled ticket is undisplayed");
		verifyTrue(myTicketPage.CancelledTicketIsUnDisplayed());
		
		log.info("TC16 - Step 22: Click to logout button");
		homePage = generalPage.clickToLogoutButton();
	}
	

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
