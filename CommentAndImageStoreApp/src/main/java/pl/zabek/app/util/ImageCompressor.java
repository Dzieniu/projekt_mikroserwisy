/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.zabek.app.util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

/**
 *
 * @author zabek
 */
public class ImageCompressor {
    public static void compressImage(pl.zabek.app.Image i){
        try {
            InputStream in = new ByteArrayInputStream(i.getImage());
            BufferedImage image = ImageIO.read(in);
            
            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(i.getImageName());
            ImageWriter writer = (ImageWriter) writers.next();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageOutputStream ios = ImageIO.createImageOutputStream(os);
            writer.setOutput(ios);
            
            ImageWriteParam param = writer.getDefaultWriteParam();

            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(0.35f);  // Change the quality value you prefer
            writer.write(null, new IIOImage(image, null, null), param);
            
            os.flush();
            i.setImage(os.toByteArray());
        } catch (IOException ex) {
            Logger.getLogger(ImageCompressor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void rescaleImage(pl.zabek.app.Image i){
        try {
            InputStream in = new ByteArrayInputStream(i.getImage());
            BufferedImage image = ImageIO.read(in);

            BufferedImage finalImage = resize(image, 300, 300);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(finalImage, "jpg", baos);
            baos.flush();
            i.setImage(baos.toByteArray());
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
        
    public static String getFileType(String name){
        return name.substring(name.indexOf(".") +1).toLowerCase();
    }
}
