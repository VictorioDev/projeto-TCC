/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.PerguntaJogadaBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.Conexao;

/**
 *
 * @author user
 */
public class PerguntaJogadaDAO {

    public static void SalvarPerguntaJogada(PerguntaJogadaBean pj) throws SQLException {
        String sql = "insert into perguntajogada(idJogador,idPergunta, pontos, acertou, tempo, data) values (?,?,?,?,?,now())";
        Connection conexao = Conexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, pj.getJogador().getIdJogador());
        stmt.setInt(2, pj.getPergunta().getIdPergunta());
        stmt.setInt(3, pj.getPontos());
        stmt.setBoolean(4, pj.isAcertou());
        stmt.setFloat(5, pj.getTempo());

        stmt.execute();
        stmt.close();
        conexao.close();
    }

}
