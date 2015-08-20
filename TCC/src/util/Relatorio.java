/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.sql.ResultSet;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author victorio
 */
public class Relatorio {
    public static void gerarRelatório(String caminho, ResultSet rs) throws JRException{
        JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
        JasperPrint jasperPrint = JasperFillManager.fillReport(caminho, new HashMap(), jrRS);
        JasperViewer.viewReport(jasperPrint, false);
    }
    
}
