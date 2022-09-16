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

import com.max.backgroundlinuxmanager.controllers.events.WallpaperButtonAdapter;
import com.max.backgroundlinuxmanager.models.entities.AppConfiguration;
import com.max.backgroundlinuxmanager.models.entities.Wallpaper;
import com.max.backgroundlinuxmanager.models.entities.WallpaperXML;
import com.max.backgroundlinuxmanager.utils.ColorManager;
import com.max.backgroundlinuxmanager.views.components.AppColors.AppColors;
import com.max.backgroundlinuxmanager.threads.ImageLoader;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * @author Maximiliano Fernández thebluemax13 at gmail.com
 */
public class WallpaperPanel extends javax.swing.JPanel implements ActionListener{

    /**
     * Creates new form JpaneWallpaper
     */
    protected Wallpaper wp;
    public static final String PCOLOR = "pcolor";
    public static final String SCOLOR = "scolor";

    public WallpaperPanel(Wallpaper wp) {

        this.wp = wp;
        initComponents();
        nameLabel.setText(this.wp.getName());
        imageHolder.setText("");
        imageHolder.setIcon(null);
        comboBuild();
        setColors(new AppColors().generalColor(), new AppColors().foregroundColorGeneral());

        pcolor.setName(PCOLOR);
        pcolor.setText(wp.getPcolor());

        scolor.setText(wp.getScolor());
        scolor.setName(SCOLOR);

        WallpaperButtonAdapter adapter = new WallpaperButtonAdapter(wp);
        pcolor.addMouseListener(adapter);
        scolor.addMouseListener(adapter);

    }
    
     public WallpaperPanel(Wallpaper wp, WallpaperXML walpaperXML) {
        this.wp = wp;
        initComponents();
        nameLabel.setText(this.wp.getName());
        imageHolder.setText("");
        imageHolder.setIcon(null);
        comboBuild();
        setColors(new AppColors().generalColor(), new AppColors().foregroundColorGeneral());

        pcolor.setName(PCOLOR);
        pcolor.setText(wp.getPcolor());

        scolor.setText(wp.getScolor());
        scolor.setName(SCOLOR);

        WallpaperButtonAdapter adapter = new WallpaperButtonAdapter(wp);
        pcolor.addMouseListener(adapter);
        scolor.addMouseListener(adapter);
      //  jButton1.addActionListener(optionCombobox);

    }

    private void setColors(Color background, Color foreground) {
        this.setBackground(background);
        pcolor.setBackground(ColorManager.getColor(wp.getPcolor()));
        pcolor.setOpaque(true);
        scolor.setOpaque(true);

        scolor.setBackground(ColorManager.getColor(wp.getScolor()));
        nameLabel.setForeground(new AppColors().foregroundColorGeneral());
        optionLabel.setForeground(foreground);
        shaderLabel.setForeground(foreground);
        sColorLabel.setForeground(foreground);
        pColorLabel.setForeground(foreground);
    }

    private void comboBuild() {

        String[] arr = AppConfiguration.OPTIONS_WALLPAPER;
        for (int i = 0; i < arr.length; i++) {
            optionCombobox.addItem(arr[i]);
        }
        optionCombobox.setSelectedItem(wp.getOptions());

        arr = AppConfiguration.SHADER_TYPE;

        for (int i = 0; i < arr.length; i++) {
            shaderCombobox.addItem(arr[i]);

        }
        shaderCombobox.setSelectedItem(wp.getShaderType());
    }

    public Wallpaper getWallpaper() {
        return wp;
    }
    
    public void setListeners(ActionListener litener) {
     //   jButton1.setActionCommand(BackgroundManager.SAVE_NEW_WALLPAPER);
     //   jButton1.addActionListener(litener);
     //   jButton2.setActionCommand(BackgroundManager.CANCEL );
     //   jButton2.addActionListener(litener);
    }

    /**
     * Ejecuta el trhead que carga la imagen dentro del contenedor del la
     * miniatura
     *
     * @return void
     */
    public void loadImage() {
        System.out.println(wp.getFilename());
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<ImageIcon> future;
        File image = new File(wp.getFilename());

        if (!image.exists()) {
            image = new File("src/assets/no-image.png");
        }
        //System.out.println(getPreferredSize().width + "--" + getHeight());
        ImageLoader iLoad = new ImageLoader(getWidth() - 20, getHeight() - 300, image, true, true);
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

        executor.shutdown();
        executor = null;

    }

    ;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        imageHolder = new javax.swing.JLabel();
        controlPanel = new javax.swing.JPanel();
        optionLabel = new javax.swing.JLabel();
        optionCombobox = new javax.swing.JComboBox<>();
        shaderLabel = new javax.swing.JLabel();
        shaderCombobox = new javax.swing.JComboBox<>();
        pColorLabel = new javax.swing.JLabel();
        pcolor = new javax.swing.JLabel();
        sColorLabel = new javax.swing.JLabel();
        scolor = new javax.swing.JLabel();

        setForeground(new java.awt.Color(236, 236, 226));
        setToolTipText("");
        setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        setName(""); // NOI18N
        setLayout(new java.awt.GridLayout(3, 1));

        nameLabel.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel.setLabelFor(imageHolder);
        nameLabel.setText("jLabel6");
        nameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        nameLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        add(nameLabel);

        imageHolder.setText("jLabel1");
        imageHolder.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        imageHolder.setMaximumSize(new java.awt.Dimension(3000, 1400));
        imageHolder.setMinimumSize(new java.awt.Dimension(3000, 1400));
        add(imageHolder);

        controlPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        controlPanel.setOpaque(false);
        controlPanel.setLayout(new java.awt.GridLayout(4, 2, 5, 0));

        optionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        optionLabel.setText("Options");
        controlPanel.add(optionLabel);

        optionCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionComboboxActionPerformed(evt);
            }
        });
        controlPanel.add(optionCombobox);

        shaderLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        shaderLabel.setText("Type Shader");
        shaderLabel.setName(""); // NOI18N
        controlPanel.add(shaderLabel);

        shaderCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shaderComboboxActionPerformed(evt);
            }
        });
        controlPanel.add(shaderCombobox);

        pColorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pColorLabel.setText("Primary Color");
        controlPanel.add(pColorLabel);

        pcolor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pcolor.setText("jLabel3");
        controlPanel.add(pcolor);

        sColorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sColorLabel.setText("Secondary Color");
        controlPanel.add(sColorLabel);

        scolor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scolor.setText("jLabel4");
        controlPanel.add(scolor);

        add(controlPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void optionComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionComboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optionComboboxActionPerformed

    private void shaderComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shaderComboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_shaderComboboxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel controlPanel;
    protected javax.swing.JLabel imageHolder;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JComboBox<String> optionCombobox;
    private javax.swing.JLabel optionLabel;
    private javax.swing.JLabel pColorLabel;
    private javax.swing.JLabel pcolor;
    private javax.swing.JLabel sColorLabel;
    private javax.swing.JLabel scolor;
    private javax.swing.JComboBox<String> shaderCombobox;
    private javax.swing.JLabel shaderLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
