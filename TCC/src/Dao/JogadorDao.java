/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.JogadorBean;
import Bean.PalavraBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Conexao;

/**
 *
 * @author Victório
 */
public class JogadorDao {

    public static void SalvarJogador(JogadorBean j) throws SQLException {
        String sql = "insert into Jogador (nome,login,senha,email,imagemJogador,pontos,sexo) values (?,?,password(?),?,?,0,?)";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, j.getNome());
        stmt.setString(2, j.getLogin());
        stmt.setString(3, j.getPassword());
        stmt.setString(4, j.getEmail());
        stmt.setBytes(5, j.getImgUser());
        stmt.setString(6, j.getSexo());

        stmt.execute();
        stmt.close();
        conexao.close();

    }

    public static List<JogadorBean> RetornaJogadores(JogadorBean jog) throws SQLException {
        List<JogadorBean> listajog = new ArrayList<JogadorBean>();

        ResultSet rs;

        String sql = "select * from Jogador where nome like'" + jog.getNome() + "%'";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        rs = stmt.executeQuery();
        while (rs.next()) {
            JogadorBean jo = new JogadorBean();
            jo.setNome(rs.getString("nome"));
            jo.setIdJogador(rs.getInt("idJogador"));
            jo.setImgUser(rs.getBytes("imagemJogador"));
            jo.setLogin(rs.getString("login"));
            jo.setPassword(rs.getString("senha"));
            listajog.add(jo);

        }
        rs.close();
        stmt.close();
        conexao.close();

        return listajog;

    }
    
    public static List<JogadorBean> RetornaJogadores(String nome) throws SQLException {
        List<JogadorBean> listajog = new ArrayList<JogadorBean>();

        ResultSet rs;

        String sql = "select * from Jogador where nome like'" + nome + "%'";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        rs = stmt.executeQuery();
        while (rs.next()) {
            JogadorBean jo = new JogadorBean();
            jo.setNome(rs.getString("nome"));
            jo.setIdJogador(rs.getInt("idJogador"));
            jo.setImgUser(rs.getBytes("imagemJogador"));
            jo.setLogin(rs.getString("login"));
            jo.setPassword(rs.getString("senha"));
            listajog.add(jo);

        }
        rs.close();
        stmt.close();
        conexao.close();

        return listajog;

    }

    public static ResultSet RetornaJogadoresRs(JogadorBean jog) throws SQLException {
        List<JogadorBean> listajog = new ArrayList<JogadorBean>();

        ResultSet rs;

        String sql = "select * from Jogador where nome like'" + jog.getNome() + "%'";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        rs = stmt.executeQuery();

      
        

        return rs;
    }

    public static void ExcluirJogador(JogadorBean jog) throws SQLException {
        String slq = " delete from Jogador where idJogador = ?";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(slq);
        stmt.setInt(1, jog.getIdJogador());

        stmt.execute();
        stmt.close();
        conexao.close();

    }

    public static JogadorBean retornaJogadorLogado(JogadorBean jogador) throws SQLException {
        ResultSet rs;

        String sql = "select * from Jogador where login = '"+jogador.getLogin()+"' and senha=password('"+jogador.getPassword()+"')";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        rs = stmt.executeQuery();
        rs.first();

        JogadorBean jo = new JogadorBean();
        jo.setNome(rs.getString("nome"));
        jo.setIdJogador(rs.getInt("idJogador"));
        jo.setImgUser(rs.getBytes("imagemJogador"));
        jo.setLogin(rs.getString("login"));
        jo.setPassword(rs.getString("senha"));

        rs.close();
        stmt.close();
        conexao.close();

        return jo;

    }

    public static void AlterarJogador(JogadorBean j) throws SQLException {
        String slq = " update Jogador set nome = ?, email = ?, login = ?, senha = ?, imagemJogador = ? where idJogador = ?";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(slq);
        stmt.setString(1, j.getNome());
        stmt.setString(2, j.getEmail());
        stmt.setString(3, j.getLogin());
        stmt.setString(4, j.getPassword());
        stmt.setBytes(5, j.getImgUser());
        stmt.setInt(6, j.getIdJogador());
        stmt.execute();
        stmt.close();
        conexao.close();
    }
    
    public static void UpdatePontos(JogadorBean j) throws SQLException{
        String slq = " update Jogador set pontos = ? where idJogador = ?";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(slq);
        stmt.setInt(1, j.getPontos());
        stmt.setInt(2, j.getIdJogador());
        stmt.execute();
        stmt.close();
        conexao.close();
        
    }

}
