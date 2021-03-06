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
package com.max.backgroundlinuxmanager.views;

import com.max.backgroundlinuxmanager.views.components.AppColors.AppColors;
import com.max.backgroundlinuxmanager.views.components.ImageBlockPane;
import com.max.backgroundlinuxmanager.views.components.SidePanel;
import com.max.backgroundlinuxmanager.views.components.NavComponent;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Maximiliano Fernández <thebluemax13 at gmail.com>
 */
public class MainJFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainJFrame
     */
    private static String ADD_TO_LIBRARY = "addToLibrary";
    private static String GET_WALLPAPER = "getWallPaper";
    private static String LIBRARY_TAG ="Libreria"; 

    private JPanel container;
    private File[] fileList;
    private DefaultListModel collectionName;
    private DefaultListModel wallpaperName;
    private SidePanel sideBar;
    private NavComponent toolBar; 
    private List<ImageBlockPane>  imageList ;
    
    
/**
 *  contructor 
 */
    public MainJFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground( new AppColors().generalColor() );
        imageList = new ArrayList<>();
        initComponents();
        initSidebar();  
        initNavBar();  
   
        container = new JPanel();
        container.setSize(510, 1024);
        container.setLayout(new GridLayout(0,5));
        container.setBackground(new AppColors().generalColor());
        scrollContent.setViewportView(container);
        scrollContent.setBackground(new AppColors().generalColor());
        pack();
    }
    /**
     * Inicio de la barra lateral 
     */
    private void initSidebar(){
        sideBar = new SidePanel();
        sideBar.setLibraryView(true);
        getContentPane().add(sideBar,  new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 180, 250));
    }
    /**
     * Inicio de la barra de herramientas
     */
   private void initNavBar(){
        toolBar = new NavComponent();
        toolBar.setVisibility(true);
        getContentPane().add(toolBar,  new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 250, 150, 200));
    }
    
    /**
     * setear os listeners
     * 
     * @param aListener 
     */
    public void setListeners(ActionListener aListener) {
        toolBar.addActionListener(aListener);
    }
    /**
     * 
     * Agregar un nuevo contenedor de miniaturas al viewport
     * @param jPane Contenedor con las miniaturas
     */
    public void setViewPortContainer (JPanel jPane){
        scrollContent.setViewportView(null);
        scrollContent.setViewportView(jPane);
    }
    /**
     * 
     * @param visibility 
     */
    public void setLibraryView(boolean visibility){
        sideBar.setLibraryView(visibility);
        toolBar.setVisibility(visibility);
        if (visibility) {
            setViewPortContainer(container);         
        } else {
                scrollContent.setViewportView(null);
        }
    }
    /**
     * Agrega un objeto minuatura al contenedor
     * @param jp 
     */
    public void addToPanel (JPanel jp){
        imageList.add((ImageBlockPane)jp);
        container.add(jp);
    }
    
    public void remove(ImageBlockPane pane){
        imageList.remove(pane);
        container.remove(pane);
        this.pack();
    
    }
    /**
     * return the path from the seledted item 
     * @return 
     */
    public String getSelected(){
        String r="";
        for (int i = 0; i < imageList.size(); i++) {
            if (imageList.get(i).isChecked()) {
             r =    imageList.get(i).getFilename();
            }
        }
    return r;
    }
    public List<ImageBlockPane> getAllImgeBlock(){
        return imageList;
    }
    /**
     * agrega un nuevo elemento a la lista secundaria 
     * 
     * @param nameWallpaper 
     */
    public void setWallpaperList(String[] nameWallpaper){
       sideBar.setChildLidt(nameWallpaper);
     
    }
    
    public void setList(String folder, String[] listFile){
        sideBar.setListFather(listFile);
       
        jLabel2.setText("Active Folder: "+folder);
        
    }
    public void addSideBarEvents(MouseAdapter mouseAdapter){
        sideBar.addListMouseEvents(mouseAdapter);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        scrollContent = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Background Manager");
        setBackground(new java.awt.Color(18, 39, 93));
        setBounds(new java.awt.Rectangle(0, 0, 800, 500));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(206, 223, 253));
        setLocation(new java.awt.Point(100, 100));
        setMaximumSize(new java.awt.Dimension(1200, 1000));
        setMinimumSize(new java.awt.Dimension(900, 600));
        setName("principalFrame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(950, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(1, 1, 1));
        jLabel2.setForeground(new java.awt.Color(253, 251, 251));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setLabelFor(scrollContent);
        jLabel2.setText("jLabel2");
        jLabel2.setAutoscrolls(true);
        jLabel2.setRequestFocusEnabled(false);
        jLabel2.setVerifyInputWhenFocusTarget(false);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 510, 700, 50));

        scrollContent.setBorder(null);
        scrollContent.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollContent.setToolTipText("");
        scrollContent.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        scrollContent.setAutoscrolls(true);
        scrollContent.setMaximumSize(new java.awt.Dimension(1000, 800));
        scrollContent.setMinimumSize(new java.awt.Dimension(600, 400));
        scrollContent.setPreferredSize(new java.awt.Dimension(760, 500));
        getContentPane().add(scrollContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 5, 750, 500));

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
  //*  public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
   /*    try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>/*/

        /* Create and display the form */
    //    java.awt.EventQueue.invokeLater(new Runnable() {
      //      public void run() {
      //          new MainJFrame().setVisible(true);
      //      }
      //  });
 //   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane scrollContent;
    // End of variables declaration//GEN-END:variables
}
