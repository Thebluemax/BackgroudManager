/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.max.backgroundlinuxmanager.components.XmlWallpaperComponent;

import java.awt.Panel;
import javax.swing.JPanel;
import org.jdesktop.swingx.auth.JAASLoginService;

/**
 *
 * @author max
 */
public class XmlWallpaperPanel extends javax.swing.JPanel {

    /**
     * Creates new form XmlWallpaperPanel
     */
    protected JPanel panel;

    public XmlWallpaperPanel(int width, int heigth) {
        setBounds(0, 0, width, heigth);
        initComponents();
        System.out.println(getWidth() + "//" + getHeight());
        panel = new JPanel();
        add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, getWidth(), getHeight()));
        //navPanel.setBounds(0, 0, getWidth(), 50);

    }

    public void addToPanel(JPanel panel, int x, int y, int width, int heigth) {
        //panel.setBounds(x, y, width, heigth);
        this.panel.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, width, heigth));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(51, 51, 255));
        setForeground(new java.awt.Color(222, 222, 2));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
