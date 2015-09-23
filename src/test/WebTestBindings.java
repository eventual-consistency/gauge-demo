import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeSuite;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import steveg.common.*;

/**
 * The WebTestBindingings provides some common binding support for a web-test.
 *
 * @author Steve Gray
 **/
public class WebTestBindings
	extends AbstractWebTest
{
	/**
	 * Navigate to a URL
	 *
	 * @param url 	URL being requested.
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
	 * @param timeoutSeconds 	Number of seconds to wait before giving up.
	 * @param elementId			Element Id to locate.
	 **/
	@Step("Wait up to <timeoutSeconds> seconds for element <elmentId>")
	public void waitForElementToAppear(int timeoutSeconds, String elementId)
	{
		waitForElementToBePresent(elementId, timeoutSeconds);
	}	
	
	/**
	 * Start Selenium
	 **/
	@BeforeSuite
	public void initializeSuite()
	{
		System.out.println("Hello");
		startWebDriver();
	}
	
	/**
	 * Stop selenium
	 **/ 
	@AfterSuite
	public void teardownSuite()
	{
		stopWebDriver();
	}
}
