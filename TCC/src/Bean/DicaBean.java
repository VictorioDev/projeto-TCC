/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.applet.AudioClip;
import util.Audio;

/**
 *
 * @author Victório
 */
public class DicaBean {

    private int idDica;
    private byte[] imagem;
    private String texto;
    private byte[] som;
    private PalavraBean palavra;
    private String tipo;
    private String nomeDica;
    
    public int getIdDica() {
        return idDica;
    }

    public void setIdDica(int idDica) {
        this.idDica = idDica;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public byte[] getSom() {
        return som;
    }

    public void setSom(byte[] som) {
        this.som = som;
    }

    public PalavraBean getPalavra() {
        return palavra;
    }

    public void setPalavra(PalavraBean palavra) {
        this.palavra = palavra;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the nomeDica
     */
    public String getNomeDica() {
        return nomeDica;
    }

    /**
     * @param nomeDica the nomeDica to set
     */
    public void setNomeDica(String nomeDica) {
        this.nomeDica = nomeDica;
    }
    



}
