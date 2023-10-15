/*
 * The MIT License
 *
 * Copyright 2020 Maximiliano Fernández thebluemax13 at gmail.com.
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
package com.max.backgroundlinuxmanager.components.XmlWallpaperComponent;

import com.max.backgroundlinuxmanager.models.entities.WallpaperXML;
import com.max.backgroundlinuxmanager.utils.ManagerFiles;
import com.max.backgroundlinuxmanager.utils.XMLparse;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JOptionPane;
/**
 *
 * @author max
 */
public class XmlWallpaperListener implements ActionListener{
    public static final String CANCEL = "CANCEL";
    public static final String SAVE = "SAVE";
    public static final String NEW_XML_WALLPAPER = "NEW_XML_WALLPAPER";

    
    private XmlWallpaperComponent controller;
    public XmlWallpaperListener(XmlWallpaperComponent controller) {
        this.controller = controller;
    }
  

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case NEW_XML_WALLPAPER:
              System.out.println("add");
                WallpaperXML newWallPaper = new WallpaperXML();
                File folder = ManagerFiles.getWallpapersXMLFolder();
                if (!folder.exists()) {
                    folder.mkdir();
                }
                XMLparse xmlParse = new XMLparse();
                String filename = JOptionPane.showInputDialog("Nombre del archivo");
                int status = xmlParse.saveXML(new File(folder.getPath() + File.separator + filename + ".xml"), XMLparse.WALLPAPER_XML, newWallPaper);
                if (status == 0) {
                    // checkWallpapersXML();
                }
                break;
            case SAVE:
                controller.saveCurrent();
                break;
            case CANCEL:
                controller.clear();
                break;
        }
    }
    
}
