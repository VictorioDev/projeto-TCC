/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Bean.AlternativaBean;
import Bean.JogadorBean;
import Bean.NivelBean;
import Bean.PalavraBean;
import Bean.PerguntaBean;
import Bean.PerguntaJogadaBean;
import Dao.AlternativaDao;
import Dao.JogadorDao;
import Dao.NivelDao;
import Dao.PalavraDao;
import Dao.PerguntaDao;
import Dao.PerguntaJogadaDAO;
import static Telas.Telajogo.listapalavrass;
import java.awt.FlowLayout;
import java.awt.Font;
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
import util.UtilInterface;

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

    public static boolean gameOver;
    public static int acertos;
    public static int pontos;
    public static int erros;
    public static int tempoGasto;
    public static boolean acertou;
    long start = 0;

    private static int qtdepula = 0;
    private static boolean contador = true;
    private static boolean zerado = false;
    private static JRadioButton b;
    private static PerguntaBean p;
    private static ButtonGroup br;

    private static int corretaIndex = 0;

    private static List<JRadioButton> listaBtn = new ArrayList<JRadioButton>();
    private static AlternativaBean aCorreto = new AlternativaBean();

    public static JogadorBean j = util.UtilObjetos.jogadorLogado;
    public static Contador cont;

    /**
     * Creates new form TelaJogoPerguntaResposta
     */
    public TelaJogoPerguntaResposta() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        jtxaPergunta.setEditable(false);
        MensgJog();
        PopulaListaPerguntas();
        SorteiaPergunta();
        Configura();
        contador = true;
        verificaCronometro();
        PreeencherCampos(util.UtilObjetos.jogadorLogado);
        if (j == null) {
            System.err.println("Objeto vazio!");

        }
        setSize(800, 600);
        configIni();
    }

    private void PreeencherCampos(JogadorBean j) {
        pontos = j.getPontos();
//        txAcertos.setText(j.getPontos() + "");
        lbPontuacao.setText(j.getPontos() + " pontos");
        System.err.println(j.getPontos() + "");

    }

    private void MensgJog() {
        jtxaPergunta.setLineWrap(true);
        jtxaPergunta.setWrapStyleWord(true);
        lbMnsg.setText("Usuário " + j.getNome() + ", responda");
    }

    private void zerarActionPerformed(ActionEvent evt) {
        lbTempo.setText("00:00:00");
        contador = false;
        zerado = true;
        lbTempo.revalidate();
    }

    private void recomecarActionPerformed(ActionEvent evt) {
        contador = true;
    }

    private void pausarActionPerformed(ActionEvent evt) {
        contador = false;
    }

    private void verificaCronometro() {
        if (contador == true) {
            cont = new Contador(lbTempo);
            cont.start();
        } else {
            zerarActionPerformed(null);
        }
    }

    private void PopulaListaPerguntas() {
        try {
            for (int i = 0; i < util.UtilObjetos.listaNiveisPJogar.size(); i++) {
                for (int k = 0; k < util.UtilObjetos.listaCategoriasPJogar.size(); k++) {
                    List<PerguntaBean> listaTemp = PerguntaDao.RetornaPerguntasSO(util.UtilObjetos.listaNiveisPJogar.get(i), util.UtilObjetos.listaCategoriasPJogar.get(k));
                    for (PerguntaBean plv : listaTemp) {
                        System.err.println("Nivel:" + plv.getNivel().getDescricao());
                        System.err.println("Categoria: " + plv.getCategoria().getDescricao());
                        System.err.println("Pergunta: " + plv.getDescricao());
                        System.err.println("--------------------------------------------------------------------------");
                        listaperguntas.add(plv);
                    }
                }

            }
            //listapalavrass = PalavraDao.RetornaPalavrasSP();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String SorteiaPergunta() {

        p = new PerguntaBean();
        try {
            //listaperguntas = PerguntaDao.RetornaPerguntas();
            Random ram = new Random();
            if (listaperguntas.size() > 0) {
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
                    b.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));  
//b.setFont(new Font(“Serif”, Font.BOLD, 15));
                    pnAlternativas.add(b);
                    br.add(b);
                    i++;
                    start = System.currentTimeMillis();
                }
                listaperguntas.remove(indicePergunta);
            } else {
                zerarActionPerformed(null);
                JOptionPane.showMessageDialog(null, "Você jogou todas as perguntas disponíveis!");
                dispose();
                new ConfiguraJogo("pergunta").setVisible(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //pnAlternativas.setLayout(new GridLayout);
        return pergunta;
    }

    public void configIni() {
        qtdepula = 0;
        gameOver = false;
        tempoGasto = 0;
        acertou = false;
        start = System.currentTimeMillis();
    }

    private PerguntaJogadaBean RetornaObjeto() {
        PerguntaJogadaBean pjb = new PerguntaJogadaBean();
        pjb.setJogador(util.UtilObjetos.jogadorLogado);
        pjb.setPergunta(p);
        pjb.setTempo(tempoGasto);
        pjb.setAcertou(acertou);

        System.err.println("Id da pergunta: " + p.getDescricao());

        return pjb;
    }

    public void AtualizarBanco() {

        tempoGasto = (int) ((System.currentTimeMillis() - start) / 1000);
        System.out.println(tempoGasto);
        j.setPontos(pontos);
        try {
            JogadorDao.UpdatePontos(j);
            PerguntaJogadaDAO.SalvarPerguntaJogada(RetornaObjeto());

        } catch (SQLException ex) {
            Logger.getLogger(Telajogo.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        configIni();

    }

    public void Configura() {
        jtxaPergunta.setText(pergunta);
        try {
            //Coloca Nivel no label
            PerguntaBean a = PerguntaDao.RetornaPerguntas(p);
            lbNivel.setText(a.getNivel().getDescricao());
            lbcategoria.setText(a.getCategoria().getDescricao());
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
        jSpinner1 = new javax.swing.JSpinner();
        pnPaiDeTodos = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        scrPergunta = new javax.swing.JScrollPane();
        jtxaPergunta = new javax.swing.JTextArea();
        pnAlternativas = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        btResponder = new javax.swing.JButton();
        btPular1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txAcertos = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        txErros = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        lbPontuacao = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lbNivel = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lbcategoria = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lbTempo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbMnsg = new javax.swing.JLabel();

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Jogo de Perguntas e Respostas");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        pnPaiDeTodos.setBackground(new java.awt.Color(153, 153, 225));

        jPanel1.setBackground(new java.awt.Color(153, 153, 225));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pergunta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 18))); // NOI18N

        jtxaPergunta.setEditable(false);
        jtxaPergunta.setColumns(10);
        jtxaPergunta.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jtxaPergunta.setRows(2);
        scrPergunta.setViewportView(jtxaPergunta);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(scrPergunta, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrPergunta, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnAlternativas.setBackground(new java.awt.Color(153, 153, 225));
        pnAlternativas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Alternativas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 18))); // NOI18N
        pnAlternativas.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
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
            .addGap(0, 143, Short.MAX_VALUE)
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
        btPular1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        btPular1.setForeground(new java.awt.Color(255, 51, 51));
        btPular1.setText("PULAR");
        btPular1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPular1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(btResponder, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btPular1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btResponder, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btPular1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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

        lbPontuacao.setFont(new java.awt.Font("Vrinda", 2, 36)); // NOI18N
        lbPontuacao.setText("0 PONTO");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(lbPontuacao, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbPontuacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTempo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 225));

        lbMnsg.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jPanel2.add(lbMnsg);

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
                .addGroup(pnPaiDeTodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPaiDeTodosLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnPaiDeTodosLayout.setVerticalGroup(
            pnPaiDeTodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPaiDeTodosLayout.createSequentialGroup()
                .addGroup(pnPaiDeTodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnPaiDeTodosLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnAlternativas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addGroup(pnPaiDeTodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnPaiDeTodosLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(51, 51, 51))
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnPaiDeTodos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(pnPaiDeTodos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        int f = 0;
        for (Enumeration<AbstractButton> buttons = br.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                if (f == corretaIndex) {
                    acertos++;
                    pontos += 5;
                    txAcertos.setText(acertos + " ");
                    if ((pontos == 0) || (pontos == 1)) {
                        lbPontuacao.setText(pontos + " ponto");
                    } else {
                        lbPontuacao.setText(pontos + " pontos");
                    }
                    zerarActionPerformed(evt);
                    JOptionPane.showMessageDialog(null, "Acertou!!!!");
                    recomecarActionPerformed(evt);
                    acertou = true;
                    AtualizarBanco();
                    SorteiaPergunta();
                    Configura();
                } else {
                    erros++;
                    txErros.setText(erros + " ");
                    pontos -= 5;
                    if (pontos <= 0) {
                        lbPontuacao.setText("0 ponto");
                    } else if (pontos == 1) {
                        lbPontuacao.setText("1 ponto");
                    } else {
                        lbPontuacao.setText(pontos + " pontos");
                    }
                    zerarActionPerformed(evt);
                    JOptionPane.showMessageDialog(null, "Errou");
                    recomecarActionPerformed(evt);
                    acertou = false;
                    AtualizarBanco();
                    SorteiaPergunta();
                    Configura();
                }
            }
            f++;
        }
    }//GEN-LAST:event_btResponderActionPerformed

    private void btPular1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPular1ActionPerformed
        // TODO add your handling code here:
        pausarActionPerformed(evt);
        int pular = JOptionPane.showConfirmDialog(this, "Deseja pular a " + (qtdepula + 1) + "ª questão de 3 possíveis?", "QUER PULAR?", JOptionPane.YES_NO_CANCEL_OPTION);
        if (pular == JOptionPane.YES_OPTION) {
            zerarActionPerformed(evt);
            recomecarActionPerformed(evt);
            qtdepula++;
            SorteiaPergunta();
            Configura();
        } else {
            recomecarActionPerformed(evt);
        }
        //se pular tres vezes perde o jogo
        if (qtdepula == 3) {
            btPular1.setEnabled(false);
//            pausarCronometro();
//            JOptionPane.showMessageDialog(this, "PERDEU POR PULAR 3 QUESTÕES");
//            dispose();
        }
    }//GEN-LAST:event_btPular1ActionPerformed

    private void pnAlternativasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnAlternativasMouseClicked
            // TODO add your handling code h


    }//GEN-LAST:event_pnAlternativasMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        new TelaInicial(util.UtilObjetos.jogadorLogado).setVisible(true);
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
    private javax.swing.JButton btResponder;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextArea jtxaPergunta;
    private javax.swing.JLabel lbMnsg;
    private javax.swing.JLabel lbNivel;
    private javax.swing.JLabel lbPontuacao;
    private javax.swing.JLabel lbTempo;
    private javax.swing.JLabel lbcategoria;
    private javax.swing.JPanel pnAlternativas;
    private javax.swing.JPanel pnPaiDeTodos;
    private javax.swing.JScrollPane scrPergunta;
    private javax.swing.JTextField txAcertos;
    private javax.swing.JTextField txErros;
    // End of variables declaration//GEN-END:variables
}
