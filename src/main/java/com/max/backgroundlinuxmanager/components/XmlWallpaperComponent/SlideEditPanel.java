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
package com.max.backgroundlinuxmanager.components.XmlWallpaperComponent;

import com.max.backgroundlinuxmanager.exceptions.BackgroundException;
import com.max.backgroundlinuxmanager.models.XMLDOMBackground;
import com.max.backgroundlinuxmanager.models.entities.AppConfiguration;
import com.max.backgroundlinuxmanager.models.entities.FrameBackground;
import com.max.backgroundlinuxmanager.models.entities.SlideBackground;
import com.max.backgroundlinuxmanager.models.entities.Wallpaper;
import com.max.backgroundlinuxmanager.utils.ManagerFiles;
import com.max.backgroundlinuxmanager.views.components.ImageBlockPane;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author Maximiliano Fernández thebluemax13 at gmail.com
 */
public class SlideEditPanel extends javax.swing.JPanel {

    /**
     * Creates new form SlideEditPanel
     */
    private final JPanel container;
    private final SlideBackground slideBackground;
    private final XmlWallpaperComponent wpc;

    public SlideEditPanel(Dimension dimension, XmlWallpaperComponent wpc) {
        initComponents();
        buildCombos();
        this.wpc = wpc;
        jScrollPane1.setBackground(new Color(33,44,120));
        slideBackground = new SlideBackground();
        container = new JPanel(new GridLayout(0, 6));
        //jScrollPane1.setPreferredSize(new Dimension(1000,100));
        jScrollPane1.setViewportView(container);
        duratonSlide.setPaintTicks(true);
        duratonSlide.setSnapToTicks(true);
        duratonSlide.setMajorTickSpacing(20);
        duratonSlide.setMinorTickSpacing(5);
        duratonSlide.setValue(AppConfiguration.DEFAULT_INTERVAL_CHANGE);
        transitionSlide.setValue(AppConfiguration.DEFAULT_TRANSITION_DURATION);

    }

    private void buildCombos() {

        Calendar date = Calendar.getInstance();

        int year = date.get(Calendar.YEAR);

        int refYear = year - 10;

        for (int y = 0; y < 20; y++) {
            yearCombo.addItem(Integer.toString(refYear + y));
        }
        yearCombo.setSelectedItem(Integer.toString(year));

        for (int r = 0; r < 31; r++) {
            dayCombo.addItem(Integer.toString(r + 1));
        }
        for (int d = 0; d < 24; d++) {
            monthCombo.addItem(Integer.toString(d + 1));
        }
        for (int a = 0; a < 24; a++) {
            hourCombo.addItem(Integer.toString(a + 1));
        }

        for (int i = 0; i < 60; i++) {
            secondCombo.addItem(Integer.toString(i + 1));
            minuteCombo.addItem(Integer.toString(i + 1));
        }
    }

    public void getTimes() {

    }

    public void save() throws BackgroundException {
        slideBackground.setName("test_44");
        slideBackground.setYear(2023);
        slideBackground.setMonth(10);
        slideBackground.setDay(2);
        slideBackground.setTransition(5);
        slideBackground.setImageduration(1000);
        String path = ManagerFiles.getBackgroundsSlidePath() + "/" + slideBackground.getFilename();
        Wallpaper wp = Wallpaper.factory(path);
        try {
            ManagerFiles.writeFile(slideBackground.toString(), path);
        } catch (IOException e) {
            throw new BackgroundException(e,"sdsdfdsd");
        }
        System.out.println(slideBackground.toString());
        wpc.saveSlideInCurrent(wp);
    }

    protected void setValues(XMLDOMBackground slidemodel) {
        SlideBackground back = slidemodel.getSlideBackground();

        yearCombo.setSelectedItem(back.getTime().get("year").toString());
        monthCombo.setSelectedItem(back.getTime().get("month").toString());
        dayCombo.setSelectedItem(back.getTime().get("day").toString());
        hourCombo.setSelectedItem(back.getTime().get("hour").toString());
        minuteCombo.setSelectedItem(back.getTime().get("minute").toString());
        secondCombo.setSelectedItem(back.getTime().get("second").toString());
        duratonSlide.setValue(back.getImageDuration());
        transitionSlide.setValue(back.getTransitionDuration());

        slideName.setText(back.getName());
        slideName.setEditable(false);
    }

    protected void setMouseAdapter(MouseAdapter adapter) {
        slideName.addMouseListener(adapter);
    }

    protected Dimension getContainerBounds() {
        return new Dimension(jScrollPane1.getWidth(), jScrollPane1.getHeight());
    }

    protected void addToPanel(Component comp) {
        ImageBlockPane imageBlock = (ImageBlockPane) comp;
        imageBlock.erraseBtnVisible(false);
        imageBlock.loadImage();
        slideBackground.addFrame(FrameBackground.factory(imageBlock, 30));
        container.add(comp);
    }

    public void clear() {
        container.removeAll();
        slideBackground.clear();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setLayout(new GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();

        slideName = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();

        slideDurationHolder = new javax.swing.JPanel();
        durationLabel = new javax.swing.JLabel();
        duratonSlide = new javax.swing.JSlider(0, 2000, 1500);

        slideTransitionFrame = new javax.swing.JPanel();
        transitionSlideLabel = new javax.swing.JLabel();
        transitionSlide = new javax.swing.JSlider(0, 15, 3);

        dateFrame = new javax.swing.JPanel();

        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        yearCombo = new javax.swing.JComboBox<>();
        monthCombo = new javax.swing.JComboBox<>();
        dayCombo = new javax.swing.JComboBox<>();
        hourCombo = new javax.swing.JComboBox<>();
        minuteCombo = new javax.swing.JComboBox<>();
        secondCombo = new javax.swing.JComboBox<>();

        jScrollPane1 = new javax.swing.JScrollPane();

        btnSave = new javax.swing.JButton();
        btnSave.setText("Save");
        btnSave.addMouseListener(new SlideEventsListeners(this));

        setLayout(new java.awt.GridBagLayout());

        slideName.setText("jTextField1");
        slideName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slideNameActionPerformed(evt);
            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        add(slideName, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        durationLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        durationLabel.setText("Duration Slide image");
        add(durationLabel, gridBagConstraints);
       // gridBagConstraints.insets = new java.awt.Insets(12, 31, 12, 31);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        add(slideDurationHolder, gridBagConstraints);
        slideDurationHolder.setLayout(new BorderLayout());

        jPanel3.setLayout(new java.awt.GridLayout(2, 1));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
       // add(durationLabel, gridBagConstraints);

        duratonSlide.setPaintTicks(true);
        duratonSlide.setPaintLabels(true);
        duratonSlide.setMajorTickSpacing(100);
        duratonSlide.setMinorTickSpacing(10);
        //duratonSlide.setBounds(new Rectangle(600,20));
        duratonSlide.setValue(1500);
        slideDurationHolder.add(duratonSlide);
        duratonSlide.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                int value = source.getValue();
                System.out.println("Valor seleccionado: " + value);
            }
        });
         gridBagConstraints.gridx = 0;
          gridBagConstraints.gridy = 3;
        //  gridBagConstraints.ipadx = 518;
        //  gridBagConstraints.ipady = 7;
          gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

       // jPanel3.add(slideDurationHolder);

        slideTransitionFrame.setLayout(new java.awt.BorderLayout());

        transitionSlideLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        transitionSlideLabel.setText("Duration Transition");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 3;
        add(transitionSlideLabel, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 3;
        //  gridBagConstraints.ipadx = 518;
        //  gridBagConstraints.ipady = 7;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        add(slideTransitionFrame, gridBagConstraints);

        transitionSlide.setPaintTicks(true);
        transitionSlide.setPaintLabels(true);
        transitionSlide.setMajorTickSpacing(1);
        transitionSlide.setMinorTickSpacing(10);
        slideTransitionFrame.add(transitionSlide);

     //   slideTransitionFrame.add(slideTransitionFrame);

        gridBagConstraints = new java.awt.GridBagConstraints();

        dateFrame.setLayout(new java.awt.GridLayout(2, 5));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Year");
        dateFrame.add(jLabel5);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Month");
        dateFrame.add(jLabel6);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Day");
        dateFrame.add(jLabel7);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Hour");
        dateFrame.add(jLabel8);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Minute");
        dateFrame.add(jLabel9);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Seconds");
        dateFrame.add(jLabel10);

        dateFrame.add(yearCombo);

        dateFrame.add(monthCombo);

        dateFrame.add(dayCombo);

        dateFrame.add(hourCombo);

        dateFrame.add(minuteCombo);

        dateFrame.add(secondCombo);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        add(dateFrame, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.weighty = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        add(jScrollPane1, gridBagConstraints);


        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        add(btnSave, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void slideNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slideNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_slideNameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> dayCombo;
    private javax.swing.JSlider duratonSlide;
    private javax.swing.JComboBox<String> hourCombo;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel durationLabel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel transitionSlideLabel;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel dateFrame;
    private javax.swing.JPanel slideDurationHolder;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel slideTransitionFrame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> minuteCombo;
    private javax.swing.JComboBox<String> monthCombo;
    private javax.swing.JComboBox<String> secondCombo;
    private javax.swing.JTextField slideName;
    private javax.swing.JSlider transitionSlide;
    private javax.swing.JComboBox<String> yearCombo;
    // End of variables declaration//GEN-END:variables
}
