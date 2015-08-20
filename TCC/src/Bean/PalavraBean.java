/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.util.List;

/**
 *
 * @author Victório
 */
public class PalavraBean {

    private int idPalavra;
    private String nome;
    private DicaBean dica;
    private NivelBean nivel; 
    private List<DicaBean> dicas;
    
    
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the idPalavra
     */
    public int getIdPalavra() {
        return idPalavra;
    }

    /**
     * @param idPalavra the idPalavra to set
     */
    public void setIdPalavra(int idPalavra) {
        this.idPalavra = idPalavra;
    }

   
    public List<DicaBean> getDicas() {
        return dicas;
    }

    /**
     * @param dicas the dicas to set
     */
    public void setDicas(List<DicaBean> dicas) {
        this.dicas = dicas;
    }

    /**
     * @return the nivel
     */
    public NivelBean getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(NivelBean nivel) {
        this.nivel = nivel;
    }

    

}
