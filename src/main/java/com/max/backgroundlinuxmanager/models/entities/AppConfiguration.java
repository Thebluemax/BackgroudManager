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
package com.max.backgroundlinuxmanager.models.entities;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maximiliano Fernández <thebluemax13 at gmail.com>
 */
@XmlRootElement(name= "AppConfiguration")
public class AppConfiguration {
    public static String CONFIG_FILE = "config.xml";
    public static String SHADER_DEFAULT_TYPE = "solid";
    public static String DEFAULT_COLOR = "#000000";
    public static int DEFAULT_TRANSITION_DURATION = 300;
    public static int DEFAULT_INTERVAL_CHANGE = 2795;
   // public static String ConfigFile = "config.xml";
    private String[] shaderList  = {"solid","solid"};
    private int defaulTransitionDuration;
    private int defaultIntervalChange;
    private String shaderDefault;
    private String colorDefault;
    
    public AppConfiguration() {
        defaulTransitionDuration = DEFAULT_TRANSITION_DURATION;
        defaultIntervalChange = DEFAULT_INTERVAL_CHANGE;
        shaderDefault = SHADER_DEFAULT_TYPE;
        colorDefault = DEFAULT_COLOR;
    }

    /**
     * @return the shaderList
     */
    public String[] getShaderList() {
        return shaderList;
    }

    /**
     * @param shaderList the shaderList to set
     */
    public void setShaderList(String[] shaderList) {
        this.shaderList = shaderList;
    }

    /**
     * @return the defaulTransitionDuration
     */
    public int getDefaulTransitionDuration() {
        return defaulTransitionDuration;
    }

    /**
     * @param defaulTransitionDuration the defaulTransitionDuration to set
     */
    public void setDefaulTransitionDuration(int defaulTransitionDuration) {
        this.defaulTransitionDuration = defaulTransitionDuration;
    }

    /**
     * @return the defaultIntervalChange
     */
    public int getDefaultIntervalChange() {
        return defaultIntervalChange;
    }

    /**
     * @param defaultIntervalChange the defaultIntervalChange to set
     */
    public void setDefaultIntervalChange(int defaultIntervalChange) {
        this.defaultIntervalChange = defaultIntervalChange;
    }

    /**
     * @return the shaderDefault
     */
    public String getShaderDefault() {
        return shaderDefault;
    }

    /**
     * @param shaderDefault the shaderDefault to set
     */
    public void setShaderDefault(String shaderDefault) {
        this.shaderDefault = shaderDefault;
    }

    /**
     * @return the colorDefault
     */
    public String getColorDefault() {
        return colorDefault;
    }

    /**
     * @param colorDefault the colorDefault to set
     */
    public void setColorDefault(String colorDefault) {
        this.colorDefault = colorDefault;
    }
    
    
    

}
