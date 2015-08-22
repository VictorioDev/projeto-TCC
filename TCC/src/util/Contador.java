/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// Site abaixo obtido no seguinte site: http://forum.imasters.com.br/topic/363964-cronometro/
package util;

import Telas.TelaJogoPerguntaResposta;
import javax.swing.JLabel;

public class Contador extends Thread {

    private JLabel hr;

    public Contador(JLabel hora) {
        this.hr = hora;
    }

    @Override
    public void run() {
        try {
            int segundo = 0;
            int hora = 0;
            int minuto = 0;
            while (true) {
                if (TelaJogoPerguntaResposta.isContador()) {
                    if (TelaJogoPerguntaResposta.isZerado()) {
                        segundo = 0;
                        hora = 0;
                        minuto = 0;
                        TelaJogoPerguntaResposta.setZerado(false);
                    }

                    if (segundo == 59) {
                        segundo = 00-1;
                        minuto = minuto + 1;
                    }

                    if (minuto == 59) {
                        minuto = 00;
                        hora = hora + 1;
                    }
                    segundo++;
                    String timer = completaComZero(hora) + ":"
                            + completaComZero(minuto) + ":"
                            + completaComZero(segundo);
                    this.hr.setText(timer);
                    this.hr.revalidate();
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private String completaComZero(Integer i) {
        String retorno = null;
        if (i < 10) {
            retorno = "0" + i;
        } else {
            retorno = i.toString();
        }
        return retorno;
    }

}
