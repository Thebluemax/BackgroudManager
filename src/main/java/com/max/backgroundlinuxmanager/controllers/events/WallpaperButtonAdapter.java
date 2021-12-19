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
package com.max.backgroundlinuxmanager.controllers.events;

import com.max.backgroundlinuxmanager.models.entities.Wallpaper;
import com.max.backgroundlinuxmanager.utils.ColorManager;
import com.max.backgroundlinuxmanager.views.WallpaperPanel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JColorChooser;

/**
 *
 * @author Maximiliano Fernández thebluemax13 at gmail.com
 */
public class WallpaperButtonAdapter extends MouseAdapter{
    private Wallpaper wp;

    /**
     *
     * @param wp
     */
    public WallpaperButtonAdapter(Wallpaper wp) {
        super();
        this.wp = wp;
    }

    /**
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        Color color = JColorChooser.showDialog(null,"Escoge el color", Color.yellow);
               
                javax.swing.JLabel jl =(javax.swing.JLabel)e.getSource();
                jl.setBackground(color);
                System.out.println(color.toString());
                jl.setText(ColorManager.getColorHexa(color));
                switch(jl.getName()){
                    case WallpaperPanel.PCOLOR:
                        wp.setPcolor(ColorManager.getColorHexa(color));
                        break;
                        case WallpaperPanel.SCOLOR:
                        wp.setScolor(ColorManager.getColorHexa(color));
                        break;
                }
                
               
    }
    
    
    
}
