package com.max.backgroundlinuxmanager.components.XmlWallpaperComponent;

import com.max.backgroundlinuxmanager.exceptions.BackgroundException;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SlideEventsListeners implements MouseListener {

    private SlideEditPanel slidePanel;

    public SlideEventsListeners( SlideEditPanel slidePanel) {
        this.slidePanel = slidePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            slidePanel.save();
        } catch (BackgroundException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
