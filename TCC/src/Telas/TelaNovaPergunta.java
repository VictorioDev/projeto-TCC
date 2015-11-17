/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Bean.AlternativaBean;
import Bean.CategoriaBean;
import Bean.NivelBean;
import Bean.PerguntaBean;
import Dao.AlternativaDao;
import Dao.CategoriaDao;
import Dao.NivelDao;
import Dao.PerguntaDao;
import java.awt.Color;
import java.awt.Frame;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import util.LimitaCaracteres;
import util.UtilInterface;

/**
 *
 * @author Convidado
 */
public class TelaNovaPergunta extends javax.swing.JDialog {

    /**
     * Creates new form TelaNovaPerguntaa
     */
//    public static ImageIcon icosal = new ImageIcon("src\\icones\\Ok-icon16.png");
//    public static ImageIcon icocan = new ImageIcon("src\\icones\\Close-icon16.png");
//    public static ImageIcon icoadd = new ImageIcon("src\\icones\\Add-icon16.jpg");
//    public static ImageIcon icorem = new ImageIcon("src\\icones\\Delete-icon16.png");
    private boolean salvar = false;

    private int id = 0;
    private int limite = 10;
    String cod = new String();
    private List<String> listaalt = new ArrayList<String>();
    public List<PerguntaBean> listaper = new ArrayList<PerguntaBean>();
    public List<NivelBean> listaniveis = new ArrayList<NivelBean>();
    public List<CategoriaBean> listacategorias = new ArrayList<CategoriaBean>();
    public List<AlternativaBean> listalternativas = new ArrayList<AlternativaBean>();
    static public int auxilio = 0;
    public boolean aux2 = false;
    public List<PerguntaBean> listaPerguntas;

        
    public TelaNovaPergunta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        salvar = true;
        initComponents();
        txAlternativa.setDocument(new LimitaCaracteres());
//        iconeBotoes();
        configuraComponentes();
        setLocationRelativeTo(null);
        atualizaComboNível();
        atualizaComboCategoria();
        cbNivel.setBackground(Color.lightGray);
        cbCategoria.setBackground(Color.lightGray);
        getRootPane().setDefaultButton(btSalvar);
        acoesComponentes();
    }

    public TelaNovaPergunta(Frame parent, boolean modal, PerguntaBean pergunta) {
        super(parent, modal);
        initComponents();
        txAlternativa.setDocument(new LimitaCaracteres());
//        iconeBotoes();
        configuraComponentes();
        preencherCampos(pergunta);
        salvar = false;
        id = pergunta.getIdPergunta();

        setLocationRelativeTo(null);
        cbNivel.setBackground(Color.lightGray);
        cbCategoria.setBackground(Color.lightGray);
        getRootPane().setDefaultButton(btSalvar);
        acoesComponentes();
    }

    private void configuraComponentes() {
        btSalvar.setIcon(UtilInterface.ICONE_SALVAR);
        btCancelar.setIcon(UtilInterface.ICONE_CANCELAR);
        btAdd.setIcon(UtilInterface.ICONE_NOVO);
        btRemover.setIcon(UtilInterface.ICONE_REMOVER);
        txAlternativa.setFont(UtilInterface.FONTE_PADRAO);
        txaPergunta.setFont(UtilInterface.FONTE_PADRAO);
        cbCategoria.setFont(UtilInterface.FONTE_PADRAO);
        cbNivel.setFont(UtilInterface.FONTE_PADRAO);
    }

    private void iconeBotoes() {
//        btCancelar.setIcon(icocan);
//        btSalvar.setIcon(icosal);
//        btAdd.setIcon(icoadd);
//        btRemover.setIcon(icorem);
    }

    private void validaBotoes() {
        if (TabelaAlternativas.getSelectedRow() == -1) {
            btRemover.setEnabled(false);
        } else if (TabelaAlternativas.getSelectedRow() != -1) {
            btRemover.setEnabled(true);
        }

    }

    private PerguntaBean retornaObjeto() {
        PerguntaBean pergunta = new PerguntaBean();
        pergunta.setDescricao(txaPergunta.getText().trim());
        pergunta.setIdPergunta(id);
        pergunta.setNivel(listaniveis.get(cbNivel.getSelectedIndex() - 1));
        pergunta.setCategoria(listacategorias.get(cbCategoria.getSelectedIndex() - 1));
        pergunta.setAlternativa(listalternativas);
        return pergunta;
    }

    private void preencherCampos(PerguntaBean pergunta) {
        txaPergunta.setText(pergunta.getDescricao());
        atualizaComboCategoria();
        atualizaComboNível();
        cbCategoria.setSelectedItem(pergunta.getCategoria().getDescricao());
        cbNivel.setSelectedItem(pergunta.getNivel().getDescricao());
        try {
            listalternativas = AlternativaDao.retornaAlternativas(pergunta);
            for (AlternativaBean alternativa : listalternativas) {
                listaalt.add(alternativa.getDescricao());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        atualizaTabAlternativa();
    }

    private boolean verificaCampos() {
        boolean aux = true;
        if (txaPergunta.getText().equals("") || txaPergunta.getText().trim().equals("") || txaPergunta.getText().length() < 3) {
            aux = false;
            txaPergunta.setBackground(Color.pink);
            txaPergunta.setText("");
        } else {
            txaPergunta.setBackground(Color.white);
        }
        if (cbCategoria.getSelectedIndex() == 0) {
            aux = false;
            cbCategoria.setBackground(Color.red);
        } else {
            cbCategoria.setBackground(Color.lightGray);
        }
        if (cbNivel.getSelectedIndex() == 0) {
            aux = false;
            cbNivel.setBackground(Color.red);
        } else {
            cbNivel.setBackground(Color.lightGray);
        }
        if (listalternativas.size() == 0) {
            aux = false;
            txAlternativa.setBackground(Color.pink);
        } else {
            txAlternativa.setBackground(Color.white);
        }
        for (AlternativaBean alt : listalternativas) {

            if (auxilio != 1) {
                aux = false;
                txAlternativa.setBackground(Color.pink);
//                TabelaAlternativas.setToolTipText("Amiguinho, deve haver uma correta!!");
                txAlternativa.setText("Deve haver uma correta!!");
                btAdd.setEnabled(false);// apenas no intento de não permitir o salvamento da frase acima dentre as demais alternativas
                chxCorreta.setSelected(false);
            }

        }
        if (listalternativas.size() == 1) {
            aux = false;
            txAlternativa.setText("Deve haver mais de uma");
        }

        return aux;
    }

    private void atualizaTabAlternativa() {
        try {
            DefaultTableModel modeloalt = (DefaultTableModel) TabelaAlternativas.getModel();
            modeloalt.setNumRows(0);
            for (AlternativaBean alt : listalternativas) {
                modeloalt.addRow(new Object[]{
                    alt.getDescricao(),
                    alt.getCorreta()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void atualizaComboNível() {
        try {
//            cbNivel.removeAllItems();
//            cbNivel.addItem("<<Selecione um nível>>");
            listaniveis = NivelDao.RetornaNiveis();
            for (NivelBean n : listaniveis) {
                cbNivel.addItem(n.getDescricao());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void atualizaComboCategoria() {
        try {
//            cbCategoria.removeAllItems();
//            cbCategoria.addItem("<<Selecione uma categoria>>");
            listacategorias = CategoriaDao.retornaCategoria();
            for (CategoriaBean c : listacategorias) {
                cbCategoria.addItem(c.getDescricao());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void acoesComponentes() {
        // Para o botão remover somente estar váldo quando houver alternativas
        if (listalternativas.size() > 0) {
            validaBotoes();
        } else {
            btRemover.setEnabled(false);
        }
        //--------------------------------------------------------------------------
        //Para adicionar máximamente 5 alternatvas à determinada questão
        if (listalternativas.size() == 5) {
            btAdd.setEnabled(false);
        } else {
            btAdd.setEnabled(true);
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaPergunta = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        txAlternativa = new javax.swing.JTextField();
        chxCorreta = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        btAdd = new javax.swing.JButton();
        btRemover = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelaAlternativas = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        cbNivel = new javax.swing.JComboBox();
        jPanel11 = new javax.swing.JPanel();
        cbCategoria = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        btSalvar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Perguntas");

        jPanel5.setBackground(new java.awt.Color(153, 153, 225));

        jPanel1.setBackground(new java.awt.Color(153, 153, 225));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pergunta*", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 12))); // NOI18N

        txaPergunta.setColumns(20);
        txaPergunta.setRows(5);
        txaPergunta.setToolTipText("Digite a pergunta neste campo para o cadastro");
        txaPergunta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txaPerguntaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txaPergunta);

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Alternativas*", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 12))); // NOI18N

        txAlternativa.setToolTipText("Digite as alternativas da pergunta neste campo");
        txAlternativa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txAlternativaMouseClicked(evt);
            }
        });
        txAlternativa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txAlternativaActionPerformed(evt);
            }
        });
        txAlternativa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txAlternativaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txAlternativaKeyTyped(evt);
            }
        });

        chxCorreta.setBackground(new java.awt.Color(153, 153, 255));
        chxCorreta.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        chxCorreta.setText("Correta");
        chxCorreta.setToolTipText("Selecione, se for a correta");

        jPanel3.setBackground(new java.awt.Color(255, 255, 0));

        btAdd.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btAdd.setText("Adicionar");
        btAdd.setToolTipText("Clique para adicionar as alternativas desejadas");
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });
        jPanel3.add(btAdd);

        btRemover.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btRemover.setText("Remover");
        btRemover.setToolTipText("Clique para remover a(s) alternativa(s) desejada(s)");
        btRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoverActionPerformed(evt);
            }
        });
        jPanel3.add(btRemover);

        TabelaAlternativas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Alternativa", "Situação"
            }
        ));
        TabelaAlternativas.setToolTipText("As alternativas adicionadas para a pergunta");
        TabelaAlternativas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaAlternativasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TabelaAlternativas);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txAlternativa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chxCorreta))
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txAlternativa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chxCorreta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(153, 153, 225));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nível da pergunta*", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 12))); // NOI18N
        jPanel10.setToolTipText("");

        cbNivel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<<Selecione um nível>>" }));
        cbNivel.setToolTipText("Selecione o nível da pergunta");
        cbNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNivelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbNivel, 0, 202, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(153, 153, 225));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Categoria da pergunta*", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 12))); // NOI18N

        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<<Selecione uma Categoria>>" }));
        cbCategoria.setToolTipText("Selecione a categoria da pergunta");
        cbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 0));

        btSalvar.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btSalvar.setText("SALVAR");
        btSalvar.setToolTipText("Clique para salvar a pergunta com suas alternativas");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });
        jPanel4.add(btSalvar);

        btCancelar.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btCancelar.setText("CANCELAR");
        btCancelar.setToolTipText("Clique para cancelar esta operação");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });
        jPanel4.add(btCancelar);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txAlternativaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txAlternativaActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_txAlternativaActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        if (salvar) {
            if (verificaCampos()) {
                try {
                    PerguntaDao.salvar(retornaObjeto());
                    this.dispose();
                } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex) {
                    System.out.println("REGISTRO DUPLICADO");// se der pal tirar essa e a linha anterior...
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (verificaCampos()) {
                try {
                    PerguntaDao.alterar(retornaObjeto());
                    this.dispose();
                } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex) {
                    System.out.println("REGISTRO DUPLICADO");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        // TODO add your handling code here:

        AlternativaBean alternativa = new AlternativaBean();
        String texto = (String) txAlternativa.getText();
        if (chxCorreta.isSelected()) {
            auxilio++;
            alternativa.setCorreta("CORRETA");
            chxCorreta.setEnabled(false);
        } else {
            alternativa.setCorreta("ERRADA");
        }
        if (txAlternativa.getText().trim().equals("")) {
            txAlternativa.setBackground(Color.pink);
            txAlternativa.setToolTipText("Você deve escrever as alternativas");
        } else {
            txAlternativa.setBackground(Color.white);
            alternativa.setDescricao(texto);
            texto = texto.trim();
            for (AlternativaBean alt : listalternativas) {
                if (texto.equals(alt.getDescricao().trim())) {
                    aux2 = true;

                }
            }
            //blocos abaixo responsáveis por analisar se há alternativas iguais (e lógica desenvolvida para poder ou não selecionar a chck box correta)
            if (aux2 == false) {
                listalternativas.add(alternativa);
                txAlternativa.setText("");
                txAlternativa.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Alternativa já existente para a pergunta!", "ERRO1", JOptionPane.ERROR_MESSAGE);
                aux2 = false;
                txAlternativa.setText("");
                txAlternativa.requestFocus();
                chxCorreta.setEnabled(true);
            }

        }
        chxCorreta.setSelected(false);
        atualizaTabAlternativa();
        acoesComponentes();
    }//GEN-LAST:event_btAddActionPerformed

    private void btRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoverActionPerformed
        // TODO add your handling code here:
        if (TabelaAlternativas.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Nenhuma alternativa selecionada para a exclusão!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else {
            int opc = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esta alternativa?", "Confirme ou Não, a remoção!", JOptionPane.YES_NO_CANCEL_OPTION);
            if (opc == JOptionPane.YES_OPTION) {
                cod = listalternativas.get(TabelaAlternativas.getSelectedRow()).getCorreta();
                if (cod.equalsIgnoreCase("CORRETA")) {
                    auxilio = 0;
                    chxCorreta.setEnabled(true);
                }
                listalternativas.remove(TabelaAlternativas.getSelectedRow());
                atualizaTabAlternativa();
                acoesComponentes();
            }

        }
        txAlternativa.setText("");
        txAlternativa.requestFocus();
    }//GEN-LAST:event_btRemoverActionPerformed

    private void txaPerguntaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txaPerguntaKeyPressed
        // TODO add your handling code here:
        txaPergunta.setBackground(Color.white);
    }//GEN-LAST:event_txaPerguntaKeyPressed

    private void cbNivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNivelActionPerformed
        // TODO add your handling code here:
        if (cbNivel.getSelectedIndex() != 0) {
            cbNivel.setBackground(Color.lightGray);
        }
    }//GEN-LAST:event_cbNivelActionPerformed

    private void cbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoriaActionPerformed
        // TODO add your handling code here:
        if (cbCategoria.getSelectedIndex() != 0) {
            cbCategoria.setBackground(Color.lightGray);
        }
    }//GEN-LAST:event_cbCategoriaActionPerformed

    private void txAlternativaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txAlternativaKeyPressed
        // TODO add your handling code here:
        if (listalternativas.size() != 0) {
            txAlternativa.setBackground(Color.white);
        }

    }//GEN-LAST:event_txAlternativaKeyPressed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void txAlternativaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txAlternativaMouseClicked
        // TODO add your handling code here:
        txAlternativa.setText("");
        txAlternativa.setForeground(Color.black);
//        chxCorreta.setEnabled(true);
        btAdd.setEnabled(true);
        acoesComponentes();
    }//GEN-LAST:event_txAlternativaMouseClicked

    private void txAlternativaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txAlternativaKeyTyped
        // TODO add your handling code here:
//        if (txAlternativa.getText().length() == limite) {
//            evt.consume();
//        }
    }//GEN-LAST:event_txAlternativaKeyTyped

    private void TabelaAlternativasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaAlternativasMouseClicked
        // TODO add your handling code here:
        validaBotoes();
    }//GEN-LAST:event_TabelaAlternativasMouseClicked

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
            java.util.logging.Logger.getLogger(TelaNovaPergunta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaNovaPergunta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaNovaPergunta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaNovaPergunta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaNovaPergunta dialog = new TelaNovaPergunta(new javax.swing.JFrame(), true);
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
    private javax.swing.JTable TabelaAlternativas;
    private javax.swing.JButton btAdd;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btRemover;
    private javax.swing.JButton btSalvar;
    private javax.swing.JComboBox cbCategoria;
    private javax.swing.JComboBox cbNivel;
    private javax.swing.JCheckBox chxCorreta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txAlternativa;
    private javax.swing.JTextArea txaPergunta;
    // End of variables declaration//GEN-END:variables
}
