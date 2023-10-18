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
package com.max.backgroundlinuxmanager.controllers;

import com.max.backgroundlinuxmanager.models.XMLDOMBackground;
import com.max.backgroundlinuxmanager.models.entities.FrameBackground;
import com.max.backgroundlinuxmanager.views.components.ImageBlockPane;
import com.max.backgroundlinuxmanager.views.components.ListAndButtons;
import com.max.backgroundlinuxmanager.components.XmlWallpaperComponent.SlideEditPanel;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Maximiliano Fernández thebluemax13 at gmail.com
 */
public class SlideEditPanelController extends SlideEditPanel{
     /**
     * Creates new form SidePanel
     */
    public static String LIBRARY_TAG = "Librería";
    public static String FATHER = "father";
    public static String CHILD = "child";

    private ListAndButtons fatherList;
    private ListAndButtons childList;
    private String[] wallpapersList;
    private String[] wallpaperName;
    private List<FrameBackground> wallpaperFrames;
    protected XMLDOMBackground slidemodel;
    
    private List<ImageBlockPane> listBlock;

   
   
    public SlideEditPanelController(XMLDOMBackground slidemodel) {
        super (new Dimension(400, 200));
        this.slidemodel = slidemodel;
        
        setMouseAdapter(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);        
            }
        });
    }
    
    public void setlist(){
        wallpaperFrames = this.slidemodel.getFrameList();
        
        ExecutorService fixedPool = Executors.newFixedThreadPool(5);
       // List <ImageLoader> iconList = new ArrayList<ImageLoader>();
    //    System.err.println(super.getContainerBounds().toString());
      //  Dimension bounds = getContainerBounds();
   //     System.out.println(bounds);
            listBlock = new ArrayList<>();
        for (int i = 0; i < wallpaperFrames.size(); i++) {
            System.out.println(wallpaperFrames.get(i).getFilePath());
            File file = new File(wallpaperFrames.get(i).getFilePath());
            ImageBlockPane tempBlock = new ImageBlockPane(file,100,90);
         //   tempBlock.setSize(bounds.width/6, bounds.height/3);
          listBlock.add(tempBlock);
            addToPanel(tempBlock);
            //iconList.add(new ImageLoader(130, 150, new File()));
        }
        setValues(slidemodel);
        
             
        
        
    }
    
    public void loadImage(){
     listBlock.forEach(block -> block.loadImage()); 
    }
    
    
       
    
}
