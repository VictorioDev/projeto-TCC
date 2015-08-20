/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.DicaBean;
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
public class DicaDao {

    public static void salvarDica(DicaBean db) throws SQLException {
        String sql = "insert into Dica (texto,idPalavra,imagem,som, tipo, nomeDica) values(?,?,?,?,?,?)";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, db.getTexto());
        stmt.setInt(2, db.getPalavra().getIdPalavra());
        System.out.println("IDDDDDDDDDDDDDDDDDDDDDDDDDDDD:     " + db.getPalavra().getIdPalavra());
        stmt.setBytes(3, db.getImagem());
        stmt.setBytes(4, db.getSom());
        stmt.setString(5, db.getTipo());
        stmt.setString(6, db.getNomeDica());
        stmt.execute();
        stmt.close();
        conexao.close();
    }

    // Criar um Método para salvar cad tipo de dica
    //INSERT INTO Dica (som,idPalavra,tipo) values ('outro teste', 26,'SOM');
    public static void salvarDicaTexto(DicaBean d, int idPalavra) throws SQLException {
        try {
            String sql = "insert into Dica (texto, idPalavra) values (?,?)";
            Connection conexao = Conexao.getConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, d.getTexto());
            stmt.setInt(2, idPalavra);
            stmt.execute();
            stmt.close();
            conexao.close();
            JOptionPane.showMessageDialog(null, "Palavra salva com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar a palavra");
            ex.printStackTrace();

        }
    }

    public static List<DicaBean> RetornaDicas(PalavraBean p) throws SQLException {
        List<DicaBean> ld = new ArrayList<>();
        String sql = "select * from Dica where idPalavra =" + p.getIdPalavra() + "";
        System.out.println(sql);
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            DicaBean d = new DicaBean();
            d.setIdDica(rs.getInt("idDica"));
            d.setTexto(rs.getString("texto"));
            d.setImagem(rs.getBytes("imagem"));
            d.setSom(rs.getBytes("som"));
            d.setNomeDica(rs.getString("nomeDica"));
            d.setTipo(rs.getString("tipo"));
            ld.add(d);
        }
        return ld;

    }
    
    public static List<DicaBean> RetornaDicas(PalavraBean p,String tipo) throws SQLException {
        List<DicaBean> ld = new ArrayList<>();
        String sql = "select * from Dica where idPalavra =" + p.getIdPalavra() + " and tipo = '"+tipo+"'";
        System.out.println(sql);
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            DicaBean d = new DicaBean();
            d.setIdDica(rs.getInt("idDica"));
            d.setTexto(rs.getString("texto"));
            d.setImagem(rs.getBytes("imagem"));
            d.setSom(rs.getBytes("som"));
            d.setTipo(rs.getString("tipo"));
            ld.add(d);
        }
        return ld;

    }

    public static void AtualizaDica(DicaBean dc) throws SQLException {
        String sql = "update Dica set texto=?, som=?, imagem=?, idPalavra = ?, nomeDica=? where idDica = ?";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, dc.getTexto());
        stmt.setBytes(2, dc.getSom());
        stmt.setBytes(3, dc.getImagem());
        stmt.setInt(4, dc.getPalavra().getIdPalavra());
        stmt.setString(5, dc.getNomeDica());
        stmt.setInt(6, dc.getIdDica());

        stmt.executeUpdate();
        stmt.close();
        conexao.close();
    }

    public static void removeDicas(int idPalavra) throws SQLException {
        String sql = "delete from Dica where idPalavra = "+idPalavra;
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.executeUpdate();
        stmt.close();
        conexao.close();
    }
    
    

}
