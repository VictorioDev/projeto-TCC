/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author victo
 */
public class VerificaSistema {

    public static String suckOsInfo() throws IllegalArgumentException, IllegalAccessException {

        // Get OS Name (Like: Windows 7, Windows 8, Windows XP, etc.)
        String osVersion = System.getProperty("os.name");
        System.out.print(osVersion);
        String result = "";
        String pFilesX86 = System.getenv("ProgramFiles(X86)");
        if (pFilesX86 != (null)) {
            // Put here the code to execute when Windows x64 are Detected
            System.err.println("x64");
            return "64";

        } else {
            // Put here the code to execute when Windows x32 are Detected
            System.err.println("x86");
            return "86";

        }

        //System.exit(0);
    }

    public static void main(String[] args) throws IllegalArgumentException {
        try {
            suckOsInfo();
        } catch (IllegalAccessException ex) {
            Logger.getLogger(VerificaSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
