/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
