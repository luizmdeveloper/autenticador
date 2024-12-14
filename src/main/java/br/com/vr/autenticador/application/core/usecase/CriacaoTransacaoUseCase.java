package br.com.vr.autenticador.application.core.usecase;

import br.com.vr.autenticador.api.request.TransacaoRequest;
import br.com.vr.autenticador.application.core.entidade.TipoTransacao;
import br.com.vr.autenticador.application.core.entidade.Transacao;
import br.com.vr.autenticador.application.core.repository.CartaoRepository;
import br.com.vr.autenticador.application.core.repository.TransacaoRepository;
import br.com.vr.autenticador.application.execption.NumeroCartaoJaCadastradoException;
import br.com.vr.autenticador.application.execption.RegraDeNegocioException;

import java.time.OffsetDateTime;

public class CriacaoTransacaoUseCase {

    private final TransacaoRepository repository;
    private final CartaoRepository cartaoRepository;

    public CriacaoTransacaoUseCase(TransacaoRepository repository, CartaoRepository cartaoRepository) {
        this.repository = repository;
        this.cartaoRepository = cartaoRepository;
    }

    public Transacao criar(TransacaoRequest transacaoRequest) {
        var optionalCartao = cartaoRepository.findByNumero(transacaoRequest.getNumeroCartao());

        if (optionalCartao.isEmpty()) {
            throw new NumeroCartaoJaCadastradoException("Existe um cartão com mesmo número");
        }

        var cartao = optionalCartao.get();

        if (cartao.getSaldo().compareTo(transacaoRequest.getValor()) < 0) {
            throw new RegraDeNegocioException("Valor dá transação menor que o saldo");
        }

        if (!transacaoRequest.getSenha().equals(cartao.getSenha())) {
            throw new RegraDeNegocioException("Senha inválida");
        }

        cartao.setSaldo(cartao.getSaldo().subtract(transacaoRequest.getValor()));

        var transacao = new Transacao();
        transacao.setCartao(cartao);
        transacao.setDate(OffsetDateTime.now());
        transacao.setTipo(TipoTransacao.DEBITO);
        transacao.setValor(transacaoRequest.getValor());

        repository.save(transacao);
        cartaoRepository.atualizarSaldo(cartao);

        return transacao;
    }
}
