package br.com.vr.autenticador.application.infrastructure.security;

import br.com.vr.autenticador.application.core.entidade.Usuario;
import br.com.vr.autenticador.application.core.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(username);

        if (optionalUsuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não foi encontrado. Por favor, entre em contato com administrador do sistema");
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(optionalUsuario.get().getEmail())
                .password(optionalUsuario.get().getSenha())
                .roles("ADMIN")
                .build() ;
    }
}
