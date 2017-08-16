package br.com.alura.ichat.event;

import br.com.alura.ichat.modelo.Mensagem;

/**
 * Created by Millfford on 16/08/2017.
 */

public class MensagemEvent {

    public Mensagem mensagem;

    public MensagemEvent(Mensagem mensagem) {
        this.mensagem = mensagem;

    }
}
