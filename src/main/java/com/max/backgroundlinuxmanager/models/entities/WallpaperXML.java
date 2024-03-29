/*
 * The MIT License
 *
 * Copyright 2019 Maximiliano Fernández thebluemax13 at gmail.com.
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
package com.max.backgroundlinuxmanager.models.entities;

import com.max.backgroundlinuxmanager.BackgroundManager;
import com.max.backgroundlinuxmanager.utils.ManagerFiles;
import com.max.backgroundlinuxmanager.utils.XMLparse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maximiliano Fernández thebluemax13 at gmail.com
 */
@XmlRootElement(name = "wallpapers")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class WallpaperXML {
    
    @XmlElement(name = "wallpaper")
    private List<Wallpaper> wallpaper;

    /**
     * @return the wallpapers
     */
    

    public List<Wallpaper> getWallpapers() {
        if (wallpaper == null) {
            wallpaper = new ArrayList<>();
        }
        
        return wallpaper;
    }

    /**
     *
     * @param wallpaper
     */
    public void add(Wallpaper wallpaper) {
        if (this.wallpaper == null) {
          //  wallpaper = new ArrayList<Wallpaper>();
        }//
        this.wallpaper.add(wallpaper);
    }

    /**
     * @param wallpapers the wallpapers to set
     */
    public void setWallpapers(List<Wallpaper> wallpapers) {
        if (wallpapers != null) {
            this.wallpaper = wallpapers;
        }else{
            this.wallpaper = new ArrayList<Wallpaper>();
        }
    }
    
    public static WallpaperXML factory(File file){
         //File wallpaperXMLFIle = new File(ManagerFiles.getWallpapersXMLFolder() + "/" + filename);
            XMLparse xmlParse = new XMLparse();
            WallpaperXML wallpaperXML = null;
            try {
                wallpaperXML = xmlParse.unmarshallerWallpapers(new FileInputStream(file));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BackgroundManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            return  wallpaperXML;
    }
    
}
