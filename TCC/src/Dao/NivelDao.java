/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

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
 * @author Administrador
 */
public class NivelDao {

    public static List<NivelBean> RetornaNiveis() throws SQLException {
        List<NivelBean> listaNiv = new ArrayList<NivelBean>();
        String sql = "select * from Nivel";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stat = conexao.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        while (rs.next()) {
            NivelBean nivel = new NivelBean();
            nivel.setIdNivel(rs.getInt("idNivel"));
            nivel.setDescricao(rs.getString("descricao"));
            listaNiv.add(nivel);
        }
        conexao.close();
        stat.close();
        rs.close();
        return listaNiv;
    }

    public static List<NivelBean> RetornaNiveis(NivelBean nivel) throws SQLException {
        List<NivelBean> listaNiv = new ArrayList<NivelBean>();
        String sql = "select * from Nivel where descricao like'" + nivel.getDescricao() + "%' order by descricao";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stat = conexao.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        while (rs.next()) {
            NivelBean niv = new NivelBean();
            niv.setDescricao(rs.getString("descricao"));
            niv.setIdNivel(rs.getInt("idNivel"));
            listaNiv.add(niv);
        }
        conexao.close();
        stat.close();
        rs.close();
        return listaNiv;
    }

    public static void SalvarNivel(NivelBean nv) throws SQLException {
        try {
            String slq = "insert into Nivel (descricao) values (?)";
            Connection conexao = Conexao.getConexao();
            PreparedStatement stmt = conexao.prepareStatement(slq);
            stmt.setString(1, nv.getDescricao());
            stmt.execute();
            stmt.close();
            conexao.close();
//          JOptionPane.showMessageDialog(null, "Nivel cadastrado com sucesso!");
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao salvar o nivel!", "ERRO!", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

    }

    public static void alterar(NivelBean nivel) throws SQLException {
        try {
            String sql = "update Nivel set descricao=? where idNivel=?";
            Connection conexao = Conexao.getConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nivel.getDescricao());
            stmt.setInt(2, nivel.getIdNivel());
            stmt.executeUpdate();
            stmt.close();
            conexao.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao alterar o nível", "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void DeletarNivel(NivelBean nv) throws SQLException {
        String slq = " delete from Nivel where idNivel = ?";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(slq);
        stmt.setInt(1, nv.getIdNivel());
        stmt.executeUpdate();
        stmt.close();
        conexao.close();
    }

    public static ResultSet retornaRsComObjeto(NivelBean nv) throws SQLException {
        String sql = "select * from Nivel where descricao like'" + nv.getDescricao() + "%' order by descricao";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stat = conexao.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        return rs;
    }

    public static ResultSet retornaRsSemObjeto() throws SQLException {
        String sql = "select * from Nivel order by descricao";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stat = conexao.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        return rs;
    }

}
