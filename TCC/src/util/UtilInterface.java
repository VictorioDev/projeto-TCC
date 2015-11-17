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
    public final static ImageIcon ICONE_RELATORIO = new ImageIcon("src\\icones\\icone-imprimir.png");
    public final static ImageIcon ICONE_IMAGEMUSERPADRAO = new ImageIcon("src\\icones\\usericon_1.png");
    public final static ImageIcon ICONE_IMAGEMUSERPADRAO_GRANDE = new ImageIcon("src\\icones\\usericonadmin.png");
    public final static ImageIcon ICONE_FORCA = new ImageIcon("src\\icones\\forcaicone.png");
    public final static ImageIcon ICONE_JOGOPERGUNTA = new ImageIcon("src\\icones\\iconepergunta.jpg");
    public final static ImageIcon ICONE_DICASOM = new ImageIcon("src\\icones\\iconesom.png");
    public final static ImageIcon ICONE_DICATEXTO = new ImageIcon("src\\icones\\iconetextto.png");
    public final static ImageIcon ICONE_DICAIMAGEM = new ImageIcon("src\\icones\\iconeimagem.png");
    public final static ImageIcon ICONE_NIVEL = new ImageIcon("src\\icones\\niveisicon.png");
    public final static ImageIcon ICONE_PALAVRA = new ImageIcon("src\\icones\\palavrasicon.png");
    public final static ImageIcon ICONE_PALAVRA_GRANDE = new ImageIcon("src\\icones\\palavraicon72.png");
    public final static ImageIcon ICONE_PERGUNTA_GRANDE = new ImageIcon("src\\icones\\question-icon.png");
    public final static ImageIcon ICONE_PERGUNTA = new ImageIcon("src\\icones\\questionicon.png");
    public final static ImageIcon ICONE_CATEGORIA = new ImageIcon("src\\icones\\categorieicon32.png");
    public final static ImageIcon ICONE_CATEGORIA_GRANDE = new ImageIcon("src\\icones\\categorieicon72.png");
    public final static ImageIcon ICONE_NIVEL_GRANDE = new ImageIcon("src\\icones\\nivelicon72.png");
    public final static ImageIcon ICONE_JOGAR = new ImageIcon("src\\icones\\playicon32.jpg");
    public final static ImageIcon ICONE_JOGAR_GRANDE = new ImageIcon("src\\icones\\playicon72.png");
    public final static ImageIcon ICONE_SAIR = new ImageIcon("src\\icones\\back64.png");

    public final static Color COR_ERRO = Color.RED;
    public final static Font FONTE_PADRAO = new Font("Comic Sans MS", 0, 12);

    public static void setFontes(Component[] vetComp) {
        for (Component c : vetComp) {
            c.setFont(FONTE_PADRAO);
        }
    }

}
