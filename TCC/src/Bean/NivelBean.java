/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

import java.util.List;

/**
 *
 * @author Administrador
 */
public class NivelBean {

     private String descricao;
     private int idNivel;
     private List<PalavraBean> lplv;

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the idNivel
     */
    public int getIdNivel() {
        return idNivel;
    }

    /**
     * @param idNivel the idNivel to set
     */
    public void setIdNivel(int idNivel) {
        this.idNivel = idNivel;
    }

    /**
     * @return the lplv
     */
    public List<PalavraBean> getLplv() {
        return lplv;
    }

    /**
     * @param lplv the lplv to set
     */
    public void setLplv(List<PalavraBean> lplv) {
        this.lplv = lplv;
    }

   
}
