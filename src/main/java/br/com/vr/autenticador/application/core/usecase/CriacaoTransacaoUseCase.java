package br.com.vr.autenticador.application.core.usecase;

import br.com.vr.autenticador.api.request.TransacaoRequest;
import br.com.vr.autenticador.application.core.entidade.TipoTransacao;
import br.com.vr.autenticador.application.core.entidade.Transacao;
import br.com.vr.autenticador.application.core.presetation.TransacaoPresentation;
import br.com.vr.autenticador.application.core.repository.CartaoRepository;
import br.com.vr.autenticador.application.core.repository.TransacaoRepository;
import br.com.vr.autenticador.application.execption.ErroGenericoException;
import br.com.vr.autenticador.application.execption.RegraDeNegocioException;
import br.com.vr.autenticador.application.infrastructure.security.encoder.Encoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CriacaoTransacaoUseCase {

    private final TransacaoRepository repository;
    private final CartaoRepository cartaoRepository;
    private final TransacaoPresentation presentation;
    private final Encoder encoder;

    public CriacaoTransacaoUseCase(TransacaoRepository repository, CartaoRepository cartaoRepository, TransacaoPresentation presentation, Encoder encoder) {
        this.repository = repository;
        this.cartaoRepository = cartaoRepository;
        this.presentation = presentation;
        this.encoder = encoder;
    }

    public Transacao criar(TransacaoRequest transacaoRequest) {
        var optionalCartao = cartaoRepository.findByNumero(transacaoRequest.getNumeroCartao());

        if (optionalCartao.isEmpty()) {
            throw new RegraDeNegocioException("Cartão não existe");
        }

        var cartao = optionalCartao.get();

        if (!cartao.getSenha().equals(encoder.encode(transacaoRequest.getSenha()))) {
            throw new RegraDeNegocioException("Senha inválida");
        }

        if (cartao.getSaldo().compareTo(transacaoRequest.getValor()) < 0) {
            throw new RegraDeNegocioException("Saldo insuficiente para transação");
        }

        var saldoAnterior = cartao.getSaldo();

        cartao.setSaldo(cartao.getSaldo().subtract(transacaoRequest.getValor()));
        cartaoRepository.atualizarSaldo(cartao);

        var transacao = presentation.convertRequestToEntity(transacaoRequest);

        try {
            transacao.setCartao(cartao);
            transacao.setTipo(TipoTransacao.CREDITO);
            repository.save(transacao);
        } catch (Exception ex) {
            cartao.setSaldo(saldoAnterior);
            cartaoRepository.atualizarSaldo(cartao);
            throw new ErroGenericoException("Ocorreu um erro ao salvar a transação");
        }

        return transacao;
    }
}
