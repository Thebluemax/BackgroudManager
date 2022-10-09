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
package com.max.backgroundlinuxmanager.views.components;

import com.max.backgroundlinuxmanager.threads.ImageSave;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Maximiliano Fernández thebluemax13 at gmail.com
 */
public class AddingToLibraryDialog extends JDialog {

    private JLabel label;
    private JLabel labelCount;
    private JButton closeBtn;
    private JFrame parent;

    private File[] fileList;

    public AddingToLibraryDialog(JFrame parent) {
        super(parent, "Keep the Image to Folder");
        this.parent = parent;
        JPanel panel = new JPanel();
        closeBtn = new JButton("Close");
        closeBtn.addActionListener(new MyActionListener());
        closeBtn.setEnabled(false);
        label = new JLabel();
        labelCount = new JLabel();

        setSize(400, 200);
        label.setText("Saving Images");
        Container container = getContentPane();
        panel.add(label);
        panel.add(labelCount);
        panel.add(closeBtn);
        container.add(panel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);

    }

    public boolean setList(File[] list) {
        fileList = list;
        return launchWork();
    }
/**
 * Ejecuta el poolthread con la lista de archivos a guardar 
 * @return 
 */
    private boolean launchWork() {
        ExecutorService executor = Executors.newFixedThreadPool(fileList.length);

        ThreadPoolExecutor pool = (ThreadPoolExecutor) executor;

        for (int i = 0; i < fileList.length; i++) {
            executor.submit(new ImageSave(fileList[i]));
        }
        
        while (pool.getActiveCount() != 0) {
            System.err.println(pool.isTerminated() + "*" + pool.getTaskCount() + "/" + pool.getActiveCount());

            labelCount.setText(pool.getTaskCount() + "/" +(pool.getTaskCount()- pool.getActiveCount()));
        }
        executor.shutdown();
        closeBtn.setEnabled(true);
        return true;
    }

    class MyActionListener implements ActionListener {

        //close and dispose of the window.
        public void actionPerformed(ActionEvent e) {
            System.out.println("disposing the window..");
            setVisible(false);
            dispose();
        }

    }
}
