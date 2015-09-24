package wingman.common.drivers;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import wingman.common.LoggingHelper;
import wingman.common.WebDriverUtility;

/**
 * The ChromeWebDriverLoader conjures instances of the ChromeDriver
 * running locally on the users PC.
 * 
 * @author     Steve Gray
 * @version    1.0.0.0 
 **/
public class ChromeWebDriverLoader
    implements IWebDriverLoader
{
    private static final String CHROMEDRIVER_PATH_ENVIRON = "webdriver.chrome.driver";

    /**
     * Create a WebDriver instance to work with.
     *
     * The ChromeDriver is pretty naieve when it comes to relative paths, and will seemingly LogError
     * unless I take the very same path and getAbsolutePath on it.     
     **/
    public WebDriver createWebDriver()
    {
        WebDriver result = null;
        
        LoggingHelper.LogInfo(getClass().getName(), "Initializing the ChomeDriver instance.");
        try 
        {            
            // Load parameters and validate
            String path = WebDriverUtility.getEnvironmentString(CHROMEDRIVER_PATH_ENVIRON);
            String absolutePath = WebDriverUtility.validatedFilePath(path);

            // Push the environment variable to make sure ChromeDriver works.
            System.setProperty(CHROMEDRIVER_PATH_ENVIRON, absolutePath);          

            // Start ChromeDriver
            ChromeOptions options = new ChromeOptions();  
            result = new ChromeDriver(options);   
        } 
        catch(Exception e)
        {
            LoggingHelper.LogError(getClass().getName(), "Cannot initialize the ChromeDriver: %s", e.toString());
            throw e;
        }
        
        LoggingHelper.LogVerbose(getClass().getName(), "Succesfully initialized the ChomeDriver instance.");     
        return result;
    }
}
