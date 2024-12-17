package br.com.vr.autenticador.application.core.usecase;

import br.com.vr.autenticador.api.request.CartaoRequest;
import br.com.vr.autenticador.application.core.entidade.Cartao;
import br.com.vr.autenticador.application.core.presetation.CartaoPresentation;
import br.com.vr.autenticador.application.core.repository.CartaoRepository;
import br.com.vr.autenticador.application.execption.NumeroCartaoJaCadastradoException;
import br.com.vr.autenticador.application.infrastructure.security.encoder.Encoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

public class CriacaoCartaoUseCase {

    private final CartaoRepository repository;
    private final CartaoPresentation presentation;
    private final Encoder encoder;

    public CriacaoCartaoUseCase(CartaoRepository repository, CartaoPresentation presentation, Encoder encoder) {
        this.repository = repository;
        this.presentation = presentation;
        this.encoder = encoder;
    }

    public Cartao save(CartaoRequest cartaoRequest) {
        var optionalCartao = repository.findByNumero(cartaoRequest.getNumeroCartao());

        if (optionalCartao.isPresent()) {
            throw new NumeroCartaoJaCadastradoException("Existe um cartão com mesmo número");
        }

        var cartao = presentation.convertRequestToEntity(cartaoRequest);
        cartao.setSenha(encoder.encode(cartaoRequest.getSenha()));
        cartao.setSaldo(BigDecimal.valueOf(500));
        repository.save(cartao);

        return cartao;
    }
}
