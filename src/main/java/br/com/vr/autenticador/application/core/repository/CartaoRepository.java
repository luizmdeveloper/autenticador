package br.com.vr.autenticador.application.core.repository;

import br.com.vr.autenticador.application.core.entidade.Cartao;

import java.util.Optional;

public interface CartaoRepository {

    Optional<Cartao> findByNumero(String numero);

    void save(Cartao cartao);

    void atualizarSaldo(Cartao cartao);
}
