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
public class BackgroundManager implements ActionListener{
    private ConfigurationManager configManager;
    private MainJFrame frame;// = new MainJFrame();
    private File [] resurces;
    private List<File> resurceList;
    private List<String> filenameList;
    private List<File> wallpaperList;
    private Wallpapers wallpapers;
    private List<Wallpaper> wpaperList;

    public void initApp () {
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
    private void setListeners(){
    frame.getList().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                int index = -1;
                if (evt.getClickCount() == 2) {
                    // Double-click detected
                   index = list.locationToIndex(evt.getPoint());
                   String t = frame.getListElement(index);
                   switch(t){
                       case "Libreria":
                           frame.setLibraryView(true);
                           break;
                       default:
                             frame.setLibraryView(false);
                             buildWallpapers(t);
                           break;
                   }
                } else if (evt.getClickCount() == 3) {

                    // Triple-click detected
                   index = list.locationToIndex(evt.getPoint());
                }
                if (index >=0) {
                   //System.out.println(frame.getListElement(index));
                   
                }
                
            }
        });
        frame.getWallpaperList().addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent evt) {
             JList list = (JList)evt.getSource();
                int index = -1;
            //super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
            if (evt.getClickCount() == 2) {
                    // Double-click detected
                    
                   index = list.locationToIndex(evt.getPoint());
                   String t = frame.getWallpaperName(index);
                  Wallpaper wp = wallpapers.getWallpapers().get(index);
                  if (wp.getName().compareTo(t) == 0) {
                      JpaneWallpaper jpw = new JpaneWallpaper(wp);
                    frame.setViewPortContainer(jpw);
                }
                } else if (evt.getClickCount() == 3) {

                    // Triple-click detected
                 // index = list.locationToIndex(evt.getPoint());
                }
               // if (index >=0) {
                   //System.out.println(frame.getListElement(index));
                   
               // }
        }
            
        
        });
        frame.setListeners(this);
    }
    public void consoleBatchProcess(){}
    
    private void buildWallpapers(String filename){
        //System.out.println(ManagerFiles.getWallpapersFolder()+"/"+filename);
        File wallpaperFIle = new File(ManagerFiles.getWallpapersFolder()+"/"+filename);
        XMLparse xmlParse = new XMLparse();
        try {
            wallpapers = xmlParse.unmarshallerWallpapers(new FileInputStream(wallpaperFIle));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BackgroundManager.class.getName()).log(Level.SEVERE, null, ex);
        }
       // System.out.println(wallpapers.getWallpapers().size()+"ggg");
        wpaperList =  wallpapers.getWallpapers();
        String[] jPaneContent = new String[wpaperList.size()];
        for (int i = 0; i <wpaperList.size(); i++) {
           jPaneContent[i] = wpaperList.get(i).getName();
           
        }
        frame.setWallpaperList(jPaneContent);
    }
    public void saveFilesInBGFolder(File[] files){
        for (int i = 0; i < files.length; i++) {
            String pathNew = ManagerFiles.getBackgroundsPath() +"/"+ files[i].getName();
            try {
                ManagerFiles.copyFile(files[i], new File(pathNew));
            } catch (IOException ex) {
                Logger.getLogger(BackgroundManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    public void checkWallpapers(){
    File folder = ManagerFiles.getWallpapersFolder();
    resurceList.clear();
        ManagerFiles.getFiles(folder, resurceList);
        // File folder = new File (h);//ManagerProperties.getImageFolder();
        filenameList.add("Libreria");
        for (int i = 0; i < resurceList.size(); i++) {
            filenameList.add(resurceList.get(i).getName());
        }
           if (folder.isDirectory()) {
            frame.setList(folder.getPath(), filenameList.toArray(new String[filenameList.size()]));       
        } else {
           //frame.setList(folder.list());
            System.out.println(folder.exists()+"--"+folder.getName()); 
        }
    }
    public void checkBackgroundFolder(){
        File folder = ManagerFiles.getBackgroundsFolder();
        ManagerFiles.getFiles(folder, resurceList);
              
           for (int i = 0; i < resurceList.size(); i++) {
            String ext = resurceList.get(i).getName();
            ext = ext.substring(ext.length()-3, ext.length());
            //System.out.println(ext);
            if (ext.compareTo("xml") != 0) {
                NewJPanel p = new NewJPanel();
                p.setIcon(resurceList.get(i));
                frame.addToPanel(p);
            }
        }
    }
    public void getConfig(){}
    public void checkConfig(){
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
                    resurces  = jFileChooser.getSelectedFiles();
                    saveFilesInBGFolder(resurces);
                } else {
                }
                
    }
}
