/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.max.backgroundlinuxmanager.controllers.views;

import com.max.backgroundlinuxmanager.controllers.BackgroundManager;
import com.max.backgroundlinuxmanager.exceptions.BackgroundException;
import com.max.backgroundlinuxmanager.models.entities.Wallpaper;
import com.max.backgroundlinuxmanager.models.entities.WallpaperXML;
import com.max.backgroundlinuxmanager.utils.ManagerFiles;
import com.max.backgroundlinuxmanager.utils.XMLparse;
import com.max.backgroundlinuxmanager.views.components.SidePanel;
import com.max.backgroundlinuxmanager.views.components.WallpaperPanel;
import com.max.backgroundlinuxmanager.views.components.XmlWallpaperPanel;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author max
 */
public class XmlWallpaperController extends XmlWallpaperPanel implements ItemListener {

    private List<File> cachedFilesList;
    private WallpaperXML wallpaperXML;
    private File wallpaperXMLFIle;
    private List<Wallpaper> wpaperList;
    private String activeWallpaperName;
    private WallpaperPanel panel;
    public static String EMPTY_STRING = "";

    public XmlWallpaperController() {
        cachedFilesList = new ArrayList();
        checkWallpapersXML();
        wallpaperCombo.addItemListener(this);
    }

    /**
     * Obteniendo los archivos xml con las colecciones de wallpapers
     */
    public void checkWallpapersXML() {
        File folder = ManagerFiles.getWallpapersXMLFolder();
        cachedFilesList.clear();
        String[] filenames = null;

        if (folder.isDirectory()) {
            ManagerFiles.getFiles(folder, cachedFilesList);
            filenames = new String[cachedFilesList.size() + 1];
            filenames[0] = EMPTY_STRING;

            for (int i = 0; i < cachedFilesList.size(); i++) {
                filenames[i + 1] = cachedFilesList.get(i).getName();
            }
            poblateCombo(filenames);

        } else {
            filenames = new String[1];
            System.out.println("Error no directorio");
            new BackgroundException(new FileNotFoundException(), "El directorio de wallpaper del usurio no existe");
        }
    }

    private void poblateCombo(String[] filemanes) {
        for (int i = 0; i < filemanes.length; i++) {
            wallpaperCombo.addItem(filemanes[i]);
        }
    }

    private void buildWallpapers(String filename) {
        if (wallpaperXML == null) {
            wallpaperXMLFIle = new File(ManagerFiles.getWallpapersXMLFolder() + "/" + filename);
            XMLparse xmlParse = new XMLparse();
            try {
                wallpaperXML = xmlParse.unmarshallerWallpapers(new FileInputStream(wallpaperXMLFIle));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BackgroundManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        wpaperList = wallpaperXML.getWallpapers();
        activeWallpaperName = filename;

    }

    public void newWallpaper(String selected) {

        Wallpaper newWallpaper = Wallpaper.factory(selected);
        panel = new WallpaperPanel(newWallpaper);
        addToPanel(panel, 0, 50, getWidth(), getHeight() - 300);
        panel.loadImage();

    }

    public boolean saveCurrent() {
        Wallpaper wp = panel.getWallpaper();
        try{
        wallpaperXML.add(wp);
        }catch(Exception e){
            new BackgroundException(e, "No se ha seleccionado un walpaper");
        }
        XMLparse xmlParse = new XMLparse();
        
        int status = xmlParse.saveXML(wallpaperXMLFIle, XMLparse.WALLPAPER_XML, wallpaperXML);
        if (status == 0) {
            // checkWallpapersXML();
        }
        return false;
    }
    public void setListener(ActionListener listener){
        panel.setListeners(listener);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        System.out.println("Selected" + e.getStateChange());
        if (e.getSource() == wallpaperCombo && e.getStateChange() == 1) {
            buildWallpapers((String) wallpaperCombo.getSelectedItem());
            System.out.println((String) wallpaperCombo.getSelectedItem());
        }

    }

}
