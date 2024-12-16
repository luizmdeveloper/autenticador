package br.com.vr.autenticador.application.core.presetation;

import br.com.vr.autenticador.api.request.TransacaoRequest;
import br.com.vr.autenticador.application.core.entidade.Transacao;

public interface TransacaoPresentation {

    Transacao convertRequestToEntity(TransacaoRequest request);
}
