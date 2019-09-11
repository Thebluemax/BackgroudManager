/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.max.backgroundlinuxmanager.controllers;

import com.max.backgroundlinuxmanager.models.ConfigurationManager;
import com.max.backgroundlinuxmanager.models.XMLparse;
import com.max.backgroundlinuxmanager.models.entities.Wallpaper;
import com.max.backgroundlinuxmanager.models.entities.Wallpapers;
import com.max.backgroundlinuxmanager.utils.ManagerFiles;
import com.max.backgroundlinuxmanager.views.*;
import com.max.backgroundlinuxmanager.views.components.SidePanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;

/**
 *
 * @author max
 */
public class BackgroundManager implements ActionListener {

    private ConfigurationManager configManager;
    private MainJFrame frame;// = new MainJFrame();
    private File[] resurces;
    private List<File> resurceList;
    private List<String> filenameList;
    private List<File> wallpaperList;
    private Wallpapers wallpapers;
    private List<Wallpaper> wpaperList;

    public void initApp() {
        frame = new MainJFrame();
        frame.setVisible(true);
        resurceList = new ArrayList();
        filenameList = new ArrayList();
        wallpaperList = new ArrayList();

        checkConfig();
        checkBackgroundFolder();
        checkWallpapers();
        setListeners();
    }

    private void setListeners() {
        frame.addSideBarEvents(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                int index = list.getSelectedIndex();
                String element = (String) list.getModel().getElementAt(index);
                if (list.getName().compareTo(SidePanel.CHILD) == 0) {
                    frame.setViewPortContainer(new WallpaperPanel(wallpapers.getWallpapers().get(index)));
                    
                } else {
                    System.out.println(element);
                    if (element.compareTo(SidePanel.LIBRARY_TAG) == 0) {
                        frame.setLibraryView(true);
                    } else {
                        buildWallpapers(element);
                        frame.setLibraryView(false);
                    }              
                }         
            }
        });
        frame.setListeners(this);
    }

    public void consoleBatchProcess() {
    }

    private void buildWallpapers(String filename) {
        File wallpaperFIle = new File(ManagerFiles.getWallpapersFolder() + "/" + filename);
        XMLparse xmlParse = new XMLparse();
        try {
            wallpapers = xmlParse.unmarshallerWallpapers(new FileInputStream(wallpaperFIle));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BackgroundManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        // System.out.println(wallpapers.getWallpapers().size()+"ggg");
        wpaperList = wallpapers.getWallpapers();
        String[] jPaneContent = new String[wpaperList.size()];
        for (int i = 0; i < wpaperList.size(); i++) {
            jPaneContent[i] = wpaperList.get(i).getName();

        }
        frame.setWallpaperList(jPaneContent);
        frame.setLibraryView(true);
    }

    public void saveFilesInBGFolder(File[] files) {
        for (int i = 0; i < files.length; i++) {
            String pathNew = ManagerFiles.getBackgroundsPath() + "/" + files[i].getName();
            try {
                ManagerFiles.copyFile(files[i], new File(pathNew));
            } catch (IOException ex) {
                Logger.getLogger(BackgroundManager.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    /**
     * Obteniendo los archivos xml con las colecciones de wallpapers
     */
    public void checkWallpapers() {
        File folder = ManagerFiles.getWallpapersFolder();
        resurceList.clear();
        if (folder.isDirectory()) {
            ManagerFiles.getFiles(folder, resurceList);
            System.out.println(resurceList.size());
            String[] filenames = new String[resurceList.size() + 1];
            filenames[0] = SidePanel.LIBRARY_TAG;
            for (int i = 0; i < resurceList.size(); i++) {
                filenames[i + 1] = resurceList.get(i).getName();
            }
            frame.setList(folder.getPath(), filenames);
        } else {
            System.out.println("Error no directorio");
        }
    }

    public void checkBackgroundFolder() {
        File folder = ManagerFiles.getBackgroundsFolder();
        resurceList.clear();
        ManagerFiles.getFiles(folder, resurceList);

        for (int i = 0; i < resurceList.size(); i++) {
            String ext = resurceList.get(i).getName();
            ext = ext.substring(ext.length() - 3, ext.length());
           //System.out.println(ext);
            if (ext.compareTo("xml") != 0) {
                ImageBlockPane p = new ImageBlockPane();
                p.setIcon(resurceList.get(i));
                frame.addToPanel(p);
            } else {
                ImageBlockPane p = new ImageBlockPane();
                
                p.setIcon(new File("src/assets/slide.png"));
                p.setLabel(resurceList.get(i).getName());
                frame.addToPanel(p);
            }
        }
    }

    public void getConfig() {
    }

    public void checkConfig() {
        configManager = new ConfigurationManager();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println(ae.getActionCommand());
        JFileChooser jFileChooser = new JFileChooser(ManagerFiles.getDefaultImagePath());
        jFileChooser.setMultiSelectionEnabled(true);
        jFileChooser.setApproveButtonText("Run Application");
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int chooseStatus = jFileChooser.showOpenDialog(frame);
        if (chooseStatus == JFileChooser.APPROVE_OPTION) {
            System.out.println("ww");
            resurces = jFileChooser.getSelectedFiles();
            saveFilesInBGFolder(resurces);
        } else {
        }

    }
}
