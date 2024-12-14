package br.com.vr.autenticador.application.core.usecase;

import br.com.vr.autenticador.application.core.repository.CartaoRepository;
import br.com.vr.autenticador.application.execption.EntidadeNaoEncontradoException;

import java.math.BigDecimal;

public class ConsultaSaldoCartaoUseCase {

    private final CartaoRepository repository;

    public ConsultaSaldoCartaoUseCase(CartaoRepository repository) {
        this.repository = repository;
    }

    public BigDecimal consultar(String numeroCartao) {
        var optionalCartao = repository.findByNumero(numeroCartao);

        if (optionalCartao.isPresent()) {
            throw new EntidadeNaoEncontradoException("Cartão não foi encontrado");
        }

        return optionalCartao.get().getSaldo();
    }

}
