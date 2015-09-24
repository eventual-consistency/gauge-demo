import com.thoughtworks.gauge.AfterSpec;
import com.thoughtworks.gauge.BeforeSpec;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.screenshot.ICustomScreenshotGrabber;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import wingman.common.AbstractWebTest;
import wingman.common.LoggingHelper;

/**
 * The WebTestBindingings provides some common binding support for a web-test.
 *
 * @author Steve Gray
 **/
public class WebTestBindings
    extends AbstractWebTest
    implements ICustomScreenshotGrabber
{
    /**
     * Navigate to a URL
     *
     * @param url     URL being requested.
     **/
    @Step("Navigate to <url>")
    public void navigateTo(String url)
    {
        performGetRequest(url);
    }

    /**
     * Click a button that has specific text on it. If there are multiple,
     * this will click the first button implicitly.
     *
     * @param labelText Text on button to find
     **/
    @Step("Click button <labelText>")
    public void clickLabelledButton(String labelText)
    {
        WebElement element = findButtonByLabel(labelText);
        element.click();
    }
    
    /**
     * Type text into the element om the page by the specified Id.
     **/
    @Step("Type <text> into <elementId>")
    public void typeTextIntoElementById(String text, String elementId)
    {
        WebElement element = findElementById(elementId);
        element.sendKeys(text);
    }

    /**
     * Assert that the page contains some text.
     **/
    @Step("Page should contain text <text>")
    public void assertPageContainsText(String text)
    {
        if (!doesPageContainText(text))
            throw new RuntimeException("Page does not contain the specified text.");
    }
    
    /**
     * Wait N seconds for an element with id=someId to appear on the page.
     *
     * @param timeoutSeconds     Number of seconds to wait before giving up.
     * @param elementId            Element Id to locate.
     **/
    @Step("Wait up to <timeoutSeconds> seconds for element <elmentId>")
    public void waitForElementToAppear(int timeoutSeconds, String elementId)
    {
        waitForElementToBePresent(elementId, timeoutSeconds);
    }    
    
    /**
     * Start Selenium
     **/
    @BeforeSpec
    public void initializeSpec()
    {
        startWebDriver();        
    }
    
    /**
     * Stop selenium
     **/ 
    @AfterSpec
    public void teardownSpec()
    {
        stopWebDriver();                                    
    }
    
    /**
     * Take screenshot.
     *
     * @return Byte array of the screenshot taken.
     * Return an empty Byte array if unable to capture screen.
     */
    public byte[] takeScreenshot()
    { 
           return getScreenshotBytes();
    }
}