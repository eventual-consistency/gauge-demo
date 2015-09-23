package steveg.common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * The AbstractWebTest class provides common helper functions for running
 * unit tests via Selium WebDriver. 
 *
 * @author 		Steve Gray
 * @version		1.0.0.0
 **/
public abstract class AbstractWebTest
{	
	private WebDriver m_webDriver;	// Webdriver client instance
	
	/**
	 * Initialize a new instance of AbstractWebTest 
	 **/
	protected AbstractWebTest()
	{
		// Intentionally blank	
	}
	
	/**
	 * Initialize the class WebDriver instance for the current request.
	 **/
	protected void startWebDriver()
	{
		if (m_webDriver != null)
			throw new RuntimeException("The WebDriver was already initialized - but we attempted to initialize twice.");
		m_webDriver = new ChromeDriver();	
	}
	
	/**
	 * Tear down the Selenium driver instance
	 **/
	protected void stopWebDriver()
	{
		if (m_webDriver != null) {
			
			System.out.println("Shutdown");
			try {
			m_webDriver.quit();
			System.out.println("Quit complete");
			} catch (Exception e)
			{
							System.out.println("Shutdown error");

			System.out.println(e.toString());
			}
			finally
			{
							System.out.println("Shutdown Complete");

			}
		}
		m_webDriver = null;
	}
	
	/**
	 * Ensure the WebDriver is ready to be used 
	 **/
	private void validateWebDriverState()
	{
		if (m_webDriver == null)
			throw new RuntimeException("The WebDriver was not initialized before a request was attempted.");		
	}
	
	/**
	 * Navigate to a URL using the WebDriver. Raises an error if the final request
	 * is not a HTTP success (200 OK or equivelent response)
	 *
	 * @param url 	URL being requested
	 **/
	protected void performGetRequest(String url)
	{
		validateWebDriverState();
		m_webDriver.get(url);
	}
	
	/**
	 * Search the current web-page/document for a button where the text matches the specified
	 * value. This for looking up specific user actions.
	 *
	 * @param label Label text to locate.
	 **/
	protected WebElement findButtonByLabel(String label)
	{
		validateWebDriverState();
		
		WebElement element = m_webDriver.findElement(By.xpath("//button[contains(text(),'" + label +"')]"));
		if (element == null)
			throw new RuntimeException("The WebDriver was not able to find the specified element on the page.");		
			
		return element;
	}
	
	/**
	 * Search the page and find an element by it's Id
	 * 
	 * @param elementId HTML Element Id of the object to find.
	 **/
	protected WebElement findElementById(String elementId)
	{
		validateWebDriverState();
		
		WebElement element = m_webDriver.findElement(By.id(elementId));
		if (element == null)
			throw new RuntimeException("The WebDriver was not able to find the specified element on the page.");
			
		return element;
	}
	
	/**
	 * Does the page contain the specified text in it's source anywhere?
	 *
	 * @param textToFind Text to find on the page.
	 **/
	protected Boolean doesPageContainText(String textToFind)
	{
		validateWebDriverState();
		
		return m_webDriver.getPageSource().contains(textToFind);
	}
	
	/**
	 * Wait N seconds for some elenent to appear on the page
	 *
	 * @parm elementId Element to locate in page structure
	 * @param timeoutSeconds Number of seconds to wait before failing.
	 **/
	protected void waitForElementToBePresent(String elementId, int timeoutSeconds)
	{
		validateWebDriverState();
		
		WebElement myDynamicElement = (new WebDriverWait(m_webDriver, timeoutSeconds))
  			.until(ExpectedConditions.presenceOfElementLocated(By.id(elementId)));
	}
}