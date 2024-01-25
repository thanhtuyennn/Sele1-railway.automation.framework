package pageUIs;

public class RegisterPageUI {
	public static final String REGISTER_TEXTBOX = "xpath=//form[@id='RegisterForm']//input[@id='%s']";
	public static final String REGISTER_BUTTON = "xpath=//input[@value='Register']";
	public static final String EXISTING_ACCOUNT_ERROR = "css=p.message.error";
	public static final String INVALID_PASSWORD_ERROR = "xpath=//label[@class='validation-error'and@for='password']";
	public static final String INVALID_ID_ERROR = "xpath=//label[@class='validation-error'and@for='pid']";
	
}
