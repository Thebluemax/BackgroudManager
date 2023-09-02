/**
 * The MIT License
 *
 * Copyright 2019 Maximiliano Fernández thebluemax13 at gmail.com.
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
    private MainFrameController mainFrame;
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
        mainFrame = new MainFrameController(appConfig);
        mainFrame.setVisible(true);
    }

    /**
     *   Carga de la configuración
     **/
    public void loadConfig() {
        configManager = new ConfigurationManager();
        appConfig = configManager.getConfig();
    }
}
