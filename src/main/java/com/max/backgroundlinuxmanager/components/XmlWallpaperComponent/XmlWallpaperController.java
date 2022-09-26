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

import com.max.backgroundlinuxmanager.components.MainFrame.MainFrameListener;
import com.max.backgroundlinuxmanager.exceptions.BackgroundException;
import com.max.backgroundlinuxmanager.models.entities.AppConfiguration;
import com.max.backgroundlinuxmanager.models.entities.Wallpaper;
import com.max.backgroundlinuxmanager.models.entities.WallpaperXML;
import com.max.backgroundlinuxmanager.utils.ManagerFiles;
import com.max.backgroundlinuxmanager.utils.XMLparse;
import com.max.backgroundlinuxmanager.views.components.WallpaperPanel;
import com.max.backgroundlinuxmanager.views.components.WallpaperView;
import com.max.backgroundlinuxmanager.components.XmlWallpaperComponent.XmlWallpaperPanel;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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
    private JPanel wPanel;
    private AppConfiguration appConf;
    public static String EMPTY_STRING = "";
    private XmlPanelNav navComponent;

    public XmlWallpaperController(AppConfiguration appConf, int width, int heigth) {
        super(width, heigth);
        navComponent = new XmlPanelNav();
        this.appConf = appConf;
        cachedFilesList = new ArrayList();
        checkWallpapersXML();
        add(navComponent, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, width, 30));
        navComponent.setAddListener(new XmlWallpaperListener(this));

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
        buildWallpapers(activeWallpaperName);
    }

    /**
     *
     * @param filename String
     * @return void
     */
    private void buildWallpapers(String filename) {
        wallpaperXMLFIle = new File(ManagerFiles.getWallpapersXMLFolder() + "/" + filename);
        wallpaperXML = WallpaperXML.factory(wallpaperXMLFIle);
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
        wPanel = new WallpaperPanel(newWallpaper);
        addToPanel( wPanel, 0, 0, getWidth(), getHeight());
        WallpaperPanel p = (WallpaperPanel) wPanel;
        p.loadImage();
        p.setListeners(new XmlWallpaperListener(this));
    }

    /**
     *
     * @return boolean
     */
    public boolean saveCurrent() {
        WallpaperPanel p = (WallpaperPanel) wPanel;
        Wallpaper wp = p.getWallpaper();
        try {
            wallpaperXML.add(wp);
        } catch (Exception e) {
            new BackgroundException(e, "No se ha seleccionado un walpaper");
        }
        XMLparse xmlParse = new XMLparse();
        int status = xmlParse.saveXML(wallpaperXMLFIle, XMLparse.WALLPAPER_XML, wallpaperXML);
        this.panel.removeAll();
        //panel = null;
        if (status == 0) {

        }
        return false;
    }

    /**
     * @return void
     */
    public void showWallpaper() {
        wPanel = new WallpaperView(wpaperList, wallpaperXML);
        System.out.println(getWidth() + "-" + (getHeight()));
        addToPanel(wPanel, 0, 0, getWidth(), getHeight());
        WallpaperView p = (WallpaperView) wPanel;
        p.buildList();
    }

    /**
     * @return void
     */
    public void clear() {
        if (wPanel != null) {
            panel.remove(wPanel);
            wPanel = null;
        }
    }

    /**
     *
     * @param listener
     */
    public void setCloseListener(MainFrameListener listener) {
         navComponent.setCloseListener(listener);
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
