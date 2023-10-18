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

import com.max.backgroundlinuxmanager.views.components.ImageBlockPane;

/**
 *
 * @author Maximiliano Fernández thebluemax13 at gmail.com
 */
public class FrameBackground {

    private String filePath;
    private int durationImage;
    private int durationTransition;

    public int getDurationTransition() {
        return durationTransition;
    }

    public void setDurationTransition(int durationTransition) {
        this.durationTransition = durationTransition;
    }
    private String pathTo;

    /**
     *
     */
    public FrameBackground() {
    }

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return the duration
     */
    public int getDuration() {
        return durationImage;
    }

    /**
     * @param Duration the duration to set
     */
    public void setDuration(int Duration) {
        this.durationImage = Duration;
    }

    /**
     * @return the pathTo
     */
    public String getPathTo() {
        return pathTo;
    }

    /**
     * @param pathTo the pathTo to set
     */
    public void setPathTo(String pathTo) {
        this.pathTo = pathTo;
    }

    public static FrameBackground factory(ImageBlockPane imageBlock, int $duration) {
        FrameBackground frame = new FrameBackground();
        frame.setFilePath(imageBlock.getFilePath());
        frame.setDurationTransition($duration);
        return frame;
    }

}
