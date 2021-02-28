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
package com.max.backgroundlinuxmanager.models;

import com.max.backgroundlinuxmanager.models.entities.AppConfiguration;
import com.max.backgroundlinuxmanager.utils.ManagerFiles;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maximiliano Fernández <thebluemax13 at gmail.com>
 */
public class ConfigurationManager {
    
    private String configFilePath = "";
    private File configFile;
    private AppConfiguration appConfig;
    
    /**
     *
     */
    public ConfigurationManager() {
        if(configFileExist()){
            XMLparse xmlParse = new XMLparse();
            appConfig = xmlParse.unmarshallerConfig(configFile);
        }else {

         configFolderAndFile();
        }
    }
    
    private boolean configFileExist() {
        configFile  = ManagerFiles.getConfigurationFile();
        return configFile.exists() && configFile.isFile();
    }

    /**
     * Crea el archivo y la carpeta con la configuración de la aplicación
     */
    private void configFolderAndFile(){
        File confFile = ManagerFiles.getConfigurationFile();
        if (confFile == null || !confFile.exists()) {
            
            File folderConfig = new File(ManagerFiles.configurationFolderPath());
            folderConfig.mkdir();
            appConfig = new AppConfiguration();
            XMLparse xmlParse = new XMLparse();
            xmlParse.saveXML(new File(ManagerFiles.configurationFolderPath()+File.separator+AppConfiguration.CONFIG_FILE), XMLparse.CONFIG, appConfig);
        }
    }

    /**
     * Getter del objeto AppConfiguration con la configuración actual
     *
     * @return AppConfiguration
     */
    private AppConfiguration getConfigInfo(){
        return appConfig;
    }
    
}
