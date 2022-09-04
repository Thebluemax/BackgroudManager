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
package com.max.backgroundlinuxmanager.utils;

import com.max.backgroundlinuxmanager.models.entities.AppConfiguration;
import com.max.backgroundlinuxmanager.models.entities.Wallpaper;
import com.max.backgroundlinuxmanager.models.entities.WallpaperXML;
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
 * @author Maximiliano Fernández thebluemax13 at gmail.com
 */
public class XMLparse {

    /**
     *
     */
    public static int CONFIG = 1;
    public static int WALLPAPER_XML = 2;

    public static int BACKGROUNDS = 0;

    private JAXBContext jaxbContest;

    /**
     *
     */
    public XMLparse() {
    }

    /**
     *Devuelve el contexto indicado en el typo seleccionado en el parámetro del
     * método.
     * 
     * @param type int que representa el type de clase a contextualizar
     * @return el contexto jaxb con el modelo definido
     */
    public static JAXBContext getJaxbContext(int type) {
//TODO: El método se definió como estático en un principio, pero dado el desarrollo del programa se ha se modificar a privado
        JAXBContext jaxbContext = null;
        //Creamos la instancia de JAXB
        try {
            if (type == WALLPAPER_XML) {
                jaxbContext = JAXBContext.newInstance(WallpaperXML.class);
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
    public WallpaperXML unmarshallerWallpapers(FileInputStream fileStream) {
        WallpaperXML wallpapers = null;
        jaxbContest = XMLparse.getJaxbContext(WALLPAPER_XML);
        Unmarshaller unmarshaller = null;
        try {
            unmarshaller = jaxbContest.createUnmarshaller();
            wallpapers = (WallpaperXML) unmarshaller.unmarshal(fileStream);
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
    public AppConfiguration unmarshallerConfig(File fileStream){
        AppConfiguration config = null;
        jaxbContest = XMLparse.getJaxbContext(CONFIG);
       return (AppConfiguration) unmarshaller(jaxbContest, fileStream);
    }
    /**
     * 
     * @param jaxbContest
     * @return 
     */
    private Object unmarshaller(JAXBContext jaxbContest, File fileStream){
        Object obj = null;
         Unmarshaller unmarshaller = null;
        try {
            unmarshaller = jaxbContest.createUnmarshaller();
            obj = (Object) unmarshaller.unmarshal(fileStream);
        } catch (JAXBException ex) {
            Logger.getLogger(XMLparse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    /**
     *Gurda un objeto en un archivo XML, empleando el modelo especificado en el tipo 
     * los tipos pueden ser 3 y están definidos el las varibles estáticas de la clase
     * 
     * @param xml Objeto File en el que se guardará el documento XML
     * @param type int que el tipo de modelo a usar
     * @param objeto La instancia del modelo a persistir
     * @return int estatus de la operación.
     */
    public int saveXML(File xml, int type, Object objecto) {
        int status = 0;
        Marshaller marshaller;
        System.out.println("Guardando Comanda");

        System.out.println("Guardando XML");
        try {
            //usamos el metodo de Marshaller para crear un documento con la
            //estructura de VendesArticles y los datos almacenados
            jaxbContest = getJaxbContext(type);
            marshaller = jaxbContest.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            if (type == CONFIG) {

               marshaller.marshal((AppConfiguration)objecto, xml);

            } else {
               marshaller.marshal((WallpaperXML)objecto, xml);

            }

        } catch (JAXBException ex) {
            Logger.getLogger(XMLparse.class.getName()).log(Level.SEVERE, null, ex);
            status = 2;
        } catch (NullPointerException ex) {
            Logger.getLogger(XMLparse.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getCause());
            status = 2;
        }

        return status;
   }

}
