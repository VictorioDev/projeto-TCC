/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import Bean.JogadorBean;
import Dao.JogadorDao;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 *
 * @author victorio
 */
public class teste {
   public static List<JogadorBean> lj = new ArrayList<>();
    
    
    public static void cv() {
       try {
           lj = JogadorDao.RetornaJogadores("");
       } catch (SQLException ex) {
           Logger.getLogger(teste.class.getName()).log(Level.SEVERE, null, ex);
       }
       try {
           Desconvertimg(lj.get(0).getImgUser());
       } catch (IOException ex) {
           Logger.getLogger(teste.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        
    }
    
    public static void main(String args[]){
        cv();
    }
    
    
      public static void Desconvertimg(byte[] bytes) throws IOException{
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator<?> readers = ImageIO.getImageReadersByFormatName("png");
 
        //ImageIO is a class containing static methods for locating ImageReaders
        //and ImageWriters, and performing simple encoding and decoding. 
 
        ImageReader reader = (ImageReader) readers.next();
        Object source = bis; 
        ImageInputStream iis = ImageIO.createImageInputStream(source); 
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
 
        Image image = reader.read(0, param);
        //got an image file
 
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        //bufferedImage is the RenderedImage to be written
 
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, null, null);
 
        File imageFile = new File("src/imgUsers/imgger.jpg");
        ImageIO.write(bufferedImage, "jpg", imageFile);
 
        System.out.println(imageFile.getPath());
    }
    
    
}
