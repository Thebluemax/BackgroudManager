 /*
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
package com.max.backgroundlinuxmanager.controllers;

import com.max.backgroundlinuxmanager.utils.XMLparse;
import com.max.backgroundlinuxmanager.models.entities.AppConfiguration;
import com.max.backgroundlinuxmanager.utils.ManagerFiles;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maximiliano Fernández thebluemax13 at gmail.com
 */
public class ConfigurationManager {
    
    private String configFilePath = "";
    private File configFile;
    private AppConfiguration appConfig;
    
    /**
     *
     */
    public ConfigurationManager() {
        configFile  = ManagerFiles.getConfigurationFile();
        configFilePath = ManagerFiles.configurationFolderPath();
        initConf();
    }
    
    private void initConf(){
         if(configFileExist()){
           parseConf();
        }else {
         configFolderAndFile();
        }
    }
    
    private void parseConf() {
         XMLparse xmlParse = new XMLparse();
         appConfig = xmlParse.unmarshallerConfig(configFile);
    }
    
    private boolean configFileExist() {
        return configFile.exists() && configFile.isFile();
    }
    
    private void configFolderAndFile(){
        File confFile = ManagerFiles.getConfigurationFile();
        if (confFile == null || !confFile.exists()) {
            
            File folderConfig = new File(configFilePath);
            folderConfig.mkdir();
            appConfig = new AppConfiguration();
            XMLparse xmlParse = new XMLparse();
            xmlParse.saveXML(new File(getDefaultFilePath()), XMLparse.CONFIG, appConfig);
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
    
    private String getDefaultFilePath(){
        return configFilePath + 
                    File.separator + AppConfiguration.CONFIG_FILE;
    }
}
