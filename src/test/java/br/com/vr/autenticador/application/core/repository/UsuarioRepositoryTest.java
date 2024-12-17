package br.com.vr.autenticador.application.core.repository;

import br.com.vr.autenticador.application.core.entidade.Usuario;
import br.com.vr.autenticador.application.core.repository.impl.UsarioRepositoryImpl;
import br.com.vr.autenticador.application.infrastructure.repository.JpaUsuarioRepository;
import br.com.vr.autenticador.helper.UsuarioHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UsuarioRepositoryTest {

    private UsuarioRepository usuarioRepository;

    @Mock
    private JpaUsuarioRepository jpaUsuarioRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.usuarioRepository = new UsarioRepositoryImpl(jpaUsuarioRepository);
    }

    @Test
    public void testFindByNumerQuandoOcorrerSucesso() {
        Mockito.when(jpaUsuarioRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(UsuarioHelper.criarUsuario()));

        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail("luiz.cavalcante@vr.com.br");

        Assertions.assertNotNull(optionalUsuario);
        Assertions.assertFalse(optionalUsuario.isEmpty());
    }
}
