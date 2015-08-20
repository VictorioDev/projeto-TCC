/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;  
  
import java.awt.BorderLayout;  
import java.awt.Dimension;  
import java.awt.Graphics;  
import java.awt.Image;  
import java.net.URL;  
  
import javax.swing.ImageIcon;  
import javax.swing.JButton;
import javax.swing.JFrame;  
import javax.swing.JPanel;  
  
public class Teste extends JPanel {  
  
    private Image image; 
    private JButton btnok;
    
  
    public Teste() {  
        this.initialize(); 
        btnok = new JButton();
        
    }  
  
    
    
    protected void initialize() {  
        this.image = this.getImage("fundo.jpg");  
          
        this.setLayout(new BorderLayout());  
    }  
  
    public Image getImage(String path) {  
        URL imageURL = getClass().getResource(path);  
        if (imageURL == null)  
            return null;  
  
        return new ImageIcon(imageURL).getImage();  
    }  
  
    public void paintComponent(Graphics g) {  
        super.paintComponent(g);  
        Dimension dDesktop = this.getSize();  
  
        double width = dDesktop.getWidth();  
        double height = dDesktop.getHeight();  
  
        Image background = new ImageIcon(this.image.getScaledInstance(  
                (int) width, (int) height, 1)).getImage();  
  
        g.drawImage(background, 0, 0, this);  
    }  
  
    public static void main(String[] args) {  
        JFrame frame = new JFrame();  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setContentPane(new Teste());  
        frame.setSize(400, 300);  
        frame.setVisible(true);  
    }  
}  