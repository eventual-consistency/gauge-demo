package wingman.common.drivers;

import org.openqa.selenium.WebDriver;

/**
 * The IWebDriverLoader summons up a WebDriver instance for our application to
 * work with. This allows us to pull the driver from various locations as required.
 *
 * @author    Steve Gray
 * @version   1.0.0
 **/
public interface IWebDriverLoader
{
    /**
     * Create a WebDriver instance to work with.     
     **/
    WebDriver createWebDriver();    
}