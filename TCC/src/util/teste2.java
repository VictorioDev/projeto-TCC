/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Victorio
 */
public class teste2 {

   
    
    
    public static void main(String[] args) {
         JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    
    f.pack();
    f.setVisible(true);
    
    }

    private static void paintComponent( Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
   // Assume x, y, and diameter are instance variables.
   Ellipse2D.Double circle = new Ellipse2D.Double(20, 40, 10, 20);
   g2d.fill(circle);
    }
    
  
  
    
}
