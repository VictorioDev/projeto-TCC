/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Bean.CategoriaBean;
import Bean.DicaBean;
import Bean.NivelBean;
import Bean.PalavraBean;
import Dao.CategoriaDao;
import Dao.DicaDao;
import Dao.NivelDao;
import Dao.PalavraDao;
import static Telas.TelaPesquisaJogadores.listaj;
import java.awt.Color;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import util.LimitaCaracteres;
import util.Relatorio;
import util.UtilInterface;

/**
 *
 * @author Victório
 */
public class TelaPesquisaPalavra extends javax.swing.JFrame {

    private List<PalavraBean> listaPalavras;
    private List<NivelBean> listaNiveis;
    private List<DicaBean> listaDicas;
    private List<CategoriaBean> listaCategorias;

    /**
     * Creates new form TelaPesquisaPalavra
     */
    public TelaPesquisaPalavra() {
        initComponents();
        try {
            populaCombo();
        } catch (SQLException ex) {
            Logger.getLogger(TelaPesquisaPalavra.class.getName()).log(Level.SEVERE, null, ex);
        }

        VerificaBotoes();
        txDescricaoPalavra.setDocument(new LimitaCaracteres());
        configuraComponentes();
    }

     private void configuraComponentes(){
        btnAlterar.setIcon(UtilInterface.ICONE_ALTERAR);
        btnExcluir.setIcon(UtilInterface.ICONE_REMOVER);
        btnImprimir.setIcon(UtilInterface.ICONE_RELATORIO);
        btnNovaPalavra.setIcon(UtilInterface.ICONE_NOVO);
        btnPesquisar.setIcon(UtilInterface.ICONE_PESQUISAR);
        txMensagemDeRetorno.setFont(UtilInterface.FONTE_PADRAO);
        lbNomePalavra.setFont(UtilInterface.FONTE_PADRAO);
        txDescricaoPalavra.setFont(UtilInterface.FONTE_PADRAO);
        cbNiveis.setFont(UtilInterface.FONTE_PADRAO);
        cbFiltro.setFont(UtilInterface.FONTE_PADRAO);
        cbCategoria.setFont(UtilInterface.FONTE_PADRAO);
    }
    
    private void atualizaTabela() {
        DefaultTableModel modelo = (DefaultTableModel) tabelaPesquisaPalavra.getModel();
        modelo.setNumRows(0);
        try {
            listaPalavras = PalavraDao.retornaPlvs(retornaObjeto(), cbNiveis.getSelectedItem().toString(), cbCategoria.getSelectedItem().toString());
            for (PalavraBean p : listaPalavras) {
                listaDicas = DicaDao.RetornaDicas(p);
                modelo.addRow(new Object[]{
                    p.getNome(),
                    p.getNivel().getDescricao(), p.getCategoria().getDescricao(),listaDicas.size()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (listaPalavras.size() == 0) {
            txMensagemDeRetorno.setText("Não há registros!");
            txMensagemDeRetorno.setForeground(Color.red);

        } else {
            if (tabelaPesquisaPalavra.getSelectedRow() == -1 || listaPalavras.size() < 0) {
                btnAlterar.setEnabled(false);
                btnExcluir.setEnabled(false);

            } else {
                btnAlterar.setEnabled(true);
                btnExcluir.setEnabled(true);

            }
            txMensagemDeRetorno.setText("");
        }
    }

    private void VerificaBotoes() {
        if (tabelaPesquisaPalavra.getSelectedRow() == -1 || listaj.size() < 0) {
            btnAlterar.setEnabled(false);
            btnExcluir.setEnabled(false);

        } else {
            btnAlterar.setEnabled(true);
            btnExcluir.setEnabled(true);

        }

    }

    private void populaCombo() throws SQLException {
        listaNiveis = NivelDao.RetornaNiveis();
        listaCategorias = CategoriaDao.retornaCategoria();
        
        cbCategoria.removeAllItems();
        cbCategoria.addItem("<<Tudo>>");
        for (CategoriaBean c : listaCategorias) {
            cbCategoria.addItem(c.getDescricao());
        }
        
        cbNiveis.removeAllItems();;
        cbNiveis.addItem("<<Tudo>>");
        for (NivelBean n : listaNiveis) {
            cbNiveis.addItem(n.getDescricao());

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

        jMenuItem1 = new javax.swing.JMenuItem();
        pnTudo = new javax.swing.JPanel();
        lbNomePalavra = new javax.swing.JLabel();
        txDescricaoPalavra = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaPesquisaPalavra = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnNovaPalavra = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txMensagemDeRetorno = new javax.swing.JLabel();
        cbNiveis = new javax.swing.JComboBox();
        cbFiltro = new javax.swing.JLabel();
        cbCategoria = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pesquisa de Palavras");
        setBackground(new java.awt.Color(153, 153, 225));
        setResizable(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });

        pnTudo.setBackground(new java.awt.Color(153, 153, 225));
        pnTudo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 12))); // NOI18N
        pnTudo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                pnTudoMouseMoved(evt);
            }
        });

        lbNomePalavra.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        lbNomePalavra.setText("Palavra:*");

        txDescricaoPalavra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txDescricaoPalavraActionPerformed(evt);
            }
        });

        btnPesquisar.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        tabelaPesquisaPalavra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Palavra(s) ", "Nivel", "Categoria", "NumDicas"
            }
        ));
        tabelaPesquisaPalavra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaPesquisaPalavraMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaPesquisaPalavra);

        jPanel2.setBackground(java.awt.Color.yellow);
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel2MouseEntered(evt);
            }
        });

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

        jPanel3.setBackground(java.awt.Color.yellow);
        jPanel3.add(txMensagemDeRetorno);

        cbNiveis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<<Tudo>>" }));

        cbFiltro.setText("Filtro:");

        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<<Tudo>>" }));

        jLabel1.setText("Categoria:");

        javax.swing.GroupLayout pnTudoLayout = new javax.swing.GroupLayout(pnTudo);
        pnTudo.setLayout(pnTudoLayout);
        pnTudoLayout.setHorizontalGroup(
            pnTudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTudoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnTudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnTudoLayout.createSequentialGroup()
                        .addGroup(pnTudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNomePalavra)
                            .addComponent(txDescricaoPalavra, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnTudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbNiveis, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbFiltro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnTudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnTudoLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnTudoLayout.createSequentialGroup()
                                .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))))))
        );
        pnTudoLayout.setVerticalGroup(
            pnTudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTudoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnTudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNomePalavra)
                    .addComponent(cbFiltro)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnTudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txDescricaoPalavra, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbNiveis, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnTudo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnTudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txDescricaoPalavraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txDescricaoPalavraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txDescricaoPalavraActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        // TODO add your handling code here:
        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);
        if (txDescricaoPalavra.getText().trim().length() > 1) {
            atualizaTabela();
            VerificaBotoes();
        } else {
            txMensagemDeRetorno.setText("A pesquisa deve ter ao menos 2 caracteres");
            txMensagemDeRetorno.setForeground(Color.red);
            DefaultTableModel modelo = (DefaultTableModel) tabelaPesquisaPalavra.getModel();
            modelo.setNumRows(0);
        }

    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnNovaPalavraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaPalavraActionPerformed

        new TelaCadastroPalavra(this, true).setVisible(true);

    }//GEN-LAST:event_btnNovaPalavraActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        int opc = JOptionPane.showConfirmDialog(null, "Deseja realmente alterar esta dica?");
        if (opc == JOptionPane.YES_OPTION) {
            PalavraBean p = listaPalavras.get(tabelaPesquisaPalavra.getSelectedRow());
            try {
                new TelaCadastroPalavra(this, true, p).setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(TelaPesquisaPalavra.class.getName()).log(Level.SEVERE, null, ex);
            }

        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        PalavraBean p = listaPalavras.get(tabelaPesquisaPalavra.getSelectedRow());

        try {
            PalavraDao.ExcluiPalavras(p);
            JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!");
            atualizaTabela();
            VerificaBotoes();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi");

        }

// TODO add your handling code here:
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        JPanel panel = new JPanel();
        JRadioButton c1 = new JRadioButton("Palavras de um nivel");
        JRadioButton c2 = new JRadioButton("Palavras e suas dicas");
        ButtonGroup btnG = new ButtonGroup();
        btnG.add(c1);
        btnG.add(c2);
        panel.add(c1);
        panel.add(c2);
        JOptionPane.showMessageDialog(null, panel,"Radio Test",JOptionPane.QUESTION_MESSAGE);
        if(c1.isSelected()){
            System.err.println("selecionou primeiro");
        }
        try {
            Relatorio.gerarRelatorio("relatorios//PalavraANiveis.jasper", PalavraDao.RetornaPalavrasPorNiveisRs(retornaObjeto(), cbNiveis.getSelectedItem().toString()));
            //Relatorio.gerarRelatorio("relatorios//dicasDaPalavra.jasper", PalavraDao.RetornaPalavrasEAlternativasRs(retornaObjeto()));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi");

        } catch (JRException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void tabelaPesquisaPalavraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaPesquisaPalavraMouseClicked
        // TODO add your handling code here:
        VerificaBotoes();

    }//GEN-LAST:event_tabelaPesquisaPalavraMouseClicked

    private void jPanel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel2MouseEntered

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // TODO add your handling code here:

    }//GEN-LAST:event_formMouseMoved

    private void pnTudoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnTudoMouseMoved
        // TODO add your handling code here:

    }//GEN-LAST:event_pnTudoMouseMoved

    private PalavraBean retornaObjeto() {
        PalavraBean pl = new PalavraBean();
        pl.setNome(txDescricaoPalavra.getText());
        return pl;
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
            java.util.logging.Logger.getLogger(TelaPesquisaPalavra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPesquisaPalavra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPesquisaPalavra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPesquisaPalavra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPesquisaPalavra().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNovaPalavra;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JComboBox cbCategoria;
    private javax.swing.JLabel cbFiltro;
    private javax.swing.JComboBox cbNiveis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbNomePalavra;
    private javax.swing.JPanel pnTudo;
    private javax.swing.JTable tabelaPesquisaPalavra;
    private javax.swing.JTextField txDescricaoPalavra;
    private javax.swing.JLabel txMensagemDeRetorno;
    // End of variables declaration//GEN-END:variables
}
