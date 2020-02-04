/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.zabek.app.util;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zabek
 */
public class ImageCompressorTest {
    
    public ImageCompressorTest() {
    }

    @Test
    public void testGetFileType() {
        String result = ImageCompressor.getFileType("Image.png");
        assertEquals("png", result);
    }
    
}
