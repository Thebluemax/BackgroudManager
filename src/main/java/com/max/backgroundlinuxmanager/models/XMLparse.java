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
import com.max.backgroundlinuxmanager.models.entities.Wallpaper;
import com.max.backgroundlinuxmanager.models.entities.Wallpapers;
import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Maximiliano Fernández <thebluemax13 at gmail.com>
 */
public class XMLparse {

    /**
     *
     */
    public static int CONFIG = 1;

    /**
     *
     */
    public static int BACKGROUNDS = 0;
    
    private JAXBContext jaxbContest;

    /**
     *
     */
    public XMLparse() {
    }
  /**
   * 
   * @param type
   * @return 
   */
    public static JAXBContext getJaxbContext(int type) {

        JAXBContext jaxbContext = null;
        //Creamos la instancia de JAXB
        try {
            if (type == BACKGROUNDS) {
                jaxbContext = JAXBContext.newInstance(Wallpapers.class);
            } else {
                jaxbContext = JAXBContext.newInstance(AppConfiguration.class);
            }
        } catch (JAXBException ex) {
            Logger.getLogger(XMLparse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jaxbContext;
    }
    /**
     * 
     * @param fileStream
     * @return 
     */
    public Wallpapers unmarshallerWallpapers(FileInputStream fileStream){
        Wallpapers wallpapers = null;
        jaxbContest = XMLparse.getJaxbContext(BACKGROUNDS);
        Unmarshaller unmarshaller = null;
        try {
            unmarshaller = jaxbContest.createUnmarshaller();
            wallpapers = (Wallpapers) unmarshaller.unmarshal(fileStream);
         } catch (JAXBException ex) {
            Logger.getLogger(XMLparse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return wallpapers;
    }
    /**
     * 
     * @param fileStream
     * @return 
     */
    public AppConfiguration unmarshallerConfig(FileInputStream fileStream){
        AppConfiguration config = null;
        jaxbContest = XMLparse.getJaxbContext(CONFIG);
        Unmarshaller unmarshaller = null;
        try {
            unmarshaller = jaxbContest.createUnmarshaller();
            config = (AppConfiguration) unmarshaller.unmarshal(fileStream);
         } catch (JAXBException ex) {
            Logger.getLogger(XMLparse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return config;
    }

    /**
     *
     * @param xml
     * @param type
     * @param objecto
     * @return
     */
    public int saveXML(File xml, int type, Object objecto){
   int status = 0;
       Marshaller marshaller;
        System.out.println("Guardando Comanda");
        try {
            //usamos el metodo de Marshaller para crear un documento con la
            //estructura de VendesArticles y los datos almacenados
            jaxbContest = getJaxbContext(type);
            marshaller = jaxbContest.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            if (type == CONFIG) {
              // marshaller.marshal((AppConfiguration)objecto, xml);

            } else {
               marshaller.marshal((Wallpapers)objecto, xml);

            }

        } catch (JAXBException ex) {
            Logger.getLogger(XMLparse.class.getName()).log(Level.SEVERE, null, ex);
            status = 2;
        }catch (NullPointerException ex) {
            Logger.getLogger(XMLparse.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getCause());
            status = 2;
        }

        return status;
   }
    

}
