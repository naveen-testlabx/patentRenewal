package pages;

import commons.basepage.web.WebBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class GlobalPage extends WebBase {
 WebDriver driver ;

    public GlobalPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(className = "logo-lnk")
    private WebElement imgCompanyLogo;

    @FindBy(css = "button.ot-pc-refuse-all-handler")
    private WebElement btnPrivacyRefuse;

    public boolean isGlobalPageLoaded() {
        waitForElementToBeClickable(btnPrivacyRefuse);
        click(btnPrivacyRefuse);
        waitForInvisibilityOfAllElements(btnPrivacyRefuse);
        return isElementVisible(imgCompanyLogo);
    }

    protected boolean isMobileView() {
        int sizeWidth = driver.manage().window().getSize().getWidth();
        return sizeWidth < 1280;
    }
}
