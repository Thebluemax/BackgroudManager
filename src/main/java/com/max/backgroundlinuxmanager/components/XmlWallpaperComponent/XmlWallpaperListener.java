/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.max.backgroundlinuxmanager.components.XmlWallpaperComponent;

import com.max.backgroundlinuxmanager.components.MainFrame.MainFrameController;
import com.max.backgroundlinuxmanager.components.MainFrame.MainJFrame;
import com.max.backgroundlinuxmanager.models.entities.WallpaperXML;
import com.max.backgroundlinuxmanager.utils.ManagerFiles;
import com.max.backgroundlinuxmanager.utils.XMLparse;
import com.max.backgroundlinuxmanager.views.components.WallpaperView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
 *
 * @author max
 */
public class XmlWallpaperListener implements ActionListener{
    public static final String CANCEL = "CANCEL";
    public static final String SAVE = "SAVE";
    public static final String NEW_XML_WALLPAPER = "NEW_XML_WALLPAPER";

    
    private XmlWallpaperController controller;
    public XmlWallpaperListener(XmlWallpaperController controller) {
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
        }
    }
    
}
