/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.max.backgroundlinuxmanager.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.*;
import com.max.backgroundlinuxmanager.utils.XMLparse;
import com.max.backgroundlinuxmanager.controllers.utils.DeleteOption;
import com.max.backgroundlinuxmanager.components.Library.LibraryComponent;
import com.max.backgroundlinuxmanager.models.entities.WallpaperXML;
import com.max.backgroundlinuxmanager.components.MainFrame.MainFrameController;
import com.max.backgroundlinuxmanager.views.components.ImageBlockPane;
import com.max.backgroundlinuxmanager.controllers.views.XmlWallpaperController;
import com.max.backgroundlinuxmanager.utils.ManagerFiles;
import com.max.backgroundlinuxmanager.exceptions.BackgroundException;
import com.max.backgroundlinuxmanager.models.entities.AppConfiguration;
import com.max.backgroundlinuxmanager.views.components.NavComponent;

/**
 *
 * @author max
 */
public class BackgroundManager  {


    private ConfigurationManager configManager;
    private MainFrameController frame;// = new MainJFrame();
    private AppConfiguration appConfig;

    /**
     *
     */
    public void initApp() {
        initComponents();
        checkConfig();
    }

    private void initComponents() {
        frame = new MainFrameController(appConfig);
        frame.setVisible(true);

     //   library = new LibraryComponent(frame);

    //    System.out.println(frame.getHeight() + "add" + frame.getWidth());
     //   frame.addToMain(library, 5, 5, frame.getWidth() - 10, frame.getHeight() - 105);
     //   library.inti();
      //  XmlPanel = new XmlWallpaperController();
      //  frame.addToMain(XmlPanel, 5, 5, frame.getWidth() - 10, frame.getHeight() - 105);
      //  XmlPanel.setVisible(false);

     //   nav = new NavComponent();
     //   frame.addToMain(nav, 5, frame.getContentPane().getHeight() - 60, frame.getContentPane().getWidth() - 5, 60);
     //   setListeners();
    }

    /**
     *   Método que crea un objeto con la configuración de la APP
     **/
    public void checkConfig() {
        configManager = new ConfigurationManager();
        appConfig = configManager.getConfig();
    }

    /**
     * TODO posible CLI
     */
   /* public void consoleBatchProcess() {
    }/*/

    /**
     * implementación de la interface ActionListener
     *
     * @param ae
     */
    
  /*  @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("click" + ae.getActionCommand());
        switch (ae.getActionCommand()) {
          
            case NEW_WALLPAPER:
                String selected = library.getSelected();
                newWallpaper(selected);
                break;

            case BackgroundManager.SAVE_NEW_WALLPAPER:
                XmlPanel.saveCurrent();
                library.setVisible(true);
                XmlPanel.setVisible(false);
                break;

            case BackgroundManager.CANCEL:
                XmlPanel.clear();
                library.setVisible(true);
                XmlPanel.setVisible(false);
                break;
            case BackgroundManager.WALLPAPER:
                library.setVisible(false);
                XmlPanel.setVisible(true);
                XmlPanel.seeWallpaper();
                XmlPanel.setListener(this);

                break;
            case BackgroundManager.DELETE_ACTION:
                JButton block = (JButton) ae.getSource();
                ImageBlockPane blockImage = (ImageBlockPane) block.getParent();

                System.out.println(blockImage.getImage().getName());
                DeleteOption option = new DeleteOption(blockImage);

                if (option.getResult()) {
                    cachedFilesList.remove(blockImage.getImage());
                    frame.remove(blockImage);
                }
                break;

            case BackgroundManager.CLOSE_XMLWALLPAPER:
                System.out.println("close add");
                XmlPanel.clear();
                library.setVisible(true);
                XmlPanel.setVisible(false);
                break;

            case BackgroundManager.ADD_ACTION:
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

            /**
             * case BackgroundManager.ADD_ACTION: System.out.println("add");
             * WallpaperXML newWallPaper = new WallpaperXML(); File folder =
             * ManagerFiles.getWallpapersFolder(); if(!folder.exists()){
             * folder.mkdir(); } xmlParse = new XMLparse(); xmlParse.saveXML(new
             * File (folder.getPath() +
             * "test.xml"),XMLparse.WALLPAPER_XML,newWallPaper); break;
             
        }

    }*/
}
