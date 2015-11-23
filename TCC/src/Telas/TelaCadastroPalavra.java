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
import java.awt.Color;
import java.awt.Image;
import java.awt.Label;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;
import util.LimitaCaracteres;
import util.UtilInterface;

/**
 *
 * @author Victório
 */
public class TelaCadastroPalavra extends javax.swing.JDialog {

    private boolean salvar = true;
    private JFileChooser arquivo = new JFileChooser();
    private List<String> listanomedicas = new ArrayList<String>();
    private List<NivelBean> listaniveis = new ArrayList<NivelBean>();
    private List<CategoriaBean> listaCategorias = new ArrayList<CategoriaBean>();
    private ImageIcon imagem;
    private ImageIcon image;
    private File som;
    private int id = 0;
    public String nomeDica;
//    public static List<byte[]> listaImagens = new ArrayList<byte[]>();
//    public static List<byte[]> listaSons = new ArrayList<byte[]>();
//    public static List<String> listaTexto = new ArrayList<String>();
    public List<PalavraBean> listapl = new ArrayList<PalavraBean>();
    public List<DicaBean> listadc = new ArrayList<DicaBean>();
    public MaskFormatter mask;

    /**
     * Creates new form TelaCadastroPergunta
     */
    public TelaCadastroPalavra(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        salvar = true;
        acoesComponentes("");
        AtualizaCombos();

        txNome.setDocument(new LimitaCaracteres());
        configuraComponentes();
        setLocationRelativeTo(null);
    }

    public TelaCadastroPalavra(java.awt.Frame parent, boolean modal, PalavraBean pl) throws SQLException {
        super(parent, modal);
        initComponents();
        salvar = false;
        id = pl.getIdPalavra();

        acoesComponentes("");
        txNome.setDocument(new LimitaCaracteres());
        configuraComponentes();
        preencherCampos(pl);
    }

    private void configuraComponentes() {
        btnSalvar.setIcon(UtilInterface.ICONE_SALVAR);
        btnCancelar.setIcon(UtilInterface.ICONE_CANCELAR);
        btnAdicionar.setIcon(UtilInterface.ICONE_NOVO);
        btnRemover.setIcon(UtilInterface.ICONE_REMOVER);
        UtilInterface.setFontes(jPanel1.getComponents());
        UtilInterface.setFontes(pnCadastro.getComponents());
        UtilInterface.setFontes(pnBotoes.getComponents());
    }

    private void preencherCampos(PalavraBean p) throws SQLException {

        txNome.setText(p.getNome());
        AtualizaCombos();
        cbNiveis.setSelectedItem(p.getNivel().getDescricao());
        cbCategoria.setSelectedItem(p.getCategoria().getDescricao());
        listadc = DicaDao.RetornaDicas(p);
        for (DicaBean pdc : listadc) {
            listanomedicas.add(pdc.getNomeDica());
        }
        atualizaTabelaDicas();

    }

    private PalavraBean retornaObjetoPalavra() {
        PalavraBean palavra = new PalavraBean();
        palavra.setNome(txNome.getText().trim());
        palavra.setDicas(listadc);
        palavra.setNivel(listaniveis.get(cbNiveis.getSelectedIndex() - 1));
        palavra.setCategoria(listaCategorias.get(cbCategoria.getSelectedIndex() - 1));
        palavra.setIdPalavra(id);
        return palavra;
    }

//    private DicaBean retornaObjetoDicaTexto(String texto) {
//        try {
//            listapl = PalavraDao.RetornaPalavras(retornaObjetoPalavra());
//        } catch (SQLException e) {
//        }
//
//        DicaBean dc = new DicaBean();
//        dc.setTexto(texto);
//
//        dc.setImagem(null);
//        dc.setSom(null);
//        return dc;
//    }
//
//    private DicaBean retornaObjetoDicaSom(byte[] som) {
//
//        try {
//            listapl = PalavraDao.RetornaPalavras(retornaObjetoPalavra());
//        } catch (SQLException e) {
//        }
//
//        DicaBean dc = new DicaBean();
//
//        System.out.println(listapl.get(0).getIdPalavra());
//
//        dc.setTexto(null);
//        dc.setImagem(null);
//        dc.setSom(som);
//        return dc;
//    }
//
//    private DicaBean retornaObjetoDicaImagem(byte[] imgem) {
//
//        try {
//            listapl = PalavraDao.RetornaPalavras(retornaObjetoPalavra());
//        } catch (SQLException e) {
//        }
//
//        DicaBean dc = new DicaBean();
//        System.out.println(listapl.get(0).getIdPalavra());
//        dc.setTexto(null);
//        dc.setImagem(imgem);
//        dc.setSom(null);
//        return dc;
//    }
    private void AtualizaCombos() {
        try {
            cbNiveis.removeAllItems();
            cbNiveis.addItem("<<Selecione>>");
            listaniveis = NivelDao.RetornaNiveis();
            for (NivelBean n : listaniveis) {
                cbNiveis.addItem(n.getDescricao());

            }
            cbCategoria.removeAllItems();
            cbCategoria.addItem("<<Selecione>>");
            listaCategorias = CategoriaDao.retornaCategoria();
            for (CategoriaBean c : listaCategorias) {
                cbCategoria.addItem(c.getDescricao());
            }

        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastroPalavra.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean verificacampos() {
        boolean aux = false;
        if (txNome.getText().trim().equals("")) {
            lbNomePalavra.setForeground(Color.red);
            aux = false;
        } else {
            lbNomePalavra.setForeground(Color.black);
            aux = true;
        }
        //Codigo abaixo do luizao, sobre os demais campos obrigatórios
        //Combo Categoria
        if (cbCategoria.getSelectedIndex() == 0) {
            lbCategoria.setForeground(Color.red);
            aux = false;
        } else {
            lbCategoria.setForeground(Color.black);
            aux = true;
        }
        //Combo nível
        if (cbNiveis.getSelectedIndex() == 0) {
            lbNivel.setForeground(Color.red);
            aux = false;
        } else {
            lbNivel.setForeground(Color.black);
            aux = true;
        }
        //combo dicas
        if ((cbDica.getSelectedIndex() == 0) || (listanomedicas.size() == 0)) {
            lbTipoDica.setForeground(Color.red);
            aux = false;
        } else if ((cbDica.getSelectedIndex() != 0) || (listanomedicas.size() > 0)) {
            lbTipoDica.setForeground(Color.black);
            aux = true;
        }
        return aux;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnCadastro = new javax.swing.JPanel();
        lbNomePalavra = new javax.swing.JLabel();
        lbNivel = new javax.swing.JLabel();
        cbNiveis = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        cbDica = new javax.swing.JComboBox();
        lbTipoDica = new javax.swing.JLabel();
        btnAdicionar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        pnBotoes = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txNome = new javax.swing.JTextField();
        cbCategoria = new javax.swing.JComboBox();
        lbCategoria = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Palavras");
        setBackground(new java.awt.Color(153, 153, 225));
        setResizable(false);

        pnCadastro.setBackground(new java.awt.Color(153, 153, 225));
        pnCadastro.setBorder(javax.swing.BorderFactory.createTitledBorder("Palavra"));

        lbNomePalavra.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        lbNomePalavra.setText("Nome:*");

        lbNivel.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        lbNivel.setText("Nivel da palavra:*");

        cbNiveis.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        cbNiveis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<<Selecione>>", " " }));
        cbNiveis.setToolTipText("Selecione a categoria da palavra");
        cbNiveis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNiveisActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 153, 225));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dicas"));

        cbDica.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        cbDica.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<<Selecione>>", "Texto", "Imagem", "Som" }));
        cbDica.setToolTipText("Selecione o tipo da dica");
        cbDica.setFocusable(false);
        cbDica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDicaActionPerformed(evt);
            }
        });

        lbTipoDica.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        lbTipoDica.setText("Tipo da dica:*");

        btnAdicionar.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnAdicionar.setText("Adicionar");
        btnAdicionar.setToolTipText("Clique para adicionar a(s) dica(s) desejada(s)");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnRemover.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnRemover.setText("Remover");
        btnRemover.setToolTipText("Clique para remover a(s) dica(s) desejada(s) ");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        jScrollPane1.setEnabled(false);

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dicas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela.setToolTipText("As dicas adicionadas para a palavra");
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelaMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        pnBotoes.setBackground(java.awt.Color.yellow);
        pnBotoes.setForeground(new java.awt.Color(153, 153, 255));

        btnSalvar.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setToolTipText("Clique para salvar a palavra com sua(s) dica(s)");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        pnBotoes.add(btnSalvar);

        btnCancelar.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setToolTipText("Clique para cancelar esta operação");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pnBotoes.add(btnCancelar);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbDica, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbTipoDica))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lbTipoDica)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbDica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txNome.setToolTipText("Digite a palavra neste campo para o cadastro");

        cbCategoria.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<<Selecione>>", " " }));
        cbCategoria.setToolTipText("Selecione o nível da palavra ");
        cbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCategoriaActionPerformed(evt);
            }
        });

        lbCategoria.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        lbCategoria.setText("Categoria da palavra:*");

        javax.swing.GroupLayout pnCadastroLayout = new javax.swing.GroupLayout(pnCadastro);
        pnCadastro.setLayout(pnCadastroLayout);
        pnCadastroLayout.setHorizontalGroup(
            pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txNome, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNomePalavra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnCadastroLayout.createSequentialGroup()
                        .addComponent(lbNivel)
                        .addGap(32, 32, 32)
                        .addComponent(lbCategoria))
                    .addGroup(pnCadastroLayout.createSequentialGroup()
                        .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbNiveis, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnCadastroLayout.setVerticalGroup(
            pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNomePalavra)
                    .addComponent(lbNivel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCategoria)
                    .addComponent(cbNiveis))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void atualizaTabelaDicas() {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        for (int i = 0; i < listanomedicas.size(); i++) {
            modelo.addRow(new Object[]{
                listanomedicas.get(i)
            });
        }

    }

    private void PegarNome(FileNameExtensionFilter filtro) {
        arquivo.setFileFilter(filtro);
        arquivo.setAcceptAllFileFilterUsed(false);

        arquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);

        File file = new File("");
        String caminho_padrao = "G:";
        File pathInicial = new File(caminho_padrao);
        arquivo.setCurrentDirectory(pathInicial);

        int salvar = arquivo.showOpenDialog(null);
        if (salvar == JFileChooser.APPROVE_OPTION) {
            ImageIcon image = new ImageIcon(arquivo.getSelectedFile().getPath());
            File filep = new File(arquivo.getSelectedFile().getPath());
            imagem = image;
            som = filep;
            System.out.println(arquivo.getSelectedFile().getName());
            nomeDica = arquivo.getSelectedFile().getName();
            listanomedicas.add(arquivo.getSelectedFile().getName());
            atualizaTabelaDicas();

        }

    }

    public byte[] converter() {

        BufferedInputStream bis = null;

        byte[] bFile = new byte[(int) som.length()];

        try {
            bis = new BufferedInputStream(new FileInputStream(som));
            bis.read(bFile);
            return bFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new byte[0];
    }

    public byte[] ConvertImgByte() throws IOException {
        BufferedImage imagemB = ImageIO.read(new File(imagem.getDescription()));
        // System.out.println (imagemB);
        //REDIMENSIONAR A IMAGEM
//        int width = 200;
//        int height = 200;
        int width = 0;
        int height = 0;
        if (imagemB.getWidth() > 500) {
            width = 500;
            height = 500;
        } else {
            width = imagemB.getWidth();
            height = imagemB.getHeight();
        }

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

    private int posicaoImagens() {
        int tamanho = 0;
        for (int i = 0; i < listanomedicas.size(); i++) {
            listanomedicas.get(i);
        }

        return tamanho;
    }

//    private List<DicaBean> retornaListDicas() {
//        for (int i = 0; i < listaTexto.size(); i++) {
//            listadc.add(retornaObjetoDicaTexto(listaTexto.get(i)));
//        }
//        for (int i = 0; i < listaSons.size(); i++) {
//            listadc.add(retornaObjetoDicaSom(listaSons.get(i)));
//        }
//        for (int i = 0; i < listaImagens.size(); i++) {
//            listadc.add(retornaObjetoDicaImagem(listaImagens.get(i)));
//        }
//
//        return listadc;
//    }
//    private void salvarDicas() {
//        if (verificacampos()) {
//            try {
//
//                for (int i = 0; i < listaTexto.size(); i++) {
//                    System.out.println(listaTexto.get(i));
//                    DicaDao.salvarDica(retornaObjetoDicaTexto(listaTexto.get(i)));
//
//                }
//                for (int i = 0; i < listaSons.size(); i++) {
//                    DicaDao.salvarDica(retornaObjetoDicaSom(listaSons.get(i)));
//
//                }
//
//                for (int i = 0; i < listaImagens.size(); i++) {
//                    DicaDao.salvarDica(retornaObjetoDicaImagem(listaImagens.get(i)));
//
//                }
//                dispose();
//                new TelaPesquisaPalavra().setVisible(true);
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//
//        } else {
//            JOptionPane.showMessageDialog(null, "Há campos obrigatórios não preenchidos!");
//        }
//
//    }
    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        DicaBean dica = new DicaBean();
        if (cbDica.getSelectedIndex() == 2) {
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagens", "jpg", "png", "jpeg");
            PegarNome(filtro);
            try {
                dica.setImagem(ConvertImgByte());
                dica.setNomeDica(nomeDica);
                dica.setTipo("Imagem");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar a imagem!");
            }
            acoesComponentes("btn");

        } else if (cbDica.getSelectedIndex() == 3) {
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Sons", "mp3", "WMV");

            PegarNome(filtro);
            dica.setNomeDica(nomeDica);
            dica.setSom(converter());
            dica.setTipo("Som");
            acoesComponentes("btn");
        }
        if (cbDica.getSelectedIndex() == 1) {
            String dicatexto = JOptionPane.showInputDialog(null, "Insira a dica!");
            if (dicatexto.trim().equals("")) {
                lbTipoDica.setForeground(Color.red);
            } else {
                listanomedicas.add(dicatexto);
                dica.setNomeDica(dicatexto);
                dica.setTexto(dicatexto);
                dica.setTipo("Texto");
                atualizaTabelaDicas();
                if (listanomedicas.size() > 0) {
                    lbTipoDica.setForeground(Color.black);
                }
                acoesComponentes("btn");
            }
        }
        listadc.add(dica);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        dispose();

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (verificacampos()) {
            if (salvar) {
                try {
                    PalavraDao.salvarPalavra(retornaObjetoPalavra());
                    dispose();
//                } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex) {
//                        JOptionPane.showMessageDialog(null, UtilInterface.MSG_REGISTRO_DUPLICADO);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PalavraDao.atualizaPalavra(retornaObjetoPalavra());
                    dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
//        if (tabela.getSelectedRow() == -1) {
//            JOptionPane.showMessageDialog(null, "Nenhuma dica selecionada para a exclusão!");
//
//        } else {
        int opc = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esta dica?");
        if (opc == JOptionPane.YES_OPTION) {
            listanomedicas.remove(tabela.getSelectedRow());
            listadc.remove(tabela.getSelectedRow());
            atualizaTabelaDicas();
            acoesComponentes("btn");

        }


    }//GEN-LAST:event_btnRemoverActionPerformed

    private void cbDicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDicaActionPerformed
        acoesComponentes("combo");
    }//GEN-LAST:event_cbDicaActionPerformed

    private void cbNiveisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNiveisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbNiveisActionPerformed

    private void cbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCategoriaActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        // TODO add your handling code here:
        //acoesComponentes("btn");
//        if (tabela.getSelectedRow() != -1) {
//            btnRemover.setEnabled(true);
//            System.err.println("Clicou");
//        } else {
//            btnRemover.setEnabled(false);
//        }
    }//GEN-LAST:event_tabelaMouseClicked

    private void tabelaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseReleased
        // TODO add your handling code here:
        acoesComponentes("btn");
    }//GEN-LAST:event_tabelaMouseReleased

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
            java.util.logging.Logger.getLogger(TelaCadastroPalavra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroPalavra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroPalavra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroPalavra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaCadastroPalavra dialog = new TelaCadastroPalavra(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnCancelar;
    private static javax.swing.JButton btnRemover;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox cbCategoria;
    private javax.swing.JComboBox cbDica;
    private javax.swing.JComboBox cbNiveis;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCategoria;
    private javax.swing.JLabel lbNivel;
    private javax.swing.JLabel lbNomePalavra;
    private javax.swing.JLabel lbTipoDica;
    private javax.swing.JPanel pnBotoes;
    private javax.swing.JPanel pnCadastro;
    private static javax.swing.JTable tabela;
    private javax.swing.JTextField txNome;
    // End of variables declaration//GEN-END:variables

    private void acoesComponentes(String comp) {
//        if (listanomedicas.size() > 0) {
//            btnRemover.setEnabled(true);
//            
//        } else {
//            btnRemover.setEnabled(false);
//        }
        // TODO add your handling code here:

        if (comp.equalsIgnoreCase("combo")) {
            if (cbDica.getSelectedIndex() == 0) {
                btnAdicionar.setEnabled(false);

            } else {

                btnAdicionar.setEnabled(true);
            }
        } else if (comp.equalsIgnoreCase("btn")) {
            if (listanomedicas.size() > 0 && tabela.getSelectedRow() != -1) {
                btnRemover.setEnabled(true);

            } else {
                btnRemover.setEnabled(false);
            }
        } else {
            if (cbDica.getSelectedIndex() == 0) {
                btnAdicionar.setEnabled(false);

            } else {

                btnAdicionar.setEnabled(true);
            }

            if (listanomedicas.size() > 0) {
                btnRemover.setEnabled(true);

            } else {
                btnRemover.setEnabled(false);
            }
        }

    }
}
