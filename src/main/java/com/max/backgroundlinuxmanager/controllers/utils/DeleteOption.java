/*
 * The MIT License
 *
 * Copyright 2020 Maximiliano Fernández thebluemax13 at gmail.com.
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
package com.max.backgroundlinuxmanager.controllers.utils;

import javax.swing.JOptionPane;
import com.max.backgroundlinuxmanager.utils.ManagerFiles;
import com.max.backgroundlinuxmanager.views.components.ImageBlockPane;

/**
 *
 * @author Maximiliano Fernández thebluemax13 at gmail.com
 */
public class DeleteOption {

    private ImageBlockPane blockPane;
    private int dialegButton;

    public DeleteOption(ImageBlockPane blockPane) {
        this.blockPane = blockPane;

    }

    public boolean getResult() {
        dialegButton = JOptionPane.YES_NO_OPTION;
        JOptionPane.showConfirmDialog(null, "Quiere borrar la entrada:" + blockPane.getImage().getName(), "Eliminar Entrada", dialegButton);
        int result = 0;
        if (dialegButton == JOptionPane.YES_OPTION) {

            return ManagerFiles.deleteFile(blockPane.getImage());

        } else {
            return false;
        }

    }

}
