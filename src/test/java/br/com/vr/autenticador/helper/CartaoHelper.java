package br.com.vr.autenticador.helper;

import br.com.vr.autenticador.api.request.CartaoRequest;
import br.com.vr.autenticador.application.core.entidade.Cartao;

import java.math.BigDecimal;

public class CartaoHelper {

    private CartaoHelper() {}

    public static Cartao criarCartao() {
        var cartao = new Cartao();
        cartao.setNumero("5364 8532 7186 2921");
        cartao.setSenha("teste");
        cartao.setSaldo(BigDecimal.TEN);
        return cartao;
    }

    public static CartaoRequest criarRequest() {
        var request = new CartaoRequest();
        request.setNumeroCartao("5364 8532 7186 2921");
        request.setSenha("teste");
        return request;
    }

}
