# gauge-demo
This is a demo showing Gauge (http://www.getgauge.io/) running tests against Selenium WebDriver. 
It has support for MacOS, Windows & Linux execution of tests against Chrome and FireFox, and is 
trivally extensible to support any Selenium provider. 

The application includes an application binding that allows for natural expression of web tests:

## Example Test Specification
This is an example of a Gauge test that is written using the business language:

* Navigate to "http://www.google.com/"
* Type "Who is the US President" into "lst-ib"
* Wait up to "10" seconds for element "search"
* Page should contain text "Barack Obama"

This test will enter text into a box on the Google website and wait for the resultant page to load 
and show the present as long as governments don't between now and when you look.

## The Supported Test Language
You can find the full code of the bindings in WebTestBindings.java, but the supported commands are:

* Navigate to &lt;url&gt;
* Type &lt;text> into &lt;elementId&gt;
* Click button &lt;labelText&gt;
* Page should contain text &lt;text&gt;
* Wait up to &lt;timeoutSeconds&gt; seconds for element &lt;elementId&gt;

This is easily extensible with additional web-testing phrases as required.
 
## Configuring Drivers
In your gauge environment file (./env/environment-name/environment-name.properties), set the following variable:

		wingman.common.drivers.loader 
		
Allowed values are:

* wingman.common.drivers.ChromeWebDriverLoader 
* wingman.common.drivers.FirefoxWebDriverLoader 
* wingman.common.drivers.RemoteWebDriverLoader

## Specific Parameters
### ChromeWebDriverLoader
* webdriver.chrome.driver 
	* URL to platform-specific binary for chromedriver
	* The MacOS version of chromedriver is shipped with this project.
	
### RemoteWebDriverLoader 
* wingman.drivers.RemoteWebDriverLoader.hubUrl
	* URL of remote WebDriver hub. 
	* Example: http://10.2.1.100:4444/wd/hub/

* wingman.drivers.RemoteWebDriverLoader.browserName
	* Browser to request from remote hub.
	* Example: chrome (or any WebDriver implementation).
	
* wingman.drivers.RemoteWebDriverLoader.setJavascriptEnabled
	* Enable javascript for the remote test?
	* Allowed values: true | false


## Running WebDriver in Containers
If you want to run some remote WebDriver clients, a simple way to do this on a docker host is to use the official Selenium containers. Only Chrome and FireFox presently support headless operation.

* To create a Chrome container:
		 docker run -d -p 4444:4444 -v /dev/shm:/dev/shm selenium/standalone-chrome

* To create a Firefox container:
		docker run -d -p 4444:4444 -v /dev/shm:/dev/shm selenium/standalone-firefox

For more information, please see: https://github.com/SeleniumHQ/docker-selenium


	
	