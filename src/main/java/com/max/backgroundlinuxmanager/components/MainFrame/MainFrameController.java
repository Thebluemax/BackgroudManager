/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.max.backgroundlinuxmanager.components.MainFrame;

import com.max.backgroundlinuxmanager.components.Library.LibraryController;
import com.max.backgroundlinuxmanager.controllers.views.XmlWallpaperController;
import com.max.backgroundlinuxmanager.models.entities.AppConfiguration;
import com.max.backgroundlinuxmanager.views.components.ImageBlockPane;
import com.max.backgroundlinuxmanager.views.components.NavComponent;
import com.max.backgroundlinuxmanager.views.components.SidePanel;
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

    /**
     * Creates new form MainJFrame
     */
    //  private static String ADD_TO_LIBRARY = "addToLibrary";
    //   private static String GET_WALLPAPER = "getWallPaper";
    //  private static String LIBRARY_TAG ="Libreria"; 
    //   private JPanel container;
//    private File[] fileList;
//    private DefaultListModel collectionName;
    //   private DefaultListModel wallpaperName;
    private LibraryController library;
    private XmlWallpaperController xmlWallpaper;
    private NavComponent nav;
    private AppConfiguration appConfig;
    //   private List<ImageBlockPane>  imageList ;

    public MainFrameController(AppConfiguration appConfig) {
        super();
        this.appConfig = appConfig;
        init();
        // initSidebar();  
        // initNavBar();  
    }

    private void init() {
        library = new LibraryController(appConfig);
        xmlWallpaper = new XmlWallpaperController(appConfig);
        nav = new NavComponent();
        
        addToMain(library, 0, 0, getWidth(), getHeight() - 100);
        addToMain(nav, 0, getHeight() - 100, getWidth(), 100);
       // addToMain(xmlWallpaper, 0, 0, getWidth(), getHeight());
    }
    /**
     * Hide the library panel
     * @param status boolean
     */
    public void showLibrary(boolean status){
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
     *
     * Agregar un nuevo contenedor de miniaturas al viewport
     *
     * @param jPane Contenedor con las miniaturas
     */
    /**public void setViewPortContainer(JPanel jPane) {
        //   scrollContent.setViewportView(null);
        //  scrollContent.setViewportView(jPane);
    }**/

    /**
     *
     * @param visibility
     */
  /**  public void setLibraryView(boolean visibility) {
       
        if (visibility) {
            setViewPortContainer(container);
        } else {
            //    scrollContent.setViewportView(null);
        }
    }**/

    /**
     * return the path from the seledted item
     *
     * @return
     */
    /**public String getSelected() {
        String r = "";
        for (int i = 0; i < imageList.size(); i++) {
            if (imageList.get(i).isChecked()) {
                r = imageList.get(i).getFilePath();
            }
        }
        return r;
    }**/

    /*public List<ImageBlockPane> getAllImgeBlock() {
        return imageList;
    }*/

    /**
     * agrega un nuevo elemento a la lista secundaria
     *
     * @param nameWallpaper
     */
  /**  public void setWallpaperList(String[] nameWallpaper) {
        sideBar.setChildLidt(nameWallpaper);

    }

    public void setList(String wallpaperXML, String[] listFile) {
        sideBar.setListFather(listFile);

    }

    public void addSideBarEvents(MouseAdapter mouseAdapter) {
        sideBar.addListMouseEvents(mouseAdapter);
    }**/

}
