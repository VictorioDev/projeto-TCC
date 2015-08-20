/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Bean.PalavraJogadaBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.Conexao;

/**
 *
 * @author user
 */
public class PalavraJogadaDAO {
    
    
    public static void SalvarPalavraJogada(PalavraJogadaBean pj)throws SQLException{
        String sql = "insert into PalavraJogada(idJogador,idPalavra, tempo, palpites, acertou, data) values (?,?,?,?,?,now())";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, pj.getJogador().getIdJogador());
        stmt.setInt(2, pj.getPalavra().getIdPalavra());
        stmt.setFloat(3, pj.getTempo());
        stmt.setInt(4, pj.getPalpites());
        stmt.setBoolean(5, pj.isAcertou());
        stmt.execute();
        stmt.close();
        conexao.close();          
    }
    
}
