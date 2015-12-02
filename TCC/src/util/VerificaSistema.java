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

    public static void suckOsInfo() throws IllegalArgumentException, IllegalAccessException {

        // Get OS Name (Like: Windows 7, Windows 8, Windows XP, etc.)
        String osVersion = System.getProperty("os.name");
        System.out.print(osVersion);

        String pFilesX86 = System.getenv("ProgramFiles(X86)");
        if (pFilesX86 != (null)) {
            // Put here the code to execute when Windows x64 are Detected
            System.out.println(" 64bit");
            System.setProperty("java.library.path", "vm/x64");

            Field fieldSysPath = null;
            try {
                fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
            } catch (NoSuchFieldException ex) {
                Logger.getLogger(VerificaSistema.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(VerificaSistema.class.getName()).log(Level.SEVERE, null, ex);
            }
            fieldSysPath.setAccessible(true);
            fieldSysPath.set(null, null);
        } else {
            // Put here the code to execute when Windows x32 are Detected
            System.out.println(" 32bit");
            System.setProperty("java.library.path", "vm/x86");

            Field fieldSysPath = null;
            try {
                fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
            } catch (NoSuchFieldException ex) {
                Logger.getLogger(VerificaSistema.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(VerificaSistema.class.getName()).log(Level.SEVERE, null, ex);
            }
            fieldSysPath.setAccessible(true);
            fieldSysPath.set(null, null);

        }

        System.out.println("Now getSystemInfo class will EXIT");
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
