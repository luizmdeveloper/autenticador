package br.com.vr.autenticador.application.core.presetation;

import br.com.vr.autenticador.api.request.CartaoRequest;
import br.com.vr.autenticador.api.response.CartaoResponse;
import br.com.vr.autenticador.application.core.entidade.Cartao;

public interface CartaoPresentation {

    CartaoResponse convertEntityToResponse(Cartao cartao);
    Cartao convertRequestToEntity(CartaoRequest request);
}
