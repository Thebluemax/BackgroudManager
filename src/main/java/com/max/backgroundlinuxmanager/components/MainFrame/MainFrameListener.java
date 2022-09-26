/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.max.backgroundlinuxmanager.components.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implementación de la interface ActionListener para las acciones 
 * correspondientes a MainFrameController 
 * 
 * @author max
 */
public class MainFrameListener implements ActionListener{
    public final static String SAVE_TO_NEW_WALLPAPER = "SAVE_TO_WALLPAPER";
    public final static String NEW_XML_WALLPAPER = "NEW_XML_WALLPAPER";
    public final static String NEW_XML_SLIDE = "NEW_XML_SLIDE";
    public final static String SHOW_XML_WALLPAPER = "SHOW_XML_WALLPAPER";

    public final static String CLOSE_XML_WALLPAPER = "CLOSE_XML_WALLPAPER";

    private MainFrameController mainFrame;
    /**
     * 
     * @param mainFrame 
     */
    public MainFrameListener(MainFrameController mainFrame) {
        this.mainFrame = mainFrame;
    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
       System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case SAVE_TO_NEW_WALLPAPER:
                mainFrame.newWallpaper();
                break;
            case NEW_XML_SLIDE:
                break;
            case CLOSE_XML_WALLPAPER:
                mainFrame.closeXmlWallpaper();
                break;
            case SHOW_XML_WALLPAPER:
                mainFrame.showActiveXmlWallpaper();
                break;
        }
    }
    
}
