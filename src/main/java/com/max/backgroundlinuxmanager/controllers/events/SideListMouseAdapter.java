/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.max.backgroundlinuxmanager.controllers.events;

import com.max.backgroundlinuxmanager.controllers.SlideEditPanelController;
import com.max.backgroundlinuxmanager.components.MainFrame.MainFrameController;
import com.max.backgroundlinuxmanager.exceptions.BackgroundException;
import com.max.backgroundlinuxmanager.models.XMLDOMBackground;
import com.max.backgroundlinuxmanager.models.entities.Wallpaper;
import com.max.backgroundlinuxmanager.models.entities.WallpaperXML;
import com.max.backgroundlinuxmanager.utils.ManagerFiles;
import com.max.backgroundlinuxmanager.views.components.WallpaperPanel;
import com.max.backgroundlinuxmanager.views.components.SidePanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JList;

/**
 *
 * @author max
 */
public class SideListMouseAdapter extends MouseAdapter {

    private String element = "";
    private JList list;
    private WallpaperXML wallpapers;
    private MainFrameController frame;
    private XMLDOMBackground slideModel;
    private SlideEditPanelController editPanel;
    private Wallpaper activeWallpaper;
    private String activeWallpaperName;

    public SideListMouseAdapter(WallpaperXML wallpapers, MainFrameController frame) {
        element = "";
        this.wallpapers = wallpapers;
        this.frame = frame;
    }

    public void mouseClicked(MouseEvent evt) {

        list = (JList) evt.getSource();
        int index = list.getSelectedIndex();
        if (index != -1) {
            element = (String) list.getModel().getElementAt(index);
        }

        if (list.getName().compareTo(SidePanel.CHILD) == 0) {
            if (element.isBlank()) {
                new BackgroundException(new NullPointerException(), "The Collection is empty");
            }

            System.out.println(".mouseClicked()");
            String filename = wallpapers.getWallpapers().get(index).getFilename();
            if (ManagerFiles.isSlide(filename)) {

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
            activeWallpaper = wallpapers.getWallpapers().get(index);
        } else {
            System.out.println(".mouseClicked()-paper");

            System.out.println(element);
            if (element.compareTo(SidePanel.LIBRARY_TAG) == 0) {
                frame.setLibraryView(true);
            } else {
                activeWallpaperName = element;
               // buildWallpapers(element);
                frame.setLibraryView(false);
            }
        }
    }
}

