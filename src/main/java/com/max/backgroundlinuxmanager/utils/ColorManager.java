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
package com.max.backgroundlinuxmanager.utils;

import java.awt.Color;

/**
 *
 * @author Maximiliano Fernández <thebluemax13 at gmail.com>
 */
public class ColorManager {

    /**
     * Get a color object from a hexa color definition
     *
     * @param color string The color en a hexa format #ffffff
     * @return Color object color
     */
    public static Color getColor(String color) {
        int red = 255;
        int green = 255;
        int blue = 255;

        if (color.length() == 7) {
            red = Integer.parseInt(color.substring(1, 3), 16);
            green = Integer.parseInt(color.substring(3, 5), 16);
            blue = Integer.parseInt(color.substring(5, 7), 16);
        }
        //System.out.println(color+" "+red+"-"+green+"-"+blue+"/"+color.substring(1, 3));
        return new Color(red, green, blue);
    }

    /**
     * retun a hedecimal color from a Java Object Color Instance
     *
     * @param color
     * @return
     */
    public static String getColorHexa(Color color) {
        String hexadecimal = "#";
        hexadecimal += evaluateString(Integer.toHexString(color.getRed()));
        hexadecimal += evaluateString(Integer.toHexString(color.getGreen()));
        hexadecimal += evaluateString(Integer.toHexString(color.getBlue()));
        return hexadecimal;
    }

    /**
     * add a 0 to the lowers number values ej a => 0a;
     *
     * @param s string
     * @return
     */
    private static String evaluateString(String s) {
        if (s.length() == 1) {
            s = "0" + s;
        }
        return s;
    }

}
