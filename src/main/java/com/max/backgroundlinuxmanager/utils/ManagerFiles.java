/*
 * The MIT License
 *
 * Copyright 2019 Maximiliano Fernández thebluemax13 at gmail.com.
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
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maximiliano Fernández thebluemax13 at gmail.com
 */
public class ManagerFiles {

    /**
     *
     */
    public static String WALLPAPER_FOLDER = "/gnome-background-properties";

    /**
     *
     */
    public static String BG_FOLDER = "/backgrounds";
    public static String WP_FOLDER = "/wallpapers";

    /**
     *
     */
    public static String BACKGROUNDS_THUMBS = "/thumbs";

    /**
     *
     */
    public static String LOCAL_SHARED = "/.local/share";

    /**
     *
     */
    public static String CONFIGURATION_FOLDER = "/background-manager";

    /**
     *
     * @return Un string que es el path a la carpeta del usuario.
     */
    public static String getUserFolder() {
        return System.getProperty("user.home");
    }

    /**
     * Get the path of the default Picture folder defined in the System
     * configuration
     *
     * @return path strig the path.
     */
    public static String getDefaultImagePath() {
        String path = "";
        try {
            Process runtimeProcess = Runtime.getRuntime().exec("xdg-user-dir PICTURES");
            BufferedReader runtimeInput = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));
            String tmpString;
            while ((tmpString = runtimeInput.readLine()) != null) {
                path = tmpString;
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
    public static File getBackgroundsFolder() {
        File f = null;
        try {
                f = new File(ManagerFiles.getBackgroundsPath());
                if(!f.exists()){
                    f.mkdir();
                 File d =   new File(ManagerFiles.getThumbsPath());
                 d.mkdir();
            } 
              //  f = new File(getUserFolder() + LOCAL_SHARED + BG_FOLDER + "/" + WP_FOLDER);
        } catch (Exception e) {
             System.err.println(e.toString());
        }
        return f;
    } 

    /**
     *
     * @return
     */
    public static File getWallpapersXMLFolder() {

        File f = null;
        try {
            f = new File(getUserFolder() + LOCAL_SHARED + WALLPAPER_FOLDER);
        } catch (Exception e) {
            // System.err.println(e.toString());
        }
        return f;
    }

    /**
     *
     * @return
     */
    public static String getBackgroundsPath() {
        return  getUserFolder() + LOCAL_SHARED + BG_FOLDER + WP_FOLDER;
                
    }
     /**
     *
     * @return
     */
    public static String getThumbsPath() {
        return  getUserFolder() + LOCAL_SHARED  + BG_FOLDER + WP_FOLDER + BACKGROUNDS_THUMBS;
                
    }

    /**
     *
     * @return
     */
    public static File getConfigurationFile() {
        File f = null;
        f = new File(configurationFolderPath() + File.separator + AppConfiguration.CONFIG_FILE);
        return f;
    }

    /**
     *
     * @return
     */
    public static String configurationFolderPath() {
        return getUserFolder() + LOCAL_SHARED + CONFIGURATION_FOLDER;
    }

    /**
     *
     * @param folder
     * @param files
     */
    public static void getFiles(File folder, List<File> files) {
        File[] fileList = folder.listFiles();

        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].canRead() && fileList[i].isDirectory()) {

              //  getFiles(fileList[i], files);
            } else {
               // try {
                   // String tipodeArchivo = Files.probeContentType(fileList[i].toPath());
                    files.add(fileList[i]);

             //   } catch (IOException ex) {
             //       Logger.getLogger(ManagerFiles.class.getName()).log(Level.SEVERE, null, ex);
              //  }
            }
        }
    }

    /**
     * Copy a file to a new file
     *
     * @param in file to copy
     * @param out new file
     * @throws IOException
     */
    public static int copyFile(File in, File out) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        int length = -1;
        try {
            is = new FileInputStream(in);
            os = new FileOutputStream(out);
            byte[] buffer = new byte[1024];

            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManagerFiles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManagerFiles.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            is.close();
            os.close();
        }
        return length;
    }

    public static boolean deleteFile(File file) {

        boolean deleted = false;
        if (file.exists()) {
            deleted = file.delete();
        }
        return deleted;
    }
    
    public static boolean isSlide(String filename){
        
        return false;
    }

}
