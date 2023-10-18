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
package com.max.backgroundlinuxmanager.views.components;

import com.max.backgroundlinuxmanager.components.XmlWallpaperComponent.WallpaperPanel;
import com.max.backgroundlinuxmanager.components.Library.LibraryListener;
import java.io.File;
import com.max.backgroundlinuxmanager.threads.ImageLoader;
import com.max.backgroundlinuxmanager.utils.IconFontManager;
import com.max.backgroundlinuxmanager.utils.ManagerFiles;
import com.max.backgroundlinuxmanager.views.components.AppColors.AppColors;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import jiconfont.icons.font_awesome.FontAwesome;

/**
 *
 * @author Maximiliano Fernández thebluemax13 at gmail.com
 */
public class ImageBlockPane extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    private File image;
    private File thumb;
    private String filename;
    private String pathThumb;
    private String pathReal;
    private File loadingFile;

    public ImageBlockPane(File f, int width, int height) {
        pathThumb = ManagerFiles.getBackgroundsFolder() + "/thumbs/" + f.getName();
        pathReal = ManagerFiles.getBackgroundsFolder() + "/" + f.getName();

        thumb = new File(pathThumb);
        this.setBackground(new AppColors().generalColor());
        initComponents();
        ImageIcon icon = new ImageIcon("src/assets/835.gif");//ImageManager.resize(loadingFile, 120, 100);
        image = f;
        imageHolder.setSize(width, height);
        imageHolder.setText("");
        imageHolder.setIcon(icon);
        erraseBtn.setText("");
        erraseBtn.setIcon(IconFontManager.createIcon(FontAwesome.TRASH, 12, Color.BLACK));

        setIcon(f.getName());
        erraseBtnVisible(true);
    }

    public void setListener(ActionListener event) {
        erraseBtn.setActionCommand(LibraryListener.DELETE_OF_LIBRARY);
        erraseBtn.addActionListener(event);
    }
    public void setBlockListener(MouseListener listener) {
        addMouseListener(listener);
    }
    public void setIcon(String name) {

        objectCheckbox.setLabel(name);
        filename = name;

    }
/**
 * Ejecuta el trhead que carga la imagen dentro del contenedor del la miniatura
 * 
 * 
 */
    public void loadImage() {
        //System.out.println(filename);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<ImageIcon> future;
        if(thumb.exists()){
            image = thumb;
        }
        if (!image.exists()) {
            image = new File("src/assets/no-image.png");
        }
        
        ImageLoader iLoad = new ImageLoader(imageHolder.getSize().width, imageHolder.getSize().height, image, true, true);
        future = executor.submit(() -> {
                return iLoad.call(); //To change body of generated lambdas, choose Tools | Templates.
        });

        try {
            imageHolder.setIcon(future.get());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(WallpaperPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        executor.shutdown();
    }

    public void erraseBtnVisible(boolean show) {
        erraseBtn.setVisible(show);
    }
    /**
     * Setter para la etiqueta del bloque
     * @param label String de la etiqueta 
     */
    public void setLabel(String label) {
        objectCheckbox.setLabel(label);
    }
/**
 * Retorna un booleano que indica si el bloque ha sido marcado
 * @return 
 */
    public boolean isChecked() {

        return objectCheckbox.isSelected();

    }

    public String getFilename() {
        return filename;
    }

    public String getFilePath() {
        return pathReal;//image.getAbsolutePath();
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public void setThumb(File thumb) {
        this.thumb = thumb;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setPathThumb(String pathThumb) {
        this.pathThumb = pathThumb;
    }

    public void setPathReal(String pathReal) {
        this.pathReal = pathReal;
    }
    
    public ImageBlockPane clone() {
        
    ImageBlockPane newlock = new ImageBlockPane(this.image, this.getWidth(), this.getHeight());
    return newlock;
    }
     /**
     * Check if the background file is a xml document
     *
     * @param filename
     * @return boolean
     */
    public static boolean isSlide(String filename) {
        String name = filename;
        name = name.substring(name.length() - 3, name.length());
        return (name.compareTo("xml") == 0);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        imageHolder = new javax.swing.JLabel();
        objectCheckbox = new javax.swing.JCheckBox();
        erraseBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(46, 36, 57));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        setForeground(new java.awt.Color(2, 2, 2));
        setMaximumSize(new java.awt.Dimension(150, 150));
        setPreferredSize(new java.awt.Dimension(150, 150));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imageHolder.setBackground(new java.awt.Color(35, 24, 12));
        imageHolder.setText("jLabel1");
        imageHolder.setMaximumSize(new java.awt.Dimension(150, 80));
        imageHolder.setPreferredSize(new java.awt.Dimension(150, 80));
        add(imageHolder, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 25, -1, -1));

        objectCheckbox.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        objectCheckbox.setText("jCheckBox1");
        objectCheckbox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        objectCheckbox.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        add(objectCheckbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 154, -1));

        erraseBtn.setText("jButton1");
        add(erraseBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 106, 154, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton erraseBtn;
    private javax.swing.JLabel imageHolder;
    private javax.swing.JCheckBox objectCheckbox;
    // End of variables declaration//GEN-END:variables
}
