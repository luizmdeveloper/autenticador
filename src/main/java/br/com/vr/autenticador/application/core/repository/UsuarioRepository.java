package br.com.vr.autenticador.application.core.repository;

import br.com.vr.autenticador.application.core.entidade.Usuario;

import java.util.Optional;

public interface UsuarioRepository {

    Optional<Usuario> findByEmail(String email);
}
