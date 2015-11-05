/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author user
 */
public class PerguntaJogadaBean {

    private float tempo;
    private int pontos;
    private JogadorBean jogador;
    private PerguntaBean pergunta;
    private boolean acertou;

    /**
     * @return the tempo
     */
    public float getTempo() {
        return tempo;
    }

    /**
     * @param tempo the tempo to set
     */
    public void setTempo(float tempo) {
        this.tempo = tempo;
    }

    /**
     * @return the pontos
     */
    public int getPontos() {
        return pontos;
    }

    /**
     * @param pontos the pontos to set
     */
    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    /**
     * @return the jogador
     */
    public JogadorBean getJogador() {
        return jogador;
    }

    /**
     * @param jogador the jogador to set
     */
    public void setJogador(JogadorBean jogador) {
        this.jogador = jogador;
    }

    /**
     * @return the pergunta
     */
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
     * @return the acertou
     */
    public boolean isAcertou() {
        return acertou;
    }

    /**
     * @param acertou the acertou to set
     */
    public void setAcertou(boolean acertou) {
        this.acertou = acertou;
    }

}
