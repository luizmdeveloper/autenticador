package br.com.vr.autenticador.application.core.repository.impl;

import br.com.vr.autenticador.application.core.entidade.Usuario;
import br.com.vr.autenticador.application.core.repository.UsuarioRepository;
import br.com.vr.autenticador.application.infrastructure.repository.JpaUsuarioRepository;

import java.util.Optional;

public class UsarioRepositoryImpl implements UsuarioRepository {

    private final JpaUsuarioRepository jpaUsuarioRepository;

    public UsarioRepositoryImpl(JpaUsuarioRepository jpaUsuarioRepository) {
        this.jpaUsuarioRepository = jpaUsuarioRepository;
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return jpaUsuarioRepository.findByEmail(email);
    }
}
