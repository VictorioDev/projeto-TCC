/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.CategoriaBean;
import Bean.DicaBean;
import Bean.NivelBean;
import Bean.PalavraBean;
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
 * @author Victório
 */
public class PalavraDao {

    public static void atualizaPalavra(PalavraBean pl) throws SQLException {
        String sql = "update Palavra set idNivel=?, nome=? where idPalavra = ? ";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, pl.getNivel().getIdNivel());
        stmt.setString(2, pl.getNome());
        stmt.setInt(3, pl.getIdPalavra());
        stmt.executeUpdate();
        DicaDao.removeDicas(pl.getIdPalavra());
        for (DicaBean dica : pl.getDicas()) {
            dica.setPalavra(pl);
            DicaDao.salvarDica(dica);
        }

        conexao.close();
    }

    public static ResultSet RetornaPalavraMaisJogadaAcertada(String dataInicial, String dataFinal, int escolha) throws SQLException {
        String sql = "";
        if (escolha == 1) {
            sql = "SELECT\n"
                    + "	p.idPalavra,\n"
                    + "	p.nome,\n"
                    + "	count(*) as NumVezesJogadas,\n"
                    + "	DATE_FORMAT(pj.`data`,'%d/%m/%Y') as DataJogo,\n"
                    + "	sum(pj.acertou) QtdeAcertos,\n"
                    + "	(sum(pj.palpites) / count(*)) as MediaPalpites\n"
                    + "\n"
                    + "FROM\n"
                    + "	palavrajogada pj,\n"
                    + "	palavra p \n"
                    + "WHERE\n"
                    + "	pj.idPalavra = p.idPalavra\n"
                    + " and pj.`data` between '" + dataInicial + "' and '" + dataFinal + "'\n"
                    + "GROUP BY\n"
                    + "	pj.idPalavra\n"
                    + "ORDER BY\n"
                    + "NumVezesJogadas desc";
        } else {
            sql = "SELECT\n"
                    + "	p.idPalavra,\n"
                    + "	p.nome,\n"
                    + "	count(*) as NumVezesJogadas,\n"
                    + "	DATE_FORMAT(pj.`data`,'%d/%m/%Y') as DataJogo,\n"
                    + "	sum(pj.acertou) QtdeAcertos,\n"
                    + "	(sum(pj.palpites) / count(*)) as MediaPalpites\n"
                    + "\n"
                    + "FROM\n"
                    + "	palavrajogada pj,\n"
                    + "	palavra p \n"
                    + "WHERE\n"
                    + "	pj.idPalavra = p.idPalavra\n"
                    + " and pj.`data` between '" + dataInicial + "' and '" + dataFinal + "'\n"
                    + "GROUP BY\n"
                    + " pj.idPalavra\n"
                    + " ORDER BY\n"
                    + "QtdeAcertos desc";
        }

        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        return rs;
    }

    public static void salvarPalavra(PalavraBean palavra) throws SQLException {

        String sql = "insert into Palavra (nome,idNivel,idCategoria) values (?,?,?)";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, palavra.getNome());
        stmt.setInt(2, palavra.getNivel().getIdNivel());
        stmt.setInt(3, palavra.getCategoria().getIdCategoria());
        stmt.execute();
        stmt.close();

        String sql2 = "select max(idPalavra) from Palavra";
        PreparedStatement stmt2 = conexao.prepareStatement(sql2);
        ResultSet rs = stmt2.executeQuery();
        rs.first();

        int idPalavra = rs.getInt("max(idPalavra)");
        palavra.setIdPalavra(idPalavra);

        System.out.println("ID DA PALAVRA: " + palavra.getIdPalavra());
        for (DicaBean dica : palavra.getDicas()) {
            dica.setPalavra(palavra);
            DicaDao.salvarDica(dica);
        }

        conexao.close();

    }

    public static List<PalavraBean> RetornaPalavras(PalavraBean p) throws SQLException {
        List<PalavraBean> listaPl = new ArrayList<PalavraBean>();
        String sql = "select * from Palavra where nome like'" + p.getNome() + "%'";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            PalavraBean pl = new PalavraBean();
            pl.setNome(rs.getString("nome"));
            pl.setIdPalavra(rs.getInt("idPalavra"));
            listaPl.add(pl);
        }
        rs.close();
        stmt.close();
        conexao.close();

        return listaPl;

    }

    public static List<PalavraBean> RetornaPalavrasSP() throws SQLException {
        List<PalavraBean> listaPl = new ArrayList<PalavraBean>();
        String sql = "select * from Palavra";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            PalavraBean pl = new PalavraBean();
            pl.setNome(rs.getString("nome"));
            pl.setIdPalavra(rs.getInt("idPalavra"));
            listaPl.add(pl);
        }
        rs.close();
        stmt.close();
        conexao.close();

        return listaPl;

    }

    public static List<PalavraBean> RetornaPalavrasSO(String nivel, String categoria) throws SQLException {
        List<PalavraBean> listaPl = new ArrayList<PalavraBean>();
        Connection conexao = Conexao.getConexao();
        String sql = "SELECT"
                + " p.idPalavra,"
                + " p.nome,"
                + " n.idNivel,"
                + " n.descricao,"
                + "c.idCategoria,"
                + "c.descricao"
                + " FROM "
                + "Palavra p,"
                + "Nivel n, "
                + "Categoria c "
                + "WHERE "
                + "p.idNivel = n.idNivel AND "
                + "p.idCategoria = c.idCategoria AND "
                + "n.descricao = '" + nivel + "' "
                + "AND c.descricao = '" + categoria + "' "
                + "ORDER BY p.nome";
        System.err.println("Sql: " + sql);
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            NivelBean n = new NivelBean();
            n.setIdNivel(rs.getInt("idNivel"));
            n.setDescricao(rs.getString("descricao"));

            CategoriaBean cat = new CategoriaBean();
            cat.setIdCategoria(rs.getInt("idCategoria"));
            cat.setDescricao(rs.getString("c.descricao"));

            PalavraBean plv = new PalavraBean();
            plv.setIdPalavra(rs.getInt("idPalavra"));
            plv.setNome(rs.getString("nome"));
            plv.setNivel(n);
            plv.setCategoria(cat);
            listaPl.add(plv);
        }
        rs.close();
        stmt.close();
        conexao.close();

        return listaPl;

    }

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
                + "ORDER BY p.nome";
        System.err.println("Sql: " + sql);
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            NivelBean n = new NivelBean();
            n.setIdNivel(rs.getInt("idNivel"));
            n.setDescricao(rs.getString("descricao"));

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

    public static List<PalavraBean> retornaPlvs(PalavraBean pl, String filtroNivel, String filtroCategoria) throws SQLException {
        List<PalavraBean> listaPl = new ArrayList<PalavraBean>();
        String sql = null;
        if (filtroNivel.equalsIgnoreCase("<<Tudo>>") && filtroCategoria.equalsIgnoreCase("<<Tudo>>")) {
            sql = "SELECT"
                    + " p.idPalavra,"
                    + " p.nome,"
                    + " n.idNivel,"
                    + " n.descricao,"
                    + " c.idCategoria,"
                    + " c.descricao"
                    + " FROM "
                    + "Palavra p,"
                    + "Nivel n, "
                    + "Categoria c "
                    + "WHERE "
                    + "p.idNivel = n.idNivel AND "
                    + "p.idCategoria = c.idCategoria"
                    + " AND p.nome like'" + pl.getNome() + "%'"
                    + "ORDER BY p.nome";
            System.err.println(sql);
        } else if (!filtroNivel.equalsIgnoreCase("<<Tudo>>") && filtroCategoria.equalsIgnoreCase("<<Tudo>>")) {
            sql = "SELECT"
                    + " p.idPalavra,"
                    + " p.nome,"
                    + " n.idNivel,"
                    + " n.descricao,"
                    + "c.idCategoria,"
                    + "c.descricao"
                    + " FROM "
                    + "Palavra p,"
                    + "Nivel n, "
                    + "Categoria c "
                    + "WHERE "
                    + "p.idNivel = n.idNivel AND "
                    + "p.idCategoria = c.idCategoria "
                    + "AND p.nome like'" + pl.getNome() + "%' and n.descricao = '" + filtroNivel + "' "
                    + "ORDER BY p.nome";
            System.err.println(sql);
        } else if (filtroNivel.equalsIgnoreCase("<<Tudo>>") && !filtroCategoria.equalsIgnoreCase("<<Tudo>>")) {
            sql = "SELECT"
                    + " p.idPalavra,"
                    + " p.nome,"
                    + " n.idNivel,"
                    + " n.descricao,"
                    + "c.idCategoria,"
                    + "c.descricao"
                    + " FROM "
                    + "Palavra p,"
                    + "Nivel n, "
                    + "Categoria c "
                    + "WHERE "
                    + "p.idNivel = n.idNivel AND "
                    + "p.idCategoria = c.idCategoria "
                    + "AND p.nome like'" + pl.getNome() + "%' and c.descricao = '" + filtroCategoria + "' "
                    + "ORDER BY p.nome";
            System.err.println(sql);
        } else {

            sql = "SELECT"
                    + " p.idPalavra,"
                    + " p.nome,"
                    + " n.idNivel,"
                    + " n.descricao,"
                    + "c.idCategoria,"
                    + "c.descricao"
                    + " FROM "
                    + "Palavra p,"
                    + "Nivel n, "
                    + "Categoria c "
                    + "WHERE "
                    + "p.idNivel = n.idNivel AND "
                    + "p.idCategoria = c.idCategoria "
                    + "AND p.nome like'" + pl.getNome() + "%' and n.descricao = '" + filtroNivel + "' "
                    + "AND c.descricao = '" + filtroCategoria + "' "
                    + "ORDER BY p.nome";
            System.err.println(sql);
        }

        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {

            NivelBean n = new NivelBean();
            n.setIdNivel(rs.getInt("idNivel"));
            n.setDescricao(rs.getString("descricao"));

            CategoriaBean cat = new CategoriaBean();
            cat.setIdCategoria(rs.getInt("idCategoria"));
            cat.setDescricao(rs.getString("c.descricao"));

            PalavraBean plv = new PalavraBean();
            plv.setIdPalavra(rs.getInt("idPalavra"));
            plv.setNome(rs.getString("nome"));
            plv.setNivel(n);
            plv.setCategoria(cat);

            listaPl.add(plv);

        }

        conexao.close();
        stmt.close();
        rs.close();
        return listaPl;
    }

    public static void ExcluiPalavras(PalavraBean p) throws SQLException {
        String sql = "delete from Palavra where idPalavra = ?";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, p.getIdPalavra());
        stmt.execute();
        stmt.close();
        conexao.close();
    }

    public static ResultSet RetornaPalavrasPorNiveisRs(PalavraBean p, String parametro) throws SQLException {

        String sql = "";
        if (parametro.equalsIgnoreCase("<<Tudo>>")) {
//            sql = "SELECT"
//                    + " p.idPalavra,"
//                    + " p.nome as 'palavra_nome',"
//                    + " n.idNivel,"
//                    + " n.descricao as 'nivel_descricao'"
//                    + " FROM "
//                    + "Palavra p,"
//                    + "Nivel n "
//                    + "WHERE "
//                    + "p.idNivel = n.idNivel "
//                    + "ORDER BY nivel_descricao and palavra_nome";
            sql = "SELECT "
                    + "p.nome "
                    + "AS palavra_nome,"
                    + "n.descricao "
                    + "AS nivel_descricao "
                    + "FROM nivel n INNER JOIN palavra "
                    + "p ON n.idNivel = p.idNivel "
                    + "order by nivel_descricao";
        } else {
//            sql = "SELECT"
//                    + " p.idPalavra,"
//                    + " p.nome,"
//                    + " n.idNivel,"
//                    + " n.descricao"
//                    + " FROM "
//                    + "Palavra p,"
//                    + "Nivel n "
//                    + "WHERE "
//                    + "p.idNivel = n.idNivel"
//                    + " AND p.nome like'" + p.getNome() + "%' "
//                    + "ORDER BY p.nome";
            sql = "SELECT "
                    + "p.nome "
                    + "AS palavra_nome,"
                    + "n.descricao "
                    + "AS nivel_descricao "
                    + "FROM nivel n INNER JOIN palavra "
                    + "p ON n.idNivel = p.idNivel AND n.descricao like '" + parametro + "' "
                    + "order by nivel_descricao and palavra_nome";
        }
        System.err.println(sql);
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        return rs;
    }

    public static boolean RetornaQtdePalavrasNivECat(String parametro, String nivOrCat) throws SQLException {
        int qtde = 0;
        String sql = "";
        if (nivOrCat.equalsIgnoreCase("Nivel")) {
            sql = "SELECT count(*) "
                    + "FROM nivel n, palavra "
                    + "p where n.idNivel = p.idNivel AND n.descricao like '" + parametro + "' ";
        } else {
            sql = "SELECT count(*) "
                    + "FROM categoria c, palavra "
                    + "p where c.idCategoria = p.idCategoria AND c.descricao like '" + parametro + "' ";
        }

//            sql = "SELECT"
//                    + " p.idPalavra,"
//                    + " p.nome,"
//                    + " n.idNivel,"
//                    + " n.descricao"
//                    + " FROM "
//                    + "Palavra p,"
//                    + "Nivel n "
//                    + "WHERE "
//                    + "p.idNivel = n.idNivel"
//                    + " AND p.nome like'" + p.getNome() + "%' "
//                    + "ORDER BY p.nome";

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

    public static ResultSet RetornaPalavrasEAlternativasRs(PalavraBean p) throws SQLException {
        String sql = "select p.nome as descricaoPalavra, d.texto, d.nomeDica, d.som, d.imagem from palavra p inner join dica d on d.idPalavra = p.idPalavra order by descricaoPalavra";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        return rs;
    }
}
