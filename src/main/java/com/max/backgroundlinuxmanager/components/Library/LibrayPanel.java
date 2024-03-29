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
package com.max.backgroundlinuxmanager.components.Library;

import javax.swing.plaf.synth.SynthTabbedPaneUI;
import java.awt.*;

/**
 * @author max
 */
public class LibrayPanel extends javax.swing.JPanel {

    /**
     * Creates new form LibrayPanel
     */
    public LibrayPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        scrollContent = new javax.swing.JScrollPane();
        navHolder = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();

        setForeground(new java.awt.Color(88, 222, 31));
        setLayout(new GridBagLayout());
        GridBagConstraints mainConstraint = new GridBagConstraints();
        mainConstraint.weightx = 4;
        mainConstraint.weighty = 3;
        mainConstraint.fill = GridBagConstraints.BOTH;
        mainConstraint.gridy = 1;
        mainConstraint.gridx = 0;
        mainConstraint.ipady = 40;

        scrollContent.setBorder(null);
        scrollContent.setForeground(new java.awt.Color(222, 22, 222));
        scrollContent.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollContent.setToolTipText("");
        scrollContent.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        scrollContent.setAutoscrolls(true);
        scrollContent.setName(""); // NOI18N
        scrollContent.setVerifyInputWhenFocusTarget(false);
        add(scrollContent, mainConstraint);

        navHolder.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        navHolder.setFocusable(false);
        navHolder.setLayout(new java.awt.GridBagLayout());

        titleLabel.setFont(new java.awt.Font("Dialog", Font.PLAIN, 24)); // NOI18N
        titleLabel.setText("Library");
        titleLabel.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        navHolder.add(titleLabel, gridBagConstraints);

        addButton.setText("ADD");
        addButton.addActionListener(evt -> addButtonActionPerformed(evt));
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        navHolder.add(addButton, gridBagConstraints);
        System.out.println(getWidth() + "hh");
        mainConstraint.weighty = .5;
        mainConstraint.weightx = 4;
        mainConstraint.gridy = 0;
        mainConstraint.gridx = 0;
        add(navHolder, mainConstraint);
        navHolder.getAccessibleContext().setAccessibleName("");
        navHolder.getAccessibleContext().setAccessibleParent(navHolder);
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_addButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton addButton;
    private javax.swing.JPanel navHolder;
    protected javax.swing.JScrollPane scrollContent;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
