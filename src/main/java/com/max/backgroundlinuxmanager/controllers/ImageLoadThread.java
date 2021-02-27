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
package com.max.backgroundlinuxmanager.controllers;

import com.max.backgroundlinuxmanager.views.components.ImageBlockPane;
import java.io.File;
import java.util.List;

/**
 *
 * @author Maximiliano Fernández <thebluemax13 at gmail.com>
 */
public class ImageLoadThread implements Runnable {
    private List<ImageBlockPane> container;

    /**
     *
     * @param path
     * @param list
     */
    public ImageLoadThread(String path, List<ImageBlockPane> list) {
        container = list;
        ImageBlockPane bPanel = new ImageBlockPane(new File(path));
        bPanel.setLabel(path);
        try {
            bPanel.loadImage();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        container.add(bPanel);
    }

    /**
     *
     */
    @Override
    public void run() {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
