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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Maximiliano Fernández <thebluemax13 at gmail.com>
 */
@XmlRootElement(name= "wallpaper")
@XmlType(propOrder = {"name", "filename", "options", "pcolor", "scolor", "shaderType"})
public class Wallpaper {
    private String name;
    private String filename;
    private String options;
    private String pcolor;
    private String scolor;
    private String shader_type;

    public Wallpaper() {
    }
    
    public Wallpaper(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    @XmlElement(name="name")
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the filename
     */
    @XmlElement(name="filename")
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
 @XmlElement(name="options")
    public String getOptions() {
        return options;
    }

    /**
     * @param filename the filename to set
     */
    public void setOptions(String option) {
        this.options = option;
    }
    /**
     * @return the pcolor
     */
    @XmlElement(name="pcolor")
    public String getPcolor() {
        return pcolor;
    }

    /**
     * @param pcolor the pcolor to set
     */
    public void setPcolor(String pcolor) {
        this.pcolor = pcolor;
    }

    /**
     * @return the scolor
     */
    @XmlElement(name="scolor")
    public String getScolor() {
        return scolor;
    }

    /**
     * @param scolor the scolor to set
     */
    public void setScolor(String scolor) {
        this.scolor = scolor;
    }

    /**
     * @return the shader_type
     */
    @XmlElement(name="shader_type")
    public String getShaderType() {
        return shader_type;
    }

    /**
     * @param shaderType the shader_type to set
     */
    public void setShaderType(String shaderType) {
        this.shader_type = shaderType;
    }
    
}
