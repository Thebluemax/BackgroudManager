/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.max.backgroundlinuxmanager;

import com.max.backgroundlinuxmanager.components.MainFrame.MainFrameController;
import com.max.backgroundlinuxmanager.controllers.ConfigurationManager;
import com.max.backgroundlinuxmanager.models.entities.AppConfiguration;

/**
 *
 * @author max
 */
public class BackgroundManager  {


    private ConfigurationManager configManager;
    private MainFrameController frame;
    private AppConfiguration appConfig;

    /**
     * Initi app
     */
    public void initApp() {
        initComponents();
        loadConfig();
    }
/**
 * Inicio del componente principal
 */
    private void initComponents() {
        frame = new MainFrameController(appConfig);
        frame.setVisible(true);
    }

    /**
     *   Carga de la configuración
     **/
    public void loadConfig() {
        configManager = new ConfigurationManager();
        appConfig = configManager.getConfig();
    }
}
