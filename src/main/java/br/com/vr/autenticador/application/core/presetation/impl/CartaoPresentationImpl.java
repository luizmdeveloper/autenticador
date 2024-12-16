package br.com.vr.autenticador.application.core.presetation.impl;

import br.com.vr.autenticador.api.request.CartaoRequest;
import br.com.vr.autenticador.api.response.CartaoResponse;
import br.com.vr.autenticador.application.core.entidade.Cartao;
import br.com.vr.autenticador.application.core.presetation.CartaoPresentation;

import java.util.Objects;

public class CartaoPresentationImpl implements CartaoPresentation {

    @Override
    public CartaoResponse convertEntityToResponse(Cartao cartao) {
        if (Objects.nonNull(cartao)) {
            var response = new CartaoResponse();
            response.setNumeroCartao(cartao.getNumero());
            response.setSenha(cartao.getSenha());
            return response;
        }

        return null;
    }

    @Override
    public Cartao convertRequestToEntity(CartaoRequest request) {
        if (Objects.nonNull(request)) {
            var response = new Cartao();
            response.setNumero(request.getNumeroCartao());
            response.setSenha(request.getSenha());
            return response;
        }
        return null;
    }


}
