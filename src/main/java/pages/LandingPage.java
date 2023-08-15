package pages;


import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import commons.basepage.web.WebBase;
import commons.logger.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;

public class LandingPage extends GlobalPage {
    private static final Logger LOG = new Logger(WebBase.class.getName());

    @FindBy(xpath = "//*[@class='conversations-visitor-open-icon']")
    private WebElement openliveChat;

    @FindBy(xpath = "//button[@aria-label='Accept']")
    private WebElement acceptBtn;

    @FindBy(className = "nav_logo-link")
    private WebElement logoImages;

    @FindBy(id = "chat-heading-avatar")
    private WebElement chatHeadopen;

    @FindBy(xpath = "//*[@aria-label='Write a message']")
    private WebElement writeAMsg;

    @FindBy(xpath = "//div[@class='button-wrap hidden_mobile nav']//a")
    private WebElement getStarted;

    @FindBy(xpath = "//div[@class='title-2']")
    private WebElement getStartedPage;

    @FindBy(xpath = "//span[@for='Invoice-upload']")
    private WebElement priceComparison;

    @FindBy(xpath = "//span[@for='Book-a-Meeting-2']")
    private WebElement bookAMeeting;

    @FindBy(xpath = "//span[@for='Manual-upload']")
    private WebElement enquiry;

    @FindBy(name = "First-Name")
    private WebElement firstName;

    @FindBy(name = "Last-Name-5")
    private WebElement lastName;

    @FindBy(name = "Email-5")
    private WebElement emailID;

    @FindBy(name = "Message-3")
    private WebElement messageBox;

    @FindBy(id = "upload-button")
    private WebElement submitButton;


    /**
     * Assigning Driver instance to the page objets
     *
     * @param driver the driver
     */
    public LandingPage(WebDriver driver) {
        super(driver);
    }

    /**
     * verification OF patent renewal logo
     *
     * @return
     */
    public void LandingPageLogo() {
        try {
            if (isElementDisplayed(acceptBtn)) {
                assertTrue(acceptBtn.isDisplayed(), "accept Button for the cookies not displayed");
                click(acceptBtn);
                ExtentCucumberAdapter.addTestStepLog("user accepted the cookies from the patent renewal website");
            }
            isElementDisplayed(logoImages);
            ExtentCucumberAdapter.addTestStepLog("user is able to see the Company Logo at top Left ");
        } catch (Exception e) {
            assertFalse(acceptBtn.isDisplayed(), "accept Button for the cookies not displayed");
            ExtentCucumberAdapter.addTestStepLog("user is not able to see the Company Logo at top Left " + e);
        }
    }

    /**
     * Verification if Live chat window option
     */
    public void verifyLiveChat() {
        try {
            driver.switchTo().frame("hubspot-conversations-iframe");
            if (isElementVisible(openliveChat)) {
                isElementVisible_Enabled(openliveChat);
                isElementDisplayed(openliveChat);
                ExtentCucumberAdapter.addTestStepLog("user is able to see the Live chat support window in home page");
            }
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            assertFalse(openliveChat.isDisplayed(), "user is able to see the Live chat support window in home page");
            ExtentCucumberAdapter.addTestStepLog("user is not able to see the Live chat support window in home page" + e);
        }
    }

    /**
     * verify user able to chat with customer care
     */
    public void chatWithSupport() {
        try {
            driver.switchTo().frame("hubspot-conversations-iframe");
            if (isElementVisible_Enabled(openliveChat)) {
                click(openliveChat);
                isElementDisplayed(chatHeadopen);
                sendKeys(writeAMsg, "Entering Test using Automation");
                writeAMsg.sendKeys(Keys.ENTER);
                driver.switchTo().defaultContent();
                ExtentCucumberAdapter.addTestStepLog("user is able to enter a Query to chat with support team");
            }
        } catch (Exception e) {
            assertFalse(chatHeadopen.isDisplayed(), "user is not able to enter a Query to chat with support team");
            ExtentCucumberAdapter.addTestStepLog("user is not able to enter a Query to chat with support team" + e);
        }
    }


    /**
     * verify navigation tio get started page
     */
    public void verifyNavigationGetStartedPg() {
        try {
            driver.switchTo().defaultContent();
            getStarted.click();
            if (getStartedPage.isDisplayed()) {
                isElementDisplayed(getStartedPage);
                ExtentCucumberAdapter.addTestStepLog("user clicking on get started button");
            }else{
                getStarted.click();
                ExtentCucumberAdapter.addTestStepLog("user clicking on get started button");
            }
        } catch (Exception e) {
            assertFalse(getStarted.isDisplayed(), "get started button is not clicked");
            LOG.error("user is not able to use get start option" + e);
            ExtentCucumberAdapter.addTestStepLog("user is not able to use get start option" + e);
        }
    }

    /**
     * verify option in get started page
     */
    public void verifyGetStartedFormPg() {
        try {
            if (isElementVisible(priceComparison)) {
                isElementDisplayed(priceComparison);
                assertTrue(getStarted.isDisplayed(), "priceComparison is not displayed");
                isElementDisplayed(bookAMeeting);
                assertTrue(getStarted.isDisplayed(), "bookAMeeting is not displayed");
                isElementDisplayed(enquiry);
                ExtentCucumberAdapter.addTestStepLog("user clicking on get started button");
            }
        } catch (Exception e) {
            assertFalse(getStarted.isDisplayed(), "enquiry is not displayed");
            ExtentCucumberAdapter.addTestStepLog("user is not able to use get start option" + e);
        }
    }

    /**
     * Form Validation
     */
    public void verifyFormValidationGetStartedFormPg() {
        try {
            if (isElementVisible(firstName)) {
                isElementDisplayed(firstName);
                sendKeys(firstName,"Test");
                assertTrue(firstName.isDisplayed(), "firstName is not displayed");
                isElementDisplayed(lastName);
                sendKeys(lastName,"Labx");
                assertTrue(lastName.isDisplayed(), "lastName is not displayed");
                isElementDisplayed(emailID);
                sendKeys(emailID,"testLabx@io.com");
                assertTrue(emailID.isDisplayed(), "lastName is not displayed");
                isElementDisplayed(messageBox);
                sendKeys(messageBox,"random text from automation");

                ExtentCucumberAdapter.addTestStepLog("form filed validation is done");
            }
        } catch (Exception e) {
            assertFalse(firstName.isDisplayed(), "firstName is not displayed");
            ExtentCucumberAdapter.addTestStepLog("text filed validation failed" + e);
        }
    }


}



