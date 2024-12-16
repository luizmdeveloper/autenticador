package br.com.vr.autenticador.application.core.repository.impl;

import br.com.vr.autenticador.application.core.entidade.Cartao;
import br.com.vr.autenticador.application.core.repository.CartaoRepository;
import br.com.vr.autenticador.application.infrastructure.repository.JpaCartaoRepository;

import java.util.Optional;

public class CartaoRepositoryImpl implements CartaoRepository {

    private final JpaCartaoRepository jpaCartaoRepository;

    public CartaoRepositoryImpl(JpaCartaoRepository jpaCartaoRepository) {
        this.jpaCartaoRepository = jpaCartaoRepository;
    }

    @Override
    public Optional<Cartao> findByNumero(String numero) {
        return jpaCartaoRepository.findByNumero(numero);
    }

    @Override
    public void save(Cartao cartao) {
        jpaCartaoRepository.save(cartao);
    }

    @Override
    public void atualizarSaldo(Cartao cartao) {
        jpaCartaoRepository.save(cartao);
    }
}
