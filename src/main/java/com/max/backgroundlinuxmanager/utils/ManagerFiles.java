/*
 * The MIT License
 *
 * Copyright 2019 Maximiliano Fernández <thebluemax13 at gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.max.backgroundlinuxmanager.utils;

import com.max.backgroundlinuxmanager.models.entities.AppConfiguration;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;

import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maximiliano Fernández <thebluemax13 at gmail.com>
 */
public class ManagerFiles {
    public static String WALLPAPER_FOLDER = "gnome-background-properties/";
    public static String BACKGROUNDS_FOLDER = "backgrounds";
    public static String LOCAL_SHARED= "/.local/share/";
    public static String CONFIGURATION_FOLDER = "background-manager";


    public static String getUserFolder () {
        return System.getProperty("user.home");
    }
    public static String FolderToObject  (){
        String s = LOCAL_SHARED.substring(1, LOCAL_SHARED.length());
        return s+BACKGROUNDS_FOLDER+"/";
    }
   
    public static String getDefaultImagePath() {
        String path = "";
        try {
            Process runtimeProcess = Runtime.getRuntime().exec("xdg-user-dir PICTURES");
            BufferedReader runtimeInput = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));
            String tmpString;
            while (( tmpString = runtimeInput.readLine()) != null) {
               path =  tmpString;
               //System.out.println("\n"+path+"e");
            }
        } catch (IOException ex) {
            Logger.getLogger(ManagerFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return path;
    }
    /**
     * 
     * @return 
     */
    public static File getBackgroundsFolder(){
    
    File f = null;
        try {
           f = new File(getUserFolder() + LOCAL_SHARED + BACKGROUNDS_FOLDER);
        } catch (Exception e) {
           // System.err.println(e.toString());
        }
        return f;
    }
    public static File getWallpapersFolder(){
    
    File f = null;
        try {
           f = new File(getUserFolder() + LOCAL_SHARED + WALLPAPER_FOLDER);
        } catch (Exception e) {
           // System.err.println(e.toString());
        }
        return f;
    }
    public static String getBackgroundsPath(){
        return getUserFolder() + LOCAL_SHARED + BACKGROUNDS_FOLDER;
    }
    public static File getConfigurationFile () {
        File f = null;
        f = new File( configurationFolderPath()+File.separator + AppConfiguration.CONFIG_FILE );
    return f;
    }
    public static String configurationFolderPath () {
    return  getUserFolder() + LOCAL_SHARED + CONFIGURATION_FOLDER;
    }
    public static void getFiles(File folder, List<File> files) {
        File[] fileList = folder.listFiles();
        
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].canRead() && fileList[i].isDirectory()) {
                getFiles(fileList[i], files);
            } else {
                files.add(fileList[i]);
            }
        }
    }
    public static void copyFile(File in, File out) throws IOException {
    InputStream is = null;
        OutputStream os = null;
    try {
        is = new FileInputStream(in);
        os = new FileOutputStream(out);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
    }   catch (FileNotFoundException ex) {
            Logger.getLogger(ManagerFiles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManagerFiles.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        is.close();
        os.close();
    }
    }
}
