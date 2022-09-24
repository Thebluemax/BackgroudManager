/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.max.backgroundlinuxmanager.components.Library;

import com.max.backgroundlinuxmanager.controllers.utils.FileChooserController;
import com.max.backgroundlinuxmanager.components.MainFrame.MainFrameController;
import com.max.backgroundlinuxmanager.models.entities.AppConfiguration;
import com.max.backgroundlinuxmanager.utils.ManagerFiles;
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
public class LibraryComponent extends LibrayPanel {

    private int width;
    private int heigth;
    private int column = 0;
    private int rows = 0;
    private List<File> cachedFilesList;
    protected List<ImageBlockPane> blockList;
    protected JPanel container;
    private MainFrameController frame;

    public LibraryComponent(AppConfiguration appConfig) {
        super();
        column = 5;
        blockList = new ArrayList<>();
        container = new JPanel();
        cachedFilesList = new ArrayList();
        init();
        setListeners();
        getBackgroundLibrary();
    }

    public void init() {
        width = getWidth();
        heigth = getHeight();
        GridLayout layout = new GridLayout(rows, column, 3, 3);
        container.setLayout(layout);
        container.setBackground(new AppColors().generalColor());
        scrollContent.setSize(width, heigth);
        scrollContent.setLocation(0, 50);
        scrollContent.setBackground(Color.red);
        scrollContent.setViewportView(container);
        scrollContent.setBackground(new AppColors().foregroundColorGeneral());
    }

   
    /**
     * Agrega un objeto minuatura al contenedor
     *
     * @param jp
     */
    public void addToPanel(JPanel jp) {
        blockList.add((ImageBlockPane) jp);
        container.add(jp);

    }

    /**
     *
     * @param pane
     */
    public void remove(ImageBlockPane pane) {

        blockList.remove(pane);
        int pos = container.getComponentZOrder(pane);
        System.out.println("" + pos);
        container.remove(pos);
        container.revalidate();
        container.repaint();

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

    /**
     *
     * @return
     */
    public List<ImageBlockPane> getAllImgeBlock() {
        return blockList;
    }

    /**
     *
     * @param cachedFile
     */
    public void checkForTypeFile(File cachedFile, int xRef, int yRef) {

        if (!ImageBlockPane.isSlide(cachedFile.getName())) {
            addToPanel(new ImageBlockPane(cachedFile, xRef, yRef));
        } else {
            addToPanel(new ImageBlockPane(new File("src/assets/slide.png"), xRef, yRef));
        }
    }

    /**
     *
     */
    public void getBackgroundLibrary() {
        File folder = ManagerFiles.getBackgroundsFolder();
        cachedFilesList.clear();
        ManagerFiles.getFiles(folder, cachedFilesList);
        int xRef = width / column;
        int yRef = (int) Math.round(xRef / 1.4);
        for (int i = 0; i < cachedFilesList.size(); i++) {
            checkForTypeFile(cachedFilesList.get(i), xRef, yRef);
        }

        blockList.forEach(block -> {
            block.loadImage();

            block.setListener(new LibraryListener(this));
        });
    }

    public boolean addToLibrary() {
        boolean status = false;
        FileChooserController fChooser = new FileChooserController(frame, true);
        File[] resources = fChooser.getFiles();

        if (resources != null) {
            status = saveFilesInBGFolder(resources);
        }
        if (status) {
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

            filelist.add(list[i]);
        }
        getBackgroundLibrary();
    }

    public void setListeners() {
        addButton.setActionCommand(LibraryListener.ADD_TO_LIBRARY);
        addButton.addActionListener(new LibraryListener(this));
    }

}