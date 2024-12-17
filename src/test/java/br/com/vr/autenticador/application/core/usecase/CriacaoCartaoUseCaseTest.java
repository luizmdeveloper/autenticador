package br.com.vr.autenticador.application.core.usecase;

import br.com.vr.autenticador.application.core.presetation.CartaoPresentation;
import br.com.vr.autenticador.application.core.repository.CartaoRepository;
import br.com.vr.autenticador.application.execption.NumeroCartaoJaCadastradoException;
import br.com.vr.autenticador.application.infrastructure.security.encoder.Encoder;
import br.com.vr.autenticador.helper.CartaoHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
public class CriacaoCartaoUseCaseTest {

    private CriacaoCartaoUseCase criacaoCartaoUseCase;

    @Mock
    private CartaoRepository repository;

    @Mock
    private CartaoPresentation presentation;

    @Mock
    private Encoder encoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.criacaoCartaoUseCase = new CriacaoCartaoUseCase(repository, presentation, encoder);
    }

    @Test
    public void testCriacaoCartaoComSucesso() {
        var request = CartaoHelper.criarRequest();

        Mockito.when(repository.findByNumero(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(presentation.convertRequestToEntity(Mockito.any())).thenReturn(CartaoHelper.criarCartao());

        var cartao = criacaoCartaoUseCase.save(request);

        Assertions.assertNotNull(cartao);
        Assertions.assertNotNull(cartao.getSaldo());
        Assertions.assertEquals(BigDecimal.valueOf(500).doubleValue(), cartao.getSaldo().doubleValue(), 0.0001d);
    }


    @Test
    public void testCriacaoCartaoComNumeroCartaoJaExistente() {
        var request = CartaoHelper.criarRequest();

        Mockito.when(repository.findByNumero(Mockito.anyString())).thenReturn(Optional.of(CartaoHelper.criarCartao()));

        Assertions.assertThrows(NumeroCartaoJaCadastradoException.class, () -> criacaoCartaoUseCase.save(request));
    }
}