package commons;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	private WebDriver driver;
	JavascriptExecutor jsExecutor;
	private Select select;
	public static BasePage getBasePageObject() {
		return new BasePage();
	}

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	private By getByXpath(String xpathLocator ) {
		return By.xpath(xpathLocator);
	}
	
	public By getByLocator(String locatorType) {
		By by = null;
		if(locatorType.startsWith("id")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("class=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.startsWith("name=")) {
			by = By.name(locatorType.substring(5));
		} else if (locatorType.startsWith("xpath=")) {
			by = By.xpath(locatorType.substring(6));	
		} else if (locatorType.startsWith("css=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else {
			throw new RuntimeException("Locator type is not supported");
		}			
		return by;	
	}
	
	public String getDynamicXpath(String locatorType, String... dynamicValues ) {
		if(locatorType.startsWith("xpath=")) {
			locatorType = String.format(locatorType, dynamicValues);
		}
		return locatorType;
	}
	

	private WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	public void clickToElement(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).click();
	}
	
	public void clickToElement(WebDriver driver, String locatorType, String...dynamicValues) {
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
	}

	public void sendkeysToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}

	public void sendkeysToElement(WebDriver driver, String locatorType, String textValue, String...dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}
	
	public String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}
	
	public String getElementText(WebDriver driver, String locatorType, String...dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText();
	}
	
	public void waitForElementCLickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}
	
	public void waitForElementCLickable(WebDriver driver, String locatorType, String...dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	public void waitForElementVisible(WebDriver driver, String locatorType, String...dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void waitForElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	public void waitForElementInvisible(WebDriver driver, String locatorType, String...dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));

	}
	
	public boolean isElementDisplay(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).isDisplayed();
		return true;
	}
		
	public boolean isElementDisplay(WebDriver driver, String locatorType, String...dynamicValues) {
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
		return true;
	}
	
	public void overrideGlobalTimeout(WebDriver driver, int timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}
	
	public boolean isElementUndisplayed(WebDriver driver, String locatorType) {
		overrideGlobalTimeout(driver, 5);
		List<WebElement> elements =  getListWebElement(driver, locatorType);
		overrideGlobalTimeout(driver, 20);
		
		if(elements.size()==0) {
			return true;
		} else if (elements.size()>0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void scrollIntoView(WebDriver driver, String locatorType) {
		WebElement element = driver.findElement(getByLocator(locatorType));
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		
	}
	
	public void scrollIntoView(WebDriver driver, String locatorType, String...dynamicValues) {
		WebElement element = driver.findElement(getByLocator(getDynamicXpath(locatorType, dynamicValues)));
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		
	}
	
	
	
	public String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}
	
	public void switchToWindowByTitle(String expectedPageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String currentPageTitle = driver.getTitle();
			if (currentPageTitle.equals(expectedPageTitle)) {
				break;
			}
		}
	}
	public void closeWindow(String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}
	
	public String getElementAttribute(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		return element.getAttribute("value");
	}
	
	public void selectItemInDropdownByValue(WebDriver driver, String locatorType, String value) {
		WebElement element = getWebElement(driver, locatorType);
		select = new Select(element);
		select.selectByValue(value);
	}
	
	public void selectItemInDropdownByVisibleText(WebDriver driver, String locatorType, String value) {
		WebElement element = getWebElement(driver, locatorType);
		select = new Select(element);
		select.selectByVisibleText(value);
	}
	public void closePopup(WebDriver driver, String locatorType) {
		WebElement popup = getWebElement(driver, locatorType);
		if(popup.isDisplayed()) {
			System.out.println("step close popup--------------");
			jsExecutor.executeScript("arguments[0].click();",getWebElement(driver, locatorType) );
		}
	}

	
	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
	

	
	private long longTimeOut = 30;
	
	
	
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}
	}

}
