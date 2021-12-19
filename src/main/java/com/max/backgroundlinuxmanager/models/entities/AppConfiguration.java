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
package com.max.backgroundlinuxmanager.models.entities;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maximiliano Fernández thebluemax13 at gmail.com
 */
@XmlRootElement(name= "AppConfiguration")
public class AppConfiguration {

    /**
     * Nombre del archivo de configuración de la app
     */
    public static String CONFIG_FILE = "config.xml";

    /** 
     * Valor por defecto del shader
     */
    public static String DEFAULT_SHADER_TYPE = "solid";

    /**
     * Valor por defecto de la opción de wallpaper
     */
    public static String DEFAULT_OPTION = "zoom";    

    /**
     * Valor color por defecto
     */
    public static String DEFAULT_COLOR = "#000000";

    /**
     * Tiempo de duración de una transición en milisegundos
     */
    public static int DEFAULT_TRANSITION_DURATION = 300;

    /**
     * Tiempo de duración del intervalo de cambio
     */
    public static int DEFAULT_INTERVAL_CHANGE = 2795;
    /**
     * Sufijo del archivo de wallpapers
     */
    public static String WALLPAPER_SUFIX = "-wallpaper.xml";

    /**
     * Lista de opciones por defecto  
     */
    public static String[] OPTIONS_WALLPAPER = { "none", "wallpaper", "centered",
                                                "scaled", "stretched", "zoom",
                                                "spanned" };

    /**
     * Shemas de los tipos de shaders
     * schemas/org.gnome.desktop.background.lockdialog.gschema.xml.in
     */
    public static String[] SHADER_TYPE  = {"solid","horizontal","vertical"};

    /**
     *
     */
    public  static int[] YEAR = {2016, 2017,2018,2019,2020,2021,2022};
    
    private int defaulTransitionDuration;
    private int defaultIntervalChange;
    private String shaderDefault;
    private String colorDefault;
    
    
    /**
     *
     */
    public AppConfiguration() {
        defaulTransitionDuration = DEFAULT_TRANSITION_DURATION;
        defaultIntervalChange = DEFAULT_INTERVAL_CHANGE;
        shaderDefault = DEFAULT_SHADER_TYPE;
        colorDefault = DEFAULT_COLOR;
    }

    /**
     * @return AppConfiguration.SHADER_TYPE La lista de tipos de shaders
     */
    public String[] getShaderList() {
        return AppConfiguration.SHADER_TYPE;
    }
    /**
     * Obtiene la lista de opciones posibles para los wallpapers
     * @return  AppConfiguration.OPTIONS_WALLPAPER la lista de opciones
     */
    public String[] getOptionList() {
        return AppConfiguration.OPTIONS_WALLPAPER;
    }

    

    /**
     * Devuelve un integer que es el tiempo de duración de una transición
     * 
     * @return defaulTransitionDuration integer con la duración 
     * de las transiciones
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
     * Devuelve un integer que es el valor de la duración enter cambio de fondo
     * @return defaultIntervalChange tiempo de duración entre imagenes
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
