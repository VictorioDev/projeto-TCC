/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import util.UtilInterface;

/**
 *
 * @author Victorio
 */
public class TelaAdmin extends javax.swing.JDialog {

    /**
     * Creates new form TelaAdmin
     */
    private static JLabel lb;
    private static List<JLabel> listaLabels = new ArrayList<JLabel>();

    public TelaAdmin(java.awt.Frame parent, boolean modal) {
        super(parent, false);
        initComponents();
        setTitle("Adminstrador");
        setSize(720, 320);
        ImageIcon img = new ImageIcon("src\\Telas\\imagens\\3d_bars-2560x1440.jpg");
        img.setImage(img.getImage().getScaledInstance(725, 453, 100));
//        lbFundo.setIcon(img);
        configuraComponentes();
        //lbPn.setSize(6 * lb.getWidth(), lb.getHeight());

    }

    private void configuraComponentes() {
        //Menu Gerenciar
        submenuGerenciarJogadores.setIcon(UtilInterface.ICONE_IMAGEMUSERPADRAO);
        submenuGerenciarCategorias.setIcon(UtilInterface.ICONE_CATEGORIA);
        submenuGerenciarNiveis.setIcon(UtilInterface.ICONE_NIVEL);
        submenuGerenciarPalavras.setIcon(UtilInterface.ICONE_PALAVRA);
        submenuGerenciarPerguntas.setIcon(UtilInterface.ICONE_PERGUNTA);
        submenuRelatorios.setIcon(UtilInterface.ICONE_RELATORIO);
        lbSair.setIcon(UtilInterface.ICONE_SAIR);

        //Menu Jogar
//        submenuJogarForca.setIcon(UtilInterface.ICONE_FORCA);
//        submenuJogarPerguntas.setIcon(UtilInterface.ICONE_JOGOPERGUNTA);

        //Botões da tela com os devidos ícones
        for (int i = 0; i < 6; i++) {
            lb = new JLabel("");
            lb.setName("icone" + i);
            lb.setHorizontalAlignment((int) CENTER_ALIGNMENT);
            if (i == 0) {
                lb.setIcon(UtilInterface.ICONE_IMAGEMUSERPADRAO_GRANDE);
                lb.setToolTipText("Clique para gerenciar os jogadores do sistema");
                lb.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //new TelaPesquisaJogadores(null, true).setVisible(true);
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        new TelaPesquisaJogadores(null, true).setVisible(true);
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
            } else if (i == 1) {
                lb.setIcon(UtilInterface.ICONE_CATEGORIA_GRANDE);
                lb.setToolTipText("Clique para gerenciar as categorias do sistema");
                lb.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // new TelaPesquisaJogadores(null, true).setVisible(true);
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        new TelaPesquisarCategoria(null, true).setVisible(true);
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                           //lb.setBorder(BorderFactory.createLineBorder(Color.BLACK,4));
                        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
            } else if (i == 2) {
                lb.setIcon(UtilInterface.ICONE_NIVEL_GRANDE);
                lb.setToolTipText("Clique para gerenciar os níveis do sistema");
                lb.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //new TelaPesquisaJogadores(null, true).setVisible(true);
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        new TelaPesquisarNivel(null,true).setVisible(true);
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
            } else if (i == 3) {
                lb.setIcon(UtilInterface.ICONE_PALAVRA_GRANDE);
                lb.setToolTipText("Clique para gerenciar as palavras (Jogo da Forca) do sistema");
                lb.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //new TelaPesquisaJogadores(null, true).setVisible(true);
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        new TelaPesquisaPalavra().setVisible(true);
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
            } else if (i == 4) {
                lb.setIcon(UtilInterface.ICONE_PERGUNTA_GRANDE);
                lb.setToolTipText("Clique para gerenciar as perguntas (Jogo de Perguntas e Respostas) do sistema");
                lb.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //new TelaPesquisaJogadores(null, true).setVisible(true);
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        new TelaPesquisarPergunta().setVisible(true);
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
            } else {
                lb.setIcon(UtilInterface.ICONE_JOGAR_GRANDE);
                lb.setToolTipText("Clique para ir à tela de escolha entre os jogos");
                lb.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //new TelaPesquisaJogadores(null, true).setVisible(true);
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        new TelaInicial().setVisible(true);
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
            }
            listaLabels.add(lb);
            lbPn.add(lb);
        }

        //lbJogadores.setIcon(UtilInterface.ICONE_IMAGEMUSERPADRAO);
        //lbCategorias.setIcon(UtilInterface.ICONE_CATEGORIA);
        //lbNiveis.setIcon(UtilInterface.ICONE_NIVEL);
        //lbPalavras.setIcon(UtilInterface.ICONE_PALAVRA);
        //lbPerguntas.setIcon(UtilInterface.ICONE_PERGUNTA);
        //lbForca.setIcon(UtilInterface.ICONE_FORCA);
//        lbJogPerguntas.setIcon(UtilInterface.ICONE_JOGOPERGUNTA);
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
        jLabel1 = new javax.swing.JLabel();
        lbPn = new javax.swing.JPanel();
        lbSair = new javax.swing.JLabel();
        Menu = new javax.swing.JMenuBar();
        menuSobre = new javax.swing.JMenu();
        menuGerenciar = new javax.swing.JMenu();
        submenuGerenciarJogadores = new javax.swing.JMenuItem();
        submenuGerenciarCategorias = new javax.swing.JMenuItem();
        submenuGerenciarNiveis = new javax.swing.JMenuItem();
        submenuGerenciarPalavras = new javax.swing.JMenuItem();
        submenuGerenciarPerguntas = new javax.swing.JMenuItem();
        menuRelatorios = new javax.swing.JMenu();
        submenuRelatorios = new javax.swing.JMenuItem();
        menuSair = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jLabel1.setFont(new java.awt.Font("Monotype Corsiva", 2, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Jogo da Forca e Jogo de Perguntas e Respostas ");

        lbPn.setBackground(new java.awt.Color(153, 153, 255));
        lbPn.setLayout(new java.awt.GridLayout(1, 6));

        lbSair.setToolTipText("Clique para sair da Tela");
        lbSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbSairMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbPn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(38, 38, 38)))
                .addContainerGap(53, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbSair, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addComponent(lbPn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbSair, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 720, 310);

        menuSobre.setText("Jogar");
        menuSobre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSobreMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                menuSobreMouseReleased(evt);
            }
        });
        menuSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSobreActionPerformed(evt);
            }
        });
        Menu.add(menuSobre);

        menuGerenciar.setText("Gerenciar");

        submenuGerenciarJogadores.setText("Gerenciar Jogadores");
        submenuGerenciarJogadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submenuGerenciarJogadoresMouseClicked(evt);
            }
        });
        submenuGerenciarJogadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuGerenciarJogadoresActionPerformed(evt);
            }
        });
        menuGerenciar.add(submenuGerenciarJogadores);

        submenuGerenciarCategorias.setText("Gerenciar Categorias");
        submenuGerenciarCategorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submenuGerenciarCategoriasMouseClicked(evt);
            }
        });
        submenuGerenciarCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuGerenciarCategoriasActionPerformed(evt);
            }
        });
        menuGerenciar.add(submenuGerenciarCategorias);

        submenuGerenciarNiveis.setText("Gerenciar Níveis");
        submenuGerenciarNiveis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuGerenciarNiveisActionPerformed(evt);
            }
        });
        menuGerenciar.add(submenuGerenciarNiveis);

        submenuGerenciarPalavras.setText("Gerenciar Palavras");
        submenuGerenciarPalavras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuGerenciarPalavrasActionPerformed(evt);
            }
        });
        menuGerenciar.add(submenuGerenciarPalavras);

        submenuGerenciarPerguntas.setText("Gerenciar Perguntas");
        submenuGerenciarPerguntas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuGerenciarPerguntasActionPerformed(evt);
            }
        });
        menuGerenciar.add(submenuGerenciarPerguntas);

        Menu.add(menuGerenciar);

        menuRelatorios.setText("Relatórios");

        submenuRelatorios.setText("Tela de Relatórios");
        submenuRelatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuRelatoriosActionPerformed(evt);
            }
        });
        menuRelatorios.add(submenuRelatorios);

        Menu.add(menuRelatorios);

        menuSair.setText("Sair");
        menuSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSairMouseClicked(evt);
            }
        });
        Menu.add(menuSair);

        setJMenuBar(Menu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submenuGerenciarJogadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuGerenciarJogadoresActionPerformed
        // TODO add your handling code here:

        new TelaPesquisaJogadores(this, true).setVisible(true);
    }//GEN-LAST:event_submenuGerenciarJogadoresActionPerformed

    private void menuSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSairMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_menuSairMouseClicked

    private void submenuGerenciarJogadoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submenuGerenciarJogadoresMouseClicked
        // TODO add your handling code here:
        new TelaPesquisaJogadores(this, true).setVisible(true);
    }//GEN-LAST:event_submenuGerenciarJogadoresMouseClicked

    private void submenuGerenciarCategoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submenuGerenciarCategoriasMouseClicked
        // TODO add your handling code here:
        new TelaPesquisarCategoria(null, true).setVisible(true);
    }//GEN-LAST:event_submenuGerenciarCategoriasMouseClicked

    private void submenuGerenciarCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuGerenciarCategoriasActionPerformed
        // TODO add your handling code here:
        new TelaPesquisarCategoria(null, true).setVisible(true);
    }//GEN-LAST:event_submenuGerenciarCategoriasActionPerformed

    private void submenuGerenciarNiveisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuGerenciarNiveisActionPerformed
        // TODO add your handling code here:
        new TelaPesquisarNivel(null, true).setVisible(true);
    }//GEN-LAST:event_submenuGerenciarNiveisActionPerformed

    private void submenuGerenciarPalavrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuGerenciarPalavrasActionPerformed
        // TODO add your handling code here:
        new TelaPesquisaPalavra().setVisible(true);
    }//GEN-LAST:event_submenuGerenciarPalavrasActionPerformed

    private void submenuGerenciarPerguntasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuGerenciarPerguntasActionPerformed
        // TODO add your handling code here:
        new TelaPesquisarPergunta().setVisible(true);
    }//GEN-LAST:event_submenuGerenciarPerguntasActionPerformed

    private void submenuRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuRelatoriosActionPerformed
        // TODO add your handling code here:
        new TelaRelatorio(null, true).setVisible(true);
    }//GEN-LAST:event_submenuRelatoriosActionPerformed

    private void menuSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSobreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuSobreActionPerformed

    private void menuSobreMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSobreMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_menuSobreMouseReleased

    private void menuSobreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSobreMouseClicked
        // TODO add your handling code here:
        new TelaInicial().setVisible(true);
    }//GEN-LAST:event_menuSobreMouseClicked

    private void lbSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSairMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_lbSairMouseClicked

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
            java.util.logging.Logger.getLogger(TelaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaAdmin dialog = new TelaAdmin(new javax.swing.JFrame(), true);
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
    private javax.swing.JMenuBar Menu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel lbPn;
    private javax.swing.JLabel lbSair;
    private javax.swing.JMenu menuGerenciar;
    private javax.swing.JMenu menuRelatorios;
    private javax.swing.JMenu menuSair;
    private javax.swing.JMenu menuSobre;
    private javax.swing.JMenuItem submenuGerenciarCategorias;
    private javax.swing.JMenuItem submenuGerenciarJogadores;
    private javax.swing.JMenuItem submenuGerenciarNiveis;
    private javax.swing.JMenuItem submenuGerenciarPalavras;
    private javax.swing.JMenuItem submenuGerenciarPerguntas;
    private javax.swing.JMenuItem submenuRelatorios;
    // End of variables declaration//GEN-END:variables

}
