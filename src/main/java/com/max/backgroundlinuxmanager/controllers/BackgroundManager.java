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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.swing.*;
import com.max.backgroundlinuxmanager.controllers.utils.FileChooserController;
import com.max.backgroundlinuxmanager.controllers.utils.XMLparse;
import com.max.backgroundlinuxmanager.controllers.utils.DeleteOption;
import com.max.backgroundlinuxmanager.models.entities.FrameBackground;
import com.max.backgroundlinuxmanager.models.entities.SlideBackground;
import com.max.backgroundlinuxmanager.models.entities.Wallpaper;
import com.max.backgroundlinuxmanager.models.entities.WallpaperXML;
import com.max.backgroundlinuxmanager.models.entities.AppConfiguration;
import com.max.backgroundlinuxmanager.models.XMLDOMBackground;
import com.max.backgroundlinuxmanager.controllers.views.MainFrameController;
import com.max.backgroundlinuxmanager.views.components.ImageBlockPane;
import com.max.backgroundlinuxmanager.views.components.AddingToLibraryDialog;
import com.max.backgroundlinuxmanager.views.components.SidePanel;
import com.max.backgroundlinuxmanager.utils.ManagerFiles;
import com.max.backgroundlinuxmanager.exceptions.BackgroundException;
import com.max.backgroundlinuxmanager.views.WallpaperPanel;
import java.awt.Color;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author max
 */
public class BackgroundManager implements ActionListener {

    public final static String DELETE_ACTION = "DELETE";
    public final static String ADD_ACTION = "ADD";

    private ConfigurationManager configManager;
    private MainFrameController frame;// = new MainJFrame();
    private File[] resurces;
    private List<File> cachedFilesList;
    private WallpaperXML wallpaperXML;
    private List<Wallpaper> wpaperList;
    private File wallpaperXMLFIle;
    private Wallpaper activeWallpaper;
    private XMLDOMBackground slideModel;
    private SlideEditPanelController editPanel;
    private String activeWallpaperName;

    /**
     *
     */
    public void initApp()  {
        frame = new MainJFrame();
        frame.setVisible(true);
        cachedFilesList = new ArrayList();
        activeWallpaperName = "";

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
            String element = "";
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                int index = list.getSelectedIndex();
                if (index != -1) {
                    element = (String) list.getModel().getElementAt(index);
                }

                System.out.println(".mouseClicked()");
                if (list.getName().compareTo(SidePanel.CHILD) == 0) {
                    if(element.isBlank())
                         new BackgroundException(new NullPointerException(),"The Collection is empty" ); 
                    
                    System.out.println(".mouseClicked()");
                    String filename = wallpapers.getWallpapers().get(index).getFilename();
                    if (isSlide(filename)) {

                        File f = new File(ManagerFiles.getUserFolder() + "/" + wallpapers.getWallpapers().get(index).getFilename());
                        slideModel = new XMLDOMBackground(f);
                        editPanel = new SlideEditPanelController(slideModel);
                        frame.setViewPortContainer(editPanel);
                        // SlideBackground sb = slideModel.getSlideBackground();
                        //  System.gc();
                        editPanel.setlist();

                    } else {
                        
                        frame.setViewPortContainer(new WallpaperPanel(wallpapers.getWallpapers().get(index)));
                    }
                    activeWallpaper = wallpaperXML.getWallpapers().get(index);
                } else {
                    System.out.println(".mouseClicked()-paper");

                    System.out.println(element);
                    if (element.compareTo(SidePanel.LIBRARY_TAG) == 0) {
                        frame.setLibraryView(true);
                    } else {
                        activeWallpaperName = element;
                        buildWallpapers(element);
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

    private void buildWallpapers(String filename) {
        if (wallpaperXML == null) {

            wallpaperXMLFIle = new File(ManagerFiles.getWallpapersXMLFolder() + "/" + filename);
            XMLparse xmlParse = new XMLparse();
            try {
                wallpapers = xmlParse.unmarshallerWallpapers(new FileInputStream(wallpaperXMLFIle));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BackgroundManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         System.out.println(wallpapers.getWallpapers().size()+"ggg");
        wpaperList = wallpapers.getWallpapers();
        activeWallpaperName = filename;
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
    public void checkWallpapersXML() {
        File folder = ManagerFiles.getWallpapersXMLFolder();
        cachedFilesList.clear();
        String[] filenames = null;

        if (folder.isDirectory()) {
            ManagerFiles.getFiles(folder, cachedFilesList);
            filenames = new String[cachedFilesList.size() + 1];
            filenames[0] = SidePanel.LIBRARY_TAG;

            System.out.println(cachedFilesList.size() + "");

            for (int i = 0; i < cachedFilesList.size(); i++) {
                filenames[i + 1] = cachedFilesList.get(i).getName();
            }
        } else {
            filenames = new String[1];
            filenames[0] = SidePanel.LIBRARY_TAG;
            System.out.println("Error no directorio");
            new BackgroundException(new FileNotFoundException(), "El directorio de wallpaper del usurio no existe");
        }

        frame.setList(activeWallpaperName, filenames);
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
        File folder = ManagerFiles.getBackgroundsFolder(true);
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
        newWallpaper.setFilename(selected);
        //  System.out.println(ManagerFiles.getBackgroundsPath() + "/" + selected);
        //  System.out.println(ManagerFiles.folderToObject() + selected);

        newWallpaper.setName(selected);
        newWallpaper.setOptions(AppConfiguration.DEFAULT_OPTION);
        newWallpaper.setPcolor(AppConfiguration.DEFAULT_COLOR);
        newWallpaper.setScolor(AppConfiguration.DEFAULT_COLOR);
        newWallpaper.setShaderType(AppConfiguration.DEFAULT_SHADER_TYPE);
        try {
            wallpapers.add(newWallpaper);
            frame.setViewPortContainer(new WallpaperPanel(newWallpaper));
            int index = wallpapers.getWallpapers().indexOf(newWallpaper);
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
            new BackgroundException(e, "No hay un archivo de background activo");
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
    public void actionPerformed(ActionEvent ae) {
        //System.out.println(ae.getActionCommand());
        switch (ae.getActionCommand()) {
            case "ADD_LIBRARY":
                FileChooserController fChooser = new FileChooserController(frame, true);
                File[] res = fChooser.getFiles();

                if (res != null) {
                    resurces = res;
                    saveFilesInBGFolder(resurces);
                }

                break;

            case "NEW_WALLP":
                String selected = frame.getSelected();
                newWallpaper(selected);
                break;

            case "SAVE":
                XMLparse xmlParse = new XMLparse();
                System.out.println(wallpaperXMLFIle);
                xmlParse.saveXML(wallpaperXMLFIle, XMLparse.WALLPAPER_XML, wallpapers);
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
                xmlParse = new XMLparse();
                String filename = JOptionPane.showInputDialog("Nombre del archivo");
                int status = xmlParse.saveXML(new File(folder.getPath() + File.separator + filename + ".xml"), XMLparse.WALLPAPER_XML, newWallPaper);
                if (status == 0) {
                    checkWallpapersXML();
                }
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
