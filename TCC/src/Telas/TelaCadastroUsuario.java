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
import java.awt.Image;
import java.awt.image.BufferedImage;
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
import javax.swing.filechooser.FileNameExtensionFilter;
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
    /**
     * Creates new form TelaCadastroUsuario
     */
    public TelaCadastroUsuario(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        imageReturn.setImage(imageReturn.getImage().getScaledInstance(96, 96, 100));
        lbImagemUser.setIcon(imageReturn);
        getRootPane().setDefaultButton(btnSalvar);
        
        txNome.setDocument(new LimitaCaracteres());
        txNomeUser.setDocument(new LimitaCaracteres());
        btnSalvar.setIcon(UtilInterface.ICONE_SALVAR);
        btnCancelar.setIcon(UtilInterface.ICONE_CANCELAR);
        UtilInterface.setFontes(jPanel1.getComponents());
        UtilInterface.setFontes(jPanel2.getComponents());
        setResizable(false);
    }

    public TelaCadastroUsuario(java.awt.Dialog parent, boolean modal, JogadorBean j) {
        super(parent, modal);
        initComponents();
        salvar = false;
        id = j.getIdJogador();
        PreencherCampos(j);
        btnSalvar.setIcon(UtilInterface.ICONE_SALVAR);
        btnCancelar.setIcon(UtilInterface.ICONE_CANCELAR);
        UtilInterface.setFontes(jPanel1.getComponents());
        UtilInterface.setFontes(jPanel2.getComponents());
        setResizable(false);
        
    }
    
    public void PreencherCampos(JogadorBean j){
        txNome.setText(j.getNome());
        txNomeUser.setText(j.getLogin());
        txEmail.setText(j.getEmail());
        try {
            util.VoltaImagemJPG.Desconvertimg(j.getImgUser());
        } catch (IOException ex) {
            Logger.getLogger(TelaCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImageIcon img = new ImageIcon("src/imgUsers/imgger.jpg");
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

        jPanel1 = new javax.swing.JPanel();
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
        btnEnvImagem = new javax.swing.JButton();
        txNome = new javax.swing.JTextField();
        lbNome = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulário de Cadastro");

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setForeground(new java.awt.Color(153, 153, 255));

        lbNameUser.setFont(lbNameUser.getFont().deriveFont(lbNameUser.getFont().getSize()+1f));
        lbNameUser.setText("Nome de usuario:*");

        lbsenha.setFont(lbsenha.getFont().deriveFont(lbsenha.getFont().getSize()+1f));
        lbsenha.setText("Senha:*");

        lbEmail.setFont(lbEmail.getFont().deriveFont(lbEmail.getFont().getSize()+1f));
        lbEmail.setText("Email:*");

        txEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txEmailActionPerformed(evt);
            }
        });

        lbSenhaConfirm.setFont(lbSenhaConfirm.getFont().deriveFont(lbSenhaConfirm.getFont().getSize()+1f));
        lbSenhaConfirm.setText("Confirmação de senha:*");

        lbImagemUser.setPreferredSize(new java.awt.Dimension(96, 96));

        jPanel2.setBackground(java.awt.Color.yellow);
        jPanel2.setForeground(new java.awt.Color(226, 16, 16));

        btnSalvar.setFont(btnSalvar.getFont().deriveFont(btnSalvar.getFont().getSize()+1f));
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalvar);

        btnCancelar.setFont(btnCancelar.getFont().deriveFont(btnCancelar.getFont().getSize()+1f));
        btnCancelar.setText("Cancelar");
        jPanel2.add(btnCancelar);

        btnEnvImagem.setFont(btnEnvImagem.getFont().deriveFont(btnEnvImagem.getFont().getSize()+1f));
        btnEnvImagem.setText("Enviar Imagem");
        btnEnvImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnvImagemActionPerformed(evt);
            }
        });

        lbNome.setFont(lbNome.getFont().deriveFont(lbNome.getFont().getSize()+1f));
        lbNome.setText("Nome:*");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbNome)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txNome, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txConfirmSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                                    .addComponent(lbNameUser, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbsenha, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbSenhaConfirm, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txNomeUser, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txSenha, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnEnvImagem, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(lbImagemUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8)))))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbNameUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txNomeUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbsenha))
                    .addComponent(lbImagemUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnvImagem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbSenhaConfirm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txConfirmSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    dispose();
                } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex){
                    JOptionPane.showMessageDialog(null, UtilInterface.MSG_REGISTRO_DUPLICADO);
                }catch(SQLException e){
                    e.printStackTrace();
                } catch (IOException ex) {
                    Logger.getLogger(TelaCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }

            }else{
                
                try {
                    try {
                        JogadorDao.AlterarJogador(RetornObjetoJogador());
                        JOptionPane.showMessageDialog(null, "Usuario alterado com sucesso!");
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(TelaCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
                        
                
            }
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private JogadorBean RetornObjetoJogador() throws IOException {
        JogadorBean jog = new JogadorBean();
        jog.setNome(txNome.getText());
        jog.setLogin(txNomeUser.getText());
        jog.setPassword(new String(txSenha.getPassword()));
        jog.setEmail(txEmail.getText());
        jog.setImgUser(ConvertByte());
        jog.setIdJogador(id);

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

        if (txEmail.getText().trim().equals("") || !validarEmail(txEmail.getText().trim())) {
            lbEmail.setForeground(Color.red);
            err++;
        } else {
            lbEmail.setForeground(Color.BLACK);
        }

        if (txNomeUser.getText().trim().equals("")) {
            lbNameUser.setForeground(Color.red);
            err++;
        } else {
            lbNameUser.setForeground(Color.BLACK);
        }

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
            imagemSelec.setImage(imagemSelec.getImage().getScaledInstance(96, 96, 100));
            imageReturn = imagemSelec;
        }

    }

    public byte[] ConvertByte() throws IOException {
        BufferedImage imagemB = ImageIO.read(new File(imageReturn.getDescription()));
        // System.out.println (imagemB);
        //REDIMENSIONAR A IMAGEM
        int width = 200;
        int height = 200;

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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEnvImagem;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbImagemUser;
    private javax.swing.JLabel lbNameUser;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbSenhaConfirm;
    private javax.swing.JLabel lbsenha;
    private javax.swing.JPasswordField txConfirmSenha;
    private javax.swing.JTextField txEmail;
    private javax.swing.JTextField txNome;
    private javax.swing.JTextField txNomeUser;
    private javax.swing.JPasswordField txSenha;
    // End of variables declaration//GEN-END:variables
}
