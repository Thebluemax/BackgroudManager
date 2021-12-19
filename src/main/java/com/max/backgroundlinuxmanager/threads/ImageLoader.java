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
package com.max.backgroundlinuxmanager.threads;

import com.max.backgroundlinuxmanager.controllers.utils.ImageManager;
import java.io.File;
import java.util.concurrent.Callable;
import javax.swing.ImageIcon;

/**
 *
 * @author Maximiliano Fernández thebluemax13 at gmail.com
 */
public class ImageLoader implements Callable<ImageIcon> {
    
    private int width;
    private int heigth;
    private File file;
/**
 * Constructor del la clase, los parametros configuran futura del icono
 * @param width El ancho del contenedor.
 * @param heigth El alto del contenedor.
 * @param file El archivo con la imagen del wallpaper
 */
    public ImageLoader(int width, int heigth, File file ) {
     
        this.width = width;
        this.heigth = heigth;
        this.file = file;
    }

    /**
     * Ejecuta el trhed para crear la imgen reducida del wallpaper
     *  
     * @return ImageIcon EL objeto con la imagen reducida
     * @throws Exception 
     */

    @Override
    public ImageIcon call() throws Exception {
        try {
         return ImageManager.resize(file, width, heigth);

        } catch (Exception e) {
            
              return ImageManager.resize(new File("src/assets/no-image.png"), width, heigth);
        }
    }
    
}
