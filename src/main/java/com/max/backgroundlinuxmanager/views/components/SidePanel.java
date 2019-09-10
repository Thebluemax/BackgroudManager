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

import java.awt.event.MouseAdapter;

/**
 *
 * @author Maximiliano Fernández <thebluemax13 at gmail.com>
 */
public class SidePanel extends javax.swing.JPanel {

    /**
     * Creates new form SidePanel
     */
    public static String LIBRARY_TAG = "Librería";
    public static String FATHER = "father";
    public static String CHILD = "child";

    private ListAndButtons fatherList;
    private ListAndButtons childList;
    private String[] wallpapersList;
    private String[] wallpaperName;
    public SidePanel() {
        this.setOpaque(false);
        initComponents();
        fatherList = new ListAndButtons();
        fatherList.setSize(200, 300);
        childList = new ListAndButtons();
       add(fatherList);
        add(childList);
    }
    public void setListFather(String[] list) {
    fatherList.setList(list);
    }
    public void setChildLidt(String[] list) {
        childList.setList(list);
    }
    public void toggleLibrary(boolean flag){
        childList.setVisible(flag);
        fatherList.deactivateDelete(flag);
    }
    public void addListMouseEvents(MouseAdapter mouseAdapter){
        fatherList.getList().setName(FATHER);
        childList.getList().setName(CHILD);
    fatherList.addListMouseEvents(mouseAdapter);
    childList.addListMouseEvents(mouseAdapter);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
