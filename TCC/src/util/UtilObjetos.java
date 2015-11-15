package util;

import Bean.JogadorBean;
import Bean.NivelBean;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import sun.applet.Main;

public class UtilObjetos {

    public static JogadorBean jogadorLogado;
    private static NivelBean nivelSelecionado;
    public static List<String> listaNiveisPJogar = new ArrayList<String>();
    public static List<String> listaCategoriasPJogar = new ArrayList<String>();

    public static void Desconvertimg(byte[] bytes) throws IOException {
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

        File imageFile = new File("src/imgUsers/imdica.jpg");
        ImageIO.write(bufferedImage, "jpg", imageFile);

        System.out.println(imageFile.getPath());
    }

    
}
