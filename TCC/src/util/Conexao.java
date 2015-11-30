/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Administrador
 */
public class Conexao {
    public static Connection getConexao() throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //return DriverManager.getConnection("jdbc:mysql://200.17.101.29/softwareLuizVictorio", "luiz_victorio", "luiz@victorio@2014");
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1/softwareLuizVictorio", "root", "ifpr");
        } catch(ClassNotFoundException e){
            throw new SQLException(e.getMessage());

        }

    }
    
    public static void main(String[] args) {
        try {
            Conexao.getConexao();
            System.out.println("CONECTOU");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
    }
}
