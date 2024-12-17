package br.com.vr.autenticador.application.core.usecase;

import br.com.vr.autenticador.application.core.repository.CartaoRepository;
import br.com.vr.autenticador.application.execption.EntidadeNaoEncontradoException;
import br.com.vr.autenticador.helper.CartaoHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
public class ConsultaSaldoCartaoUseCaseTest {

    private ConsultaSaldoCartaoUseCase consultaSaldoCartaoUseCase;

    @Mock
    private CartaoRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.consultaSaldoCartaoUseCase = new ConsultaSaldoCartaoUseCase(repository);
    }

    @Test
    public void testConsultarSaldoCartaoExistenteComSucesso() {
        var cartao = CartaoHelper.criarCartao();

        Mockito.when(repository.findByNumero(Mockito.any())).thenReturn(Optional.of(cartao));

        var response = consultaSaldoCartaoUseCase.consultar("5364 8532 7186 2921");

        Assertions.assertNotNull(response);
        Assertions.assertEquals(BigDecimal.TEN.doubleValue(), cartao.getSaldo().doubleValue(), 0.0001d);
    }

    @Test
    public void testConsultarSaldoCartaoInexistente() {

        Mockito.when(repository.findByNumero(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(EntidadeNaoEncontradoException.class, () -> consultaSaldoCartaoUseCase.consultar("5364 8532 7186 2921"));
    }
}
