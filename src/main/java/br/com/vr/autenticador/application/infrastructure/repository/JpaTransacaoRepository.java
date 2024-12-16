package br.com.vr.autenticador.application.infrastructure.repository;

import br.com.vr.autenticador.application.core.entidade.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTransacaoRepository extends JpaRepository<Transacao, Long> {
}
