/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.max.backgroundlinuxmanager.controllers.views;

import com.max.backgroundlinuxmanager.views.MainJFrame;
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
public class MainFrameController extends MainJFrame{
    
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
 //   private SidePanel sideBar;
 //   private NavComponent toolBar; 
 //   private List<ImageBlockPane>  imageList ;

    public MainFrameController() {
        super();
     imageList = new ArrayList<>();
      //  initComponents();
       // initSidebar();  
       // initNavBar();  
    }
     /**
     * 
     * Agregar un nuevo contenedor de miniaturas al viewport
     * @param jPane Contenedor con las miniaturas
     */
    public void setViewPortContainer (JPanel jPane){
        scrollContent.setViewportView(null);
       scrollContent.setViewportView(jPane);
    }
    /**
     * 
     * @param visibility 
     */
    public void setLibraryView(boolean visibility){
        sideBar.setLibraryView(visibility);
        toolBar.setVisibility(visibility);
        if (visibility) {
            setViewPortContainer(container);         
        } else {
                scrollContent.setViewportView(null);
        }
    }
    /**
     * Agrega un objeto minuatura al contenedor
     * @param jp 
     */
    public void addToPanel (JPanel jp){
        imageList.add((ImageBlockPane)jp);
        container.add(jp);
    }
    
    public void remove(ImageBlockPane pane){
        imageList.remove(pane);
        container.remove(pane);
        this.pack();
    
    }
    /**
     * return the path from the seledted item 
     * @return 
     */
    public String getSelected(){
        String r="";
        for (int i = 0; i < imageList.size(); i++) {
            if (imageList.get(i).isChecked()) {
             r =    imageList.get(i).getFilePath();
            }
        }
    return r;
    }
    public List<ImageBlockPane> getAllImgeBlock(){
        return imageList;
    }
    
    /**
     * agrega un nuevo elemento a la lista secundaria 
     * 
     * @param nameWallpaper 
     */
    public void setWallpaperList(String[] nameWallpaper){
       sideBar.setChildLidt(nameWallpaper);
     
    }
    
    public void setList(String wallpaperXML, String[] listFile){
        sideBar.setListFather(listFile);
       
        jLabel2.setText("Active Wallpaper: "+ wallpaperXML);
        
    }
    public void addSideBarEvents(MouseAdapter mouseAdapter){
        sideBar.addListMouseEvents(mouseAdapter);
    }
    
}
