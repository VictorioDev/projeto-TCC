/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Bean.JogadorBean;
import Dao.JogadorDao;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.UtilInterface;

/**
 *
 * @author Convidado
 */
public class TelaLogin extends javax.swing.JDialog {

    private JogadorBean jogadorLogado;

    /**
     * Creates new form TelaLogin
     */
    public TelaLogin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btnLogar);
//        btnLogar.setIcon(UtilInterface.ICONE_SALVAR);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnCampos = new javax.swing.JPanel();
        lbLogin = new javax.swing.JLabel();
        txLogin = new javax.swing.JTextField();
        lbSenha = new javax.swing.JLabel();
        txSenha = new javax.swing.JPasswordField();
        pnBotoes = new javax.swing.JPanel();
        btnLogar = new javax.swing.JButton();
        lbCadastrese = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        pnCampos.setBackground(new java.awt.Color(153, 153, 255));

        lbLogin.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        lbLogin.setText("Login:*");

        txLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txLoginMouseClicked(evt);
            }
        });

        lbSenha.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        lbSenha.setText("Senha:*");

        pnBotoes.setBackground(java.awt.Color.yellow);
        pnBotoes.setForeground(new java.awt.Color(153, 153, 255));

        btnLogar.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnLogar.setText("Entrar");
        btnLogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogarActionPerformed(evt);
            }
        });
        pnBotoes.add(btnLogar);

        lbCadastrese.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        lbCadastrese.setText("Cadastre-se");
        lbCadastrese.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCadastreseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbCadastreseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbCadastreseMouseExited(evt);
            }
        });
        pnBotoes.add(lbCadastrese);

        javax.swing.GroupLayout pnCamposLayout = new javax.swing.GroupLayout(pnCampos);
        pnCampos.setLayout(pnCamposLayout);
        pnCamposLayout.setHorizontalGroup(
            pnCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCamposLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnCamposLayout.createSequentialGroup()
                        .addComponent(lbLogin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txLogin))
                    .addGroup(pnCamposLayout.createSequentialGroup()
                        .addGroup(pnCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnCamposLayout.createSequentialGroup()
                                .addComponent(lbSenha)
                                .addGap(1, 1, 1)
                                .addComponent(txSenha))
                            .addComponent(pnBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnCamposLayout.setVerticalGroup(
            pnCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCamposLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLogin))
                .addGap(18, 18, 18)
                .addGroup(pnCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSenha)
                    .addComponent(txSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnCampos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnCampos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogarActionPerformed
        // TODO add your handling code here:
        if (VerificaCampos()) {
            boolean verify = false;
            try {
                JogadorBean jogador = new JogadorBean();
                jogador.setLogin(txLogin.getText());
                jogador.setPassword(txSenha.getText());
                jogador.setPontos(300);
                jogadorLogado = JogadorDao.retornaJogadorLogado(jogador);
                util.UtilObjetos.jogadorLogado = jogadorLogado;
                dispose();
                new TelaInicial(jogadorLogado).setVisible(true);

            } catch (SQLException ex) {
                System.out.println("INVALIDO");
            }
        }


    }//GEN-LAST:event_btnLogarActionPerformed

    private void lbCadastreseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCadastreseMouseEntered
        lbCadastrese.setForeground(Color.blue);
        lbCadastrese.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lbCadastreseMouseEntered

    private void lbCadastreseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCadastreseMouseExited
        lbCadastrese.setForeground(Color.BLACK);
    }//GEN-LAST:event_lbCadastreseMouseExited

    private void lbCadastreseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCadastreseMouseClicked
        dispose();
        new TelaCadastroUsuario(null, true).setVisible(true);
    }//GEN-LAST:event_lbCadastreseMouseClicked

    private void txLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txLoginMouseClicked
        try {
            ativaTeclado();
        } catch (AWTException ex) {
            Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txLoginMouseClicked

    private boolean VerificaCampos() {
        boolean veri = false;
        int err = 0;
        if (txLogin.getText().trim().equals("")) {
            lbLogin.setForeground(Color.RED);
            err++;
        } else {
            lbLogin.setForeground(Color.RED);
        }

        if (new String(txSenha.getPassword()).equals("")) {
            lbSenha.setForeground(Color.RED);
            err++;

        } else {
            lbSenha.setForeground(Color.RED);
        }

        if (err == 0) {
            veri = true;
        }

        return veri;

    }

    private void ativaTeclado() throws AWTException {
        Robot r = new Robot();
        r.mouseMove(1230, 15);
        r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

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
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaLogin dialog = new TelaLogin(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnLogar;
    private javax.swing.JLabel lbCadastrese;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbSenha;
    private javax.swing.JPanel pnBotoes;
    private javax.swing.JPanel pnCampos;
    private javax.swing.JTextField txLogin;
    private javax.swing.JPasswordField txSenha;
    // End of variables declaration//GEN-END:variables
}
