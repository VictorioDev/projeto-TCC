/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

/**
 *
 * @author Victório
 */
public class PalavraJogadaBean {
    
    private float tempo;
    private int palpites;
    private JogadorBean jogador;
    private PalavraBean palavra;
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
     * @return the palpites
     */
    public int getPalpites() {
        return palpites;
    }

    /**
     * @param palpites the palpites to set
     */
    public void setPalpites(int palpites) {
        this.palpites = palpites;
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
     * @return the palavra
     */
    public PalavraBean getPalavra() {
        return palavra;
    }

    /**
     * @param palavra the palavra to set
     */
    public void setPalavra(PalavraBean palavra) {
        this.palavra = palavra;
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
