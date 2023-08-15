package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class SearchPage extends GlobalPage {

    @FindBy(className = "search-text")
    private WebElement inputTextSearchButton;
    @FindBy(className = "search-btn")
    private WebElement buttonSearchIcon;
    @FindBys(@FindBy(xpath = "//p[contains(@class,'paragraph-l m-xxs-top')]"))
    private List<WebElement> pSearchResultParagraph;
    @FindBys(@FindBy(css = "h2.h2"))
    private List<WebElement> h2SearchResultTitle;
    @FindBys(@FindBy(css = ".result-item > a "))
    private List<WebElement> aSearchResultLink;

    /**
     * Assigning Driver instance to the page objets
     *
     * @param driver the driver
     */
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSearchPageLoaded() {
        return isElementVisible(inputTextSearchButton);
    }

    public List<String> getSearchResultsDescription() {
        waitForElementToBeVisible(pSearchResultParagraph);
        return getText(pSearchResultParagraph);
    }

    public void performSearch(String searchTerm) {
        waitForElementToBeVisible(inputTextSearchButton);
        sendKeys(inputTextSearchButton, searchTerm);
        click(buttonSearchIcon);
    }
}
