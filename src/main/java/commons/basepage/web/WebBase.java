package commons.basepage.web;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import commons.core.PropertyReader;
import commons.logger.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WebBase {

    private static final Logger LOG = new Logger(WebBase.class.getName());

    WebDriver driver = null;
    WebDriverWait wait;

    public WebBase(WebDriver driver) {
        this.driver = driver;
        long timeOutInSeconds = new PropertyReader().getTimeOutInSeconds();
        wait = new WebDriverWait(driver, timeOutInSeconds);
        PageFactory.initElements(driver, this);
    }

    public void acceptAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            LOG.error("No alert displayed to accept" +e);
        }
    }

    public void dismissAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
        } catch (Exception e) {
            LOG.error("No alert displayed to dismiss" +e);
        }
    }

    public String getAlertText() {
        try {
            Alert alert = driver.switchTo().alert();
            return alert.getText();
        } catch (Exception e) {
            LOG.error("No alert displayed" +e);
            return null;
        }
    }

    public void selectFromDropdownByIndex(final int value, final WebElement webElement) {
        try {
            Select dropdown = new Select(webElement);
            dropdown.selectByIndex(value);
        } catch (Exception e) {
            LOG.error(String.format("Couldn't select \"%S\" from element \"%S\"!", value, webElement)+e);
        }
    }

    public void selectFromDropdownByText(final String text, final WebElement webElement) {
        try {
            Select dropdown = new Select(webElement);
            dropdown.selectByVisibleText(text);
        } catch (Exception e) {
            LOG.error(String.format("Couldn't select \"%S\" from element \"%S\"!", text, e));
        }
    }

    public void selectFromDropdownByValue(final String textValue, final WebElement webElement) {
        try {
            Select dropdown = new Select(webElement);
            dropdown.selectByValue(textValue);
        } catch (Exception e) {
            LOG.error(String.format("Couldn't select \"%S\" from element \"%S\"!", textValue, e));
        }
    }

    public void deSelectFromDropdownByIndex(final int value, final WebElement webElement) {
        try {
            Select dropdown = new Select(webElement);
            dropdown.deselectByIndex(value);
        } catch (Exception e) {
            LOG.error(String.format("Couldn't deselect \"%S\" from element \"%S\"!", value, e));
        }
    }

    public void deSelectFromDropdownByText(final String text, final WebElement webElement) {
        try {
            Select dropdown = new Select(webElement);
            dropdown.deselectByVisibleText(text);
        } catch (Exception e) {
            LOG.error(String.format("Couldn't deselect \"%S\" from element \"%S\"!", text, e));
        }
    }

    public void deSelectFromDropdownByValue(final String textValue, final WebElement webElement) {
        try {
            Select dropdown = new Select(webElement);
            dropdown.deselectByValue(textValue);
        } catch (Exception e) {
            LOG.error(String.format("Couldn't deselect \"%S\" from element \"%S\"!", textValue, e));
        }
    }

    public void deSelectAllDropdownValues(final WebElement webElement) {
        try {
            Select dropdown = new Select(webElement);
            dropdown.deselectAll();
        } catch (Exception e) {
            LOG.error(String.format("Couldn't deselect all from element \"%S\"!", e));
        }
    }

    /**
     * IsMultiple
     *
     * @param elements elements
     * @return boolean
     */
    public Boolean isMultiSelectable(final WebElement elements) {
        Select select = new Select(elements);
        return select.isMultiple();
    }

    /**
     * GetAllSelectedOption
     *
     * @param elements elements
     * @return allSelectedOptions
     */
    public List<WebElement> getAllSelectedOptions(final WebElement elements) {
        Select select = new Select(elements);
        return select.getAllSelectedOptions();
    }

    /**
     * GetOption
     *
     * @param elements elements
     * @return options
     */
    public List<WebElement> getOptions(final WebElement elements) {
        Select select = new Select(elements);
        return select.getOptions();
    }

    /**
     * GetFirstSelectedOption
     *
     * @param elements elements
     * @return webElement
     */
    public WebElement getFirstSelectedOption(final WebElement elements) {
        Select select = new Select(elements);
        return select.getFirstSelectedOption();
    }


    public boolean isExist(final List<WebElement> element) {
        return element.size() != 0;
    }

    public long getCountOfVisibleElements(final List<WebElement> elements) {
        return elements.stream().filter(WebElement::isDisplayed).count();
    }

    public void sendKeysOnFirstVisibleElement(final List<WebElement> elements, String value) {
        elements.stream().filter(WebElement::isDisplayed).findFirst().get().sendKeys(value);
    }

    public boolean isElementVisible(final WebElement webElement) {
        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (Exception e) {
            LOG.error(String.format("Couldn't display element \"%S\"!", e));
            return false;
        }
    }

  public boolean isElementDisplayed(final WebElement webElement) {
    try {
      webElement.isDisplayed();
      return true;
    } catch (Exception e) {
      LOG.error(String.format("Couldn't display element \"%S\"!", webElement)+e);
      return false;
    }
  }

  public boolean isElementVisible_Enabled(final WebElement webElement) {
    try {
      wait.until(ExpectedConditions.visibilityOf(webElement));
      webElement.isEnabled();
      return true;
    } catch (Exception e) {
        LOG.error(String.format("Couldn't display element \"%S\"!", webElement)+e);
      return false;
    }
  }

    public boolean isPageReady() {
        try {
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        } catch (WebDriverException e) {
            LOG.error(String.format("Page wasn't ready to execute tests: %s!", driver.getCurrentUrl()));
            return false;
        }
        return true;
    }

    public void mouseOver(final WebElement element) {
        fluentWait(element, 10);
        Actions action = new Actions(driver);
        action.moveToElement(element);
        action.click();
        action.perform();
    }

    public void doubleClick(final WebElement element) {
        new Actions(driver).doubleClick(element).build().perform();
    }

    public void dragAndDrop(final WebElement element1, WebElement element2) {
        new Actions(driver).dragAndDrop(element1, element2).build().perform();
    }


    public void switchToFrame(final WebElement element) {
        driver.switchTo().frame(element);
    }

    public void switchToFrame(int index) {
        driver.switchTo().frame(index);
    }

    public void switchToFrame(String frameNameOrID) {
        driver.switchTo().frame(frameNameOrID);
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    private void fluentWait(final WebElement element, final int timeout) {
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout)).pollingEvery(Duration.ofMillis(5)).ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.visibilityOf(element));

        } catch (ElementNotInteractableException e) {
            LOG.error(String.format("Fluent wait could not find the element after trying for %s seconds", timeout), e);
        }
    }

    public void scrollWebsiteToElement(final WebElement webElement) {
        waitForElementToBeVisible(webElement);
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", webElement);
        } catch (NoSuchElementException e) {
            LOG.error(String.format("Couldn't scroll to element \"%S\"!", webElement), e);
        }
    }

    public void sendKeys(final WebElement element, final CharSequence text) {
        try {
            element.sendKeys(text);
        } catch (Exception e) {
            LOG.error(String.format("Couldn't sendKeys on to element \"%S\"!", element), e);
        }
    }

    public void waitAndSendKeys(final WebElement element, final CharSequence text) {
        waitForElementToBeClickable(element);
        sendKeys(element, text);
    }

    public void sendKeysAndSubmit(final WebElement element, final CharSequence text) {
        waitForElementToBeClickable(element);
        sendKeys(element, text);
        sendKeys(element, Keys.RETURN);
    }

    public void click(final WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            LOG.error(String.format("Couldn't click on to element \"%S\"!", element), e);
        }
    }

    public void waitAndClick(final WebElement element) {
        waitForElementToBeClickable(element);
        click(element);
    }

    public void clear(final WebElement element) {
        try {
            element.clear();
        } catch (Exception e) {
            LOG.error(String.format("Couldn't clear on to element \"%S\"!", element), e);
        }
    }

    public void waitAndClear(final WebElement element) {
        waitForElementToBeClickable(element);
        clear(element);
    }

    public void clearAndsendKeys(final WebElement element, CharSequence text) {
        waitAndClear(element);
        sendKeys(element, text);
    }

    public String getText(final WebElement element) {
        try {
            String value = element.getText();
            LOG.debug(String.format("getText value is %s for the element %s", value, element));
            return value;
        } catch (Exception e) {
            LOG.error(String.format("Couldn't getText from the element \"%S\"!", element), e);
            return "";
        }
    }

    //Doc include https://stackoverflow.com/questions/16705165
    public String getAnyAttribute(final WebElement element, final String attributeName) {
        try {
            String value = String.valueOf(getAttributeOrCssValue(element, attributeName));
            LOG.debug(String.format("getAttribute value is %s for the element %s with attribute %s", value, element, attributeName));
            return value;
        } catch (Exception e) {
            LOG.error(String.format("Couldn't getAttribute for the element %s with attribute %s", element, attributeName), e);
            return "";
        }
    }

    private static Optional<String> getAttributeOrCssValue(final WebElement element, final String name) {
        String value = element.getAttribute(name);
        if (value == null || value.isEmpty()) {
            value = element.getCssValue(name);
        }

        return value != null && !value.isEmpty() ? Optional.of(value) : Optional.empty();
    }

    public List<String> getText(final List<WebElement> elements) {
        List<String> listOfTexts = new ArrayList<>();
        for (final WebElement element : elements) {
            listOfTexts.add(getText(element));
        }
        return listOfTexts;
    }


    /**
     * Click element by value
     *
     * @param elements element
     * @param value    value
     */
    public void clickElementByValue(final List<WebElement> elements, String value) {
        elements.stream().filter(element -> element.getAttribute("value").matches(value)).forEach(WebElement::click);
    }

    /**
     * Get All Links
     *
     * @return all links
     */
    public List<String> getAllLinks(final List<WebElement> elements) {
        return elements.stream().map(ele -> ele.getText().trim()).collect(Collectors.toList());
    }


    /**
     * SwitchToNewWindow
     */
    public void switchToNewWindow() {
        String parentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                driver.close();
                driver.switchTo().window(parentWindow);
            }
        }
    }

    /**
     * Add cookies
     *
     * @param key   key
     * @param value value
     */
    public void addCookie(final String key, final String value) {
        driver.manage().addCookie(new Cookie(key, value));
    }

    /**
     * Delete Cookie
     *
     * @param key key
     */
    public void deleteCookie(final String key) {
        driver.manage().deleteCookieNamed(key);
    }

    /**
     * Delete all cookie
     *
     * @param key key
     */
    public void deleteAllCookie(final String key) {
        driver.manage().deleteAllCookies();
    }

    /**
     * Get Named Cookie
     *
     * @param key   key
     * @param value value
     */
    public void getNamedCookie(final String key, final String value) {
        driver.manage().addCookie(new Cookie(key, value));
        Cookie cookie = driver.manage().getCookieNamed(key);

    }

    public void waitForElementToBeClickable(final WebElement webElement) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (ElementNotInteractableException e) {
            LOG.error(String.format("Couldn't click on element \"%S\"!", webElement));
        }
    }

    public void waitForElementToBeVisible(final WebElement webElement) {
        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
          LOG.info("Expected Object " + webElement + " is visible on the Page");
        } catch (ElementNotInteractableException e) {
            LOG.error(String.format("Couldn't see element \"%S\"!", webElement));
        }
    }

    public void waitForElementToBeVisible(final List<WebElement> webElements) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(webElements));
        } catch (ElementNotInteractableException e) {
            LOG.error(String.format("Couldn't see elements \"%S\"!", webElements));
        }
    }

    public void waitForTitleToBe(final String title) {
        try {
            wait.until(ExpectedConditions.titleIs(title));
        } catch (Exception e) {
            LOG.error(String.format("title to be \"%s\". Current title: \"%s\"", title, driver.getTitle()));
        }
    }

    public void waitForTitleToContain(final String title) {
        try {
            wait.until(ExpectedConditions.titleContains(title));
        } catch (Exception e) {
            LOG.error(String.format("title to contain \"%s\". Current title: \"%s\"", title, driver.getTitle()));
        }
    }

    public void waitForUrlToBe(final String url) {
        try {
            wait.until(ExpectedConditions.urlToBe(url));
        } catch (Exception e) {
            LOG.error(String.format("URL to be \"%s\". Current URL: \"%s\"", url, driver.getCurrentUrl()));
        }
    }

    public void waitForUrlToContain(final String url) {
        try {
            wait.until(ExpectedConditions.urlContains(url));
        } catch (Exception e) {
            LOG.error(String.format("URL to contain \"%s\". Current URL: \"%s\"", url, driver.getCurrentUrl()));
        }
    }

    public void waitForUrlToMatchAFormat(final String regex) {
        try {
            wait.until(ExpectedConditions.urlMatches(regex));
        } catch (Exception e) {
            LOG.error(String.format("URL format to match \"%s\". Current URL: \"%s\"", regex, driver.getCurrentUrl()));
        }
    }

    public void waitForTextTobePresentInElement(final WebElement element, final String text) {
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (Exception e) {
            LOG.error(String.format("text ('%s') to be present in element %s", text, element));
        }
    }

    public void waitForTextTobePresentInElementValue(final WebElement element, final String text) {
        try {
            wait.until(ExpectedConditions.textToBePresentInElementValue(element, text));
        } catch (Exception e) {
            LOG.error(String.format("text ('%s') to be present in element value %s", text, element));
        }
    }

    public void waitForTextTobePresentInElementLocated(final By locator, final String text) {
        try {
            wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        } catch (Exception e) {
            LOG.error(String.format("text ('%s') to be present in element found by %s", text, locator));
        }
    }

    public void waitForTextTobePresentInElementValue(final By locator, final String text) {
        try {
            wait.until(ExpectedConditions.textToBePresentInElementValue(locator, text));
        } catch (Exception e) {
            LOG.error(String.format("text ('%s') to be the value of element located by %s", text, locator));
        }
    }

    public void waitForInvisibilityOfElementLocated(final By locator) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            LOG.error("element to no longer be visible: " + locator);
        }
    }

    public void waitForInvisibilityOfElementWithText(final By locator, final String text) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementWithText(locator, text));
        } catch (Exception e) {
            LOG.error(String.format("element containing '%s' to no longer be visible: %s", text, locator));
        }
    }

    public void waitForStalenessOf(final WebElement element) {
        try {
            wait.until(ExpectedConditions.stalenessOf(element));
        } catch (Exception e) {
            LOG.error(String.format("element (%s) to become stale", element));
        }
    }


    public void waitForElementToBeSelected(final WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeSelected(element));
        } catch (Exception e) {
            LOG.error(String.format("element (%s) to %s be selected", element, element.isSelected() ? "" : "not "));
        }
    }

    public void waitForElementSelectionStateToBe(final WebElement element, boolean selected) {
        try {
            wait.until(ExpectedConditions.elementSelectionStateToBe(element, selected));
        } catch (Exception e) {
            LOG.error(String.format("element (%s) to %sbe selected", element, selected ? "" : "not "));
        }
    }

    public void waitForElementToBeSelected(final By locator) {
        try {
            wait.until(ExpectedConditions.elementToBeSelected(locator));
        } catch (Exception e) {
            LOG.error(String.format("element (%s) to %s be selected", locator, driver.findElement(locator).isSelected() ? "" : "not "));
        }
    }

    public void waitForElementSelectionStateToBe(final By locator, boolean selected) {
        try {
            wait.until(ExpectedConditions.elementSelectionStateToBe(locator, selected));
        } catch (Exception e) {
            LOG.error(String.format("element (%s) to %sbe selected", locator, selected ? "" : "not "));
        }
    }

    public void waitForNumberOfWindowsToBe(int expectedNumberOfWindows) {
        try {
            wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows));
        } catch (Exception e) {
            LOG.error(String.format("number of open windows to be %s but actual windows are %s" + expectedNumberOfWindows, driver.getWindowHandles().size()));
        }
    }

    //give detailed docs  TODO outerHTML etc.,
    public void waitForAttributeToBe(final By locator, String attribute, String value) {
        try {
            wait.until(ExpectedConditions.attributeToBe(locator, attribute, value));
        } catch (Exception e) {
            LOG.error(String.format("element found by %s to have value \"%s\". Current value: \"%s\"", locator, value, driver.findElement(locator).getAttribute(attribute)));
        }
    }

    public void waitForTextToBe(final By locator, String value) {
        try {
            wait.until(ExpectedConditions.textToBe(locator, value));
        } catch (Exception e) {
            LOG.error(String.format("element found by %s to have text \"%s\". Current text: \"%s\"", locator, value, driver.findElement(locator).getText()));
        }
    }

    public void waitForTextMatches(final By locator, Pattern pattern) {
        try {
            wait.until(ExpectedConditions.textMatches(locator, pattern));
        } catch (Exception e) {
            LOG.error(String.format("text found by %s to match pattern \"%s\". Current text: \"%s\"", locator, pattern.pattern(), driver.findElement(locator).getText()));
        }
    }


    public void waitForAttributeToBe(final WebElement element, final String attribute, final String value) {
        try {
            wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
        } catch (Exception e) {
            LOG.error(String.format("Attribute or property '%s' to be '%s'. Current value: '%s'", attribute, value, element.getAttribute(attribute)));
        }
    }

    //give detailed docs  TODO outerHTML etc.,
    public void waitForAttributeContains(final WebElement element, final String attribute, final String value) {
        try {
            wait.until(ExpectedConditions.attributeContains(element, attribute, value));
        } catch (Exception e) {
            LOG.error(String.format("value to contain \"%s\".", e));
        }
    }


    public void waitForAttributeToBeNotEmpty(final WebElement element, final String attribute) {
        try {
            wait.until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
        } catch (Exception e) {
            LOG.error(String.format("attribute value to be empty \"%s\".", e));
        }
    }

    public void waitForInvisibilityOfAllElements(final List<WebElement> elements) {
        try {
            wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
        } catch (Exception e) {
            LOG.error("invisibility of all elements " + e);
        }
    }

    public void waitForInvisibilityOfAllElements(final WebElement element) {
        try {
            wait.until(ExpectedConditions.invisibilityOfAllElements(Arrays.asList(element)));
        } catch (Exception e) {
            LOG.error("invisibility of all element " + e);
        }
    }

    public void waitForInvisibilityOf(final WebElement element) {
        try {
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            LOG.error("invisibility of " + e);
        }
    }

    public void waitForJavaScriptThrowsNoExceptions(final String javaScript) {
        try {
            wait.until(ExpectedConditions.javaScriptThrowsNoExceptions(javaScript));
        } catch (Exception e) {
            LOG.error(String.format("js %s to be executable", e));
        }
    }

    public void waitForPresenceOfElementLocated(final By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            LOG.error(String.format("presence of element located by: " + e));
        }
    }


}
