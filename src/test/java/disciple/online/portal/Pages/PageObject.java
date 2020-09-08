package disciple.online.portal.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

public abstract class PageObject {

    public final Logger log = Logger.getLogger(String.valueOf(getClass()));
    public WebDriver driver;
    @FindBy(xpath = "//div[@class='jq-icon-warning']")
    private WebElement errorMessage;
    @FindBy(xpath = "//div[@class='jq-icon-success']")
    private WebElement successMessage;


    public PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public abstract boolean isInitialized();

    public boolean errorMessageIsShown() {
        try {
            return errorMessage.isEnabled();
        } catch (NoSuchElementException exErrorMessage) {
                    return false;
        }
    }

    public boolean successMessageIsShown() {
        try {
            return successMessage.isDisplayed();
        } catch (NoSuchElementException exErrorMessage1) {
            return false;
        }
    }

    protected void clickElement(WebElement toClick) {
        if (toClick == null) {
            throw new IllegalStateException("Element is null");
        }
        log.info("Try to click on: " + toClick.getText());
        try {
            click(toClick);
        } catch (UnhandledAlertException uae) {
            try {
                final Alert alert = driver.switchTo().alert();
                alert.dismiss();
                log.info("Alert occurred: " + alert.getText());

            } catch (NoAlertPresentException nape) {
                log.info("Alert is either missing or it was dismissed");
            }

            click(toClick);
        }

    }

    private void click(WebElement toClick) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.and(ExpectedConditions.visibilityOf(toClick), ExpectedConditions.elementToBeClickable(toClick)));
        } catch (TimeoutException e) {
            throw new IllegalStateException("Element " + toClick.getText() + " was either not visible or not clickable", e);
        }

        try {
            if (toClick.isEnabled()) {
                String elementText = toClick.getText();
                toClick.click();
                log.info("Clicked on: " + elementText);
            } else {
                throw new IllegalStateException("Element " + toClick.getText() + " is disabled");
            }

        } catch (NoSuchElementException e) {
            throw new IllegalStateException("Element " + toClick.getText() + " does not exist");
        } catch (ElementClickInterceptedException ecie) {
            String javascriptToPressButton = "try {arguments[0].click(); return \"OK\";} catch (err) {return \"ERROR\"+ err.toString();}";
            String returnedValue = (String) ((JavascriptExecutor) driver).executeScript(javascriptToPressButton, toClick);
            if (!"OK".equals(returnedValue)) {
                throw new Error("I literally tried everything to press the element: " + toClick + "(Text:" + toClick.getText() + ") the javascript throws this " + returnedValue);
            }
        }
    }

    protected void sendKeysToElement(WebElement elementToSendKeysTo, CharSequence... keysToSend) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.or(ExpectedConditions.elementToBeClickable(elementToSendKeysTo)));
        } catch (TimeoutException e) {
            throw new IllegalStateException("Element was not clickable", e);
        }
        try {
            if (elementToSendKeysTo.isDisplayed()) {
                elementToSendKeysTo.sendKeys(keysToSend);
            } else {
                throw new IllegalStateException("Element is not displayed");
            }
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("Element does not exist");
        }
    }

    /**
     * Try's to find a WebElement for a given id or a part of the id.
     *
     * @param typeOfElement E.g. "a", "div", "button", "span"...
     * @param elementID     ID of the element
     * @return The first matching element on the page
     * @throws NoSuchElementException If no matching elements are found
     */
    protected WebElement findElementByID(String typeOfElement, String elementID) {
        return driver.findElement(By.xpath("//" + typeOfElement + "[contains(@id,'" + elementID + "')]"));
    }
}
