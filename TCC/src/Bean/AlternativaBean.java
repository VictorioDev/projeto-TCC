/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author Administrador
 */
public class AlternativaBean {

    private String descricao;
    private int idAlternativa;
    private String correta;
    private PerguntaBean pergunta;

    public String getCorreta() {
        return correta;
    }

    public void setCorreta(String correta) {
        this.correta = correta;
    }

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

    public PerguntaBean getPergunta() {
        return pergunta;
    }

    /**
     * @param pergunta the pergunta to set
     */
    public void setPergunta(PerguntaBean pergunta) {
        this.pergunta = pergunta;
    }

    /**
     * @return the idAlternativa
     */
    public int getIdAlternativa() {
        return idAlternativa;
    }

    /**
     * @param idAlternativa the idAlternativa to set
     */
    public void setIdAlternativa(int idAlternativa) {
        this.idAlternativa = idAlternativa;
    }

}
