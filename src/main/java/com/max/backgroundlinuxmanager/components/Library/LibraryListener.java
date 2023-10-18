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
package com.max.backgroundlinuxmanager.components.Library;

import com.max.backgroundlinuxmanager.controllers.utils.DeleteOption;
import com.max.backgroundlinuxmanager.utils.ManagerFiles;
import com.max.backgroundlinuxmanager.views.components.ImageBlockPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
/**
 *
 * @author max
 */
public class LibraryListener implements ActionListener{
     public static final String ADD_TO_LIBRARY = "ADD_TO_LIBRARY";
     public static final String DELETE_OF_LIBRARY = "DELETE_OF_LIBRARY";
     private LibraryComponent library;


    public LibraryListener(LibraryComponent library) {
        this.library = library;
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ADD_TO_LIBRARY:
                 library.addToLibrary();
                break;
            case DELETE_OF_LIBRARY:
                JButton block = (JButton) e.getSource();
                ImageBlockPane blockImage = (ImageBlockPane) block.getParent();

                System.out.println(blockImage.getImage().getName());
                DeleteOption option = new DeleteOption(blockImage);

                if (option.getResult()) {
                    ManagerFiles.deleteFile(blockImage.getImage());
                    library.remove(blockImage);
                }
                System.out.println("delete to library");
                break;
        }
       
    }
    
}
