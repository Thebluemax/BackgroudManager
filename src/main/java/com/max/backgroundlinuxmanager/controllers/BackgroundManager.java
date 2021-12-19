/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.max.backgroundlinuxmanager.controllers;

import com.max.backgroundlinuxmanager.controllers.utils.FileChooserController;
import com.max.backgroundlinuxmanager.views.components.ImageBlockPane;
import com.max.backgroundlinuxmanager.models.ConfigurationManager;
import com.max.backgroundlinuxmanager.models.XMLDOMBackground;
import com.max.backgroundlinuxmanager.models.XMLparse;
import com.max.backgroundlinuxmanager.models.entities.AppConfiguration;
import com.max.backgroundlinuxmanager.models.entities.Wallpaper;
import com.max.backgroundlinuxmanager.models.entities.WallpaperXML;
import com.max.backgroundlinuxmanager.utils.ManagerFiles;
import com.max.backgroundlinuxmanager.views.*;
import com.max.backgroundlinuxmanager.views.components.AddingToLibraryDialog;
import com.max.backgroundlinuxmanager.views.components.SidePanel;
import com.max.backgroundlinuxmanager.controllers.utils.DeleteOption;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.max.backgroundlinuxmanager.exceptions.BackgroundException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.*;

/**
 *
 * @author max
 */
public class BackgroundManager implements ActionListener {

    public final static String DELETE_ACTION = "DELETE";
    public final static String ADD_ACTION = "ADD";

    private ConfigurationManager configManager;
    private MainJFrame frame;// = new MainJFrame();
    private File[] resurces;
    private List<File> cachedFilesList;
    private WallpaperXML wallpaperXML;
    private List<Wallpaper> wpaperList;
    private File wallpaperFIle;
    private Wallpaper activeWallpaper;
    private XMLDOMBackground slideModel;
    private SlideEditPanelController editPanel;

    /**
     *
     */
    public void initApp()  {
        frame = new MainJFrame();
        frame.setVisible(true);
        cachedFilesList = new ArrayList();

        checkConfig();

        checkWallpapersXML();
        getBackgroundLibrary();
        setListeners();
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
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                int index = list.getSelectedIndex();
                String element = (String) list.getModel().getElementAt(index);

                System.out.println(".mouseClicked()");
                if (list.getName().compareTo(SidePanel.CHILD) == 0) {
                    String filename = wallpaperXML.getWallpapers().get(index).getFilename();
                    if (isSlide(filename)) {
                        
                        File f = new File(ManagerFiles.getUserFolder() + "/" + wallpaperXML.getWallpapers().get(index).getFilename());
                        slideModel = new XMLDOMBackground(f);
                        editPanel = new SlideEditPanelController( slideModel );
                        frame.setViewPortContainer(editPanel);
                       // SlideBackground sb = slideModel.getSlideBackground();
                      //  System.gc();
                        editPanel.setlist();
                        
                    } else {
                        frame.setViewPortContainer(new WallpaperPanel(wallpaperXML.getWallpapers().get(index)));
                    }
                    activeWallpaper = wallpaperXML.getWallpapers().get(index);
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

    /**
     *
     */
    public void consoleBatchProcess() {
    }

    private void buildWallpapers(String filename) {
        if (wallpaperXML == null) {

            wallpaperFIle = new File(ManagerFiles.getWallpapersFolder() + "/" + filename);
            XMLparse xmlParse = new XMLparse();
            try {
                wallpaperXML = xmlParse.unmarshallerWallpapers(new FileInputStream(wallpaperFIle));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BackgroundManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // System.out.println(wallpapers.getWallpapers().size()+"ggg");
        wpaperList = wallpaperXML.getWallpapers();
        String[] jPaneContent = new String[wpaperList.size()];
        // discoverSlide();
        for (int i = 0; i < wpaperList.size(); i++) {
            jPaneContent[i] = wpaperList.get(i).getName();

        }
        frame.setWallpaperList(jPaneContent);
        frame.setLibraryView(true);
    }

    /**
     *
     * @param files
     */
    public void saveFilesInBGFolder(File[] files) {

        AddingToLibraryDialog dialog = new AddingToLibraryDialog(frame);
        if (dialog.setList(files)) {
            refresBackgroundLibrary(files);
        }

    }

    /**
     * Obteniendo los archivos xml con las colecciones de wallpapers
     */
    public void checkWallpapersXML()  {
        File folder = ManagerFiles.getWallpapersFolder();
        cachedFilesList.clear();
        String[] filenames = new String[cachedFilesList.size() + 1];
        filenames[0] = SidePanel.LIBRARY_TAG;
        if (folder.isDirectory()) {
            ManagerFiles.getFiles(folder, cachedFilesList);
            //System.out.println(cachedFilesList.size());
            for (int i = 0; i < cachedFilesList.size(); i++) {
                filenames[i + 1] = cachedFilesList.get(i).getName();
            }
        } else {
            System.out.println("Error no directorio");
             new BackgroundException(new FileNotFoundException(),"El directorio de wallpaper del usurio no existe");
        }
        frame.setList(folder.getPath(), filenames);
    }

    /**
     * Check if the background file is a xml document
     *
     * @param filename
     * @return boolean
     */
    private boolean isSlide(String filename) {

        String name = filename;
        name = name.substring(name.length() - 3, name.length());
        return (name.compareTo("xml") == 0);
    }

    /**
     *
     */
    public void getBackgroundLibrary() {
        File folder = ManagerFiles.getBackgroundsFolder();
        cachedFilesList.clear();
        ManagerFiles.getFiles(folder, cachedFilesList);

        for (int i = 0; i < cachedFilesList.size(); i++) {

            checkForTypeFile(cachedFilesList.get(i));

        }

        frame.getAllImgeBlock().forEach(block -> {
            block.loadImage();
            block.setListener(this);
        });
    }

    private void refresBackgroundLibrary(File[] list) {

        for (int i = 0; i < list.length; i++) {

            checkForTypeFile(list[i]);
            cachedFilesList.add(list[i]);

        }
        List<ImageBlockPane> listImageBlock = frame.getAllImgeBlock();
        int init = listImageBlock.size() - list.length;
        for (int h = init - 1; h < listImageBlock.size(); h++) {
            listImageBlock.get(h).loadImage();

        }
    }

    /**
     * Add a new wallpaper to a walpaperXML from a image selected from the library
     */
    private void newWallpaper(String selected) {
        
        Wallpaper newWallpaper = new Wallpaper();
        newWallpaper.setFilename(ManagerFiles.FolderToObject() + selected);
      //  System.out.println(ManagerFiles.getBackgroundsPath() + "/" + selected);
      //  System.out.println(ManagerFiles.FolderToObject() + selected);


        newWallpaper.setName(selected);
        newWallpaper.setOptions(AppConfiguration.DEFAULT_OPTION);
        newWallpaper.setPcolor(AppConfiguration.DEFAULT_COLOR);
        newWallpaper.setScolor(AppConfiguration.DEFAULT_COLOR);
        newWallpaper.setShaderType(AppConfiguration.DEFAULT_SHADER_TYPE);
        try {
             wallpaperXML.add(newWallpaper);
        frame.setViewPortContainer(new WallpaperPanel(newWallpaper));
         int index = wallpaperXML.getWallpapers().indexOf(newWallpaper);
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
             new BackgroundException(e ,"No hay un archivo de background activo");
        }
       
         activeWallpaper = newWallpaper;
    }

    private void checkForTypeFile(File cachedFile) {

        if (!isSlide(cachedFile.getName())) {
            frame.addToPanel(new ImageBlockPane(cachedFile));
        } else {
            frame.addToPanel(new ImageBlockPane(new File("src/assets/slide.png")));
        }

    }

    /**
     * implementación de la interface ActionListener
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae){
        //System.out.println(ae.getActionCommand());
        switch (ae.getActionCommand()) {
            case "ADD_LIBRARY":
                FileChooserController fChooser = new FileChooserController(frame, true);
                File[] res = fChooser.getFiles();

                if (res != null) {
                    resurces = res;
                    saveFilesInBGFolder(resurces);

                } else {

                }
                break;
            case "NEW_WALLP":

                String selected = frame.getSelected();
              //  System.out.println(selected + "t");
                
                                    newWallpaper(selected);

                break;
            case "SAVE":
                XMLparse xmlParse = new XMLparse();
                 System.out.println(wallpaperFIle);
                xmlParse.saveXML(wallpaperFIle, XMLparse.BACKGROUNDS, wallpaperXML);
                break;
                
            case BackgroundManager.DELETE_ACTION:
                JButton block =(JButton) ae.getSource();
                ImageBlockPane blockImage = (ImageBlockPane) block.getParent();
                
                System.out.println(blockImage.getImage().getName());

                //JOptionPane.showConfirmDialog(null, "quiere borrar la entrada:" + activeWallpaper.getName(), "Eliminar Entrada", dialegButton);
               // if (dialegButton == JOptionPane.YES_OPTION) {
                DeleteOption option = new DeleteOption(blockImage);
                if(option.getResult()){
                    
                   cachedFilesList.remove(blockImage.getImage());
                   
                    frame.remove(blockImage);
                    
                }
                  // activeWallpaper = null;
              //  }
                break;

            case  BackgroundManager.ADD_ACTION:
                System.out.println("add");
                WallpaperXML newWallPaper = new WallpaperXML();
                File folder = ManagerFiles.getWallpapersFolder();
                if(!folder.exists()){
                    folder.mkdir();
                }
                xmlParse = new XMLparse();
                xmlParse.saveXML(new File (folder.getPath() + "test.xml"),XMLparse.WALLPAPER_XML,newWallPaper);
                break;
        }

    }
}
