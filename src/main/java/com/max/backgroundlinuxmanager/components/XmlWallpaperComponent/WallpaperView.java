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
package com.max.backgroundlinuxmanager.components.XmlWallpaperComponent;

import com.max.backgroundlinuxmanager.components.XmlWallpaperComponent.WallpaperPanel;
import com.max.backgroundlinuxmanager.models.entities.Wallpaper;
import com.max.backgroundlinuxmanager.models.entities.WallpaperXML;
import com.max.backgroundlinuxmanager.threads.ImageLoader;
import java.awt.GridLayout;
import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author max
 */
public class WallpaperView extends javax.swing.JPanel {

    /**
     * Creates new form WallpapaerView
     */
    protected JPanel container;
    private WallpaperXML wallpaperXml;
    private javax.swing.JScrollPane scrollPane1;
     private javax.swing.JPanel wallpaperHolder;
    private List<Wallpaper> wList;

    public WallpaperView(List<Wallpaper> wList, WallpaperXML wallpaper) {
        wallpaperXml = wallpaper;
        this.wList = wList;
        wallpaperHolder = new JPanel();
        container = new JPanel();
        scrollPane1 = new JScrollPane();
        GridLayout layout = new GridLayout(1, 10);
        container.setLayout(layout);
        initComponents();
       // scrollPane1.setBounds(0, 0, getWidth(), 80);

    }

    public void buildList() {
        add(scrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, getWidth(), 120));
        add( wallpaperHolder, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, getWidth(), getHeight() - 60));

        scrollPane1.setViewportView(container);
        System.out.println(getWidth() + "()" + getHeight());
        int width = getWidth() + 100;
        int height = getHeight() + 60;
        for (int i = 0; i < wList.size(); i++) {
            Wallpaper get = wList.get(i);
            File fWallpaper = new File(get.getFilename());
            JLabel holder = new JLabel();
            holder.setMaximumSize(new java.awt.Dimension(150, 80));
            holder.setPreferredSize(new java.awt.Dimension(150, 80));
            container.add(holder);
            loadImage(fWallpaper, holder);
            holder.addMouseListener(new XmlWallpaperMouseListener(this));
        }
    }

    private void loadImage(File file, JLabel holder) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<ImageIcon> future;

        if (!file.exists()) {
            file = new File("src/assets/no-image.png");
        }

        ImageLoader iLoad = new ImageLoader(200, 170, file, true, true);
        future = executor.submit(() -> {
            return iLoad.call(); //To change body of generated lambdas, choose Tools | Templates.
        });

        try {
            holder.setIcon(future.get());
        } catch (InterruptedException ex) {
            Logger.getLogger(WallpaperPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(WallpaperPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        executor.shutdown();
        executor = null;

    }

    public void showWallpaper(JLabel label) {
        int index = container.getComponentZOrder(label);
        Wallpaper wp = wList.get(index);
        WallpaperPanel view = new WallpaperPanel(wp);
        wallpaperHolder.add(view, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, getWidth(), getHeight() - 60));
        view.loadImage();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        saveBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        deleteBnt = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        );

        setBackground(new java.awt.Color(51, 51, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        saveBtn.setText("SAVE");
        jPanel2.add(saveBtn);

        editBtn.setText("EDIT");
        jPanel2.add(editBtn);

        deleteBnt.setText("DELETE");
        jPanel2.add(deleteBnt);

        cancelBtn.setText("CANCEL");
        jPanel2.add(cancelBtn);

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 533, 936, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton deleteBnt;
    private javax.swing.JButton editBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton saveBtn;
    // End of variables declaration//GEN-END:variables
}
