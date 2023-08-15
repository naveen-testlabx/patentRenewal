package stepDefinitions;


import commons.core.WebDriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.SearchPage;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class SearchPageStepDef {
    SearchPage searchPage;

    public SearchPageStepDef() {
        WebDriver webDriver = new WebDriverFactory().getWebDriver();
        searchPage = new SearchPage(webDriver);
    }

    @And("searches for {string}")
    public void searchesFor(String searchTerm) {
        assertTrue(searchPage.isSearchPageLoaded(), "Search Page not loaded");
        searchPage.performSearch(searchTerm);
    }

    @Then("the results should contain the {string}")
    public void theResultsShouldContainThe(String searchTerm) {
        List<String> searchResultTexts = searchPage.getSearchResultsDescription();
        boolean isResultMatched = searchResultTexts.stream().allMatch(searchResult -> searchResult.contains(searchTerm));
        assertTrue(isResultMatched, "Search result not matched with search Term. " + searchResultTexts);
    }
}

