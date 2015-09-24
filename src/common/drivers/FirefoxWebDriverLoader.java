package wingman.common.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import wingman.common.LoggingHelper;

/**
 * The FirefoxWebDriverLoader conjures instances of the FirefoxDriver
 * running locally on the users PC.
 * 
 * @author     Steve Gray
 * @version    1.0.0.0 
 **/
public class FirefoxWebDriverLoader
    implements IWebDriverLoader
{
    /**
     * Create a WebDriver instance to work with.
     **/
    public WebDriver createWebDriver()
    {
        WebDriver result = null;
        
        LoggingHelper.LogInfo(getClass().getName(), "Initializing the FirefoxDriver instance.");
        try 
        {            
            result = new FirefoxDriver();   
        } 
        catch(Exception e)
        {
            LoggingHelper.LogError(getClass().getName(), "Cannot initialize the FirefoxDriver: %s", e.toString());
            throw e;
        }
        
        LoggingHelper.LogVerbose(getClass().getName(), "Succesfully initialized the FirefoxDriver instance.");     
        return result;
    }
}
