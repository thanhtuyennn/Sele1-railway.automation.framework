package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.GeneralPageUI;
import pageUIs.HomePageUI;

public class FAQPageObject extends BasePage {
	private WebDriver driver;
	
	public FAQPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	
}
