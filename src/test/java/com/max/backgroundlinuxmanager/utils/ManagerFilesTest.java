/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.max.backgroundlinuxmanager.utils;

import java.io.File;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author max
 */
public class ManagerFilesTest {

    public ManagerFilesTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getUserFolder method, of class ManagerFiles.
     */
    @Test
    public void testGetUserFolder() {
        System.out.println("getUserFolder");
        String home = "max";
        String expResult = "/home/" + home;
        String result = ManagerFiles.getUserFolder();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getDefaultImagePath method, of class ManagerFiles.
     */
    @Test
    public void testGetDefaultImagePath() {
        System.out.println("getDefaultImagePath");
        String expResult = "";
        String result = ManagerFiles.getDefaultImagePath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBackgroundsFolder method, of class ManagerFiles.
     */
    @Test
    public void testGetBackgroundsFolder() {
        System.out.println("getBackgroundsFolder");
        File expResult = null;
        File result = ManagerFiles.getBackgroundsFolder();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWallpapersXMLFolder method, of class ManagerFiles.
     */
    @Test
    public void testGetWallpapersXMLFolder() {
        System.out.println("getWallpapersXMLFolder");
        File expResult = null;
        File result = ManagerFiles.getWallpapersXMLFolder();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBackgroundsPath method, of class ManagerFiles.
     */
    @Test
    public void testGetBackgroundsPath() {
        System.out.println("getBackgroundsPath");
        String expResult = "";
        String result = ManagerFiles.getBackgroundsPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getThumbsPath method, of class ManagerFiles.
     */
    @Test
    public void testGetThumbsPath() {
        System.out.println("getThumbsPath");
        String expResult = "";
        String result = ManagerFiles.getThumbsPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConfigurationFile method, of class ManagerFiles.
     */
    @Test
    public void testGetConfigurationFile() {
        System.out.println("getConfigurationFile");
        File expResult = null;
        File result = ManagerFiles.getConfigurationFile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of configurationFolderPath method, of class ManagerFiles.
     */
    @Test
    public void testConfigurationFolderPath() {
        System.out.println("configurationFolderPath");
        String home = "max";
        String expResult = "/home/" + home + "/.local/share/background-manager";
        String result = ManagerFiles.configurationFolderPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getFiles method, of class ManagerFiles.
     */
    @Test
    public void testGetFiles() {
        System.out.println("getFiles");
        File folder = null;
        List<File> files = null;
        ManagerFiles.getFiles(folder, files);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copyFile method, of class ManagerFiles.
     */
    @Test
    public void testCopyFile() throws Exception {
        System.out.println("copyFile");
        File in = null;
        File out = null;
        int expResult = 0;
        int result = ManagerFiles.copyFile(in, out);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteFile method, of class ManagerFiles.
     */
    @Test
    public void testDeleteFile() {
        System.out.println("deleteFile");
        File file = null;
        boolean expResult = false;
        boolean result = ManagerFiles.deleteFile(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSlide method, of class ManagerFiles.
     */
    @Test
    public void testIsSlide() {
        System.out.println("isSlide");
        String filename = "";
        boolean expResult = false;
        boolean result = ManagerFiles.isSlide(filename);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
