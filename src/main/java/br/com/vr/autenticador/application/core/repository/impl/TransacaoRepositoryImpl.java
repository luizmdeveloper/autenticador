package br.com.vr.autenticador.application.core.repository.impl;

import br.com.vr.autenticador.application.core.entidade.Transacao;
import br.com.vr.autenticador.application.core.repository.TransacaoRepository;
import br.com.vr.autenticador.application.infrastructure.repository.JpaTransacaoRepository;

public class TransacaoRepositoryImpl implements TransacaoRepository {

    private final JpaTransacaoRepository jpaTransacaoRepository;

    public TransacaoRepositoryImpl(JpaTransacaoRepository jpaTransacaoRepository) {
        this.jpaTransacaoRepository = jpaTransacaoRepository;
    }

    @Override
    public void save(Transacao transacao) {
        jpaTransacaoRepository.save(transacao);
    }

}
