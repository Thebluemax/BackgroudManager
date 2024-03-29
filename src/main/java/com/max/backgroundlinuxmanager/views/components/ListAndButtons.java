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

import com.max.backgroundlinuxmanager.BackgroundManager;
import com.max.backgroundlinuxmanager.utils.IconFontManager;
import com.max.backgroundlinuxmanager.views.components.AppColors.AppColors;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JList;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

/**
 *
 * @author Maximiliano Fernández thebluemax13 at gmail.com
 */
public class ListAndButtons extends javax.swing.JPanel {

    private DefaultListModel stringList;

    /**
     * Creates new form ListAndButtons
     */
    public ListAndButtons() {
        setBackground(new AppColors().generalColor());
        initComponents();
        prepareButtons();
        Color general = new AppColors().generalColor();
        elementsList.setBackground(general);
        elementsList.setSelectionBackground(general);
        listHolder.setBackground(general);
        
       // listHolder.setSize(160, 300);
        elementsList.setForeground(new AppColors().foregroundColorGeneral());
        elementsList.setSelectionBackground(new AppColors().foregroundColorGeneral());
        elementsList.setSelectionForeground(general);
        //elementsList.setSize(175, 200);
        //this.setPreferredSize(new Dimension(180, 350));

    }

    public void addButtonListener(ActionListener actionListener) {
    //    addBtn.setActionCommand(BackgroundManager.ADD_ACTION);
        addBtn.addActionListener(actionListener);
        deleteBtn.addActionListener(actionListener);
    }

    public void toggleActivation() {
        this.setEnabled(this.isEnabled() ? false : true);
    }

    public void setList(String[] list) {
        stringList = new DefaultListModel();
        for (int i = 0; i < list.length; i++) {
            stringList.addElement(list[i]);
        }
        elementsList.setModel(stringList);
        //elementsList.setSize(this.getWidth() / 2, 50);

    }

    @Override
    public void setSize(int w, int h) {

        super.setSize(w, h);

        elementsList.setValueIsAdjusting(false);
        btnHolder.setSize(w, h);
       listHolder.setSize(w, h);
        elementsList.setSize(w, h);
        deleteBtn.setSize(w / 2, 50);
        addBtn.setSize(w / 2, 50);

    }

    public void deactivateDelete(boolean flag) {
        deleteBtn.setEnabled(flag);
    }

    private void prepareButtons() {
        Icon icon = IconFontManager.createIcon(FontAwesome.PLUS_CIRCLE, 32, Color.WHITE);
        addBtn.setText("");
        addBtn.setIcon(icon);
        deleteBtn.setText("");
        icon = IconFontManager.createIcon(FontAwesome.ERASER, 32, Color.RED);
        deleteBtn.setIcon(icon);

    }

    public JList<String> getList() {
        return elementsList;
    }

    public String getListElement(int index) {
        return (String) stringList.get(index);
    }

    public void addListMouseEvents(MouseAdapter mouseAdapter) {
        elementsList.addMouseListener(mouseAdapter);
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

        listHolder = new javax.swing.JScrollPane();
        elementsList = new javax.swing.JList<>();
        btnHolder = new javax.swing.JPanel();
        addBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(13, 39, 93));
        setForeground(new java.awt.Color(255, 255, 255));
        setToolTipText("");
        setMaximumSize(new java.awt.Dimension(200, 350));
        setMinimumSize(new java.awt.Dimension(180, 300));
        setPreferredSize(new java.awt.Dimension(180, 300));
        setLayout(new java.awt.GridBagLayout());

        listHolder.setBorder(null);
        listHolder.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        listHolder.setAlignmentX(0.0F);
        listHolder.setAlignmentY(0.0F);
        listHolder.setMaximumSize(new java.awt.Dimension(180, 200));
        listHolder.setMinimumSize(new java.awt.Dimension(140, 200));
        listHolder.setOpaque(false);
        listHolder.setPreferredSize(new java.awt.Dimension(160, 200));

        elementsList.setBackground(new AppColors().generalColor());
        elementsList.setForeground(new java.awt.Color(237, 88, 88));
        elementsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        elementsList.setToolTipText("");
        elementsList.setAlignmentX(0.0F);
        elementsList.setAlignmentY(0.0F);
        elementsList.setDoubleBuffered(true);
        elementsList.setLayoutOrientation(javax.swing.JList.VERTICAL_WRAP);
        elementsList.setPreferredSize(new java.awt.Dimension(49, 280));
        listHolder.setViewportView(elementsList);
        elementsList.getAccessibleContext().setAccessibleParent(listHolder);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(listHolder, gridBagConstraints);

        btnHolder.setOpaque(false);
        btnHolder.setLayout(new java.awt.GridLayout(1, 2, 1, 1));

        addBtn.setBackground(new java.awt.Color(154, 214, 6));
        addBtn.setDefaultCapable(false);
        btnHolder.add(addBtn);

        deleteBtn.setBackground(new java.awt.Color(248, 103, 77));
        btnHolder.add(deleteBtn);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        add(btnHolder, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JPanel btnHolder;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JList<String> elementsList;
    private javax.swing.JScrollPane listHolder;
    // End of variables declaration//GEN-END:variables
}
