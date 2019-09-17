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

import com.max.backgroundlinuxmanager.models.entities.SlideBackground;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author Maximiliano Fernández <thebluemax13 at gmail.com>
 */
public class XMLDOMBackground {
    private Document xmlDocument;
    private static final String STATIC_TAG = "static";
    private static final String TRANSITION_TAG = "transition";
    private static final String DURATION_TAG = "static";
    private static final String FILE_TAG = "file";
    private static final String FROM_TAG = "from";
    private static final String TO_TAG = "to";
    private static final String START_TIME_TAG = "starttime";
   // private static final String STATIC_TAG = "static";
    private SlideBackground slideBackground;
    private Element root;


    public XMLDOMBackground(File xmlFile ) {
        try {
            SAXBuilder builder = new SAXBuilder();
            xmlDocument = builder.build(xmlFile);
           root  = xmlDocument.getRootElement();
            slideBackground = new SlideBackground();
            setTime();
            poblateList();
        } catch (JDOMException ex) {
            Logger.getLogger(XMLDOMBackground.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLDOMBackground.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void setTime(){
        Element starttime = root.getChild(START_TIME_TAG);
        List<Element> tempList = starttime.getChildren();
        Map<String, Integer> tempMap = new HashMap<>();
        for (int i = 0; i < tempList.size(); i++) {
            Element get = tempList.get(i);
            tempMap.put(get.getName(), Integer.getInteger(get.getText()));
            
        }
        slideBackground.setStartTime(tempMap);
    }
    private void poblateList(){
    List<Element> staticItem = root.getChildren(STATIC_TAG);
     System.out.println(staticItem.size());
        for (int i = 0; i < staticItem.size(); i++) {
            System.out.println(staticItem.get(i).getChild(FILE_TAG).getText());
        }
    }
   
    
}
