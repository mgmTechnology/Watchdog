package de.concepts.io;

import java.io.File;
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

    /**
     * get the extension of a file
     * @param file
     * @return
     */
    public static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

    /**
     * get extension of a given absolut path
     * @param fullPath
     * @return
     */
    public static String getPathExtension(String fullPath) {
        String extension = "";

        if (fullPath.contains(".")) {
            extension = fullPath.substring(fullPath.lastIndexOf(".") + 1);
        }
        return extension;
    }
}
