/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.max.backgroundlinuxmanager.components.Library;

import com.max.backgroundlinuxmanager.controllers.utils.FileChooserController;
import com.max.backgroundlinuxmanager.components.MainFrame.MainFrameController;
import com.max.backgroundlinuxmanager.models.entities.AppConfiguration;
import com.max.backgroundlinuxmanager.views.components.AddingToLibraryDialog;
import com.max.backgroundlinuxmanager.views.components.AppColors.AppColors;
import com.max.backgroundlinuxmanager.views.components.ImageBlockPane;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author max
 */
public class LibraryController extends LibrayPanel {

    public static String ADD = "ADD_LIBRARY";
    private int width;
    private int heigth;
    private int column = 0;
    private int rows = 0;
    private List<File> cachedFilesList;
    protected List<ImageBlockPane> imageList;
    protected JPanel container;
    private MainFrameController frame;

    public LibraryController(AppConfiguration appConfig) {
        super();
     //   this.frame = frame;
     //   width = frame.getWidth() - 80;
     //   heigth = frame.getHeight() - 80;
        column = 5;
        imageList = new ArrayList<>();
        container = new JPanel();

    }

    public void inti() {
        GridLayout layout = new GridLayout(rows, column, 3, 3);
        container.setLayout(layout);
        container.setBackground(new AppColors().generalColor());
        scrollContent.setSize(width, heigth - 50);
        scrollContent.setLocation(0, 50);
        scrollContent.setBackground(Color.red);
        scrollContent.setViewportView(container);
        scrollContent.setBackground(new AppColors().foregroundColorGeneral());
    }

    /**
     *
     * Agregar un nuevo contenedor de miniaturas al viewport
     *
     * @param jPane Contenedor con las miniaturas
     */
    public void setViewPortContainer(JPanel jPane) {
        scrollContent.setViewportView(null);
        scrollContent.setViewportView(jPane);
    }

    /**
     * Agrega un objeto minuatura al contenedor
     *
     * @param jp
     */
    public void addToPanel(JPanel jp) {
        imageList.add((ImageBlockPane) jp);
        container.add(jp);

    }

    public void remove(ImageBlockPane pane) {
        imageList.remove(pane);
        container.remove(pane);

    }

    /**
     * return the path from the seledted item
     *
     * @return
     */
    public String getSelected() {
        String r = "";
        for (int i = 0; i < container.getComponentCount(); i++) {
            ImageBlockPane block = (ImageBlockPane) container.getComponent(i);
            if (block.isChecked()) {
                r = block.getFilePath();
            }
        }
        return r;
    }

    public List<ImageBlockPane> getAllImgeBlock() {
        return imageList;
    }

    public void checkForTypeFile(File cachedFile) {
        int xRef = width / column;
        int yRef = (int) Math.round(xRef / 1.4);
        if (!isSlide(cachedFile.getName())) {
            addToPanel(new ImageBlockPane(cachedFile, xRef, yRef));
        } else {
            addToPanel(new ImageBlockPane(new File("src/assets/slide.png"), xRef, yRef));
        }

    }

    /**
     * Check if the background file is a xml document
     *
     * @param filename
     * @return boolean
     */
    public boolean isSlide(String filename) {

        String name = filename;
        name = name.substring(name.length() - 3, name.length());
        return (name.compareTo("xml") == 0);
    }

    /**
     *
     */
    public void getBackgroundLibrary(List<File> cachedFilesList) {

        for (int i = 0; i < cachedFilesList.size(); i++) {

            checkForTypeFile(cachedFilesList.get(i));

        }

        getAllImgeBlock().forEach(block -> {
            block.loadImage();
            // block.setListener(this);
        });
    }

    public boolean addToLibrary() {
        boolean status = false;
        FileChooserController fChooser = new FileChooserController(frame, true);
        File[] resources = fChooser.getFiles();

        if (resources != null) {
            status = saveFilesInBGFolder(resources);
        }
        if(status){
            refresBackgroundLibrary(resources);
        }
        return status;
    }
    /**
     *
     * @param files
     */
    public boolean saveFilesInBGFolder(File[] files) {

        AddingToLibraryDialog dialog = new AddingToLibraryDialog(frame);
        return dialog.setList(files);

    }

     private void refresBackgroundLibrary(File[] list) {
        List<File> filelist = new ArrayList<File>();
        for (int i = 0; i < list.length; i++) {

            //cachedFilesList.add(list[i]);
            filelist.add(list[i]);

        }
        getBackgroundLibrary(filelist);
        
    }
    public void setListeners(ActionListener action) {
        addButton.setActionCommand(ADD);
        addButton.addActionListener(action);
    }

}
