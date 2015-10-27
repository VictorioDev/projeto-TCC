/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Bean.AlternativaBean;
import Bean.CategoriaBean;
import Bean.JogadorBean;
import Bean.NivelBean;
import Bean.PerguntaBean;
import Dao.AlternativaDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author victo
 */
public class AlimentaBanco {

    public static void main(String[] args) throws SQLException {
      SalvarNiveis();

    }

    public static void SalvarUsuario() throws SQLException {
        String sql = "insert into Jogador (nome,login,senha,email,imagemJogador,pontos) values (?,?,password(?),?,?,?)";
        String alfa = "abcdefghijklmnopqrstuvwxyz";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        for (int i = 0; i < alfa.length(); i++) {
            Random ram = new Random();
            int pontos = ram.nextInt(50);
            stmt.setString(1, alfa.charAt(i) + "");
            stmt.setString(2, alfa.charAt(i) + "");
            stmt.setString(3, "root");
            stmt.setString(4, "teste");
            stmt.setBytes(5, null);
            stmt.setInt(6, pontos);
            stmt.execute();

        }
        stmt.close();
        conexao.close();

    }

    public static void SalvarCategorias() throws SQLException {

        List<String> categoria = Arrays.asList("Matematica", "Portugues", "História", "Geografia", "Ciências");
        String sql = "insert into Categoria (descricao) values (?)";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        categoria.forEach(x -> {
            try {
                stmt.setString(1, x);
                stmt.execute();
            } catch (SQLException ex) {
                Logger.getLogger(AlimentaBanco.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        stmt.close();
        conexao.close();
    }
    
    public static void SalvarNiveis() throws SQLException {

        List<String> nivel = Arrays.asList("Médio", "Difícil", "MuitoDificil", "NemFudenoVcFaz");
        String sql = "insert into nivel (descricao) values (?)";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        nivel.forEach(x -> {
            try {
                stmt.setString(1, x);
                stmt.execute();
            } catch (SQLException ex) {
                Logger.getLogger(AlimentaBanco.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        stmt.close();
        conexao.close();
    }
    
   

}
