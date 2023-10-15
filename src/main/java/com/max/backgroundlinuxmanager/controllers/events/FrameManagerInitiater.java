/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.max.backgroundlinuxmanager.controllers.events;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author max
 */
public class FrameManagerInitiater {
    private List<FrameManagerEventInterface> listeners = new ArrayList<>();
    
    public void addListener(FrameManagerEventInterface listener) {
        listeners.add(listener);
    }
    
    public void run() {
    for (FrameManagerEventInterface hl : listeners)
            hl.closeAction();
    }
}
