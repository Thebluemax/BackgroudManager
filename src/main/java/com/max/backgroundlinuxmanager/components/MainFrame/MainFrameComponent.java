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

import com.max.backgroundlinuxmanager.components.Library.LibraryComponent;
import com.max.backgroundlinuxmanager.components.XmlWallpaperComponent.XmlWallpaperComponent;
import com.max.backgroundlinuxmanager.exceptions.BackgroundException;
import com.max.backgroundlinuxmanager.models.entities.AppConfiguration;
import com.max.backgroundlinuxmanager.views.components.NavComponent;

/**
 *
 * @author max
 */
public class MainFrameComponent extends MainJFrame {

    private LibraryComponent library;
    private XmlWallpaperComponent xmlWallpaper;
    private NavComponent nav;
    private AppConfiguration appConfig;

    public MainFrameComponent(AppConfiguration appConfig) {
        super();
        this.appConfig = appConfig;
        init();
    }

    private void init() {
        library = new LibraryComponent(appConfig);
        xmlWallpaper = new XmlWallpaperComponent(appConfig,  getWidth(), getHeight());
        nav = new NavComponent();
        //System.out.println(getHeight() + "++" + getWidth());
        addToMain(library, 0, 0, getWidth(), getHeight() - 90);
        addToMain(xmlWallpaper, 0, 0, getWidth(), getHeight());
        addToMain(nav, 0, getHeight() - 90, getWidth(), 50);
        
        nav.addActionListener(new MainFrameListener(this));
        xmlWallpaper.setCloseListener(new MainFrameListener(this));
        
        showXmlWallpaper(false);
        showLibrary(true);
    }

    /**
     * Hide the library panel
     *
     * @param status boolean
     */
    public void showLibrary(boolean status) {
        library.setVisible(status);
        nav.setVisibility(status);
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
        String selected = getSelected();
        if (selected == "") {
            new BackgroundException(new Exception(), "Se ha seleccionar al menos una imagen");
        } else {
            showLibrary(false);
            showXmlWallpaper(true);
            xmlWallpaper.newWallpaper(selected, this);
        }

    }

    public void closeXmlWallpaper() {
        
        xmlWallpaper.setVisible(false);
        xmlWallpaper.clear();
        showLibrary(true);
    }

    public void showXmlWallpaper() {
        showLibrary(false);
        showXmlWallpaper(true);
        xmlWallpaper.showWallpaper();
    }

    public void showActiveXmlWallpaper() {
        showXmlWallpaper();
       // xmlWallpaper.showWallpaper();
    }
    
     public void showSlideXmlWallpaper() {
        showLibrary(false);
        showXmlWallpaper(true);
        xmlWallpaper.showSlideWallpaper(library.getSelectedBlocks());
    }
}
