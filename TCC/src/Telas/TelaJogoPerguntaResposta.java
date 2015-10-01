/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Bean.AlternativaBean;
import Bean.NivelBean;
import Bean.PerguntaBean;
import Dao.AlternativaDao;
import Dao.NivelDao;
import Dao.PerguntaDao;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.RadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import util.Contador;

/**
 *
 * @author Administrador
 */
public class TelaJogoPerguntaResposta extends javax.swing.JFrame {

    public static ImageIcon icores = new ImageIcon("src\\icones\\resposta.png");
    public static ImageIcon icopul = new ImageIcon("src\\icones\\pular.jpg");
    public static ImageIcon icodic = new ImageIcon("src\\icones\\dicas.jpg");
    public static ImageIcon icorkg = new ImageIcon("src\\icones\\ranking.jpg");

    public List<PerguntaBean> listaperguntas = new ArrayList<PerguntaBean>();
    public List<AlternativaBean> listalternativas = new ArrayList<AlternativaBean>();
    public int indicePergunta;
    public String pergunta;
    public String alternativas;

    private static int qtdepula = 0;
    private static boolean contador = true;
    private static boolean zerado = false;
    private static JRadioButton b;
    private static PerguntaBean p;
    private static ButtonGroup br;

    private static int corretaIndex = 0;

    private static List<JRadioButton> listaBtn = new ArrayList<JRadioButton>();
    private static AlternativaBean aCorreto = new AlternativaBean();

    /**
     * Creates new form TelaJogoPerguntaResposta
     */
    public TelaJogoPerguntaResposta() {
        initComponents();
        jtxaPergunta.setEditable(false);
        iconesBotoes();
        SorteiaPergunta();
        Configura();
        Contador cont = new Contador(lbTempo);
        cont.start();
    }

    private void iconesBotoes() {
        btResponder.setIcon(icores);
        btPular1.setIcon(icopul);
//        btRanking.setIcon(icorkg);

    }

    private void codificandoCronometro() {

    }

    public String SorteiaPergunta() {
       
        p = new PerguntaBean();
        try {
            listaperguntas = PerguntaDao.RetornaPerguntas();
            Random ram = new Random();
            indicePergunta = ram.nextInt(listaperguntas.size());
            p = listaperguntas.get(indicePergunta);
            pergunta = p.getDescricao();
            List<AlternativaBean> l = AlternativaDao.retornaAlternativas(p);

            br = new ButtonGroup();
            pnAlternativas.setLayout(new GridLayout(l.size(), 1));
            int i = 0;

            for (AlternativaBean alternativaBean : l) {
                if (alternativaBean.getCorreta().equalsIgnoreCase("correta")) {
                    corretaIndex = i;
                    aCorreto = alternativaBean;
                }
                i++;
            }

            System.err.println("correta: " + corretaIndex);
            pnAlternativas.removeAll();
            i = 0;
            for (AlternativaBean l1 : l) {
                b = new JRadioButton(l1.getDescricao());
                b.setName("btn" + i);
                b.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() > 0) {
                            int f = 0;
                            for (Enumeration<AbstractButton> buttons = br.getElements(); buttons.hasMoreElements();) {
                                AbstractButton button = buttons.nextElement();

                                if (button.isSelected()) {
                                    if(f == corretaIndex){
                                        JOptionPane.showMessageDialog(null, "Acertou!!!!");
                                        SorteiaPergunta();
                                        Configura();
                                    }
                                }
                                f++;
                            }

//                            if (br.isSelected(b.getModel())) {
//                                if (b.getName().equalsIgnoreCase(name)) {
//                                    SorteiaPergunta();
//                                    Configura();
//                                    System.err.println(b.getName());
//                                } else {
//                                    JOptionPane.showMessageDialog(null, "Errrrroooouu");
//                                }
//                            }
                        }
                    }
                });
                //listaBtn.add(b);
                pnAlternativas.add(b);
                br.add(b);
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //pnAlternativas.setLayout(new GridLayout);
        return pergunta;
    }

    public void Configura() {
        jtxaPergunta.setText(pergunta);
        try {
            //Coloca Nivel no label
            PerguntaBean a = PerguntaDao.RetornaPerguntas(p);
            lbNivel.setText(a.getNivel().getDescricao());
            //lbcategoria.setText(a.getCategoria().getDescricao());
        } catch (SQLException ex) {
            Logger.getLogger(TelaJogoPerguntaResposta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Código abaixo destinado a contagem do cronometro
    private void pausarCronometro() {
        contador = false;
    }

    public static boolean isContador() {
        return contador;
    }

    public static boolean isZerado() {
        return zerado;
    }

    public static void setZerado(boolean zero) {
        zerado = zero;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        pnPaiDeTodos = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        scrPergunta = new javax.swing.JScrollPane();
        jtxaPergunta = new javax.swing.JTextArea();
        pnAlternativas = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        btResponder = new javax.swing.JButton();
        btPular1 = new javax.swing.JButton();
        btRanking = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txAcertos = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        txErros = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lbNivel = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lbcategoria = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lbTempo = new javax.swing.JLabel();

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jogo de Perguntas e Respostas");

        pnPaiDeTodos.setBackground(new java.awt.Color(153, 153, 225));

        jPanel1.setBackground(new java.awt.Color(153, 153, 225));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pergunta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 18))); // NOI18N

        jtxaPergunta.setEditable(false);
        jtxaPergunta.setColumns(20);
        jtxaPergunta.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtxaPergunta.setRows(5);
        scrPergunta.setViewportView(jtxaPergunta);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(scrPergunta, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrPergunta, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnAlternativas.setBackground(new java.awt.Color(153, 153, 225));
        pnAlternativas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Alternativas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 18))); // NOI18N
        pnAlternativas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnAlternativasMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnAlternativasLayout = new javax.swing.GroupLayout(pnAlternativas);
        pnAlternativas.setLayout(pnAlternativasLayout);
        pnAlternativasLayout.setHorizontalGroup(
            pnAlternativasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnAlternativasLayout.setVerticalGroup(
            pnAlternativasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 111, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(153, 153, 225));

        btResponder.setBackground(new java.awt.Color(153, 153, 153));
        btResponder.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        btResponder.setForeground(new java.awt.Color(255, 51, 51));
        btResponder.setText("RESPONDER");
        btResponder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResponderActionPerformed(evt);
            }
        });

        btPular1.setBackground(new java.awt.Color(153, 153, 153));
        btPular1.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btPular1.setForeground(new java.awt.Color(255, 51, 51));
        btPular1.setText("PULAR");
        btPular1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPular1ActionPerformed(evt);
            }
        });

        btRanking.setBackground(new java.awt.Color(153, 153, 153));
        btRanking.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btRanking.setForeground(new java.awt.Color(255, 51, 51));
        btRanking.setText("RANKING");
        btRanking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRankingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(btRanking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btPular1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btResponder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(btResponder, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btRanking, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btPular1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 225));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Acertos Totais"));

        txAcertos.setEditable(false);
        txAcertos.setText("0");
        txAcertos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txAcertosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txAcertos)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txAcertos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel4.setBackground(new java.awt.Color(153, 153, 225));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Erros Totais"));

        txErros.setEditable(false);
        txErros.setText("0");
        txErros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txErrosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txErros)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txErros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel9.setBackground(new java.awt.Color(153, 153, 225));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Pontuação"));

        jLabel1.setFont(new java.awt.Font("Vrinda", 2, 36)); // NOI18N
        jLabel1.setText("0 PONTO(S)");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(153, 153, 225));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Nível"));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(lbNivel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(153, 153, 225));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Categoria"));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbcategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                .addGap(65, 65, 65))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lbcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(153, 153, 225));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Tempo"));

        lbTempo.setFont(new java.awt.Font("Vrinda", 2, 36)); // NOI18N
        lbTempo.setText("00:00:00");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(lbTempo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTempo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnPaiDeTodosLayout = new javax.swing.GroupLayout(pnPaiDeTodos);
        pnPaiDeTodos.setLayout(pnPaiDeTodosLayout);
        pnPaiDeTodosLayout.setHorizontalGroup(
            pnPaiDeTodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnPaiDeTodosLayout.createSequentialGroup()
                .addGroup(pnPaiDeTodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnAlternativas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnPaiDeTodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnPaiDeTodosLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnPaiDeTodosLayout.setVerticalGroup(
            pnPaiDeTodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPaiDeTodosLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(pnPaiDeTodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnPaiDeTodosLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnAlternativas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnPaiDeTodos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addComponent(pnPaiDeTodos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txAcertosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txAcertosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txAcertosActionPerformed

    private void txErrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txErrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txErrosActionPerformed

    private void btResponderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResponderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btResponderActionPerformed

    private void btRankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRankingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btRankingActionPerformed

    private void btPular1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPular1ActionPerformed
        // TODO add your handling code here:
        int pular = JOptionPane.showConfirmDialog(this, "Deseja pular a " + (qtdepula + 1) + "ª questão de 3 possíveis?", "QUER PULAR?", JOptionPane.YES_NO_CANCEL_OPTION);
        if (pular == JOptionPane.YES_OPTION) {
            qtdepula++;
            SorteiaPergunta();
            Configura();
        }
        //se pular tres vezes perde o jogo
        if (qtdepula == 3) {
            pausarCronometro();
            JOptionPane.showMessageDialog(this, "PERDEU POR PULAR 3 QUESTÕES");
            dispose();
        }
    }//GEN-LAST:event_btPular1ActionPerformed

    private void pnAlternativasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnAlternativasMouseClicked
            // TODO add your handling code h


    }//GEN-LAST:event_pnAlternativasMouseClicked

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
            java.util.logging.Logger.getLogger(TelaJogoPerguntaResposta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaJogoPerguntaResposta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaJogoPerguntaResposta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaJogoPerguntaResposta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaJogoPerguntaResposta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btPular1;
    private javax.swing.JButton btRanking;
    private javax.swing.JButton btResponder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jtxaPergunta;
    private javax.swing.JLabel lbNivel;
    private javax.swing.JLabel lbTempo;
    private javax.swing.JLabel lbcategoria;
    private javax.swing.JPanel pnAlternativas;
    private javax.swing.JPanel pnPaiDeTodos;
    private javax.swing.JScrollPane scrPergunta;
    private javax.swing.JTextField txAcertos;
    private javax.swing.JTextField txErros;
    // End of variables declaration//GEN-END:variables
}
