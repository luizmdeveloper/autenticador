package br.com.vr.autenticador.application.infrastructure.config;

import br.com.vr.autenticador.application.core.presetation.CartaoPresentation;
import br.com.vr.autenticador.application.core.presetation.TransacaoPresentation;
import br.com.vr.autenticador.application.core.presetation.impl.CartaoPresentationImpl;
import br.com.vr.autenticador.application.core.presetation.impl.TransacaoPresentationImpl;
import br.com.vr.autenticador.application.core.repository.CartaoRepository;
import br.com.vr.autenticador.application.core.repository.TransacaoRepository;
import br.com.vr.autenticador.application.core.repository.UsuarioRepository;
import br.com.vr.autenticador.application.core.repository.impl.CartaoRepositoryImpl;
import br.com.vr.autenticador.application.core.repository.impl.TransacaoRepositoryImpl;
import br.com.vr.autenticador.application.core.repository.impl.UsarioRepositoryImpl;
import br.com.vr.autenticador.application.core.usecase.ConsultaSaldoCartaoUseCase;
import br.com.vr.autenticador.application.core.usecase.CriacaoCartaoUseCase;
import br.com.vr.autenticador.application.core.usecase.CriacaoTransacaoUseCase;
import br.com.vr.autenticador.application.infrastructure.repository.JpaCartaoRepository;
import br.com.vr.autenticador.application.infrastructure.repository.JpaTransacaoRepository;
import br.com.vr.autenticador.application.infrastructure.repository.JpaUsuarioRepository;
import br.com.vr.autenticador.application.infrastructure.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AutenticadorResolveDependency {

    @Autowired
    private JpaCartaoRepository jpaCartaoRepository;

    @Autowired
    private JpaTransacaoRepository jpaTransacaoRepository;

    @Autowired
    private JpaUsuarioRepository jpaUsuarioRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CartaoPresentation cartaoPresentation() {
        return new CartaoPresentationImpl();
    }

    @Bean
    public TransacaoPresentation transacaoPresentation() {
        return new TransacaoPresentationImpl();
    }

    @Bean
    public CartaoRepository cartaoRepository() {
        return new CartaoRepositoryImpl(jpaCartaoRepository);
    }

    @Bean
    public TransacaoRepository transacaoRepository() {
        return new TransacaoRepositoryImpl(jpaTransacaoRepository);
    }

    @Bean
    public UsuarioRepository usuarioRepository() {
        return new UsarioRepositoryImpl(jpaUsuarioRepository);
    }

    @Bean
    public CriacaoCartaoUseCase criacaoCartaoUseCase() {
        return new CriacaoCartaoUseCase(cartaoRepository(), cartaoPresentation());
    }

    @Bean
    public CriacaoTransacaoUseCase criacaoTransacaoUseCase() {
        return new CriacaoTransacaoUseCase(transacaoRepository(), cartaoRepository(), transacaoPresentation());
    }

    @Bean
    public ConsultaSaldoCartaoUseCase consultaSaldoCartaoUseCase() {
        return new ConsultaSaldoCartaoUseCase(cartaoRepository());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(usuarioRepository());
    }
}
