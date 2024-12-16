package br.com.vr.autenticador.application.infrastructure.repository;

import br.com.vr.autenticador.application.core.entidade.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaCartaoRepository extends JpaRepository<Cartao, Long> {

    Optional<Cartao> findByNumero(String numero);
}
