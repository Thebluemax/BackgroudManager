/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case ADD_TO_LIBRARY:
                System.out.println("add to library");
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
                   ;
                }
                System.out.println("delete to library");
                break;
        }
       
    }
    
}
