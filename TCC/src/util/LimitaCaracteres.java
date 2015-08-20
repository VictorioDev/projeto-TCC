/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Victorio
 */
public class LimitaCaracteres extends PlainDocument{

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        int tamanho = (this.getLength() + str.length());
        if(tamanho <= 30){
            super.insertString(offs, str, a); //To change body of generated methods, choose Tools | Templates.
        }else{
            super.insertString(offs, str.replaceAll("[aA0-zZ9]", ""), a); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
