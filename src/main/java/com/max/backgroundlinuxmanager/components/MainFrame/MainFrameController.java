/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.max.backgroundlinuxmanager.components.MainFrame;

import com.max.backgroundlinuxmanager.components.Library.LibraryComponent;
import com.max.backgroundlinuxmanager.components.XmlWallpaperComponent.XmlWallpaperController;
import com.max.backgroundlinuxmanager.exceptions.BackgroundException;
import com.max.backgroundlinuxmanager.models.entities.AppConfiguration;
import com.max.backgroundlinuxmanager.views.components.NavComponent;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;

/**
 *
 * @author max
 */
public class MainFrameController extends MainJFrame {

   
//    private File[] fileList;
//    private DefaultListModel collectionName;
    //   private DefaultListModel wallpaperName;
    private LibraryComponent library;
    private XmlWallpaperController xmlWallpaper;
    private NavComponent nav;
    private AppConfiguration appConfig;
   

    public MainFrameController(AppConfiguration appConfig) {
        super();
        this.appConfig = appConfig;
        init();
    }

    private void init() {
        library = new LibraryComponent(appConfig);
        xmlWallpaper = new XmlWallpaperController(appConfig,  getWidth(), getHeight());
        nav = new NavComponent();
           System.out.println( getHeight() +"++"+ getWidth());
        addToMain(library, 0, 0, getWidth(), getHeight() - 100);
        addToMain(nav, 0, getHeight() - 100, getWidth(), 100);
        addToMain(xmlWallpaper, 0, 0, getWidth(), getHeight());
        nav.addActionListener(new MainFrameListener(this));
        xmlWallpaper.setCloseListener(new MainFrameListener(this));
    }

    /**
     * Hide the library panel
     *
     * @param status boolean
     */
    public void showLibrary(boolean status) {
        library.setVisible(status);
    }

    public void showXmlWallpaper(boolean status) {
        xmlWallpaper.setVisible(status);
    }

    /**
     * return the file path in the seledted library item
     *
     * @return
     */
    public String getSelected() {
        return library.getSelected();
    }

    /**
     * Add a new wallpaper to a walpaperXML from a image selected from the
     * library
     */
    public void newWallpaper() {
        String selected = library.getSelected();
        if (selected == "") {
            new BackgroundException(new Exception(), "Se ha seleccionar al menos una imagen");
        } else {
            library.setVisible(false);
            xmlWallpaper.setVisible(true);
            xmlWallpaper.newWallpaper(selected);
            // xmlWallpaper.setListener(this);
        }

    }

    public void closeXmlWallpaper() {
        xmlWallpaper.clear();
        library.setVisible(true);
        xmlWallpaper.setVisible(false);
    }
    /**
     *
     * @param visibility
     */
    /**
     * public void setLibraryView(boolean visibility) {
     *
     * if (visibility) { setViewPortContainer(container); } else { //
     * scrollContent.setViewportView(null); }
    }*
     */
    /**
     * return the path from the seledted item
     *
     * @return
     */
    /**
     * public String getSelected() { String r = ""; for (int i = 0; i <
     * imageList.size(); i++) { if (imageList.get(i).isChecked()) { r =
     * imageList.get(i).getFilePath(); } } return r;
    }*
     */

    /*public List<ImageBlockPane> getAllImgeBlock() {
        return imageList;
    }*/
    /**
     * agrega un nuevo elemento a la lista secundaria
     *
     * @param nameWallpaper
     */
    /**
     * public void setWallpaperList(String[] nameWallpaper) {
     * sideBar.setChildLidt(nameWallpaper);
     *
     * }
     *
     * public void setList(String wallpaperXML, String[] listFile) {
     * sideBar.setListFather(listFile);
     *
     * }
     *
     * public void addSideBarEvents(MouseAdapter mouseAdapter) {
     * sideBar.addListMouseEvents(mouseAdapter);
    }*
     */
}
