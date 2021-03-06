/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Bean.CategoriaBean;
import Bean.NivelBean;
import Bean.PerguntaBean;
import Dao.CategoriaDao;
import Dao.NivelDao;
import Dao.PalavraDao;
import Dao.PerguntaDao;
import java.awt.Color;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import net.sf.jasperreports.engine.JRException;
import util.LimitaCaracteres;
import util.Relatorio;
import util.UtilInterface;

/**
 *
 * @author Convidado
 */
public class TelaPesquisarPergunta extends javax.swing.JFrame {

    /**
     * Creates new form TelaManterPergunta
     */
//    public static ImageIcon icopes = new ImageIcon("src\\icones\\Zoom-icon16_1.png");
//    public static ImageIcon icoalt = new ImageIcon("src\\icones\\Document-Write-icon16.png");
//    public static ImageIcon iconov = new ImageIcon("src\\icones\\Document-Blank-icon16.png");
//    public static ImageIcon icoexc = new ImageIcon("src\\icones\\Delete-icon16.png");
//    public static ImageIcon icorel = new ImageIcon("src\\icones\\relatorio_icone.jpg");
    public TelaPesquisarPergunta() {
        initComponents();
        try {
            populaCombo();
        } catch (SQLException ex) {
            Logger.getLogger(TelaPesquisaPalavra.class.getName()).log(Level.SEVERE, null, ex);
        }
        TxaPergunta.setDocument(new limitadorPesquisaPergunta());
        setResizable(false);
        configuraComponentes();
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btPesquisar);
        btAlterar.setEnabled(false);
        btExcluir.setEnabled(false);
    }

    private List<PerguntaBean> listaPerguntas;
    private List<NivelBean> listaNiveis;
    private List<CategoriaBean> listaCategorias;

    private PerguntaBean retornaObjeto() {
        PerguntaBean pergunta = new PerguntaBean();
        pergunta.setDescricao(TxaPergunta.getText().trim());
        return pergunta;
    }

    private void configuraComponentes() {
        btAlterar.setIcon(UtilInterface.ICONE_ALTERAR);
        btExcluir.setIcon(UtilInterface.ICONE_REMOVER);
        btImprimir.setIcon(UtilInterface.ICONE_RELATORIO);
        btNovo.setIcon(UtilInterface.ICONE_NOVO);
        btPesquisar.setIcon(UtilInterface.ICONE_PESQUISAR);
        lbMensg.setFont(UtilInterface.FONTE_PADRAO);
        lbPergunta.setFont(UtilInterface.FONTE_PADRAO);
        TxaPergunta.setFont(UtilInterface.FONTE_PADRAO);
        lbNivel.setFont(UtilInterface.FONTE_PADRAO);
        lbCategoria.setFont(UtilInterface.FONTE_PADRAO);
        cbNivel.setFont(UtilInterface.FONTE_PADRAO);
        cbCategoria.setFont(UtilInterface.FONTE_PADRAO);
        TxaPergunta.setDocument(new LimitaCaracteres(100));
    }

    private void iconeBotoes() {
//        btPesquisar.setIcon(icopes);
//        btAlterar.setIcon(icoalt);
//        btNovo.setIcon(iconov);
//        btExcluir.setIcon(icoexc);
//        btImprimir.setIcon(icorel);
    }

    private void validaBotoes() {
        if (tabelaPergunta.getSelectedRow() == -1) {
            btAlterar.setEnabled(false);
            btExcluir.setEnabled(false);
        } else if (tabelaPergunta.getSelectedRow() != -1) {
            btAlterar.setEnabled(true);
            btExcluir.setEnabled(true);
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

        cbNivel.removeAllItems();;
        cbNivel.addItem("<<Tudo>>");
        for (NivelBean n : listaNiveis) {
            cbNivel.addItem(n.getDescricao());

        }
    }

    private void AtualizaTabPergunta() {
        try {
            listaPerguntas = PerguntaDao.retornaPergutasPorObjeto(retornaObjeto(), cbNivel.getSelectedItem().toString(), cbCategoria.getSelectedItem().toString());
            DefaultTableModel modeloperg = (DefaultTableModel) tabelaPergunta.getModel();
            modeloperg.setNumRows(0);
            for (PerguntaBean per : listaPerguntas) {
                modeloperg.addRow(new Object[]{
                    per.getDescricao(),
                    per.getNivel().getDescricao(),
                    per.getCategoria().getDescricao()});
            }

            if (listaPerguntas.size() == 0) {
                lbMensg.setText("Não há perguntas registradas");
            } else if (listaPerguntas.size() == 1) {
                lbMensg.setText("Há apenas uma pergunta registrada");
            } else {
                lbPergunta.setForeground(Color.black);
                lbMensg.setText("Há " + listaPerguntas.size() + " perguntas registradas");
            }
            lbMensg.setForeground(Color.black);
        } catch (Exception e) {
            e.printStackTrace();
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

        jPanel5 = new javax.swing.JPanel();
        TxaPergunta = new javax.swing.JTextField();
        lbPergunta = new javax.swing.JLabel();
        lbNivel = new javax.swing.JLabel();
        cbNivel = new javax.swing.JComboBox();
        btPesquisar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaPergunta = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btNovo = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btImprimir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lbMensg = new javax.swing.JLabel();
        lbCategoria = new javax.swing.JLabel();
        cbCategoria = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisa de Perguntas");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(153, 153, 225));

        TxaPergunta.setToolTipText("Digite a pergunta desejada (ou as primeiras letras desta) neste campo para a pesquisa");
        TxaPergunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxaPerguntaActionPerformed(evt);
            }
        });

        lbPergunta.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        lbPergunta.setText("Pergunta:*");

        lbNivel.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        lbNivel.setText("Nível:");

        cbNivel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<<Tudo>>" }));
        cbNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNivelActionPerformed(evt);
            }
        });

        btPesquisar.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btPesquisar.setText("Pesquisar");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });

        tabelaPergunta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pergunta(s)", "Nível", "Categoria"
            }
        ));
        tabelaPergunta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaPerguntaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelaPergunta);

        jPanel2.setBackground(new java.awt.Color(255, 255, 0));

        btNovo.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btNovo.setText("Novo");
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });
        jPanel2.add(btNovo);

        btAlterar.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btAlterar.setText("Alterar");
        btAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarActionPerformed(evt);
            }
        });
        jPanel2.add(btAlterar);

        btExcluir.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btExcluir.setText("Excluir");
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });
        jPanel2.add(btExcluir);

        btImprimir.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btImprimir.setText("Imprimir");
        btImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImprimirActionPerformed(evt);
            }
        });
        jPanel2.add(btImprimir);

        jPanel3.setBackground(new java.awt.Color(255, 255, 0));
        jPanel3.add(lbMensg);

        lbCategoria.setText("Categoria:");

        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<<Tudo>>" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(lbPergunta)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(TxaPergunta))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNivel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCategoria)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCategoria)
                    .addComponent(lbNivel)
                    .addComponent(lbPergunta))
                .addGap(7, 7, 7)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbCategoria)
                    .addComponent(cbNivel)
                    .addComponent(TxaPergunta)
                    .addComponent(btPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        // TODO add your handling code here:
        if (TxaPergunta.getText().length() > 1) {
            AtualizaTabPergunta();
        } else {
            lbMensg.setText("A pesquisa deve ter ao menos 2 caracteres");
            lbMensg.setForeground(Color.red);
            lbPergunta.setForeground(Color.red);
            DefaultTableModel modelo = (DefaultTableModel) tabelaPergunta.getModel();
            modelo.setNumRows(0);
        }
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        // TODO add your handling code here:
        lbPergunta.setForeground(Color.black);
        lbMensg.setText("");
        new TelaNovaPergunta(this, true).setVisible(true);
    }//GEN-LAST:event_btNovoActionPerformed


    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        // TODO add your handling code here:
        if (tabelaPergunta.getSelectedRow() == -1) {
//            JOptionPane.showMessageDialog(null, "Nenhuma pergunta selecionada para a exclusão!", "ERRO!", JOptionPane.ERROR_MESSAGE);
            lbMensg.setForeground(Color.red);
            lbMensg.setText("Você deve selecionar a pergunta desejada!");
            TxaPergunta.requestFocus();
        } else {
            lbMensg.setText("");
            lbMensg.setForeground(Color.black);
            PerguntaBean pergunta = listaPerguntas.get(tabelaPergunta.getSelectedRow());
            try {
                int opc = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir a pergunta?");
                if (opc == JOptionPane.YES_OPTION) {
                    PerguntaDao.excluir(pergunta);
                    AtualizaTabPergunta();
                    validaBotoes();
                }
            } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex) {
                JOptionPane.showMessageDialog(null, "Essta pergunta já foi jogada por um jogador!");
                //ex.printStackTrace();
            } catch (SQLException ex) {

            }
        }
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
        // TODO add your handling code here:
        TelaNovaPergunta.auxilio = 1;
        if (tabelaPergunta.getSelectedRow() == -1) {
//            JOptionPane.showMessageDialog(null, "Nenhuma pergunta selecionada para a alteração!", "ERRO!", JOptionPane.ERROR_MESSAGE);
            lbMensg.setForeground(Color.red);
            lbMensg.setText("Você deve selecionar a pergunta desejada!");
            TxaPergunta.requestFocus();
        } else {
            lbMensg.setText("");
            lbMensg.setForeground(Color.black);
            PerguntaBean pergunta = listaPerguntas.get(tabelaPergunta.getSelectedRow());
            new TelaNovaPergunta(this, true, pergunta).setVisible(true);
        }
    }//GEN-LAST:event_btAlterarActionPerformed

    private void cbNivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNivelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbNivelActionPerformed

    private void btImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImprimirActionPerformed
        // TODO add your handling code here:

        JPanel panel = new JPanel();
        JRadioButton c1 = new JRadioButton("Perguntas de um nivel e categoria");
        JRadioButton c2 = new JRadioButton("Perguntas e suas alternativas");
        ButtonGroup btnG = new ButtonGroup();
        btnG.add(c1);
        btnG.add(c2);
        panel.add(c1);
        panel.add(c2);
        JOptionPane.showMessageDialog(null, panel, "Qual relatório deseja exibir?", JOptionPane.QUESTION_MESSAGE);
        if (c1.isSelected()) {
            System.err.println("selecionou primeiro");
            try {
                if (cbNivel.getSelectedItem().toString().equalsIgnoreCase("<<Tudo>>") && cbCategoria.getSelectedItem().toString().equalsIgnoreCase("<<Tudo>>")) {
                    if (TxaPergunta.getText().trim().equalsIgnoreCase("")) {
                        Relatorio.gerarRelatorio("relatorios//porCatNiv.jasper", PerguntaDao.retornaPergutasSemObjetoRs(cbNivel.getSelectedItem().toString(), cbCategoria.getSelectedItem().toString()));
                    } else {
                        Relatorio.gerarRelatorio("relatorios//porCatNiv.jasper", PerguntaDao.retornaPergutasPorObjetoRs(retornaObjeto(), cbNivel.getSelectedItem().toString(), cbCategoria.getSelectedItem().toString()));
                    }

                } else if (!cbNivel.getSelectedItem().toString().equalsIgnoreCase("<<Tudo>>") && cbCategoria.getSelectedItem().toString().equalsIgnoreCase("<<Tudo>>")) {
                    if (TxaPergunta.getText().trim().equalsIgnoreCase("")) {
                        Relatorio.gerarRelatorio("relatorios//porNivel.jasper", PerguntaDao.retornaPerguntasPorNiveisSemObjetoRs(cbNivel.getSelectedItem().toString()));
                    } else {
                        Relatorio.gerarRelatorio("relatorios//porNivel.jasper", PerguntaDao.retornaPerguntasPorNiveisComObjetoRs(retornaObjeto(), cbNivel.getSelectedItem().toString()));
                    }

                } else if (cbNivel.getSelectedItem().toString().equalsIgnoreCase("<<Tudo>>") && !cbCategoria.getSelectedItem().toString().equalsIgnoreCase("<<Tudo>>")) {
                    //alta aqui
                    if (TxaPergunta.getText().trim().equalsIgnoreCase("")) {
                        Relatorio.gerarRelatorio("relatorios//PerCat.jasper", PerguntaDao.retornaPerguntasPorCategoriaSemObjetoRs(cbCategoria.getSelectedItem().toString()));
                    } else {
                        Relatorio.gerarRelatorio("relatorios//PerCat.jasper", PerguntaDao.retornaPerguntasPorCategoriaComObjetoRs(retornaObjeto(), cbCategoria.getSelectedItem().toString()));
                    }

                } else {
                    if (TxaPergunta.getText().trim().equalsIgnoreCase("")) {
                        Relatorio.gerarRelatorio("relatorios//porCatNiv.jasper", PerguntaDao.retornaPergutasSemObjetoRs(cbNivel.getSelectedItem().toString(), cbCategoria.getSelectedItem().toString()));
                    } else {
                        Relatorio.gerarRelatorio("relatorios//porCatNiv.jasper", PerguntaDao.retornaPergutasPorObjetoRs(retornaObjeto(), cbNivel.getSelectedItem().toString(), cbCategoria.getSelectedItem().toString()));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi");

            } catch (JRException e) {
                e.printStackTrace();
            }

        } else if (c2.isSelected()) {
            try {
                Relatorio.gerarRelatorio("relatorios//RelatorioPerguntaAlternativa.jasper", PerguntaDao.retornaPerguntasPorCategoriaComObjetoRs(retornaObjeto(), cbNivel.getSelectedItem().toString()));
            } catch (JRException ex) {
                Logger.getLogger(TelaPesquisaPalavra.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(TelaPesquisaPalavra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_btImprimirActionPerformed

    private void TxaPerguntaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxaPerguntaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxaPerguntaActionPerformed

    private void tabelaPerguntaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaPerguntaMouseClicked
        // TODO add your handling code here:
        validaBotoes();
    }//GEN-LAST:event_tabelaPerguntaMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        new TelaAdmin(this,true).setVisible(true);
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
            java.util.logging.Logger.getLogger(TelaPesquisarPergunta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPesquisarPergunta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPesquisarPergunta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPesquisarPergunta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPesquisarPergunta().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxaPergunta;
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btImprimir;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JComboBox cbCategoria;
    private javax.swing.JComboBox cbNivel;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbCategoria;
    private javax.swing.JLabel lbMensg;
    private javax.swing.JLabel lbNivel;
    private javax.swing.JLabel lbPergunta;
    private javax.swing.JTable tabelaPergunta;
    // End of variables declaration//GEN-END:variables
}

// Classe responsável pela delimitaçao da quantidde de caracteres em um Textfield...
class limitadorPesquisaPergunta extends PlainDocument {

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        int tamanho = (this.getLength() + str.length());
        if (tamanho <= 40) {
            super.insertString(offs, str, a); //To change body of generated methods, choose Tools | Templates.
        } else {
            super.insertString(offs, str.replaceAll("[aA0-zZ9]", ""), a); //To change body of generated methods, choose Tools | Templates.
        }
    }

}
