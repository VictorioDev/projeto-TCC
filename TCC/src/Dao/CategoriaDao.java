/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Bean.CategoriaBean;
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
 * @author usuario
 */
public class CategoriaDao {
    
       public static List<CategoriaBean> retornaCategoria(CategoriaBean cat) throws SQLException{
        List<CategoriaBean> listaCat = new ArrayList<CategoriaBean>();
        String sql = "select * from Categoria where descricao like'"+cat.getDescricao()+"%' order by descricao";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stat = conexao.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        while(rs.next()){
            CategoriaBean categoria = new CategoriaBean();
            categoria.setDescricao(rs.getString("descricao"));
            categoria.setIdCategoria(rs.getInt("idCategoria"));
            listaCat.add(categoria);
        }
         conexao.close();
         stat.close();
         rs.close();
        return listaCat;
        }
       
        public static List<CategoriaBean> retornaCategoria() throws SQLException{
        List<CategoriaBean> listaCat = new ArrayList<CategoriaBean>();
        String sql = "select * from Categoria ";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stat = conexao.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        while(rs.next()){
            CategoriaBean categoria = new CategoriaBean();
            categoria.setDescricao(rs.getString("descricao"));
            categoria.setIdCategoria(rs.getInt("idCategoria"));
            listaCat.add(categoria);
        }
         conexao.close();
         stat.close();
         rs.close();
        return listaCat;
        }
     
      public static void salvar(CategoriaBean categoria) throws SQLException{
        try{
        String sql = "insert into Categoria (descricao) values (?)";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1,categoria.getDescricao());
        stmt.execute();
        stmt.close();
        conexao.close();
       }catch(Exception ex){
         ex.printStackTrace(); 
         JOptionPane.showMessageDialog(null, "Erro ao salvar a categoria","ERRO!",JOptionPane.ERROR_MESSAGE);

       }
    }
    
    public static void alterar(CategoriaBean categoria) throws SQLException{
        try{
        String sql = "update Categoria set descricao=? where idCategoria=?";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1,categoria.getDescricao());
        stmt.setInt(2, categoria.getIdCategoria());
        stmt.executeUpdate();
        stmt.close();
        conexao.close();
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao alterar a categoria","ERRO!",JOptionPane.ERROR_MESSAGE);
        }
    }
   
   public static void excluir(CategoriaBean categoria) throws SQLException{
       try{
        String sql = "delete from Categoria where idCategoria=?";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, categoria.getIdCategoria());
        stmt.executeUpdate();
        stmt.close();
        conexao.close();
       }catch(Exception ex){
           ex.printStackTrace();
       }
    }

    
}
