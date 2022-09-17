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
import com.max.backgroundlinuxmanager.components.Library.LibraryController;
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

   /* public final static String DELETE_ACTION = "DELETE";
    public final static String ADD_ACTION = "ADD";
    public final static String SAVE_NEW_WALLPAPER = "SAVE_NEW_WALLPAPER";
    public final static String NEW_WALLPAPER = "NEW_WALLPAPER";
    public final static String CANCEL = "CANCEL";
    public final static String WALLPAPER = "WALLPAPER";
    public final static String CLOSE_XMLWALLPAPER = "CLOSE_WALLPAPERXML";/*/

    private ConfigurationManager configManager;
    private MainFrameController frame;// = new MainJFrame();
    private AppConfiguration appConfig;
 //   private List<File> cachedFilesList;
  //  private WallpaperXML wallpaperXML;
   // private LibraryController library;
  //  private XmlWallpaperController XmlPanel;
  //  private NavComponent nav;

    /**
     *
     */
    public void initApp() {
    //    cachedFilesList = new ArrayList();
        initComponents();
        checkConfig();
      //  getBackgroundLibrary();
    }

    private void initComponents() {
        frame = new MainFrameController(appConfig);
        frame.setVisible(true);

     //   library = new LibraryController(frame);

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

    /*
    *Método que crea un objeto con la configuración de la APP
     */
    /**
     *
     */
    public void checkConfig() {
        configManager = new ConfigurationManager();
        appConfig = configManager.getConfig();
    }

  //  private void setListeners() {

       // frame.setListeners(this);
   //     library.setListeners(this);
     //   nav.addActionListener(this);

  //  }

    /**
     * TODO posible CLI
     */
   /* public void consoleBatchProcess() {
    }/*/

    /**
     *
     */
  /*  public void getBackgroundLibrary() {
        File folder = ManagerFiles.getBackgroundsFolder();
        cachedFilesList.clear();
        ManagerFiles.getFiles(folder, cachedFilesList);
        library.getBackgroundLibrary(cachedFilesList);
    }*/

    /**
     * Add a new wallpaper to a walpaperXML from a image selected from the
     * library
     */
  /*  private void newWallpaper(String selected) {
        if (selected == "") {
            new BackgroundException(new Exception(), "Se ha seleccionar al menos una imagen");
        } else {
            library.setVisible(false);
            XmlPanel.setVisible(true);
            XmlPanel.newWallpaper(selected);
            XmlPanel.setListener(this);
        }

    }*/

    /**
     * implementación de la interface ActionListener
     *
     * @param ae
     */
    
  /*  @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("click" + ae.getActionCommand());
        switch (ae.getActionCommand()) {
            case "ADD_LIBRARY":
                library.addToLibrary();
                break;

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
