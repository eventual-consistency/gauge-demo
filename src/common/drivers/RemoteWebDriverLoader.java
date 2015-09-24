package wingman.common.drivers;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebDriver;
import wingman.common.LoggingHelper;
import wingman.common.WebDriverUtility;

/**
 * The RemoteWebDriverLoader connects to a WebDriver running remotely.
 * 
 * @author     Steve Gray
 * @version    1.0.0.0 
 **/
public class RemoteWebDriverLoader
    implements IWebDriverLoader
{
    private static final String REMOTEDRIVER_ENVIRON_BASEURL = "wingman.drivers.RemoteWebDriverLoader.hubUrl";
    private static final String REMOTEDRIVER_ENVIRON_BROWSER = "wingman.drivers.RemoteWebDriverLoader.browserName";
    private static final String REMOTEDRIVER_ENVIRON_ENABLEJS = "wingman.drivers.RemoteWebDriverLoader.setJavaScriptEnabled";
    
    /**
     * Create a WebDriver instance to work with.
     **/
    public WebDriver createWebDriver()
    {
        WebDriver result = null;
        
        LoggingHelper.LogInfo(getClass().getName(), "Initializing the RemoteWebDriver instance.");
        try 
        {            
            // Load settings 
            URL url = WebDriverUtility.getEnvironmentStringURL(REMOTEDRIVER_ENVIRON_BASEURL);
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(WebDriverUtility.getEnvironmentString(REMOTEDRIVER_ENVIRON_BROWSER));
            capabilities.setJavascriptEnabled(WebDriverUtility.getEnvironmentStringBoolean(REMOTEDRIVER_ENVIRON_ENABLEJS));
                       
            LoggingHelper.LogInfo(getClass().getName(), "Attempting to initialize with Hub URL: %s", url.toString());
            result = new RemoteWebDriver(url, capabilities);   
        } 
        catch(Exception e)
        {
            LoggingHelper.LogError(getClass().getName(), "Cannot initialize the RemoteWebDriver: %s", e.toString());
            throw e;
        }
        
        LoggingHelper.LogVerbose(getClass().getName(), "Succesfully initialized the RemoteWebDriver instance.");     
        return result;
    }
}
