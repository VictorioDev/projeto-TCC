/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Bean.JogadorBean;
import Bean.PalavraBean;
import Dao.JogadorDao;
import Dao.PalavraDao;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import util.LimitaCaracteres;
import util.Relatorio;
import util.UtilInterface;

/**
 *
 * @author victorio
 */
public class TelaPesquisaJogadores extends javax.swing.JDialog {

    /**
     * Creates new form TelaPesquisaJogadores
     */
    public static List<JogadorBean> listaj = new ArrayList<JogadorBean>();

    public TelaPesquisaJogadores(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        VerificaBotoes();
        configuraComponentes();
        setLocationRelativeTo(null);
       
    }

    private void configuraComponentes() {
        txDescricaoNomejog.setFont(UtilInterface.FONTE_PADRAO);
        btnNovaPalavra.setIcon(UtilInterface.ICONE_NOVO);
        btnExcluir.setIcon(UtilInterface.ICONE_DELETAR);
        btnPesquisar.setIcon(UtilInterface.ICONE_PESQUISAR);
        btnAlterar.setIcon(UtilInterface.ICONE_ALTERAR);
        btnImprimir.setIcon(UtilInterface.ICONE_RELATORIO);
        txDescricaoNomejog.setDocument(new LimitaCaracteres(50));
    }

    private JogadorBean retornaObjeto() {
        JogadorBean j = new JogadorBean();
        j.setNome(txDescricaoNomejog.getText());
        return j;
    }

    private void atualizaTabela() {
        DefaultTableModel modelo = (DefaultTableModel) tabelaPesquisaJogadores.getModel();
        modelo.setNumRows(0);
        try {
            listaj = JogadorDao.RetornaJogadores(retornaObjeto());
            for (JogadorBean j : listaj) {
                modelo.addRow(new Object[]{
                    j.getNome()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (listaj.size() == 0) {
            lbMensagem.setText("Não há registros!");
            lbMensagem.setForeground(Color.red);

        }
    }

    private void VerificaBotoes() {
        if (tabelaPesquisaJogadores.getSelectedRow() == -1 || listaj.size() < 0) {
            btnAlterar.setEnabled(false);
            btnExcluir.setEnabled(false);

        } else {
            btnAlterar.setEnabled(true);
            btnExcluir.setEnabled(true);

        }
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
        lbNomePalavra = new javax.swing.JLabel();
        txDescricaoNomejog = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaPesquisaJogadores = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnNovaPalavra = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lbMensagem = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisa de Jogadores");
        setBackground(new java.awt.Color(153, 153, 225));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 153, 225));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 12))); // NOI18N

        lbNomePalavra.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        lbNomePalavra.setText("Descrição:*");

        txDescricaoNomejog.setToolTipText("Digite o nome do jogador desejado (ou as primeiras letras deste) neste campo para a pesquisa");
        txDescricaoNomejog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txDescricaoNomejogActionPerformed(evt);
            }
        });

        btnPesquisar.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        tabelaPesquisaJogadores.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        tabelaPesquisaJogadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Jogador"
            }
        ));
        tabelaPesquisaJogadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaPesquisaJogadoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaPesquisaJogadores);

        jPanel2.setBackground(java.awt.Color.yellow);

        btnNovaPalavra.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnNovaPalavra.setText("Novo");
        btnNovaPalavra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovaPalavraActionPerformed(evt);
            }
        });
        jPanel2.add(btnNovaPalavra);

        btnAlterar.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });
        jPanel2.add(btnAlterar);

        btnExcluir.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        jPanel2.add(btnExcluir);

        btnImprimir.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        jPanel2.add(btnImprimir);

        jPanel3.setBackground(new java.awt.Color(255, 255, 0));
        jPanel3.add(lbMensagem);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txDescricaoNomejog, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPesquisar))
                    .addComponent(lbNomePalavra, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbNomePalavra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txDescricaoNomejog, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txDescricaoNomejogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txDescricaoNomejogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txDescricaoNomejogActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);
        if (txDescricaoNomejog.getText().trim().length() >= 2) {
            atualizaTabela();
            VerificaBotoes();
        } else {
            lbNomePalavra.setForeground(Color.red);
            lbMensagem.setText("A pesquisa deve ter ao menos 2 caracteres");
            lbMensagem.setForeground(Color.red);
            DefaultTableModel modelo = (DefaultTableModel) tabelaPesquisaJogadores.getModel();
            modelo.setNumRows(0);
        }
//        try {
//            // TODO add your handling code here:
//            JogadorDao.RetornaJogadores(retornaObjeto());
//            atualizaTabela();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnNovaPalavraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaPalavraActionPerformed
        lbNomePalavra.setForeground(Color.black);
        lbMensagem.setText("");
        new TelaCadastroUsuario(this, true).setVisible(true);
    }//GEN-LAST:event_btnNovaPalavraActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed

        JogadorBean j = listaj.get(tabelaPesquisaJogadores.getSelectedRow());
        new TelaCadastroUsuario(this, true, j).setVisible(true);
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        JogadorBean j = listaj.get(tabelaPesquisaJogadores.getSelectedRow());
        try {
            JogadorDao.ExcluirJogador(j);
            JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!");
            atualizaTabela();
            VerificaBotoes();
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Existem registros que envolvem este jogador no sistema!");

        }catch(SQLException e){
            
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
        try {
            
            Relatorio.gerarRelatorio("relatorios//relatorioJogadores.jasper", JogadorDao.RetornaJogadoresRs(retornaObjeto()));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi");

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void tabelaPesquisaJogadoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaPesquisaJogadoresMouseClicked
        // TODO add your handling code here:
        VerificaBotoes();
    }//GEN-LAST:event_tabelaPesquisaJogadoresMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
       
        new TelaAdmin(null,true).setVisible(true);
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(TelaPesquisaJogadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPesquisaJogadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPesquisaJogadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPesquisaJogadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaPesquisaJogadores dialog = new TelaPesquisaJogadores(new javax.swing.JDialog(), true);
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
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNovaPalavra;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbMensagem;
    private javax.swing.JLabel lbNomePalavra;
    private javax.swing.JTable tabelaPesquisaJogadores;
    private javax.swing.JTextField txDescricaoNomejog;
    // End of variables declaration//GEN-END:variables
}
