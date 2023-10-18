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
package com.max.backgroundlinuxmanager.models.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Maximiliano Fernández thebluemax13 at gmail.com
 */
public class SlideBackground {

    private String name;
    private int transitionDuration;
    private int imageDuration;
    private Map<String, Integer> startTime;
    private final List<FrameBackground> frames;

    /**
     *
     */
    public SlideBackground() {
        super();
        transitionDuration = AppConfiguration.DEFAULT_TRANSITION_DURATION;
        frames = new ArrayList<>();
    }

    /**
     *
     * @param timestamp
     */
    public void setStartTime(Map<String, Integer> timestamp) {
        startTime = timestamp;
    }

    public void setTransition(int duration) {
        transitionDuration = duration;
    }

    public int getTransitionDuration() {
        return transitionDuration;
    }

    public void setImageduration(int duration) {
        imageDuration = duration;
    }

    public int getImageDuration() {
        return imageDuration;
    }

    /**
     *
     * @param fBG
     */
    public void addFrame(FrameBackground fBG) {
        
        if(!frames.isEmpty()){
            
        } 
        frames.add(fBG);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public List<FrameBackground> getFrames() {
        return frames;
    }

    public Map<String, Integer> getTime() {
        return startTime;
    }
    
    public void clear () {
        frames.clear();
    }
    

}
