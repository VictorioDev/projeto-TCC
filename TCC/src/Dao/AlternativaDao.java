/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Bean.AlternativaBean;
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
 * @author Administrador
 */
public class AlternativaDao {

     public static List<AlternativaBean> retornaAlternativas(AlternativaBean alt) throws SQLException{
        List<AlternativaBean> listaAlt = new ArrayList<AlternativaBean>();
        String sql = "select * from Alternativa";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stat = conexao.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        while(rs.next()){
            AlternativaBean alternativa = new AlternativaBean();
            alternativa.setDescricao(rs.getString("descricao"));
            alternativa.setCorreta(rs.getString("correta"));
            listaAlt.add(alternativa);
        }
         conexao.close();
         stat.close();
         rs.close();
        return listaAlt;
    }
     
      public static void salvar(AlternativaBean alternativa) throws SQLException{
        try{
        String sql = "insert into Alternativa (descricao,correta) values (?,?)";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1,alternativa.getDescricao());
        stmt.setString(2, alternativa.getCorreta());
        stmt.execute();
        stmt.close();
        conexao.close();
       }catch(Exception ex){
         ex.printStackTrace();   
       }
    }
    
    public static void alterar(AlternativaBean alternativa) throws SQLException{
        try{
        String sql = "update Alternativa set descricao=? where idAlternativa=?";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1,alternativa.getDescricao());
        stmt.setString(2, alternativa.getCorreta());
        stmt.executeUpdate();
        stmt.close();
        conexao.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
   
   public static void excluir(AlternativaBean alternativa) throws SQLException{
       try{
        String sql = "delete from Alternativa where idAlternativa=?";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, alternativa.getDescricao());
        stmt.setString(2, alternativa.getCorreta());        
        stmt.execute();
        stmt.close();
        conexao.close();
       }catch(Exception ex){
           ex.printStackTrace();
       }
    }
    

}
