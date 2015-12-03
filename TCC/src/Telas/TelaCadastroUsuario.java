/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Bean.DicaBean;
import Bean.JogadorBean;
import Dao.JogadorDao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import util.LimitaCaracteres;
import util.UtilInterface;

/**
 *
 * @author Convidado
 */
public class TelaCadastroUsuario extends javax.swing.JDialog {

    public static ImageIcon imageReturn = new ImageIcon("src/Telas/imagens/imguser.jpg");

    public static JFileChooser arquivoSelect = new JFileChooser();
    public boolean salvar = true;
    private int id = 0;
    private static boolean isAction = false;
    /**
     * Creates new form TelaCadastroUsuario
     */

    //definitions
    private DaemonThread myThread = null;
    int count = 0;
    VideoCapture webSource = null;

    Mat frame = new Mat();
    MatOfByte mem = new MatOfByte();

    //class of thread
    class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    if (webSource.grab()) {
                        try {
                            webSource.retrieve(frame);
                            Imgcodecs.imencode(".bmp", frame, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
                            BufferedImage buff = (BufferedImage) im;
                            Graphics g = lbImagemUser.getGraphics();

                            if (g.drawImage(buff, 0, 0, getWidth() - 320, getHeight() - 320, 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                                if (runnable == false) {
                                    System.out.println("Going to wait()");
                                    this.wait();
                                }
                            }
                        } catch (Exception ex) {
                            System.out.println("Error");
                        }
                    }
                }
            }
        }
    }

    //end class
    public TelaCadastroUsuario(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        imageReturn.setImage(imageReturn.getImage().getScaledInstance(148, 136, 100));
        lbImagemUser.setIcon(imageReturn);
        getRootPane().setDefaultButton(btnSalvar);

        txNome.setDocument(new LimitaCaracteres(45));
        txNomeUser.setDocument(new LimitaCaracteres(45));
        configuraComponentes();
        setSize(481, 460);
        setResizable(true);

    }

    public TelaCadastroUsuario(java.awt.Dialog parent, boolean modal, JogadorBean j) {
        super(parent, modal);
        initComponents();
        salvar = false;
        id = j.getIdJogador();
        configuraComponentes();
        PreencherCampos(j);
        
        setSize(481, 460);
        setResizable(true);

    }

    private void configuraComponentes() {
        btnSalvar.setIcon(UtilInterface.ICONE_SALVAR);
        btnCancelar.setIcon(UtilInterface.ICONE_CANCELAR);
        UtilInterface.setFontes(pnTudo.getComponents());
        UtilInterface.setFontes(jPanel2.getComponents());
        webSource = new VideoCapture(0);
        if (webSource .grab()) {
            btnCam.setEnabled(true);
        }
        
        
      txConfirmSenha.setDocument(new LimitaCaracteres(50));
      txEmail.setDocument(new LimitaCaracteres(50));
      txNome.setDocument(new LimitaCaracteres(50));
      txNomeUser.setDocument(new LimitaCaracteres(50));
      txSenha.setDocument(new LimitaCaracteres(50));
    }

    public void PreencherCampos(JogadorBean j) {
        txNome.setText(j.getNome());
        txNomeUser.setText(j.getLogin());
        System.err.println("SEXO: " + j.getSexo());
        if (j.getSexo().equalsIgnoreCase("Masculino")) {
            cbSexo.setSelectedIndex(1);
        } else {
            cbSexo.setSelectedIndex(2);
        }

        System.err.println("EMAIL: " + j.getEmail());
        txEmail.setText(j.getEmail());
        try {
            util.VoltaImagemJPG.Desconvertimg(j.getImgUser(), "src/imgUsers/imgger.jpg");
        } catch (IOException ex) {
            Logger.getLogger(TelaCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImageIcon img = new ImageIcon("src/imgUsers/imgger.jpg");
        img.setImage(img.getImage().getScaledInstance(148, 136, 100));
        lbImagemUser.setIcon(img);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        pnTudo = new javax.swing.JPanel();
        lbNameUser = new javax.swing.JLabel();
        txNomeUser = new javax.swing.JTextField();
        lbsenha = new javax.swing.JLabel();
        txSenha = new javax.swing.JPasswordField();
        lbEmail = new javax.swing.JLabel();
        txEmail = new javax.swing.JTextField();
        lbSenhaConfirm = new javax.swing.JLabel();
        txConfirmSenha = new javax.swing.JPasswordField();
        lbImagemUser = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txNome = new javax.swing.JTextField();
        lbNome = new javax.swing.JLabel();
        lbSexo = new javax.swing.JLabel();
        cbSexo = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        btnEnvImagem = new javax.swing.JButton();
        btnCam = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulário de Cadastro");

        pnTudo.setBackground(new java.awt.Color(153, 153, 255));
        pnTudo.setForeground(new java.awt.Color(153, 153, 255));

        lbNameUser.setFont(lbNameUser.getFont().deriveFont(lbNameUser.getFont().getSize()+1f));
        lbNameUser.setText("Nome de usuario:*");

        txNomeUser.setToolTipText("Digite seu nome de usuário neste campo");

        lbsenha.setFont(lbsenha.getFont().deriveFont(lbsenha.getFont().getSize()+1f));
        lbsenha.setText("Senha:*");

        txSenha.setToolTipText(" Digite sua senha de login neste campo");

        lbEmail.setFont(lbEmail.getFont().deriveFont(lbEmail.getFont().getSize()+1f));
        lbEmail.setText("Email:");

        txEmail.setToolTipText("Digite seu email neste campo. No formato: meuemail@email.com");
        txEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txEmailActionPerformed(evt);
            }
        });

        lbSenhaConfirm.setFont(lbSenhaConfirm.getFont().deriveFont(lbSenhaConfirm.getFont().getSize()+1f));
        lbSenhaConfirm.setText("Confirmação de senha:*");

        lbImagemUser.setPreferredSize(new java.awt.Dimension(110, 110));

        jPanel2.setBackground(java.awt.Color.yellow);
        jPanel2.setForeground(new java.awt.Color(226, 16, 16));

        btnSalvar.setFont(btnSalvar.getFont().deriveFont(btnSalvar.getFont().getSize()+1f));
        btnSalvar.setText("Salvar");
        btnSalvar.setToolTipText("Clique para salvar o jogador e seus dados");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalvar);

        btnCancelar.setFont(btnCancelar.getFont().deriveFont(btnCancelar.getFont().getSize()+1f));
        btnCancelar.setText("Cancelar");
        btnCancelar.setToolTipText("Clique para cancelar esta operação");
        jPanel2.add(btnCancelar);

        txNome.setToolTipText("Digite seu nome neste campo (preferencialmente o nome completo)");

        lbNome.setFont(lbNome.getFont().deriveFont(lbNome.getFont().getSize()+1f));
        lbNome.setText("Nome:*");

        lbSexo.setText("Sexo:*");

        cbSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<<Selecione>>", "Masculino", "Feminino" }));

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));
        jPanel3.setForeground(new java.awt.Color(153, 153, 255));
        jPanel3.setLayout(new java.awt.GridLayout(2, 1));

        btnEnvImagem.setFont(btnEnvImagem.getFont().deriveFont(btnEnvImagem.getFont().getSize()+1f));
        btnEnvImagem.setText("Enviar Imagem");
        btnEnvImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnvImagemActionPerformed(evt);
            }
        });
        jPanel3.add(btnEnvImagem);

        btnCam.setText("Webcam");
        btnCam.setEnabled(false);
        btnCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCamActionPerformed(evt);
            }
        });
        jPanel3.add(btnCam);

        javax.swing.GroupLayout pnTudoLayout = new javax.swing.GroupLayout(pnTudo);
        pnTudo.setLayout(pnTudoLayout);
        pnTudoLayout.setHorizontalGroup(
            pnTudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTudoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnTudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTudoLayout.createSequentialGroup()
                        .addComponent(lbNome)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnTudoLayout.createSequentialGroup()
                        .addGroup(pnTudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txNome)
                            .addComponent(txConfirmSenha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                            .addComponent(lbNameUser)
                            .addComponent(lbEmail)
                            .addComponent(lbsenha)
                            .addComponent(lbSenhaConfirm)
                            .addComponent(txNomeUser)
                            .addComponent(txEmail)
                            .addComponent(txSenha)
                            .addComponent(lbSexo)
                            .addComponent(cbSexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnTudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbImagemUser, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(31, 31, 31))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTudoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnTudoLayout.setVerticalGroup(
            pnTudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTudoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnTudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnTudoLayout.createSequentialGroup()
                        .addComponent(txNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNameUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txNomeUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbSexo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbsenha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbSenhaConfirm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txConfirmSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnTudoLayout.createSequentialGroup()
                        .addComponent(lbImagemUser, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnTudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnTudo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txEmailActionPerformed

    private void btnEnvImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnvImagemActionPerformed
        // TODO add your handling code here:
        PegarImagem();
        lbImagemUser.setIcon(imageReturn);


    }//GEN-LAST:event_btnEnvImagemActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        if (VerificaCampos()) {
            if (salvar) {
                try {
                    JogadorDao.SalvarJogador(RetornObjetoJogador());
                    JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");
                    webSource.release();

                } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex) {
                    //JOptionPane.showMessageDialog(null, UtilInterface.MSG_REGISTRO_DUPLICADO);
                    JOptionPane.showMessageDialog(null, "O nome de usuario já existe!");
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException ex) {
                    Logger.getLogger(TelaCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {

                try {
                    try {
                        JogadorDao.AlterarJogador(RetornObjetoJogador());
                        JOptionPane.showMessageDialog(null, "Usuario alterado com sucesso!");
                        webSource.release();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(TelaCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            dispose();
            try {
                new TelaLogin(null, true).setVisible(true);
            } catch (NoSuchFieldException ex) {
                Logger.getLogger(TelaCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(TelaCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(TelaCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCamActionPerformed
        // TODO add your handling code here:
        if (!isAction) {
            //video capture form default cam
            //webSource = new VideoCapture(0);
            myThread = new DaemonThread(); //create object of tjreat class
            Thread t = new Thread(myThread);
            t.setDaemon(true);
            myThread.runnable = true;
            t.start(); //start thread
            isAction = true;
            btnCam.setText("Tirar");
        } else {
            isAction = false;
            myThread.runnable = false;
            btnCam.setText("Webcam");
            Imgcodecs.imwrite("src/imgUsers/imgger.jpg", frame);
            ImageIcon im = new ImageIcon("src/imgUsers/imgger.jpg");
            im.setImage(im.getImage().getScaledInstance(148, 136, 100));
            imageReturn = im;
            lbImagemUser.setIcon(im);
            //webSource.release();
//            int accept = jFileChooser1.showSaveDialog(this);
//            if (accept == JFileChooser.APPROVE_OPTION) {
//                File file = jFileChooser1.getSelectedFile();
//                Imgcodecs.imwrite("src/Telas/imagens/imguser.jpg", frame);
//            } else {
//                System.err.println("Cancelado!");
//            }
        }


    }//GEN-LAST:event_btnCamActionPerformed

    private JogadorBean RetornObjetoJogador() throws IOException {
        JogadorBean jog = new JogadorBean();
        jog.setNome(txNome.getText());
        jog.setLogin(txNomeUser.getText());
        jog.setPassword(new String(txSenha.getPassword()));
        jog.setEmail(txEmail.getText());
        jog.setImgUser(ConvertByte());
        jog.setIdJogador(id);
        jog.setSexo(cbSexo.getSelectedItem().toString());

        return jog;
    }

    public static boolean validarEmail(String email) {
        boolean valid = true;

// Expressão Regular para validar E-mail
        Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher m = p.matcher(email);
        if (!m.find()) {
            valid = false;
        }
        return valid;
    }

    public boolean VerificaCampos() {
        int err = 0;
        boolean veri = false;

        if (txNome.getText().trim().equals("")) {
            lbNome.setForeground(Color.red);
            err++;
        } else {
            lbNome.setForeground(Color.BLACK);
        }

        if (!txEmail.getText().trim().equals("")) {
            if (!validarEmail(txEmail.getText().trim())) {
                lbEmail.setForeground(Color.red);
                err++;
            } else {
                lbEmail.setForeground(Color.BLACK);
            }
        }

        if (txNomeUser.getText().trim().equals("")) {
            lbNameUser.setForeground(Color.red);
            err++;
        } else {
            lbNameUser.setForeground(Color.BLACK);
        }

        if (salvar = true) {
            if (new String(txSenha.getPassword()).trim().equals("")) {
                lbsenha.setForeground(Color.red);
                err++;
            } else {
                lbsenha.setForeground(Color.BLACK);
            }

            if (new String(txConfirmSenha.getPassword()).trim().equals("") || !(new String(txConfirmSenha.getPassword()).equals(new String(txSenha.getPassword())))) {
                lbSenhaConfirm.setForeground(Color.red);
                err++;
            } else {
                lbSenhaConfirm.setForeground(Color.BLACK);
            }
        }

        if (cbSexo.getSelectedIndex() == 0) {
            lbSexo.setForeground(Color.red);
            err++;
        } else {
            lbSexo.setForeground(Color.BLACK);
        }

        if (err == 0) {
            veri = true;
        }

        return veri;
    }

    private void PegarImagem() {
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagens", "jpg", "png", "jpeg");
        arquivoSelect.setFileFilter(filtro);
        arquivoSelect.setAcceptAllFileFilterUsed(false);

        arquivoSelect.setFileSelectionMode(JFileChooser.FILES_ONLY);

        File file = new File("");
        String caminho_padrao = "G:";
        File pathInicial = new File(caminho_padrao);
        arquivoSelect.setCurrentDirectory(pathInicial);

        int salvar = arquivoSelect.showOpenDialog(null);
        if (salvar == JFileChooser.APPROVE_OPTION) {
            ImageIcon imagemSelec = new ImageIcon(arquivoSelect.getSelectedFile().getPath());
            imagemSelec.setImage(imagemSelec.getImage().getScaledInstance(148, 136, 100));
            imageReturn = imagemSelec;
        }

    }

    public byte[] ConvertByte() throws IOException {
        BufferedImage imagemB = ImageIO.read(new File(imageReturn.getDescription()));
        // System.out.println (imagemB);
        //REDIMENSIONAR A IMAGEM
        int width = 500; //200
        int height = 500;

        BufferedImage ImagemRedimensionada = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        ImagemRedimensionada.getGraphics().drawImage(imagemB, 0, 0, width, height, new Color(240, 240, 240), null);
        ByteArrayOutputStream bytesImg = new ByteArrayOutputStream();
        ImageIO.write((BufferedImage) ImagemRedimensionada, "png", bytesImg);//seta a imagem para bytesImg 
        bytesImg.flush();//limpa a variável    
        byte[] byteArray = bytesImg.toByteArray();//Converte ByteArrayOutputStream para byte[]     
        bytesImg.close();//fecha a conversão    
        DicaBean d = new DicaBean();
        d.setImagem(byteArray);
        byte[] img = d.getImagem();
        return img;

    }

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
            java.util.logging.Logger.getLogger(TelaCadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaCadastroUsuario dialog = new TelaCadastroUsuario(new javax.swing.JDialog(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCam;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEnvImagem;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox cbSexo;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbImagemUser;
    private javax.swing.JLabel lbNameUser;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbSenhaConfirm;
    private javax.swing.JLabel lbSexo;
    private javax.swing.JLabel lbsenha;
    private javax.swing.JPanel pnTudo;
    private javax.swing.JPasswordField txConfirmSenha;
    private javax.swing.JTextField txEmail;
    private javax.swing.JTextField txNome;
    private javax.swing.JTextField txNomeUser;
    private javax.swing.JPasswordField txSenha;
    // End of variables declaration//GEN-END:variables
}
