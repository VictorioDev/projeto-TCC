/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.AlternativaBean;
import Bean.CategoriaBean;
import Bean.NivelBean;
import Bean.PerguntaBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import util.Conexao;

/**
 *
 * @author Convidado
 */
public class PerguntaDao {

    public static List<PerguntaBean> RetornaPerguntas(PerguntaBean per) throws SQLException {
        List<PerguntaBean> listaPer = new ArrayList<PerguntaBean>();
//        String sql = "select * from Pergunta where descricao like'" + per.getDescricao() + "%'";       
        String sql = "SELECT "
                + "pe.idPergunta,"
                + "pe.descricao,"
                + "ca.descricao as descricaoCat,"
                + "ca.idCategoria, "
                + "ni.idNivel, "
                + "ni.descricao as descricaoNiv "
                + "FROM "
                + "Pergunta pe, "
                + "Categoria ca, "
                + "Nivel ni "
                + "WHERE "
                + "pe.idCategoria = ca.idCategoria AND pe.idNivel = ni.idNivel AND "
                + "pe.descricao like '"+per.getDescricao()+"%' "
                + "ORDER BY pe.descricao";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {

            CategoriaBean categoria = new CategoriaBean();
            categoria.setDescricao(rs.getString("descricaoCat"));
            categoria.setIdCategoria(rs.getInt("idCategoria"));

            NivelBean nivel = new NivelBean();
            nivel.setDescricao(rs.getString("descricaoNiv"));
            nivel.setIdNivel(rs.getInt("idNivel"));
            
            PerguntaBean pergunta = new PerguntaBean();
            pergunta.setDescricao(rs.getString("descricao"));
            pergunta.setIdPergunta(rs.getInt("idPergunta"));
            pergunta.setCategoria(categoria);
            pergunta.setNivel(nivel);

            listaPer.add(pergunta);
        }
        rs.close();
        stmt.close();
        conexao.close();

        return listaPer;

    }

    public static List<PerguntaBean> RetornaPerguntas() throws SQLException {
        List<PerguntaBean> listaPer = new ArrayList<PerguntaBean>();
        String sql = "select * from Pergunta ";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {

            CategoriaBean categoria = new CategoriaBean();
            categoria.setDescricao(rs.getString("descricaoCat"));
            categoria.setIdCategoria(rs.getInt("idCategoria"));

            NivelBean nivel = new NivelBean();
            nivel.setDescricao(rs.getString("descricaoNiv"));
            nivel.setIdNivel(rs.getInt("idNivel"));

            List<AlternativaBean> lisalternativa = new ArrayList<AlternativaBean>();
            AlternativaBean alternativa = new AlternativaBean();
            alternativa.setDescricao(rs.getString("descricao"));
//            alternativa.setCorreta(rs.getBoolean("correta"));
            lisalternativa.add(alternativa);

            PerguntaBean pergunta = new PerguntaBean();
            pergunta.setDescricao(rs.getString("descricao"));
            pergunta.setIdPergunta(rs.getInt("idPergunta"));
            pergunta.setCategoria(categoria);
//            pergunta.setNivel(nivel);
//            pergunta.setAlternativa(lisalternativa);

            listaPer.add(pergunta);
        }
        rs.close();
        stmt.close();
        conexao.close();

        return listaPer;

    }

    public static void salvar(PerguntaBean pergunta) throws SQLException {
        try {
            String sql = "insert into Pergunta (descricao , idCategoria, idNivel) values (?,?,?)";
            Connection conexao = Conexao.getConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, pergunta.getDescricao());
            stmt.setInt(2, pergunta.getCategoria().getIdCategoria());
            stmt.setInt(3, pergunta.getNivel().getIdNivel());
            stmt.execute();
            stmt.close();

            String sql2 = "select max(idPergunta) from Pergunta";
            PreparedStatement stmt2 = conexao.prepareStatement(sql2);
            ResultSet rs = stmt2.executeQuery();
            rs.first();

            int idPergunta = rs.getInt("max(idPergunta)");
            pergunta.setIdPergunta(idPergunta);

            System.out.println("ID DA PERGUNTA: " + pergunta.getIdPergunta());
            for (AlternativaBean alternativa : pergunta.getAlternativa()) {
                alternativa.setPergunta(pergunta);
                AlternativaDao.salvar(alternativa);
            }
            conexao.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar a pergunta","ERRO!",JOptionPane.ERROR_MESSAGE);

        }
    }

    public static void alterar(PerguntaBean pergunta) throws SQLException {
        try {
            String sql = "update Pergunta set descricao=? , idCategoria=?, idNivel=? where idPergunta=?";
            Connection conexao = Conexao.getConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, pergunta.getDescricao());
            stmt.setInt(2, pergunta.getCategoria().getIdCategoria());
            stmt.setInt(3, pergunta.getNivel().getIdNivel());
            stmt.setInt(4, pergunta.getIdPergunta());
            stmt.executeUpdate();
            stmt.close();
            conexao.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao alterar pergunta","ERRO!",JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void excluir(PerguntaBean pergunta) throws SQLException {
        try {
            String sql = "delete from Pergunta where idPergunta=?";
            Connection conexao = Conexao.getConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, pergunta.getIdPergunta());
            stmt.executeUpdate();
            stmt.close();
            conexao.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao excluir pergunta","ERRO!",JOptionPane.ERROR_MESSAGE);

        }

    }

}
