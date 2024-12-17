package br.com.vr.autenticador.helper;

import br.com.vr.autenticador.api.request.TransacaoRequest;
import br.com.vr.autenticador.application.core.entidade.TipoTransacao;
import br.com.vr.autenticador.application.core.entidade.Transacao;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class TransacaoHelper {

    private TransacaoHelper() {}

    public static Transacao criarTransacao() {
        var transacao = new Transacao();
        transacao.setCartao(CartaoHelper.criarCartao());
        transacao.setDate(OffsetDateTime.now());
        transacao.setTipo(TipoTransacao.CREDITO);
        transacao.setValor(BigDecimal.valueOf(100));
        return transacao;
    }

    public static TransacaoRequest criarRequest() {
        var request = new TransacaoRequest();
        request.setNumeroCartao("5364 8532 7186 2921");
        request.setValor(BigDecimal.TEN);
        request.setSenha("1234");
        return request;
    }
}
