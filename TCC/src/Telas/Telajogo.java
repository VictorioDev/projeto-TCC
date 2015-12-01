/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Bean.DicaBean;
import Bean.JogadorBean;
import Bean.PalavraBean;
import Bean.PalavraJogadaBean;
import Dao.DicaDao;
import Dao.JogadorDao;
import Dao.PalavraDao;
import Dao.PalavraJogadaDAO;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import static sun.security.krb5.Confounder.bytes;

//import javazoom.jl.decoder.JavaLayerException;
//import javazoom.jl.player.*;
/**
 *
 * @author Victório
 */
public class Telajogo extends javax.swing.JFrame {

    public static char palpite;
    public static String trac = " ";
    public static int indicePalavra;
    public static List<PalavraBean> listapalavrass = new ArrayList<PalavraBean>();
    //public static List<DicaBean> listdica = new ArrayList<DicaBean>();
    public static PalavraBean p = new PalavraBean();
    public static String palavraa;
    public static char vetrac[];
    public static String palavrad;
    public static int pontosP = 0;
    public static int pontosJog = 0;
    public static int chances = 10;
    public static int contaErr = 0;
    public static int contPalpites = 0;
    public static long tempoGasto = 0;
    public static boolean acertou;
    public static boolean gameOver;
    private int numDicaTexto = 0;
    private int numDicaSom = 0;
    private int numDicaImagem = 0;
    public static ImageIcon imageForca = new ImageIcon("src\\ImagemForca\\ImgForcaDefaultC.jpg");
    private List<DicaBean> listadicas = new ArrayList<>();
    private List<DicaBean> listaDicaTexto = new ArrayList<>();
    private List<DicaBean> listaDicaImagem = new ArrayList<>();
    private List<DicaBean> listaDicaSom = new ArrayList<>();
    public static ImageIcon imageAuxForca;
    long start = 0;
    public static JogadorBean j = util.UtilObjetos.jogadorLogado;

    private boolean canEnableButton = false;

    public Telajogo() {

        initComponents();
        //setSize(1200, 650);
        setResizable(false);
        defineIcones();
        preeencherCampos(util.UtilObjetos.jogadorLogado);
        populaListaPalavras();
        configIni();

    }

    //Verifica quiantidade de dicas de cada tipo e preenche o List de cada tipo de dica
    private void verificaDicas() {
        List<DicaBean> listdica = new ArrayList<DicaBean>();

        try {
            listdica = DicaDao.RetornaDicas(p);
            listaDicaTexto = DicaDao.RetornaDicas(p, "texto");
            listaDicaImagem = DicaDao.RetornaDicas(p, "imagem");
            listaDicaSom = DicaDao.RetornaDicas(p, "som");
//            DicaBean d = new DicaBean();
//            d = listdica.get(0);
//            System.err.println("Dica do tipo: "+d.getTipo());
//            System.err.println("Num dicas: " + listdica.size());
        } catch (SQLException ex) {
            Logger.getLogger(Telajogo.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Saber a quantidade de dicas de cada tipo
        for (DicaBean d : listdica) {
            if (d.getTipo().equalsIgnoreCase("texto")) {
                numDicaTexto += 1;
            } else if (d.getTipo().equalsIgnoreCase("som")) {
                numDicaSom += 1;

            } else if (d.getTipo().equalsIgnoreCase("imagem")) {
                numDicaImagem += 1;
            }

        }
    }

    private void defineIcones() {
        util.UtilInterface.ICONE_DICATEXTO.setImage(util.UtilInterface.ICONE_DICATEXTO.getImage().getScaledInstance(32, 32, 100));
        util.UtilInterface.ICONE_DICAIMAGEM.setImage(util.UtilInterface.ICONE_DICAIMAGEM.getImage().getScaledInstance(32, 32, 100));
        util.UtilInterface.ICONE_DICASOM.setImage(util.UtilInterface.ICONE_DICASOM.getImage().getScaledInstance(32, 32, 100));

        lbTexto.setIcon(util.UtilInterface.ICONE_DICATEXTO);
        lbImagem.setIcon(util.UtilInterface.ICONE_DICAIMAGEM);
        lbSom.setIcon(util.UtilInterface.ICONE_DICASOM);
    }

    public void configIni() {
        listadicas.clear();
        listaDicaImagem.clear();
        listaDicaSom.clear();
        listaDicaTexto.clear();
        numDicaImagem = 0;
        numDicaSom = 0;
        numDicaTexto = 0;
        gameOver = false;
        contPalpites = 0;
        tempoGasto = 0;
        acertou = false;
        start = System.currentTimeMillis();
        chances = 10;
        imageForca.setImage(imageForca.getImage().getScaledInstance(lbImgForca.getWidth(), lbImgForca.getHeight(), 100));
        lbImgForca.setIcon(imageForca);
        voltaBotoes();
        tfchances.setText(chances + "");
        //txPt.setText(pontosJog + "");
        pontosP = 0;
        trac = "";
        palavrad = sorteiaPalavra().toUpperCase();
        vetrac = new char[palavrad.length()];
        for (int i = 0; i < palavrad.length(); i++) {
            if (palavrad.charAt(i) == ' ') {
                vetrac[i] += ' ';

            } else {
                vetrac[i] = '_';
            }
        }
        for (int i = 0; i < vetrac.length; i++) {
            trac += vetrac[i] + " ";
        }
        verificaDicas();
        habilitaBotaoCompra();
        tfpalavra.setText(trac);

    }

    private void habilitaBotaoCompra() {
        if (j.getPontos() > 0) {
            canEnableButton = true;
            if (numDicaTexto > 0) {
                lbTexto.setEnabled(true);
            } else {
                lbTexto.setEnabled(false);
            }

            if (numDicaImagem > 0) {
                lbImagem.setEnabled(true);
            } else {
                lbImagem.setEnabled(false);
            }

            if (numDicaSom > 0) {
                lbSom.setEnabled(true);
            } else {
                lbSom.setEnabled(false);
            }
        } else {
            canEnableButton = false;
            for (int i = 0; i < pnDicas.getComponents().length; i++) {
                pnDicas.getComponent(i).setEnabled(false);
            }
        }
    }

    private void preeencherCampos(JogadorBean j) {
        tfnome.setText(j.getNome());
        System.err.println("NUMERO DE PONTOS "+j.getPontos());
        txPt.setText(j.getPontos() + "");

    }

    private void voltaBotoes() {
        btnQ.setEnabled(true);
        btnW.setEnabled(true);
        btnE.setEnabled(true);
        btnR.setEnabled(true);
        btnT.setEnabled(true);
        btnY.setEnabled(true);
        btnU.setEnabled(true);
        btnI.setEnabled(true);
        btnO.setEnabled(true);
        btnP.setEnabled(true);
        btnA.setEnabled(true);
        btnS.setEnabled(true);
        btnD.setEnabled(true);
        btnF.setEnabled(true);
        btnG.setEnabled(true);
        btnH.setEnabled(true);
        btnJ.setEnabled(true);
        btnK.setEnabled(true);
        btnL.setEnabled(true);
        btnÇ.setEnabled(true);
        btnZ.setEnabled(true);
        btnD.setEnabled(true);
        btnX.setEnabled(true);
        btnC.setEnabled(true);
        btnV.setEnabled(true);
        btnB.setEnabled(true);
        btnN.setEnabled(true);
        btnM.setEnabled(true);
        btnL.setEnabled(true);
    }

    private void comprarDica(PalavraBean p, String tipo) {
        System.err.println("Numero de dicas em texto: " + numDicaTexto);
        System.err.println("Numero de dicas em imagem: " + numDicaImagem);
        System.err.println("NUmero de dicas em som: " + numDicaSom);
        DicaBean d = new DicaBean();
        int acept = JOptionPane.showConfirmDialog(null, "Tem certeza? Isso ira lhe custar 5 pontos.");
        if (acept == JOptionPane.YES_OPTION) {
            pontosJog -= 5;
            j.setPontos(pontosJog);
            try {
                JogadorDao.UpdatePontos(j);
            } catch (SQLException ex) {
                Logger.getLogger(Telajogo.class.getName()).log(Level.SEVERE, null, ex);
            }
//            try {
//                JogadorDao.UpdatePontos(j);
//                listadicas = DicaDao.RetornaDicas(p, tipo);
//            } catch (SQLException ex) {
//                Logger.getLogger(Telajogo.class.getName()).log(Level.SEVERE, null, ex);
//            }
            txPt.setText(pontosJog + "");
            //System.err.println("Tamanho: " + listadicas.size());
            if (tipo.equalsIgnoreCase("texto")) {
                Random range = new Random();
                int indice = range.nextInt(listaDicaTexto.size());
                d = listaDicaTexto.get(indice);
                listaDicaTexto.remove(indice);
                String mensagem = d.getTexto();
                JOptionPane.showMessageDialog(null, mensagem);
                numDicaTexto--;
            } else if (tipo.equalsIgnoreCase("imagem")) {
                Random range = new Random();
                int indice = range.nextInt(listaDicaImagem.size());
                d = listaDicaImagem.get(indice);
                listaDicaImagem.remove(indice);
                try {
                    util.VoltaImagemJPG.Desconvertimg(d.getImagem(), "src/imgUsers/imdica.jpg");
                } catch (IOException ex) {
                    Logger.getLogger(Telajogo.class.getName()).log(Level.SEVERE, null, ex);
                }
                ImageIcon i = new ImageIcon("src\\imgUsers\\imdica.jpg");
                i.setImage(i.getImage().getScaledInstance(500, 500, 100));
                JLabel label = new JLabel(i);
                // label.setSize(800, 800);
                JOptionPane.showMessageDialog(null, label, "Preste atenção", 1);
                numDicaImagem--;

            } else if (tipo.equalsIgnoreCase("som")) {
                Random range = new Random();
                int indice = range.nextInt(listaDicaSom.size());
                d = listaDicaSom.get(indice);
                listaDicaSom.remove(indice);
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream("src\\Sons\\dicaSom.mp3");
                    fos.write(d.getSom());
                    fos.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Telajogo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Telajogo.class.getName()).log(Level.SEVERE, null, ex);
                }
                tocarSom("src\\Sons\\dicaSom.mp3");
                numDicaSom--;
            }

        }

        System.err.println("Numero de dicas em texto: " + numDicaTexto);
        System.err.println("Numero de dicas em imagem: " + numDicaImagem);
        habilitaBotaoCompra();
    }

    private void populaListaPalavras() {
        try {
            for (int i = 0; i < util.UtilObjetos.listaNiveisPJogar.size(); i++) {
                for (int k = 0; k < util.UtilObjetos.listaCategoriasPJogar.size(); k++) {
                    List<PalavraBean> listaTemp = PalavraDao.RetornaPalavrasSO(util.UtilObjetos.listaNiveisPJogar.get(i), util.UtilObjetos.listaCategoriasPJogar.get(k));
                    for (PalavraBean plv : listaTemp) {
                        System.err.println("Nivel:" + plv.getNivel().getDescricao());
                        System.err.println("Categoria: " + plv.getCategoria().getDescricao());
                        System.err.println("Palavra: " + plv.getNome());
                        System.err.println("--------------------------------------------------------------------------");
                        listapalavrass.add(plv);
                    }
                }

            }
            //listapalavrass = PalavraDao.RetornaPalavrasSP();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String sorteiaPalavra() {

        Random ram = new Random();
        if (listapalavrass.size() > 0) {
            System.err.println("Tamanho: " + listapalavrass.size());
            indicePalavra = ram.nextInt(listapalavrass.size());
            p = listapalavrass.get(indicePalavra);
            palavraa = p.getNome();
            listapalavrass.remove(indicePalavra);
        }else{
            JOptionPane.showMessageDialog(null, "Você jogou todas as palavras disponíveis!");
            dispose();
            new ConfiguraJogo("palavra").setVisible(true);
        }
        return palavraa;
    }

    private void atualizaImagemForca(int chances) {
        if (chances == 9) {
            imageAuxForca = new ImageIcon("src\\ImagemForca\\ImgForca1.jpg");
            imageAuxForca.setImage(imageAuxForca.getImage().getScaledInstance(lbImgForca.getWidth(), lbImgForca.getHeight(), 100));

            lbImgForca.setIcon(imageAuxForca);

        }

        if (chances == 8) {
            imageAuxForca = new ImageIcon("src\\ImagemForca\\ImgForca2.jpg");
            imageAuxForca.setImage(imageAuxForca.getImage().getScaledInstance(lbImgForca.getWidth(), lbImgForca.getHeight(), 100));
            lbImgForca.setIcon(imageAuxForca);
        }

        if (chances == 7) {
            imageAuxForca = new ImageIcon("src\\ImagemForca\\ImgForca3.jpg");
            imageAuxForca.setImage(imageAuxForca.getImage().getScaledInstance(lbImgForca.getWidth(), lbImgForca.getHeight(), 100));
            lbImgForca.setIcon(imageAuxForca);
        }

        if (chances == 6) {
            imageAuxForca = new ImageIcon("src\\ImagemForca\\ImgForca4.jpg");
            imageAuxForca.setImage(imageAuxForca.getImage().getScaledInstance(lbImgForca.getWidth(), lbImgForca.getHeight(), 100));
            lbImgForca.setIcon(imageAuxForca);
        }

        if (chances == 5) {
            imageAuxForca = new ImageIcon("src\\ImagemForca\\ImgForca5.jpg");
            imageAuxForca.setImage(imageAuxForca.getImage().getScaledInstance(lbImgForca.getWidth(), lbImgForca.getHeight(), 100));
            lbImgForca.setIcon(imageAuxForca);
        }

        if (chances == 4) {
            imageAuxForca = new ImageIcon("src\\ImagemForca\\ImgForca6.jpg");
            imageAuxForca.setImage(imageAuxForca.getImage().getScaledInstance(lbImgForca.getWidth(), lbImgForca.getHeight(), 100));
            lbImgForca.setIcon(imageAuxForca);
            gameOver = true;
        }
    }

    private void aumentaPontos() {
        pontosJog += 5;
        j.setPontos(pontosJog);
    }

    public static char removerAcentos(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return str.charAt(0);
    }

    public void atualizaCampoPalavra(char palpite) {
        boolean certo = false;
        trac = "";

        //funcionando
//        for (int i = 0; i < palavrad.length(); i++) {
//            if (palavrad.charAt(i) == palpite) {
//                vetrac[i] = palpite;
//                pontosP++;
//                certo = true;
//            } else {
//                contaErr++;
//            }
//
//        }
        //modificado
        for (int i = 0; i < palavrad.length(); i++) {
            if (removerAcentos(palavrad.charAt(i) + "") == palpite) {
                vetrac[i] = palavrad.charAt(i);
                pontosP++;
                certo = true;
            } else {
                contaErr++;
            }

        }

        if (certo) {
            tocarSom("src\\Sons\\Street Fighter sound Hadouken.mp3");
        }
        if (contaErr == palavrad.length()) {
            chances--;
            //System.out.println(contaErr);

            tfchances.setText(chances + "");

        }
        System.err.println(chances);
        atualizaImagemForca(chances);
//        if (chances == 9) {
//            imageAuxForca = new ImageIcon("src\\ImagemForca\\ImgForca1.jpg");
//            imageAuxForca.setImage(imageAuxForca.getImage().getScaledInstance(200, 200, 100));
//            jLabel6.setIcon(imageAuxForca);
//
//        }
//
//        if (chances == 8) {
//            imageAuxForca = new ImageIcon("src\\ImagemForca\\ImgForca2.jpg");
//            imageAuxForca.setImage(imageAuxForca.getImage().getScaledInstance(200, 200, 100));
//            jLabel6.setIcon(imageAuxForca);
//        }
        contaErr = 0;

        for (int i = 0; i < vetrac.length; i++) {
            trac += vetrac[i] + " ";

        }

        if (gameOver) {
            tempoGasto = (System.currentTimeMillis() - start) / 1000;
            System.out.println(tempoGasto);
            System.out.println(contPalpites);
            JOptionPane.showMessageDialog(null, "Acabaram suas chances :(, tente novamente!");
            j.setPontos(pontosJog);
            try {
                JogadorDao.UpdatePontos(j);
                PalavraJogadaDAO.SalvarPalavraJogada(retornaObjeto());

            } catch (SQLException ex) {
                Logger.getLogger(Telajogo.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            configIni();
        }

//        if (pontosP > 0) {
//            System.err.println("FOI");
//            TocarSom("Todossrc\\Sons\\Street Fighter sound Hadouken.mp3");
//
//        }
        tfpalavra.setText(trac);
        if (pontosP == palavrad.length()) {
            tempoGasto = (System.currentTimeMillis() - start) / 1000;
            System.out.println(tempoGasto);
            JOptionPane.showMessageDialog(null, "Parabens você acertou a palavra!");
            acertou = true;
            aumentaPontos();
            try {
                JogadorDao.UpdatePontos(j);
                PalavraJogadaDAO.SalvarPalavraJogada(retornaObjeto());

            } catch (SQLException ex) {
                Logger.getLogger(Telajogo.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            configIni();
//            VerificaDicas();
//            HabilitaBotaoCompra();
        }

        //txPt.setText(pontosJog + "");
    }

    private PalavraJogadaBean retornaObjeto() {
        PalavraJogadaBean pjb = new PalavraJogadaBean();
        pjb.setJogador(util.UtilObjetos.jogadorLogado);
        pjb.setPalavra(p);
        pjb.setTempo(tempoGasto);
        pjb.setAcertou(acertou);
        pjb.setPalpites(contPalpites);

        return pjb;
    }

//    public static void Desconvertimg(byte[] bytes) throws IOException {
//        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
//        Iterator<?> readers = ImageIO.getImageReadersByFormatName("jpg");
//
//        //ImageIO is a class containing static methods for locating ImageReaders
//        //and ImageWriters, and performing simple encoding and decoding. 
//        ImageReader reader = (ImageReader) readers.next();
//        Object source = bis;
//        ImageInputStream iis = ImageIO.createImageInputStream(source);
//        reader.setInput(iis, true);
//        ImageReadParam param = reader.getDefaultReadParam();
//
//        Image image = reader.read(0, param);
//        //got an image file
//
//        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
//        //bufferedImage is the RenderedImage to be written
//
//        Graphics2D g2 = bufferedImage.createGraphics();
//        g2.drawImage(image, null, null);
//
//        File imageFile = new File("src/imgUsers/imgdica.jpg");
//        ImageIO.write(bufferedImage, "jpg", imageFile);
//
//        System.out.println(imageFile.getPath());
//    }
    public void tocarSom(String url) {

        new Thread("Tocando") {
            @Override
            public void run() {
                try {

                    File file = new File(url);
                    FileInputStream fis = new FileInputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(fis);

                    try {
                        //Thread t = new Thread();
                        //t.start();
                        Player player = new Player(bis);
                        player.play();

                    } catch (JavaLayerException e) {

                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }.start();

    }

    /**
     *
     *
     *
     * Creates new form Telajogo
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel6 = new javax.swing.JLabel();
        pninform = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfnome = new javax.swing.JTextField();
        txPt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfchances = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pnDicas = new javax.swing.JPanel();
        lbTexto = new javax.swing.JLabel();
        lbImagem = new javax.swing.JLabel();
        lbSom = new javax.swing.JLabel();
        pnBtn = new javax.swing.JPanel();
        btnQ = new javax.swing.JButton();
        btnW = new javax.swing.JButton();
        btnE = new javax.swing.JButton();
        btnR = new javax.swing.JButton();
        btnT = new javax.swing.JButton();
        btnY = new javax.swing.JButton();
        btnU = new javax.swing.JButton();
        btnI = new javax.swing.JButton();
        btnO = new javax.swing.JButton();
        btnP = new javax.swing.JButton();
        btnA = new javax.swing.JButton();
        btnS = new javax.swing.JButton();
        btnD = new javax.swing.JButton();
        btnF = new javax.swing.JButton();
        btnG = new javax.swing.JButton();
        btnH = new javax.swing.JButton();
        btnJ = new javax.swing.JButton();
        btnK = new javax.swing.JButton();
        btnL = new javax.swing.JButton();
        btnÇ = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnZ = new javax.swing.JButton();
        btnX = new javax.swing.JButton();
        btnC = new javax.swing.JButton();
        btnV = new javax.swing.JButton();
        btnB = new javax.swing.JButton();
        btnN = new javax.swing.JButton();
        btnM = new javax.swing.JButton();
        pnPlv = new javax.swing.JPanel();
        tfpalavra = new javax.swing.JTextField();
        lbImgForca = new javax.swing.JLabel();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel6.setText("jLabel6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        pninform.setBackground(new java.awt.Color(102, 0, 255));
        pninform.setForeground(new java.awt.Color(255, 51, 0));
        pninform.setOpaque(false);

        jLabel1.setBackground(new java.awt.Color(0, 0, 51));
        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel1.setText("Nome:");

        jLabel2.setBackground(new java.awt.Color(0, 0, 51));
        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel2.setText("Pontos:");

        tfnome.setEditable(false);
        tfnome.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N

        txPt.setEditable(false);
        txPt.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N

        jLabel3.setBackground(new java.awt.Color(0, 0, 51));
        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel3.setText("Chances:");

        tfchances.setEditable(false);
        tfchances.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N

        jLabel4.setBackground(new java.awt.Color(0, 0, 51));
        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel4.setText("Dica:");

        pnDicas.setLayout(new java.awt.GridLayout(1, 3, 1, 0));

        lbTexto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTexto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbTexto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbTextoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbTextoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbTextoMouseExited(evt);
            }
        });
        pnDicas.add(lbTexto);

        lbImagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbImagem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbImagemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbImagemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbImagemMouseExited(evt);
            }
        });
        pnDicas.add(lbImagem);

        lbSom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbSom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbSomMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbSomMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbSomMouseExited(evt);
            }
        });
        pnDicas.add(lbSom);

        javax.swing.GroupLayout pninformLayout = new javax.swing.GroupLayout(pninform);
        pninform.setLayout(pninformLayout);
        pninformLayout.setHorizontalGroup(
            pninformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pninformLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(pninformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pninformLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfnome, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pninformLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnDicas, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pninformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pninformLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txPt, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pninformLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfchances, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pninformLayout.setVerticalGroup(
            pninformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pninformLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pninformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pninformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfnome, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(txPt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addGroup(pninformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pninformLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pninformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tfchances, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pninformLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(pnDicas, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pninform, java.awt.BorderLayout.NORTH);

        pnBtn.setBackground(java.awt.Color.yellow);
        pnBtn.setOpaque(false);
        pnBtn.setLayout(new java.awt.GridLayout(3, 10, 10, 10));

        btnQ.setBackground(new java.awt.Color(0, 204, 204));
        btnQ.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnQ.setText("Q");
        btnQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQActionPerformed(evt);
            }
        });
        pnBtn.add(btnQ);

        btnW.setBackground(new java.awt.Color(0, 204, 204));
        btnW.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnW.setText("W");
        btnW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWActionPerformed(evt);
            }
        });
        pnBtn.add(btnW);

        btnE.setBackground(new java.awt.Color(0, 204, 204));
        btnE.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnE.setText("E");
        btnE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEActionPerformed(evt);
            }
        });
        pnBtn.add(btnE);

        btnR.setBackground(new java.awt.Color(0, 204, 204));
        btnR.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnR.setText("R");
        btnR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRActionPerformed(evt);
            }
        });
        pnBtn.add(btnR);

        btnT.setBackground(new java.awt.Color(0, 204, 204));
        btnT.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnT.setText("T");
        btnT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTActionPerformed(evt);
            }
        });
        pnBtn.add(btnT);

        btnY.setBackground(new java.awt.Color(0, 204, 204));
        btnY.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnY.setText("Y");
        btnY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYActionPerformed(evt);
            }
        });
        pnBtn.add(btnY);

        btnU.setBackground(new java.awt.Color(0, 204, 204));
        btnU.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnU.setText("U");
        btnU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUActionPerformed(evt);
            }
        });
        pnBtn.add(btnU);

        btnI.setBackground(new java.awt.Color(0, 204, 204));
        btnI.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnI.setText("I");
        btnI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIActionPerformed(evt);
            }
        });
        pnBtn.add(btnI);

        btnO.setBackground(new java.awt.Color(0, 204, 204));
        btnO.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnO.setText("O");
        btnO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOActionPerformed(evt);
            }
        });
        pnBtn.add(btnO);

        btnP.setBackground(new java.awt.Color(0, 204, 204));
        btnP.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnP.setText("P");
        btnP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPActionPerformed(evt);
            }
        });
        pnBtn.add(btnP);

        btnA.setBackground(new java.awt.Color(0, 204, 204));
        btnA.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnA.setText("A");
        btnA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAActionPerformed(evt);
            }
        });
        pnBtn.add(btnA);

        btnS.setBackground(new java.awt.Color(0, 204, 204));
        btnS.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnS.setText("S");
        btnS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSActionPerformed(evt);
            }
        });
        pnBtn.add(btnS);

        btnD.setBackground(new java.awt.Color(0, 204, 204));
        btnD.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnD.setText("D");
        btnD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDActionPerformed(evt);
            }
        });
        pnBtn.add(btnD);

        btnF.setBackground(new java.awt.Color(0, 204, 204));
        btnF.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnF.setText("F");
        btnF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFActionPerformed(evt);
            }
        });
        pnBtn.add(btnF);

        btnG.setBackground(new java.awt.Color(0, 204, 204));
        btnG.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnG.setText("G");
        btnG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGActionPerformed(evt);
            }
        });
        pnBtn.add(btnG);

        btnH.setBackground(new java.awt.Color(0, 204, 204));
        btnH.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnH.setText("H");
        btnH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHActionPerformed(evt);
            }
        });
        pnBtn.add(btnH);

        btnJ.setBackground(new java.awt.Color(0, 204, 204));
        btnJ.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnJ.setText("J");
        btnJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJActionPerformed(evt);
            }
        });
        pnBtn.add(btnJ);

        btnK.setBackground(new java.awt.Color(0, 204, 204));
        btnK.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnK.setText("K");
        btnK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKActionPerformed(evt);
            }
        });
        pnBtn.add(btnK);

        btnL.setBackground(new java.awt.Color(0, 204, 204));
        btnL.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnL.setText("L");
        btnL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLActionPerformed(evt);
            }
        });
        pnBtn.add(btnL);

        btnÇ.setBackground(new java.awt.Color(0, 204, 204));
        btnÇ.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnÇ.setText("Ç");
        btnÇ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnÇActionPerformed(evt);
            }
        });
        pnBtn.add(btnÇ);
        pnBtn.add(jLabel5);

        btnZ.setBackground(new java.awt.Color(0, 204, 204));
        btnZ.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnZ.setText("Z");
        btnZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZActionPerformed(evt);
            }
        });
        pnBtn.add(btnZ);

        btnX.setBackground(new java.awt.Color(0, 204, 204));
        btnX.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnX.setText("X");
        btnX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXActionPerformed(evt);
            }
        });
        pnBtn.add(btnX);

        btnC.setBackground(new java.awt.Color(0, 204, 204));
        btnC.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnC.setText("C");
        btnC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCActionPerformed(evt);
            }
        });
        pnBtn.add(btnC);

        btnV.setBackground(new java.awt.Color(0, 204, 204));
        btnV.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnV.setText("V");
        btnV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVActionPerformed(evt);
            }
        });
        pnBtn.add(btnV);

        btnB.setBackground(new java.awt.Color(0, 204, 204));
        btnB.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnB.setText("B");
        btnB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBActionPerformed(evt);
            }
        });
        pnBtn.add(btnB);

        btnN.setBackground(new java.awt.Color(0, 204, 204));
        btnN.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnN.setText("N");
        btnN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNActionPerformed(evt);
            }
        });
        pnBtn.add(btnN);

        btnM.setBackground(new java.awt.Color(0, 204, 204));
        btnM.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnM.setText("M");
        btnM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMActionPerformed(evt);
            }
        });
        pnBtn.add(btnM);

        getContentPane().add(pnBtn, java.awt.BorderLayout.SOUTH);

        pnPlv.setLayout(new java.awt.BorderLayout());

        tfpalavra.setEditable(false);
        tfpalavra.setFont(new java.awt.Font("Comic Sans MS", 3, 64)); // NOI18N
        tfpalavra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfpalavra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfpalavraActionPerformed(evt);
            }
        });
        pnPlv.add(tfpalavra, java.awt.BorderLayout.CENTER);

        lbImgForca.setForeground(new java.awt.Color(255, 255, 255));
        lbImgForca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbImgForca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagemForca/ImgForcaDefaultC.jpg"))); // NOI18N
        pnPlv.add(lbImgForca, java.awt.BorderLayout.EAST);

        getContentPane().add(pnPlv, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQActionPerformed
        btnQ.setEnabled(false);
        atualizaCampoPalavra('Q');
        contPalpites++;

    }//GEN-LAST:event_btnQActionPerformed

    private void btnAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAActionPerformed
        // TODO add your handling code here:
        btnA.setEnabled(false);
        atualizaCampoPalavra('A');
        contPalpites++;
    }//GEN-LAST:event_btnAActionPerformed

    private void btnZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZActionPerformed
        // TODO add your handling code here:
        btnZ.setEnabled(false);
        atualizaCampoPalavra('Z');
        contPalpites++;
    }//GEN-LAST:event_btnZActionPerformed

    private void btnWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWActionPerformed
        // TODO add your handling code here:
        btnW.setEnabled(false);
        atualizaCampoPalavra('W');
        contPalpites++;

    }//GEN-LAST:event_btnWActionPerformed

    private void btnSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSActionPerformed
        // TODO add your handling code here:
        btnS.setEnabled(false);
        atualizaCampoPalavra('S');
        contPalpites++;

    }//GEN-LAST:event_btnSActionPerformed

    private void btnXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXActionPerformed
        // TODO add your handling code here:
        btnX.setEnabled(false);
        atualizaCampoPalavra('X');
        contPalpites++;

    }//GEN-LAST:event_btnXActionPerformed

    private void btnEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEActionPerformed
        // TODO add your handling code here:
        btnE.setEnabled(false);
        atualizaCampoPalavra('E');
        contPalpites++;

    }//GEN-LAST:event_btnEActionPerformed

    private void btnDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDActionPerformed
        // TODO add your handling code here:
        btnD.setEnabled(false);
        atualizaCampoPalavra('D');
        contPalpites++;
    }//GEN-LAST:event_btnDActionPerformed

    private void btnCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCActionPerformed
        // TODO add your handling code here:
        btnC.setEnabled(false);
        atualizaCampoPalavra('C');
        contPalpites++;
    }//GEN-LAST:event_btnCActionPerformed

    private void btnRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRActionPerformed
        // TODO add your handling code here:
        btnR.setEnabled(false);
        atualizaCampoPalavra('R');
        contPalpites++;
    }//GEN-LAST:event_btnRActionPerformed

    private void btnFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFActionPerformed
        // TODO add your handling code here:
        btnF.setEnabled(false);
        atualizaCampoPalavra('F');
        contPalpites++;


    }//GEN-LAST:event_btnFActionPerformed

    private void btnVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVActionPerformed
        // TODO add your handling code here:
        btnV.setEnabled(false);
        atualizaCampoPalavra('V');
        contPalpites++;
    }//GEN-LAST:event_btnVActionPerformed

    private void btnTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTActionPerformed
        // TODO add your handling code here:
        btnT.setEnabled(false);
        atualizaCampoPalavra('T');
        contPalpites++;
    }//GEN-LAST:event_btnTActionPerformed

    private void btnGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGActionPerformed
        // TODO add your handling code here:
        btnG.setEnabled(false);
        atualizaCampoPalavra('G');
        contPalpites++;
    }//GEN-LAST:event_btnGActionPerformed

    private void btnBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBActionPerformed
        // TODO add your handling code here:
        btnB.setEnabled(false);
        atualizaCampoPalavra('B');
        contPalpites++;

    }//GEN-LAST:event_btnBActionPerformed

    private void btnYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYActionPerformed
        // TODO add your handling code here:
        btnY.setEnabled(false);
        atualizaCampoPalavra('Y');
        contPalpites++;

    }//GEN-LAST:event_btnYActionPerformed

    private void btnHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHActionPerformed
        // TODO add your handling code here:
        btnH.setEnabled(false);
        atualizaCampoPalavra('H');
        contPalpites++;

    }//GEN-LAST:event_btnHActionPerformed

    private void btnNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNActionPerformed
        // TODO add your handling code here:
        btnN.setEnabled(false);
        atualizaCampoPalavra('N');
        contPalpites++;
    }//GEN-LAST:event_btnNActionPerformed

    private void btnUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUActionPerformed
        // TODO add your handling code here:
        btnU.setEnabled(false);
        atualizaCampoPalavra('U');
        contPalpites++;
    }//GEN-LAST:event_btnUActionPerformed

    private void btnJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJActionPerformed
        // TODO add your handling code here:
        btnJ.setEnabled(false);
        atualizaCampoPalavra('J');
        contPalpites++;
    }//GEN-LAST:event_btnJActionPerformed

    private void btnMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMActionPerformed
        // TODO add your handling code here:
        btnM.setEnabled(false);
        atualizaCampoPalavra('M');
        contPalpites++;
    }//GEN-LAST:event_btnMActionPerformed

    private void btnIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIActionPerformed
        // TODO add your handling code here:
        btnI.setEnabled(false);
        atualizaCampoPalavra('I');
        contPalpites++;
    }//GEN-LAST:event_btnIActionPerformed

    private void btnKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKActionPerformed
        // TODO add your handling code here:
        btnK.setEnabled(false);
        atualizaCampoPalavra('K');
        contPalpites++;
    }//GEN-LAST:event_btnKActionPerformed

    private void btnOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOActionPerformed
        // TODO add your handling code here:
        btnO.setEnabled(false);
        atualizaCampoPalavra('O');
        contPalpites++;
    }//GEN-LAST:event_btnOActionPerformed

    private void btnÇActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnÇActionPerformed
        // TODO add your handling code here:
        btnÇ.setEnabled(false);
        atualizaCampoPalavra('Ç');
        contPalpites++;
    }//GEN-LAST:event_btnÇActionPerformed

    private void btnLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLActionPerformed
        // TODO add your handling code here:
        btnL.setEnabled(false);
        atualizaCampoPalavra('L');
        contPalpites++;
    }//GEN-LAST:event_btnLActionPerformed

    private void btnPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPActionPerformed
        // TODO add your handling code here:
        btnP.setEnabled(false);
        atualizaCampoPalavra('P');
        contPalpites++;

    }//GEN-LAST:event_btnPActionPerformed

    private void tfpalavraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfpalavraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfpalavraActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        System.out.println("key pressed tela");
    }//GEN-LAST:event_formKeyPressed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        System.out.println("key typed tela");
    }//GEN-LAST:event_formKeyTyped

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        System.out.println("key relased tela");
    }//GEN-LAST:event_formKeyReleased

    private void lbTextoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTextoMouseEntered
        // TODO add your handling code here:
        if (numDicaTexto > 0 && canEnableButton) {
            lbTexto.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
            lbTexto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

    }//GEN-LAST:event_lbTextoMouseEntered

    private void lbTextoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTextoMouseExited
        // TODO add your handling code here:
        lbTexto.setBorder(null);
    }//GEN-LAST:event_lbTextoMouseExited

    private void lbImagemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbImagemMouseEntered
        // TODO add your handling code here:
        if (numDicaImagem > 0 && canEnableButton) {
            lbImagem.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
            lbImagem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

    }//GEN-LAST:event_lbImagemMouseEntered

    private void lbImagemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbImagemMouseExited
        // TODO add your handling code here:
        lbImagem.setBorder(null);
    }//GEN-LAST:event_lbImagemMouseExited

    private void lbSomMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSomMouseEntered
        // TODO add your handling code here:
        if (numDicaSom > 0 && canEnableButton) {
            lbSom.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
            lbSom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

    }//GEN-LAST:event_lbSomMouseEntered

    private void lbSomMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSomMouseExited
        // TODO add your handling code here:
        lbSom.setBorder(null);
    }//GEN-LAST:event_lbSomMouseExited

    private void lbTextoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTextoMouseClicked
        // TODO add your handling code here:
        if (numDicaTexto > 0 && canEnableButton) {
            comprarDica(p, "texto");
            habilitaBotaoCompra();
        }


    }//GEN-LAST:event_lbTextoMouseClicked

    private void lbImagemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbImagemMouseClicked
        // TODO add your handling code here:
        if (numDicaImagem > 0 && canEnableButton) {
            comprarDica(p, "imagem");
            habilitaBotaoCompra();
        }

    }//GEN-LAST:event_lbImagemMouseClicked

    private void lbSomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSomMouseClicked
        // TODO add your handling code here:
        if (numDicaSom > 0 && canEnableButton) {
            comprarDica(p, "som");
            habilitaBotaoCompra();

        }
    }//GEN-LAST:event_lbSomMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Telajogo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Telajogo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Telajogo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Telajogo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Telajogo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnA;
    private javax.swing.JButton btnB;
    private javax.swing.JButton btnC;
    private javax.swing.JButton btnD;
    private javax.swing.JButton btnE;
    private javax.swing.JButton btnF;
    private javax.swing.JButton btnG;
    private javax.swing.JButton btnH;
    private javax.swing.JButton btnI;
    private javax.swing.JButton btnJ;
    private javax.swing.JButton btnK;
    private javax.swing.JButton btnL;
    private javax.swing.JButton btnM;
    private javax.swing.JButton btnN;
    private javax.swing.JButton btnO;
    private javax.swing.JButton btnP;
    private javax.swing.JButton btnQ;
    private javax.swing.JButton btnR;
    private javax.swing.JButton btnS;
    private javax.swing.JButton btnT;
    private javax.swing.JButton btnU;
    private javax.swing.JButton btnV;
    private javax.swing.JButton btnW;
    private javax.swing.JButton btnX;
    private javax.swing.JButton btnY;
    private javax.swing.JButton btnZ;
    private javax.swing.JButton btnÇ;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel lbImagem;
    private javax.swing.JLabel lbImgForca;
    private javax.swing.JLabel lbSom;
    private javax.swing.JLabel lbTexto;
    private javax.swing.JPanel pnBtn;
    private javax.swing.JPanel pnDicas;
    private javax.swing.JPanel pnPlv;
    private javax.swing.JPanel pninform;
    private javax.swing.JTextField tfchances;
    private javax.swing.JTextField tfnome;
    private javax.swing.JTextField tfpalavra;
    private javax.swing.JTextField txPt;
    // End of variables declaration//GEN-END:variables
}
