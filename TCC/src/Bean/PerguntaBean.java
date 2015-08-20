/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.util.List;

/**
 *
 * @author Convidado
 */
public class PerguntaBean {
    private String descricao;
    private int idPergunta;
    private CategoriaBean categoria;
    private NivelBean nivel;
    private List<AlternativaBean> alternativa;

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
     * @return the idPergunta
     */
    public int getIdPergunta() {
        return idPergunta;
    }

    /**
     * @param idPergunta the idPergunta to set
     */
    public void setIdPergunta(int idPergunta) {
        this.idPergunta = idPergunta;
    }

    /**
     * @return the categoria
     */
    public CategoriaBean getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(CategoriaBean categoria) {
        this.categoria = categoria;
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

    /**
     * @return the alternativa
     */
    public List<AlternativaBean> getAlternativa() {
        return alternativa;
    }

    /**
     * @param alternativa the alternativa to set
     */
    public void setAlternativa(List<AlternativaBean> alternativa) {
        this.alternativa = alternativa;
    }
    
    
}
