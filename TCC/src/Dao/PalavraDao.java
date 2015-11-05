/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.DicaBean;
import Bean.NivelBean;
import Bean.PalavraBean;
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

    public static void salvarPalavra(PalavraBean palavra) throws SQLException {

        String sql = "insert into Palavra (nome,idNivel) values (?,?)";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, palavra.getNome());
        stmt.setInt(2, palavra.getNivel().getIdNivel());
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

    public static List<PalavraBean> retornaPlvs(PalavraBean pl, String filtro) throws SQLException {
        List<PalavraBean> listaPl = new ArrayList<PalavraBean>();
        String sql = null;
        if (filtro.equalsIgnoreCase("<<Tudo>>")) {
            sql = "SELECT"
                    + " p.idPalavra,"
                    + " p.nome,"
                    + " n.idNivel,"
                    + " n.descricao"
                    + " FROM "
                    + "Palavra p,"
                    + "Nivel n "
                    + "WHERE "
                    + "p.idNivel = n.idNivel"
                    + " AND p.nome like'" + pl.getNome() + "%'"
                    + "ORDER BY p.nome";
        } else {

            sql = "SELECT"
                    + " p.idPalavra,"
                    + " p.nome,"
                    + " n.idNivel,"
                    + " n.descricao"
                    + " FROM "
                    + "Palavra p,"
                    + "Nivel n "
                    + "WHERE "
                    + "p.idNivel = n.idNivel"
                    + " AND p.nome like'" + pl.getNome() + "%' and n.descricao = '" + filtro + "'"
                    + "ORDER BY p.nome";
        }

        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {

            NivelBean n = new NivelBean();
            n.setIdNivel(rs.getInt("idNivel"));
            n.setDescricao(rs.getString("descricao"));

            PalavraBean plv = new PalavraBean();
            plv.setIdPalavra(rs.getInt("idPalavra"));
            plv.setNome(rs.getString("nome"));
            plv.setNivel(n);

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

    public static ResultSet retornaPalavrasRs(PalavraBean p, String parametro) throws SQLException {

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
}
