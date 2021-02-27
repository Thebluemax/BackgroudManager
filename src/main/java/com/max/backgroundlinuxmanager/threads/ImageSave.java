/*
 * The MIT License
 *
 * Copyright 2020 Maximiliano Fernández <thebluemax13 at gmail.com>.
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
package com.max.backgroundlinuxmanager.threads;

import com.max.backgroundlinuxmanager.utils.ManagerFiles;
import java.io.File;
import java.util.concurrent.Callable;
import javax.naming.ldap.ManageReferralControl;

/**
 *
 * @author Maximiliano Fernández <thebluemax13 at gmail.com>
 */
public class ImageSave implements Callable<Integer>{
    
    
    private File fileImage;
    private File newFileImage;

    public ImageSave(File file) {
        
        fileImage = file;
        String pathNew = ManagerFiles.getBackgroundsPath() + "/" + fileImage.getName();
        newFileImage = new File(pathNew);
    }
    
    

    @Override
    public Integer call() throws Exception {
        return  ManagerFiles.copyFile(fileImage, newFileImage);
    }
    
    
    
}
