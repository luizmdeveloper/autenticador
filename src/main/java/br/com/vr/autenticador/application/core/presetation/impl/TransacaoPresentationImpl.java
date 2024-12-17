package br.com.vr.autenticador.application.core.presetation.impl;

import br.com.vr.autenticador.api.request.TransacaoRequest;
import br.com.vr.autenticador.application.core.entidade.Transacao;
import br.com.vr.autenticador.application.core.presetation.TransacaoPresentation;

import java.time.OffsetDateTime;

public class TransacaoPresentationImpl implements TransacaoPresentation {

    @Override
    public Transacao convertRequestToEntity(TransacaoRequest request) {
        var transacao = new Transacao();
        transacao.setValor(request.getValor());
        transacao.setDate(OffsetDateTime.now());
        return transacao;
    }

}
