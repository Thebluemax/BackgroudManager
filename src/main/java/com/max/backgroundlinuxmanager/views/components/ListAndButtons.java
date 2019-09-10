/*
 * The MIT License
 *
 * Copyright 2019 Maximiliano Fernández <thebluemax13 at gmail.com>.
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

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JList;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;


/**
 *
 * @author Maximiliano Fernández <thebluemax13 at gmail.com>
 */
public class ListAndButtons extends javax.swing.JPanel {
    private DefaultListModel stringList;
    /**
     * Creates new form ListAndButtons
     */
    public ListAndButtons() {
        IconFontSwing.register(FontAwesome.getIconFont());
        initComponents();
        prepareButtons();
    }
    public void addButtonListener(ActionListener actionListener) {
        addBtn.addActionListener(actionListener);
        deleteBtn.addActionListener(actionListener);
    }
    
    public void toggleActivation(){
        this.setEnabled( this.isEnabled() ? false : true);
    }
    public void setList(String[] list) {
    stringList = new DefaultListModel();
        for (int i = 0; i < list.length; i++) {
            stringList.addElement(list[i]);
        }
        elementsList.setModel(stringList);
    }
    public void deactivateDelete(boolean flag){
    deleteBtn.setEnabled(flag);
    }
    private void prepareButtons(){
        Icon icon = IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 40, Color.WHITE);
    addBtn.setText("");
        addBtn.setIcon(icon);
        deleteBtn.setText("");
                icon = IconFontSwing.buildIcon(FontAwesome.ERASER, 40, Color.WHITE);
    deleteBtn.setIcon(icon);
    
    }
    public JList<String> getList(){
    return elementsList;
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

        listHolder = new javax.swing.JScrollPane();
        elementsList = new javax.swing.JList<>();
        btnHolder = new javax.swing.JPanel();
        addBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(13, 39, 93));
        setBorder(javax.swing.BorderFactory.createTitledBorder("www"));
        setForeground(new java.awt.Color(255, 255, 255));
        setToolTipText("");
        setMaximumSize(new java.awt.Dimension(327, 6557));
        setMinimumSize(new java.awt.Dimension(158, 276));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(200, 250));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        elementsList.setBackground(new java.awt.Color(194, 184, 184));
        elementsList.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        elementsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        elementsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        elementsList.setOpaque(false);
        listHolder.setViewportView(elementsList);

        add(listHolder, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 17, 184, 160));

        btnHolder.setLayout(new java.awt.GridLayout());

        addBtn.setBackground(new java.awt.Color(154, 214, 6));
        btnHolder.add(addBtn);

        deleteBtn.setBackground(new java.awt.Color(248, 103, 77));
        btnHolder.add(deleteBtn);

        add(btnHolder, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 181, 184, 50));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JPanel btnHolder;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JList<String> elementsList;
    private javax.swing.JScrollPane listHolder;
    // End of variables declaration//GEN-END:variables
}
