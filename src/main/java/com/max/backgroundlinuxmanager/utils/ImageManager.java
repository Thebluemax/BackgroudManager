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
package com.max.backgroundlinuxmanager.utils;

import com.max.backgroundlinuxmanager.utils.ManagerFiles;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Maximiliano Fernández thebluemax13 at gmail.com
 */
public class ImageManager {

    private File inputFile;
    private String filename;
    /**
     *
     */
    public ImageManager() {
    }
    
    /**
     *Método para reducir una imagen a la escala deseada
     * 
     * @param InputFile El archivo de imagen
     * @param scaledWidth El valor para el ancho
     * @param scaledHeight El valor para el alto
     * @return ImageIcon EL objeto con la imagen reducida 
     */
    public static ImageIcon resize(File InputFile, int scaledWidth, int scaledHeight, boolean saveThumb)
            {
                if (scaledHeight == 0 || scaledWidth == 0) {
                    scaledHeight = 100;
                    scaledWidth =150;
                }
        // reads input image
        File inputFile = InputFile;
        
        BufferedImage inputImage = null;
        Image thumb = null;
        
        try {
            inputImage = ImageIO.read(inputFile);
            thumb = inputImage.getScaledInstance(scaledWidth,scaledHeight, Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            Logger.getLogger(ImageManager.class.getName()).log(Level.SEVERE, null, ex);
            thumb = inputImage.getScaledInstance(scaledWidth,scaledHeight, Image.SCALE_SMOOTH);
        }catch(NullPointerException ex){
            System.out.println(ex.getCause().getMessage());          
        }catch(IllegalArgumentException ilEx){
            
        }
        if(saveThumb){
            saveThumb(thumb,scaledWidth,scaledHeight, inputFile.getName());
        }
        
        return new ImageIcon(thumb);
    }
    
    private static void saveThumb(Image thumb,int scaledWidth, int scaledHeight, String filename) {
        try {
            BufferedImage buffer = new BufferedImage(scaledWidth,scaledHeight, BufferedImage.TYPE_INT_RGB);
            buffer.getGraphics().drawImage(thumb, 0, 0, null);
            String path =  ManagerFiles.getThumbsPath() + File.separator + filename;
            ImageIO.write(buffer, "jpg",new File(path));
        } catch (IOException ex) {
            Logger.getLogger(ImageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

