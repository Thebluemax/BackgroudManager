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
package com.max.backgroundlinuxmanager.components.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implementación de la interface ActionListener para las acciones 
 correspondientes a MainFrameComponent 
 * 
 * @author max
 */
public class MainFrameListener implements ActionListener{
    public final static String SAVE_TO_NEW_WALLPAPER = "SAVE_TO_WALLPAPER";
    public final static String NEW_XML_WALLPAPER = "NEW_XML_WALLPAPER";
    public final static String NEW_XML_SLIDE = "NEW_XML_SLIDE";
    public final static String SHOW_XML_WALLPAPER = "SHOW_XML_WALLPAPER";

    public final static String CLOSE_XML_WALLPAPER = "CLOSE_XML_WALLPAPER";

    private MainFrameComponent mainFrame;
    /**
     * 
     * @param mainFrame 
     */
    public MainFrameListener(MainFrameComponent mainFrame) {
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
                mainFrame.showSlideXmlWallpaper();
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
