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
package com.max.backgroundlinuxmanager.controllers.views;

import com.max.backgroundlinuxmanager.models.entities.Wallpaper;
import com.max.backgroundlinuxmanager.threads.ImageLoader;
import com.max.backgroundlinuxmanager.utils.ManagerFiles;
import com.max.backgroundlinuxmanager.views.components.WallpaperPanel;
import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author max
 */
public class WallpaperController extends WallpaperPanel{
    
    public WallpaperController(Wallpaper wp){
        super(wp);
         loadImage();
    }
    
  /*  @Override
    public void loadImage() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<ImageIcon> future;
        File f = new File(ManagerFiles.getUserFolder() + "/" + wp.getFilename());
        ImageLoader iLoad = new ImageLoader(imageHolder.getWidth(), imageHolder.getHeight(), f, true, true);
        future = executor.submit(() -> {
            return iLoad.call(); //To change body of generated lambdas, choose Tools | Templates.
        });

        try {
            imageHolder.setIcon(future.get());
        } catch (InterruptedException ex) {
            Logger.getLogger(WallpaperPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(WallpaperPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }*/
}
