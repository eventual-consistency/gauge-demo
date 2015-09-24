package wingman.common;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * The WebDriverUtility provides some helper functions for use within
 * the library.
 *
 * @author Steve Gray
 * @version 1.0.0.0
 **/
public class WebDriverUtility
{
    private static final String CLASS_NAME = "WebDriverUtility";
    
    /**
     * Get an environment string as a URL or fail with a runtime error
     * 
     * @param variableName Environment variable variableName
     **/
    public static URL getEnvironmentStringURL(String variableName)
    {
        // Get the environment variable        
        String environ = getEnvironmentString(variableName);
        
        try 
        {
            URL output = new URL(environ);
            LoggingHelper.LogVerbose(CLASS_NAME, "Loaded enviroment variable URL: %s = %s", variableName, environ);
            return output;
        }
        catch (MalformedURLException e)
        {
            LoggingHelper.LogError(CLASS_NAME, "The value '%s' is not a valid URL", environ);            
            throw new RuntimeException(e.toString());
        }
    }
    
    /**
     * Get an environment string as a URL or fail with a runtime error
     * 
     * @param variableName Environment variable variableName
     **/
    public static Boolean getEnvironmentStringBoolean(String variableName)
    {
        // Get the environment variable        
        String environ = getEnvironmentString(variableName);
        
        try 
        {
            Boolean output = Boolean.valueOf(environ);
            LoggingHelper.LogVerbose(CLASS_NAME, "Loaded enviroment variable URL: %s = %s", variableName, output);
            return output;
        }
        catch (Exception e)
        {
            LoggingHelper.LogError(CLASS_NAME, "The value '%s' is not a valid Boolean", environ);            
            throw e;
        }
    }
    
    /**
     * Get an validated path or fail with a runtime error
     * 
     * @param relativePath Relative path
     **/
    public static String validatedFilePath(String relativePath)
    {
        try
        {
            LoggingHelper.LogVerbose(CLASS_NAME, "Validating file path: %s", relativePath);

            File original = new File(relativePath);
            String absolutePath = original.getAbsolutePath();
            File file = new File(absolutePath);
            
            if (file.exists())
                return absolutePath;
            else
                throw new RuntimeException(String.format("The file '%s' does not exist.", absolutePath));
        }
        catch (Exception e)
        {
            LoggingHelper.LogError(CLASS_NAME, "The value '%s' is not a valid file path", e.toString());    
            throw e;
        }   
    }
    
    /**
     * Get an environment string or fail with a runtime error
     * 
     * @param variableName Environment variable variableName
     **/
    public static String getEnvironmentString(String variableName)
    {
        // Validate parameters
        if (variableName == null || variableName.isEmpty())     
            throw new RuntimeException("Cannot get environment string - The variableName input was null.");

        LoggingHelper.LogVerbose(CLASS_NAME, "Loading enviroment variable: %s", variableName);
        
        // Get the environment variable        
        String environ = System.getenv(variableName);
        if (variableName == null || variableName.isEmpty())     
        {
            LoggingHelper.LogError(CLASS_NAME, "Error, the variable '%s' was null or not present.", variableName);
            throw new RuntimeException(String.format("Cannot getEnvironmentStringBoolean - The '%s' enviroment variable was null.", variableName));
        }
        
        return environ;
    }
    
    /**
     * Get an environment string or a default value.
     * 
     * @param variableName Environment variable variableName
     * @param defaultValue Default value to return
     **/
    public static String getEnvironmentStringOrDefault(String variableName, String defaultValue)
    {
        // Validate parameters
        if (variableName == null || variableName.isEmpty())     
            throw new RuntimeException("Cannot get environment string - The variableName input was null.");

        LoggingHelper.LogVerbose(CLASS_NAME, "Loading enviroment variable: %s", variableName);
        
        // Get the environment variable        
        String environ = System.getenv(variableName);
        if (variableName == null || variableName.isEmpty())     
        {
            LoggingHelper.LogInfo(CLASS_NAME, "The variable '%s' is not set. Defaulting to: %s", variableName, defaultValue);
            return defaultValue;
        }
        
        return environ;
    }
}