package stepDefinitions;


import commons.core.WebDriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.GlobalPage;
import pages.LandingPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LandingPageStepDef {
    LandingPage landingPage;
    GlobalPage globalPage;

    public LandingPageStepDef() {
        WebDriver webDriver = new WebDriverFactory().getWebDriver();
        landingPage = new LandingPage(webDriver);
        globalPage = new GlobalPage(webDriver);
    }

    @When("user should be land on home page")
    public void userShouldBeLandOnHomePage() {
        landingPage.LandingPageLogo();
        landingPage.verifyLiveChat();
    }

    @Then("user is able to chat with the support team")
    public void userIsAbleToChatWithTheSupportTeam() {
        landingPage.chatWithSupport();
    }


    @When("user clicks on Get Started button")
    public void userClicksOnGetStartedButton() {
    landingPage.verifyNavigationGetStartedPg();
    landingPage.verifyGetStartedFormPg();
    landingPage.verifyFormValidationGetStartedFormPg();
    }


}



