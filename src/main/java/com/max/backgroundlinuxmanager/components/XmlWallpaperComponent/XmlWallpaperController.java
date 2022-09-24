/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.max.backgroundlinuxmanager.components.XmlWallpaperComponent;

import com.max.backgroundlinuxmanager.components.MainFrame.MainFrameListener;
import com.max.backgroundlinuxmanager.controllers.BackgroundManager;
import com.max.backgroundlinuxmanager.exceptions.BackgroundException;
import com.max.backgroundlinuxmanager.models.entities.AppConfiguration;
import com.max.backgroundlinuxmanager.models.entities.Wallpaper;
import com.max.backgroundlinuxmanager.models.entities.WallpaperXML;
import com.max.backgroundlinuxmanager.utils.ManagerFiles;
import com.max.backgroundlinuxmanager.utils.XMLparse;
import com.max.backgroundlinuxmanager.views.components.WallpaperPanel;
import com.max.backgroundlinuxmanager.views.components.WallpaperView;
import com.max.backgroundlinuxmanager.components.XmlWallpaperComponent.XmlWallpaperPanel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author max
 */
public class XmlWallpaperController extends XmlWallpaperPanel {

    private List<File> cachedFilesList;
    private WallpaperXML wallpaperXML;
    private File wallpaperXMLFIle;
    private List<Wallpaper> wpaperList;
    private String activeWallpaperName;
    private JPanel panel;
    private AppConfiguration appConf;
    public static String EMPTY_STRING = "";
    private XmlPanelNav navComponent;

    public XmlWallpaperController(AppConfiguration appConf, int width, int heigth) {
        super(width, heigth);
        navComponent = new XmlPanelNav();
        this.appConf = appConf;
        cachedFilesList = new ArrayList();
        checkWallpapersXML();
        add(navComponent, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, width, 50));
        // wallpaperCombo.addItemListener(this);

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
            filenames = new String[cachedFilesList.size()];
            // filenames[0] = EMPTY_STRING;

            for (int i = 0; i < cachedFilesList.size(); i++) {
                filenames[i] = cachedFilesList.get(i).getName();
            }
            poblateCombo(filenames);

        } else {
            filenames = new String[1];
            System.out.println("Error no directorio");
            new BackgroundException(new FileNotFoundException(), "El directorio de wallpaper del usurio no existe");
        }
    }

    /**
     *
     * @param filemanes Strimg[]
     * @return void
     */
    private void poblateCombo(String[] filemanes) {

       activeWallpaperName = navComponent.poblateCombo(filemanes);

        //   (String) wallpaperCombo.getItemAt(0);
        buildWallpapers(activeWallpaperName);
    }

    /**
     *
     * @param filename String
     * @return void
     */
    private void buildWallpapers(String filename) {
        if (true) {
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

    /**
     *
     * @param selected String
     * @return void
     */
    public void newWallpaper(String selected) {
        System.out.println(getWidth() + "--" + getHeight());
        Wallpaper newWallpaper = Wallpaper.factory(selected);
        panel = new WallpaperPanel(newWallpaper);
        addToPanel(panel, 0, 50, getWidth(), getHeight() - 50);
        WallpaperPanel p = (WallpaperPanel) panel;
        p.loadImage();

    }

    /**
     *
     * @return boolean
     */
    public boolean saveCurrent() {
        WallpaperPanel p = (WallpaperPanel) panel;

        Wallpaper wp = p.getWallpaper();
        try {
            wallpaperXML.add(wp);
        } catch (Exception e) {
            new BackgroundException(e, "No se ha seleccionado un walpaper");
        }
        XMLparse xmlParse = new XMLparse();

        int status = xmlParse.saveXML(wallpaperXMLFIle, XMLparse.WALLPAPER_XML, wallpaperXML);

        remove(panel);
        panel = null;
        if (status == 0) {

        }
        return false;
    }

    /**
     * @return void
     */
    public void seeWallpaper() {
        panel = new WallpaperView(wpaperList, wallpaperXML);
        System.out.println(getWidth() + "-" + (getHeight() - 300));
        addToPanel(panel, 0, 50, getWidth(), getHeight() - 60);
        //panel.setPreferredSize(new Dimension(getWidth(),  getHeight() - 300));
        WallpaperView p = (WallpaperView) panel;
        p.buildList();
    }

    /**
     * @return void
     */
    public void clear() {
        if (panel != null) {
            remove(panel);
            panel = null;
        }

    }

    /**
     *
     * @param listener
     */
    public void setCloseListener(MainFrameListener listener) {
        //  cancellWallpaperBtn.setActionCommand(MainFrameListener.CLOSE_XML_WALLPAPER);
        //  cancellWallpaperBtn.addActionListener(listener);
    }
    /**
     *
     * @param e ItemEvent
     *
     * @Override public void itemStateChanged(ItemEvent e) {
     * System.out.println("Selected" + e.getStateChange()); if (e.getSource() ==
     * wallpaperCombo && e.getStateChange() == 1) { buildWallpapers((String)
     * wallpaperCombo.getSelectedItem()); clear(); seeWallpaper();
     * System.out.println((String) wallpaperCombo.getSelectedItem()); //
     * buildWallpapers(String filename) }
     *
     * }
     */

}
