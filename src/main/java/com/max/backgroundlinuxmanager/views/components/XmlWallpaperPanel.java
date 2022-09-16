/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.max.backgroundlinuxmanager.views.components;

import java.awt.Panel;
import javax.swing.JPanel;

/**
 *
 * @author max
 */
public class XmlWallpaperPanel extends javax.swing.JPanel {

    /**
     * Creates new form XmlWallpaperPanel
     */
    public XmlWallpaperPanel() {
        initComponents();
    }

    public void addToPanel(JPanel panel, int x, int y, int width, int heigth){
    add(panel);
    panel.setBounds(x, y, width, heigth);
    
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
        jPanel2 = new javax.swing.JPanel();
        newXmlWallpaperBtn = new javax.swing.JButton();
        cancellWallpaperBtn = new javax.swing.JButton();

        setLayout(null);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        newXmlWallpaperBtn.setText("ADD NEW");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel2.add(newXmlWallpaperBtn, gridBagConstraints);

        cancellWallpaperBtn.setText("ADD NEW");
        cancellWallpaperBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancellWallpaperBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel2.add(cancellWallpaperBtn, gridBagConstraints);

        wallpaperCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wallpaperComboActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(wallpaperCombo, gridBagConstraints);

        add(jPanel2);
        jPanel2.setBounds(0, 0, 400, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void wallpaperComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wallpaperComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_wallpaperComboActionPerformed

    private void cancellWallpaperBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancellWallpaperBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancellWallpaperBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton cancellWallpaperBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    protected javax.swing.JButton newXmlWallpaperBtn;
    public final javax.swing.JComboBox<String> wallpaperCombo = new javax.swing.JComboBox<>();
    // End of variables declaration//GEN-END:variables
}
