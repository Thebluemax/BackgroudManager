/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.max.backgroundlinuxmanager.views.components;

import com.max.backgroundlinuxmanager.components.XmlWallpaperComponent.XmlWallpaperListener;
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
    private List<Wallpaper> wList;

    public WallpaperView(List<Wallpaper> wList, WallpaperXML wallpaper) {
        wallpaperXml = wallpaper;
        this.wList = wList;

        initComponents();
        jScrollPane1.setBounds(0, 0, getWidth(), 80);

    }

    public void buildList() {
        container = new JPanel();
        GridLayout layout = new GridLayout(1, 10);
        container.setLayout(layout);
        jScrollPane1.setViewportView(container);
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
         //   holder.addMouseListener(new XmlWallpaperListener(this));
        }
    }

    private void loadImage(File file, JLabel holder) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<ImageIcon> future;

        if (!file.exists()) {
            file = new File("src/assets/no-image.png");
        }

        ImageLoader iLoad = new ImageLoader(holder.getWidth(), holder.getHeight(), file, true, true);
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
        System.out.println(wp.getFilename());

        WallpaperPanel view = new WallpaperPanel(wp);
        wallpaperHolder.add(view, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, getWidth()-10, getHeight()));

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
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        wallpaperHolder = new javax.swing.JPanel();
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
        setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jScrollPane1, gridBagConstraints);

        wallpaperHolder.setForeground(new java.awt.Color(2, 222, 222));
        wallpaperHolder.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(wallpaperHolder, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        saveBtn.setText("SAVE");
        jPanel2.add(saveBtn);

        editBtn.setText("EDIT");
        jPanel2.add(editBtn);

        deleteBnt.setText("DELETE");
        jPanel2.add(deleteBnt);

        cancelBtn.setText("CANCEL");
        jPanel2.add(cancelBtn);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        add(jPanel2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton deleteBnt;
    private javax.swing.JButton editBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton saveBtn;
    private javax.swing.JPanel wallpaperHolder;
    // End of variables declaration//GEN-END:variables
}
