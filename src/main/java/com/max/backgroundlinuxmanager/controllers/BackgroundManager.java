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
import com.max.backgroundlinuxmanager.controllers.views.LibraryController;
import com.max.backgroundlinuxmanager.models.entities.Wallpaper;
import com.max.backgroundlinuxmanager.models.entities.WallpaperXML;
import com.max.backgroundlinuxmanager.models.XMLDOMBackground;
import com.max.backgroundlinuxmanager.controllers.views.MainFrameController;
import com.max.backgroundlinuxmanager.views.components.ImageBlockPane;
import com.max.backgroundlinuxmanager.controllers.views.XmlWallpaperController;
import com.max.backgroundlinuxmanager.views.components.SidePanel;
import com.max.backgroundlinuxmanager.utils.ManagerFiles;
import com.max.backgroundlinuxmanager.exceptions.BackgroundException;
import com.max.backgroundlinuxmanager.views.components.NavComponent;
import com.max.backgroundlinuxmanager.views.components.WallpaperPanel;

/**
 *
 * @author max
 */
public class BackgroundManager implements ActionListener {

    public final static String DELETE_ACTION = "DELETE";
    public final static String ADD_ACTION = "ADD";
    public final static String SAVE_NEW_WALLPAPER = "SAVE_NEW_WALLPAPER";
    public final static String NEW_WALLPAPER = "NEW_WALLPAPER";
    public final static String CANCEL = "CANCEL";
    private ConfigurationManager configManager;
    private MainFrameController frame;// = new MainJFrame();
    private List<File> cachedFilesList;
    private WallpaperXML wallpaperXML;
    private LibraryController library;
    private XmlWallpaperController XmlPanel;
    private NavComponent nav;

    /**
     *
     */
    public void initApp() {
        cachedFilesList = new ArrayList();
        initComponents();
        checkConfig();
        getBackgroundLibrary();
    }

    private void initComponents() {
        frame = new MainFrameController();
        frame.setVisible(true);

        library = new LibraryController(frame);
        library.setListeners(this);
        frame.addToMain(library, 5, 5, frame.getWidth() - 10, frame.getHeight() - 100);
        library.inti();
        XmlPanel = new XmlWallpaperController();
        frame.addToMain(XmlPanel, 5, 5, frame.getWidth() - 10, frame.getHeight() - 100);
        XmlPanel.setVisible(false);

        nav = new NavComponent();
        nav.addActionListener(this);
        frame.addToMain(nav, 5, frame.getContentPane().getHeight() - 100, frame.getContentPane().getWidth() - 5, 100);

    }

    /*
    *Método que crea un objeto con la configuración de la APP
     */
    /**
     *
     */
    public void checkConfig() {
        configManager = new ConfigurationManager();
        System.out.println(configManager.toString());
    }

    private void setListeners() {
        frame.addSideBarEvents(new MouseAdapter() {
            String element = "";

            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                int index = list.getSelectedIndex();
                if (index != -1) {
                    element = (String) list.getModel().getElementAt(index);
                }

                System.out.println(".mouseClicked()");
                if (list.getName().compareTo(SidePanel.CHILD) == 0) {
                    if (element.isBlank()) {
                        new BackgroundException(new NullPointerException(), "The Collection is empty");
                    }

                    System.out.println(".mouseClicked()");
                    String filename = wallpaperXML.getWallpapers().get(index).getFilename();
                    if (library.isSlide(filename)) {

                        File f = new File(ManagerFiles.getUserFolder() + "/" + wallpaperXML.getWallpapers().get(index).getFilename());
                        //    editPanel = new SlideEditPanelController(slideModel);
                        //  frame.setViewPortContainer(editPanel);
                        // SlideBackground sb = slideModel.getSlideBackground();
                        //  System.gc();
                        //     editPanel.setlist();

                    } else {

                        frame.setViewPortContainer(new WallpaperPanel(wallpaperXML.getWallpapers().get(index)));
                    }
                } else {
                    System.out.println(".mouseClicked()-paper");

                    System.out.println(element);
                    if (element.compareTo(SidePanel.LIBRARY_TAG) == 0) {
                        frame.setLibraryView(true);
                    } else {
                        //buildWallpapers(element);
                        frame.setLibraryView(false);
                    }
                }
            }
        });
        frame.setListeners(this);
    }

    /**
     * TODO posible CLI
     */
    public void consoleBatchProcess() {
    }

    /**
     *
     */
    public void getBackgroundLibrary() {
        File folder = ManagerFiles.getBackgroundsFolder();
        cachedFilesList.clear();
        ManagerFiles.getFiles(folder, cachedFilesList);
        library.getBackgroundLibrary(cachedFilesList);
    }

    /**
     * Add a new wallpaper to a walpaperXML from a image selected from the
     * library
     */
    private void newWallpaper(String selected) {
        if (selected == "") {
            new BackgroundException(new Exception(), "Se ha seleccionar al menos una imagen");
        } else {
            library.setVisible(false);
            XmlPanel.setVisible(true);
            XmlPanel.newWallpaper(selected);
            XmlPanel.setListener(this);
        }

    }

    /**
     * implementación de la interface ActionListener
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        //System.out.println(ae.getActionCommand());
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
             */
        }

    }
}
