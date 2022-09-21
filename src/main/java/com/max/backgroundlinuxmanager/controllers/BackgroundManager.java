/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.max.backgroundlinuxmanager.controllers;

import com.max.backgroundlinuxmanager.components.MainFrame.MainFrameController;
import com.max.backgroundlinuxmanager.models.entities.AppConfiguration;

/**
 *
 * @author max
 */
public class BackgroundManager  {


    private ConfigurationManager configManager;
    private MainFrameController frame;
    private AppConfiguration appConfig;

    /**
     * Initi app
     */
    public void initApp() {
        initComponents();
        loadConfig();
    }
/**
 * Inicio del componente principal
 */
    private void initComponents() {
        frame = new MainFrameController(appConfig);
        frame.setVisible(true);
    }

    /**
     *   Carga de la configuración
     **/
    public void loadConfig() {
        configManager = new ConfigurationManager();
        appConfig = configManager.getConfig();
    }

   

    /**
     * implementación de la interface ActionListener
     *
     * @param ae
     */
    
  /*  @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("click" + ae.getActionCommand());
        switch (ae.getActionCommand()) {

            case BackgroundManager.WALLPAPER:
                library.setVisible(false);
                XmlPanel.setVisible(true);
                XmlPanel.seeWallpaper();
                XmlPanel.setListener(this);

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
