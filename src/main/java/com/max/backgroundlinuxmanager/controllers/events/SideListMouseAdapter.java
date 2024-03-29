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
package com.max.backgroundlinuxmanager.controllers.events;

import com.max.backgroundlinuxmanager.controllers.SlideEditPanelController;
import com.max.backgroundlinuxmanager.components.MainFrame.MainFrameComponent;
import com.max.backgroundlinuxmanager.models.XMLDOMBackground;
import com.max.backgroundlinuxmanager.models.entities.Wallpaper;
import com.max.backgroundlinuxmanager.models.entities.WallpaperXML;
import java.awt.event.MouseAdapter;
import javax.swing.JList;

/**
 *
 * @author max
 */
public class SideListMouseAdapter extends MouseAdapter {

    private String element = "";
    private JList list;
    private WallpaperXML wallpapers;
    private MainFrameComponent frame;
    private XMLDOMBackground slideModel;
    private SlideEditPanelController editPanel;
    private Wallpaper activeWallpaper;
    private String activeWallpaperName;

    public SideListMouseAdapter(WallpaperXML wallpapers, MainFrameComponent frame) {
        element = "";
        this.wallpapers = wallpapers;
        this.frame = frame;
    }
}

