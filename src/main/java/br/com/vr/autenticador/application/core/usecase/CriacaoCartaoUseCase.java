package br.com.vr.autenticador.application.core.usecase;

import br.com.vr.autenticador.api.request.CartaoRequest;
import br.com.vr.autenticador.application.core.entidade.Cartao;
import br.com.vr.autenticador.application.core.repository.CartaoRepository;
import br.com.vr.autenticador.application.execption.NumeroCartaoJaCadastradoException;

import java.math.BigDecimal;

public class CriacaoCartaoUseCase {

    private final CartaoRepository repository;

    public CriacaoCartaoUseCase(CartaoRepository repository) {
        this.repository = repository;
    }

    public Cartao save(CartaoRequest cartaoRequest) {
        var optionalCartao = repository.findByNumero(cartaoRequest.getNumeroCartao());

        if (optionalCartao.isPresent()) {
            throw new NumeroCartaoJaCadastradoException("Existe um cartão com mesmo número");
        }

        var cartao = new Cartao();
        cartao.setNumero(cartaoRequest.getNumeroCartao());
        cartao.setSenha(cartaoRequest.getSenha());
        cartao.setSaldo(new BigDecimal(500d));
        repository.save(cartao);

        return cartao;
    }

}
