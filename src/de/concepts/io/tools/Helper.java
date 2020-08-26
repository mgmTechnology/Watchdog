/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.io.tools;

import java.io.File;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Helper class
 */
public class Helper {
    /**
     * enlist environment variables to single string
     *
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
     *
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
     *
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

    public static void logWatchdog() {
        // BuildMyString.com generated code. Please enjoy your string responsibly.


        String theDog = "\n........................................................ .. ..... ... ... ..  .." + "\n.." +
                "." + "....................................................       .     .      .    " + "\n.........." +
                "......" + "............................ Z..M..............................." + "\n.................." +
                "..........." + "...............MM..M..............................." + "\n.........................." +
                "................" + ".?MMM.M .............................." + "\n.................................." +
                ".........MMMM7MI....." + "........................." + "\n.........................................." +
                ".7MMMMMMMMMM.............." + "............" + "\n..........................................." +
                ".MMMMMMMMMMM........................." + "\n..........................................." +
                ".MMMMMMMMMMMMMMM ...................." + "\n.......................................... " +
                "MMMMMMMMMMMMMMMM. .... ... . . . ...." + "\n......................................... " +
                "MMMMMMMMMMMMMMMMM   . .     . . . . . " + "\n.................................... ..." +
                ".MMMMMMMMMMMMMMMMMM.  . ....  . .  . . ." + "\n......................................." +
                ".MMMMMMMMMMMMMMMMMM.... ......  .. ....  " + "\n ..................................... MMMMMMMMMMM.." +
                "..~......... .. ..... .... ." + "\n............................. ....... MMMMMMMMMMM....... ..  .. ." +
                ".. . ..  ... .." + "\n.....................................MMMMMMMMMMMM....... ......   ..   .   ..." +
                " ." + "\n....................................MMMMMMMMMMMMMM...... . . . .   .......... .." + "\n...." +
                " .... ....  ... .. ... ......OMMMMMMMMMMMMMMMM.............................." + "\n..... ...... ..  " +
                ".. ..  .. .....DMMMMMMMMMMMMMMMMM .......... . ................" + "\n....................... ......" +
                ".MMMMMMMMMMMMMMMMMMMO........ ...................." + "\n.................... ........" +
                ".MMMMMMMMMMMMMMMMMMMMZ............................." + "\n....  .. .... . ..  .. ..  " +
                ".$MMMMMMMMMMMMMMMMMMMMM.............................." + "\n. .. . .... ..... . .    ." +
                ".MMMMMMMMMMMMMMMMMMMMMM:.............................." + "\n ........................" +
                ".MMMMMMMMMMMMMMMMMMMMMM+..................... ........." + "\n...............   ....." +
                ".MMMMMMMMMMMMMMMMMMMMMMMM................................" + "\n .... .......... .. .  " +
                "MMMMMMMMMMMMMMMMMMMMMMMM8................................" + "\n................. ... " +
                "MMMMMMMMMMMMMMMMMMMMMMMMM+................................" + "\n...... ............  " +
                "MMMMMMMMMMMMMMMMMMM: MMMMM:................................" + "\n.............. .  .. " +
                "MMMMMMMMMMMMMMMMMZ ..MMMMM,................................" + "\n....... ...... . ... " +
                "MMMMMMMMMMMMMMMMM ...MMMMM ................................" + "\n................. . " +
                "MMMMMMMMMMMMMMMMMM....MMMMM ........... . ... . ........  . " + "\n.................." +
                ".8MMMMMMMMMMMMMMMMM.....MMMMM ................................" + "\n.................." +
                ".NMMMMMMMMMMMMMMM ......MMMMM ............... . ...    ... .. " + "\n.... .........   ." +
                ".MMMMMMMMMMMMMM.........MMMMM........... .....  . ... . . . .." + "\n.... . .. ... ..  " +
                "MMMMMMMMMMMMMI..   .....MMM,MM.. .....  .  .. . .  .    ...  ." + "\n ... ....      ..." +
                ".MMMDMMMMMMMMMMMMMMMM..OMM.MMM....... .. ..... ............ ." + "\n.......... ........." +
                ".OMMMMMMMMMMMMMMM ... MMMMM....... ..    . ... ... .....  ." + "\n .    .   .  .      " +
                "::::::::::::::::,......:::::.......                         " + "                                   " +
                "                    ";
        System.out.println(theDog);
        //logger.info(theDog);
    }
}
