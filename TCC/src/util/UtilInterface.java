package util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.color.ColorSpace;
import javax.swing.ImageIcon;

public class UtilInterface {

    public final static String MSG_REGISTRO_DUPLICADO = "o resgistro atual já é existente!";
    public final static ImageIcon ICONE_SALVAR = new ImageIcon("src\\icones\\Ok-icon16.png");
    public final static ImageIcon ICONE_CANCELAR = new ImageIcon("src\\icones\\Close-icon16.png");
    public final static ImageIcon ICONE_NOVO = new ImageIcon("src\\icones\\Document-Blank-icon16.png");
    public final static ImageIcon ICONE_ALTERAR = new ImageIcon("src\\icones\\Document-Write-icon16.png");
    public final static ImageIcon ICONE_PESQUISAR = new ImageIcon("src\\icones\\Zoom-icon16.png");
    public final static ImageIcon ICONE_DELETAR = new ImageIcon("src\\icones\\Delete-icon16.png");
    public final static ImageIcon ICONE_REMOVER = new ImageIcon("src\\icones\\Delete-icon16.png");
    public final static ImageIcon ICONE_RELATORIO = new ImageIcon("src\\icones\\relatorio_icone.jpg");
    public final static ImageIcon ICONE_IMAGEMUSERPADRAO = new ImageIcon("src\\Telas\\imagens\\imguser.jpg");
    public final static ImageIcon ICONE_FORCA = new ImageIcon("src\\icones\\forcaicon.png");
    public final static ImageIcon ICONE_DICASOM = new ImageIcon("src\\icones\\iconesom.png");
    public final static ImageIcon ICONE_DICATEXTO = new ImageIcon("src\\icones\\iconetextto.png");
    public final static ImageIcon ICONE_DICAIMAGEM = new ImageIcon("src\\icones\\iconeimagem.png");

    public final static Color COR_ERRO = Color.RED;
    public final static Font FONTE_PADRAO = new Font("Comic Sans MS", 0, 12);

    public static void setFontes(Component[] vetComp) {
        for (Component c : vetComp) {
            c.setFont(FONTE_PADRAO);
        }
    }

}
