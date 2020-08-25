package de.concepts.io;

import java.util.Enumeration;
import java.util.Properties;

/**
 * Helper class
 */
public class Helper {
    /**
     * enlist environment variables to single string
     * @return
     */
    public static String getServerEnvironmentVariables() {
        StringBuilder retVal = new StringBuilder();
        Properties p = System.getProperties();
        Enumeration keys = p.keys();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            String value = (String) p.get(key);
            retVal.append(key + ": " + value + "\n");
        }
        return retVal.toString();
    }
}
