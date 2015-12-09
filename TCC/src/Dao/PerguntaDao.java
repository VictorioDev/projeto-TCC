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

//    public static List<PalavraBean> retornaPlvs(PalavraBean pl, String filtro) throws SQLException {
//        List<PalavraBean> listaPl = new ArrayList<PalavraBean>();
//        String sql = null;
//        if(filtro.equalsIgnoreCase("<<Selecione>>")){
//           sql = "SELECT"
//                + " p.idPalavra,"
//                + " p.nome,"
//                + " n.idNivel,"
//                + " n.descricao"
//                + " FROM "
//                + "Palavra p,"
//                + "Nivel n "
//                + "WHERE "
//                + "p.idNivel = n.idNivel"
//                + " AND p.nome like'" + pl.getNome() + "%'"
//                + "ORDER BY p.nome";
//        }else{
//            
//            sql = "SELECT"
//                + " p.idPalavra,"
//                + " p.nome,"
//                + " n.idNivel,"
//                + " n.descricao"
//                + " FROM "
//                + "Palavra p,"
//                + "Nivel n "
//                + "WHERE "
//                + "p.idNivel = n.idNivel"
//                + " AND p.nome like'" + pl.getNome() + "%' and n.descricao = '"+filtro+"'"
//                + "ORDER BY p.nome";
//        }
//    }
    public static List<PerguntaBean> RetornaPerguntasSO(String nivel, String categoria) throws SQLException {
        List<PerguntaBean> listaPl = new ArrayList<PerguntaBean>();
        Connection conexao = Conexao.getConexao();
        String sql = "SELECT"
                + " p.idPergunta,"
                + " p.descricao,"
                + " n.idNivel,"
                + " n.descricao,"
                + "c.idCategoria,"
                + "c.descricao"
                + " FROM "
                + "Pergunta p,"
                + "Nivel n, "
                + "Categoria c "
                + "WHERE "
                + "p.idNivel = n.idNivel AND "
                + "p.idCategoria = c.idCategoria AND "
                + "n.descricao = '" + nivel + "' "
                + "AND c.descricao = '" + categoria + "' "
                + "ORDER BY p.descricao";
        System.err.println("Sql: " + sql);
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            NivelBean n = new NivelBean();
            n.setIdNivel(rs.getInt("idNivel"));
            n.setDescricao(rs.getString("n.descricao"));

            CategoriaBean cat = new CategoriaBean();
            cat.setIdCategoria(rs.getInt("idCategoria"));
            cat.setDescricao(rs.getString("c.descricao"));

            PerguntaBean plv = new PerguntaBean();
            plv.setIdPergunta(rs.getInt("idPergunta"));
            plv.setDescricao(rs.getString("p.descricao"));
            plv.setNivel(n);
            plv.setCategoria(cat);
            listaPl.add(plv);
        }
        rs.close();
        stmt.close();
        conexao.close();

        return listaPl;

    }

    public static ResultSet RetornaPerguntaMaisJogadaAcertada(String dataInicial, String dataFinal, int escolha) throws SQLException {
        String sql = "";
        if (escolha == 2) {
            sql = "SELECT\n"
                    + "	p.idPergunta,\n"
                    + "	p.descricao,\n"
                    + "	count(*) as NumVezesJogadas,\n"
                    + "	DATE_FORMAT(pj.`data`,'%d/%m/%Y') as DataJogo,\n"
                    + "	sum(pj.acertou) QtdeAcertos\n"
                    + "FROM\n"
                    + "	perguntajogada pj,\n"
                    + "	pergunta p\n"
                    + "WHERE\n"
                    + "	pj.idPergunta = p.idPergunta\n"
                    + " and pj.`data` between '" + dataInicial + "' and '" + dataFinal + "'\n"
                    + "GROUP BY\n"
                    + "	p.idPergunta\n"
                    + "ORDER BY\n"
                    + "NumVezesJogadas desc";
        } else {
            sql = "SELECT\n"
                    + "	p.idPergunta,\n"
                    + "	p.descricao,\n"
                    + "	count(*) as NumVezesJogadas,\n"
                    + "	DATE_FORMAT(pj.`data`,'%d/%m/%Y') as DataJogo,\n"
                    + "	sum(pj.acertou) QtdeAcertos\n"
                    + "FROM\n"
                    + "	perguntajogada pj,\n"
                    + "	pergunta p\n"
                    + "WHERE\n"
                    + "	pj.idPergunta = p.idPergunta\n"
                    + " and pj.`data` between '" + dataInicial + "' and '" + dataFinal + "'\n"
                    + "GROUP BY\n"
                    + "	p.idPergunta\n"
                    + "ORDER BY\n"
                    + "QtdeAcertos desc";
        }

        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        return rs;
    }

    public static PerguntaBean RetornaPerguntas(PerguntaBean per) throws SQLException {
        PerguntaBean pe = new PerguntaBean();
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
                + "pe.descricao like '" + per.getDescricao() + "%' "
                + "ORDER BY pe.descricao";

        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if (rs.first()) {
            NivelBean n = new NivelBean();
            n.setIdNivel(rs.getInt("idNivel"));
            n.setDescricao(rs.getString("descricaoNiv"));
            pe.setNivel(n);
            CategoriaBean c = new CategoriaBean();
            c.setIdCategoria(rs.getInt("idCategoria"));
            c.setDescricao(rs.getString("descricaoCat"));
            pe.setCategoria(c);
        }

        rs.close();
        stmt.close();
        conexao.close();

        return pe;

    }

    public static List<PerguntaBean> RetornaPerguntas(PerguntaBean per, String filtro) throws SQLException {
        List<PerguntaBean> listaPer = new ArrayList<PerguntaBean>();
//        String sql = "select * from Pergunta where descricao like'" + per.getDescricao() + "%'"; 
        String sql = "";
        if (filtro.equalsIgnoreCase("Pergunta")) {
            sql = "SELECT "
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
                    + "pe.descricao like '" + per.getDescricao() + "%' "
                    + "ORDER BY pe.descricao";
        } else if (filtro.equalsIgnoreCase("Categoria")) {
            sql = "SELECT "
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
                    + "pe.descricao like '" + per.getDescricao() + "%' "
                    + "ORDER BY ca.descricao";
        } else {
            sql = "SELECT "
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
                    + "pe.descricao like '" + per.getDescricao() + "%' "
                    + "ORDER BY ni.descricao";
        }

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

    public static ResultSet retornaPergutasSemObjetoRs(String filtroNivel, String filtroCategoria) throws SQLException {
        List<PerguntaBean> listaPl = new ArrayList<PerguntaBean>();
        String sql = null;
        if (filtroNivel.equalsIgnoreCase("<<Tudo>>") && filtroCategoria.equalsIgnoreCase("<<Tudo>>")) {
            sql = "SELECT "
                    + " pe.idPergunta,"
                    + " pe.descricao,"
                    + " ca.descricao as categoria_descricao,"
                    + " ca.idCategoria, "
                    + " ni.idNivel, "
                    + " ni.descricao as nivel_descricao "
                    + " FROM "
                    + " Pergunta pe, "
                    + " Categoria ca, "
                    + " Nivel ni "
                    + " WHERE "
                    + " pe.idCategoria = ca.idCategoria AND pe.idNivel = ni.idNivel"
                    + " ORDER BY pe.descricao";
            System.err.println(sql);
        } else if (!filtroNivel.equalsIgnoreCase("<<Tudo>>") && filtroCategoria.equalsIgnoreCase("<<Tudo>>")) {
            sql = "SELECT"
                    + " pe.idPergunta,"
                    + " pe.descricao,"
                    + " ni.idNivel,"
                    + " ni.descricao as nivel_descricao, "
                    + " ca.idCategoria,"
                    + " ca.descricao as categoria_descricao "
                    + " FROM "
                    + " Pergunta pe,"
                    + " Nivel ni, "
                    + " Categoria ca "
                    + " WHERE "
                    + " pe.idNivel = ni.idNivel AND "
                    + " pe.idCategoria = ca.idCategoria "
                    + " ni.descricao = '" + filtroNivel + "' "
                    + " ORDER BY pe.descricao";
            System.err.println(sql);
        } else if (filtroNivel.equalsIgnoreCase("<<Tudo>>") && !filtroCategoria.equalsIgnoreCase("<<Tudo>>")) {
            sql = "SELECT"
                    + " pe.idPergunta,"
                    + " pe.descricao,"
                    + " ni.idNivel,"
                    + " ni.descricao as nivel_descricao,"
                    + " ca.idCategoria,"
                    + " ca.descricao as categoria_descricao"
                    + " FROM "
                    + " Pergunta pe,"
                    + " Nivel ni, "
                    + " Categoria ca "
                    + " WHERE "
                    + " pe.idNivel = ni.idNivel AND "
                    + " pe.idCategoria = ca.idCategoria "
                    + "ca.descricao = '" + filtroCategoria + "' "
                    + " ORDER BY pe.descricao";
            System.err.println(sql);
        } else {

            sql = "SELECT"
                    + " pe.idPergunta,"
                    + " pe.descricao,"
                    + " ni.idNivel,"
                    + " ni.descricao as nivel_descricao,"
                    + " ca.idCategoria,"
                    + " ca.descricao as categoria_descricao"
                    + " FROM "
                    + " Pergunta pe,"
                    + " Nivel ni, "
                    + " Categoria ca "
                    + " WHERE "
                    + " pe.idNivel = ni.idNivel AND "
                    + " pe.idCategoria = ca.idCategoria AND"
                    + " ni.descricao = '" + filtroNivel + "'"
                    + " AND ca.descricao = '" + filtroCategoria + "'"
                    + " ORDER BY pe.descricao";
            System.err.println(sql);

        }
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        return rs;

    }

    public static List<PerguntaBean> retornaPergutasPorObjeto(PerguntaBean per, String filtroNivel, String filtroCategoria) throws SQLException {
        List<PerguntaBean> listaPl = new ArrayList<PerguntaBean>();
        String sql = null;
        if (filtroNivel.equalsIgnoreCase("<<Tudo>>") && filtroCategoria.equalsIgnoreCase("<<Tudo>>")) {
            sql = "SELECT "
                    + " pe.idPergunta,"
                    + " pe.descricao,"
                    + " ca.descricao as descricaoCat,"
                    + " ca.idCategoria, "
                    + " ni.idNivel, "
                    + " ni.descricao as descricaoNiv "
                    + " FROM "
                    + " Pergunta pe, "
                    + " Categoria ca, "
                    + " Nivel ni "
                    + " WHERE "
                    + " pe.idCategoria = ca.idCategoria AND pe.idNivel = ni.idNivel AND "
                    + " pe.descricao like '" + per.getDescricao() + "%' "
                    + " ORDER BY pe.descricao";
            System.err.println(sql);
        } else if (!filtroNivel.equalsIgnoreCase("<<Tudo>>") && filtroCategoria.equalsIgnoreCase("<<Tudo>>")) {
            sql = "SELECT"
                    + " pe.idPergunta,"
                    + " pe.descricao,"
                    + " ni.idNivel,"
                    + " ni.descricao as descricaoNiv, "
                    + " ca.idCategoria,"
                    + " ca.descricao as descricaoCat "
                    + " FROM "
                    + " Pergunta pe,"
                    + " Nivel ni, "
                    + " Categoria ca "
                    + " WHERE "
                    + " pe.idNivel = ni.idNivel AND "
                    + " pe.idCategoria = ca.idCategoria "
                    + " AND pe.descricao like'" + per.getDescricao() + "%' and ni.descricao = '" + filtroNivel + "' "
                    + " ORDER BY pe.descricao";
            System.err.println(sql);
        } else if (filtroNivel.equalsIgnoreCase("<<Tudo>>") && !filtroCategoria.equalsIgnoreCase("<<Tudo>>")) {
            sql = "SELECT"
                    + " pe.idPergunta,"
                    + " pe.descricao,"
                    + " ni.idNivel,"
                    + " ni.descricao as descricaoNiv,"
                    + " ca.idCategoria,"
                    + " ca.descricao as descricaoCat"
                    + " FROM "
                    + " Pergunta pe,"
                    + " Nivel ni, "
                    + " Categoria ca "
                    + " WHERE "
                    + " pe.idNivel = ni.idNivel AND "
                    + " pe.idCategoria = ca.idCategoria "
                    + " AND pe.descricao like'" + per.getDescricao() + "%' and ca.descricao = '" + filtroCategoria + "' "
                    + " ORDER BY pe.descricao";
            System.err.println(sql);
        } else {

            sql = "SELECT"
                    + " pe.idPergunta,"
                    + " pe.descricao,"
                    + " ni.idNivel,"
                    + " ni.descricao as descricaoNiv,"
                    + " ca.idCategoria,"
                    + " ca.descricao as descricaoCat"
                    + " FROM "
                    + " Pergunta pe,"
                    + " Nivel ni, "
                    + " Categoria ca "
                    + " WHERE "
                    + " pe.idNivel = ni.idNivel AND "
                    + " pe.idCategoria = ca.idCategoria "
                    + " AND pe.descricao like'" + per.getDescricao() + "%' and ni.descricao = '" + filtroNivel + "' "
                    + " AND ca.descricao = '" + filtroCategoria + "' "
                    + " ORDER BY pe.descricao";
            System.err.println(sql);
        }

        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {

            NivelBean n = new NivelBean();
            n.setIdNivel(rs.getInt("idNivel"));
            n.setDescricao(rs.getString("descricaoNiv"));

            CategoriaBean cat = new CategoriaBean();
            cat.setIdCategoria(rs.getInt("idCategoria"));
            cat.setDescricao(rs.getString("descricaoCat"));

            PerguntaBean perg = new PerguntaBean();
            perg.setIdPergunta(rs.getInt("idPergunta"));
            perg.setDescricao(rs.getString("descricao"));
            perg.setNivel(n);
            perg.setCategoria(cat);

            listaPl.add(perg);

        }

        conexao.close();
        stmt.close();
        rs.close();
        return listaPl;
    }

    public static List<PerguntaBean> RetornaPerguntas() throws SQLException {
        List<PerguntaBean> listaPer = new ArrayList<PerguntaBean>();
        String sql = "select * from Pergunta ";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {

            CategoriaBean categoria = new CategoriaBean();
            categoria.setDescricao(rs.getString("descricao"));
            categoria.setIdCategoria(rs.getInt("idCategoria"));

            NivelBean nivel = new NivelBean();
            nivel.setDescricao(rs.getString("descricao"));
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
            pergunta.setAlternativa(lisalternativa);

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
            JOptionPane.showMessageDialog(null, "Erro ao salvar a pergunta", "ERRO!", JOptionPane.ERROR_MESSAGE);

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
            AlternativaDao.excluirr(pergunta.getIdPergunta());
            for (AlternativaBean alternativa : pergunta.getAlternativa()) {
                alternativa.setPergunta(pergunta);
                AlternativaDao.salvar(alternativa);
            }
            stmt.close();
            conexao.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao alterar pergunta", "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void excluir(PerguntaBean pergunta) throws SQLException {
        
            String sql = "delete from Pergunta where idPergunta=?";
            Connection conexao = Conexao.getConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, pergunta.getIdPergunta());
            stmt.executeUpdate();
            stmt.close();
            conexao.close();
       

    }

    public static ResultSet retornaRs(PerguntaBean per, String parametro) throws SQLException {
        String sql = "";
        if (parametro.equalsIgnoreCase("Tudo")) {
            sql = "SELECT "
                    + "pe.idPergunta,"
                    + "pe.descricao,"
                    + "ca.descricao as descricaoCat,"
                    + "ca.idCategoria, "
                    + "ni.idNivel, "
                    + "ni.descricao as Niveldesc "
                    + "FROM "
                    + "Pergunta pe, "
                    + "Categoria ca, "
                    + "Nivel ni "
                    + "WHERE "
                    + "pe.idNivel = ni.idNivel "
                    + "ORDER BY Niveldesc";
        } else {
            sql = "SELECT "
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
                    + "pe.descricao like '" + per.getDescricao() + "%' "
                    + "ORDER BY pe.descricao";
        }

        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        return rs;
    }

    public static ResultSet retornaPerguntasPorNiveisComObjetoRs(PerguntaBean p, String parametro) throws SQLException {

        String sql = "";
        if (parametro.equalsIgnoreCase("<<Tudo>>")) {
            sql = "SELECT "
                    + "p.descricao "
                    + "AS pergunta_descricao,"
                    + "n.descricao "
                    + "AS nivel_descricao "
                    + "FROM nivel n INNER JOIN pergunta "
                    + "p ON n.idNivel = p.idNivel and p.descricao like '" + p.getDescricao() + "%' "
                    + "order by nivel_descricao";
        } else {
            sql = "SELECT "
                    + "p.descricao "
                    + "AS pergunta_descricao,"
                    + "n.descricao "
                    + "AS nivel_descricao "
                    + "FROM nivel n INNER JOIN pergunta "
                    + "p ON n.idNivel = p.idNivel AND n.descricao like '" + parametro + "' "
                    + " and p.descricao like '" + p.getDescricao() + "%' "
                    + "order by nivel_descricao and pergunta_descricao";
        }
        System.err.println(sql);
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        return rs;
    }

    public static ResultSet retornaPerguntasPorCategoriaComObjetoRs(PerguntaBean p, String parametro) throws SQLException {
        String sql = "";
        if (parametro.equalsIgnoreCase("<<Tudo>>")) {
            sql = "SELECT "
                    + "p.descricao "
                    + "AS pergunta_descricao,"
                    + "c.descricao "
                    + "AS categoria_descricao "
                    + "FROM caetgoria c INNER JOIN pergunta "
                    + "p ON c.idCategoria = p.idCategoria and p.descricao like '" + p.getDescricao() + "%' "
                    + "order by categoria_descricao";
        } else {
            sql = "SELECT "
                    + "p.descricao "
                    + "AS pergunta_descricao,"
                    + "c.descricao "
                    + "AS categoria_descricao "
                    + "FROM categoria c INNER JOIN pergunta "
                    + "p ON c.idCategoria = p.idCetgoria AND c.descricao like '" + parametro + "' "
                    + " and p.descricao like '" + p.getDescricao() + "%' "
                    + "order by categoria_descricao and pergunta_descricao";
        }
        System.err.println(sql);
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        return rs;
    }

    public static ResultSet retornaPerguntasPorCategoriaSemObjetoRs(String parametro) throws SQLException {
        String sql = "";
        if (parametro.equalsIgnoreCase("<<Tudo>>")) {
            sql = "SELECT "
                    + "p.descricao "
                    + "AS pergunta_descricao,"
                    + "c.descricao "
                    + "AS categoria_descricao "
                    + "FROM caetgoria c INNER JOIN pergunta "
                    + "p ON c.idCategoria = p.idCategoria "
                    + "order by categoria_descricao";
        } else {
            sql = "SELECT "
                    + "p.descricao "
                    + "AS pergunta_descricao,"
                    + "c.descricao "
                    + "AS categoria_descricao "
                    + "FROM categoria c INNER JOIN pergunta "
                    + "p ON c.idCategoria = p.idCategoria AND c.descricao like '" + parametro + "' "
                    + "order by categoria_descricao and pergunta_descricao";
        }
        System.err.println(sql);
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        return rs;
    }

    public static ResultSet retornaPerguntasPorNiveisSemObjetoRs(String parametro) throws SQLException {
        String sql = "";
        if (parametro.equalsIgnoreCase("<<Tudo>>")) {
            sql = "SELECT "
                    + "p.descricao "
                    + "AS pergunta_descricao,"
                    + "n.descricao "
                    + "AS nivel_descricao "
                    + "FROM nivel n INNER JOIN pergunta "
                    + "p ON n.idNivel = p.idNivel "
                    + "order by nivel_descricao";
        } else {
            sql = "SELECT "
                    + "p.descricao "
                    + "AS pergunta_descricao,"
                    + "n.descricao "
                    + "AS nivel_descricao "
                    + "FROM nivel n INNER JOIN pergunta "
                    + "p ON n.idNivel = p.idNivel AND n.descricao like '" + parametro + "' "
                    + "order by nivel_descricao and pergunta_descricao";
        }
        System.err.println(sql);
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        return rs;
    }

    public static boolean RetornaQtdePerguntasNivECat(String parametro, String nivOrCat) throws SQLException {
        int qtde = 0;
        String sql = "";
        if (nivOrCat.equalsIgnoreCase("Nivel")) {
            sql = "SELECT count(*) "
                    + "FROM nivel n, pergunta "
                    + "p where n.idNivel = p.idNivel AND n.descricao like '" + parametro + "' ";
        } else {
            sql = "SELECT count(*) "
                    + "FROM categoria c, pergunta "
                    + "p where c.idCategoria = p.idCategoria AND c.descricao like '" + parametro + "' ";
        }

        System.err.println(sql);
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            qtde = rs.getInt(1);
        }

        stmt.close();
        conexao.close();
        if (qtde > 0) {
            return true;
        } else {
            return false;
        }

    }

    public static ResultSet RetornaPerguntasEAlternativasRs(PerguntaBean p) throws SQLException {
//        String sql = "select p.nome as descricaoPalavra, d.texto, d.nomeDica, d.som, d.imagem from palavra p inner join dica d on d.idPalavra = p.idPalavra order by descricaoPalavra";

        String sql = "SELECT p.descricao as pergunta_descricao, "
                + "a.descricao as alternativa_descricao, a.correta as alternativa_correta "
                + "FROM p pergunta INNER JOIN a alternativa "
                + "ON p.idPergunta = a.idPergunta ";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        return rs;
    }

    public static ResultSet retornaPergutasPorObjetoRs(PerguntaBean per, String filtroNivel, String filtroCategoria) throws SQLException {
        List<PerguntaBean> listaPl = new ArrayList<PerguntaBean>();
        String sql = null;
        if (filtroNivel.equalsIgnoreCase("<<Tudo>>") && filtroCategoria.equalsIgnoreCase("<<Tudo>>")) {
            sql = "SELECT "
                    + " pe.idPergunta,"
                    + " pe.descricao,"
                    + " ca.descricao as categoria_descricao,"
                    + " ca.idCategoria, "
                    + " ni.idNivel, "
                    + " ni.descricao as nivel_descricao "
                    + " FROM "
                    + " Pergunta pe, "
                    + " Categoria ca, "
                    + " Nivel ni "
                    + " WHERE "
                    + " pe.idCategoria = ca.idCategoria AND pe.idNivel = ni.idNivel AND "
                    + " pe.descricao like '" + per.getDescricao() + "%' "
                    + " ORDER BY pe.descricao";
            System.err.println(sql);
        } else if (!filtroNivel.equalsIgnoreCase("<<Tudo>>") && filtroCategoria.equalsIgnoreCase("<<Tudo>>")) {
            sql = "SELECT"
                    + " pe.idPergunta,"
                    + " pe.descricao,"
                    + " ni.idNivel,"
                    + " ni.descricao as nivel_descricao, "
                    + " ca.idCategoria,"
                    + " ca.descricao as categoria_descricao "
                    + " FROM "
                    + " Pergunta pe,"
                    + " Nivel ni, "
                    + " Categoria ca "
                    + " WHERE "
                    + " pe.idNivel = ni.idNivel AND "
                    + " pe.idCategoria = ca.idCategoria"
                    + " AND pe.descricao like'" + per.getDescricao() + "%' and ni.descricao = '" + filtroNivel + "'"
                    + " ORDER BY pe.descricao";
            System.err.println(sql);
        } else if (filtroNivel.equalsIgnoreCase("<<Tudo>>") && !filtroCategoria.equalsIgnoreCase("<<Tudo>>")) {
            sql = "SELECT"
                    + " pe.idPergunta,"
                    + " pe.descricao,"
                    + " ni.idNivel,"
                    + " ni.descricao as nivel_descricao,"
                    + " ca.idCategoria,"
                    + " ca.descricao as categoria_descricao"
                    + " FROM "
                    + " Pergunta pe,"
                    + " Nivel ni, "
                    + " Categoria ca "
                    + " WHERE "
                    + " pe.idNivel = ni.idNivel AND "
                    + " pe.idCategoria = ca.idCategoria "
                    + " AND pe.descricao like'" + per.getDescricao() + "%' and ca.descricao = '" + filtroCategoria + "' "
                    + " ORDER BY pe.descricao";
            System.err.println(sql);
        } else {

            sql = "SELECT"
                    + " pe.idPergunta,"
                    + " pe.descricao,"
                    + " ni.idNivel,"
                    + " ni.descricao as nivel_descricao,"
                    + " ca.idCategoria,"
                    + " ca.descricao as categoria_descricao"
                    + " FROM "
                    + " Pergunta pe,"
                    + " Nivel ni, "
                    + " Categoria ca "
                    + " WHERE "
                    + " pe.idNivel = ni.idNivel AND "
                    + " pe.idCategoria = ca.idCategoria "
                    + " AND pe.descricao like'" + per.getDescricao() + "%' and ni.descricao = '" + filtroNivel + "' "
                    + " AND ca.descricao = '" + filtroCategoria + "' "
                    + " ORDER BY pe.descricao";
            System.err.println(sql);
        }

        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        return rs;
    }

}
