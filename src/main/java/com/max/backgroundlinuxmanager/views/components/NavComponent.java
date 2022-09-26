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

import com.max.backgroundlinuxmanager.components.MainFrame.MainFrameListener;
import com.max.backgroundlinuxmanager.BackgroundManager;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Maximiliano Fernández thebluemax13 at gmail.com
 */
public class NavComponent extends JPanel {

    private String[] labelList = {"Save to Wallpaper", "Save to Slide", "Show XmlWallpaper"};
    private String[] bottonRef = {MainFrameListener.SAVE_TO_NEW_WALLPAPER,
        MainFrameListener.NEW_XML_SLIDE,
        MainFrameListener.SHOW_XML_WALLPAPER};
    private List<JButton> buttonList;

    /**
     *
     */
    public NavComponent() {
        setBackground(new java.awt.Color(13, 39, 93));
        setBorder(javax.swing.BorderFactory.createCompoundBorder());
        setForeground(new java.awt.Color(255, 255, 255));
        setToolTipText("");
        setMaximumSize(new java.awt.Dimension(1750, 50));
        setMinimumSize(new java.awt.Dimension(450, 50));
        setOpaque(false);
        
        java.awt.GridLayout flowLayout1 = new java.awt.GridLayout(1, 2);
        setLayout(flowLayout1);
        buttonList = new ArrayList<>();
        setButtons();

    }

    private void setButtons() {
        for (int i = 0; i < labelList.length; i++) {
            JButton b = new JButton(labelList[i]);
            b.setSize(450, 50);
            b.setActionCommand(bottonRef[i]);
            buttonList.add(b);
            add(b);
        }
    }
    public void setDisabledNav(boolean status){
        for(int i= 0; i < buttonList.size(); i++){
            buttonList.get(i).setEnabled(status);
        }
    }

    /**
     *
     * @param flag
     */
    public void setVisibility(boolean flag) {

        for (int i = 0; i < buttonList.size(); i++) {

            if (i < 3) {
                buttonList.get(i).setVisible(flag);
            } else {
                buttonList.get(i).setEnabled(flag ? false : true);
            }
        }
    }
    
    public void setEnabled(boolean flag) {

        for (int i = 0; i < buttonList.size(); i++) {
           
          buttonList.get(i).setEnabled(flag);
           
        }
    }

    /**
     *
     * @param actionListener
     */
    public void addActionListener(ActionListener actionListener) {
        for (int i = 0; i < buttonList.size(); i++) {
            buttonList.get(i).addActionListener(actionListener);
        }
    }

}
