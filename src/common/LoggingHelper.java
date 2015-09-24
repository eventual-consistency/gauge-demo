package wingman.common;

/**
 * The LoggingHelper provides some common code that does logging. It's intended
 * to be just enough code to let command line test runners understand output, without
 * bringing a dependancy on something bigger. That said, by centralizing here, we can
 * easily move later if required
 *
 * @author    Steve Gray
 * @version   1.0.0
 **/
public final class LoggingHelper
{
    private static final String LOGMESSAGE_VERBOSE = "VERBOSE";
    private static final String LOGMESSAGE_INFO = "INFO";
    private static final String LOGMESSAGE_WARNING = "WARNING";
    private static final String LOGMESSAGE_ERROR = "ERROR";
    
    /***
     * Log a verbose message
     **/
    public static void LogVerbose(String origin, String message, Object... args)
    {
        LogByType(LOGMESSAGE_VERBOSE, origin, message, args);
    }
    
    /***
     * Log an informational message
     **/
    public static void LogInfo(String origin, String message, Object... args)
    {
        LogByType(LOGMESSAGE_INFO, origin, message, args);
    }
    
    /***
     * Log a warning message
     **/
    public static void LogWarning(String origin, String message, Object... args)
    {
        LogByType(LOGMESSAGE_WARNING, origin, message, args);
    }
    
    /***
     * Log an error message
     **/
    public static void LogError(String origin, String message, Object... args)
    {
        LogByType(LOGMESSAGE_ERROR, origin, message, args);
    }
    
    /**
     * Log a message by type
     **/
    private static void LogByType(String level, String origin, String message, Object... args)
    {
        String formatted = String.format("(%s) @ {%s}: %s", level, origin, String.format(message, args));
        System.out.print(formatted);
    }
}