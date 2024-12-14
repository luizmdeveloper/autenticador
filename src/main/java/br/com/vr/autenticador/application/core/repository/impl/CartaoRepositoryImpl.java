package br.com.vr.autenticador.application.core.repository.impl;

import br.com.vr.autenticador.application.core.entidade.Cartao;
import br.com.vr.autenticador.application.core.repository.CartaoRepository;

import java.util.Optional;

public class CartaoRepositoryImpl implements CartaoRepository {

    @Override
    public Optional<Cartao> findByNumero(String numero) {
        return Optional.empty();
    }

    @Override
    public void save(Cartao cartao) {

    }

    @Override
    public void atualizarSaldo(Cartao cartao) {

    }
}
