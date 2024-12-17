package br.com.vr.autenticador.application.core.repository;

import br.com.vr.autenticador.application.core.entidade.Cartao;
import br.com.vr.autenticador.application.core.repository.impl.CartaoRepositoryImpl;
import br.com.vr.autenticador.application.infrastructure.repository.JpaCartaoRepository;
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
public class CartaoRepositoryTest {

    private CartaoRepository cartaoRepository;

    @Mock
    private JpaCartaoRepository jpaCartaoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.cartaoRepository = new CartaoRepositoryImpl(jpaCartaoRepository);
    }

    @Test
    public void testFindByNumerQuandoOcorrerSucesso() {
        Mockito.when(jpaCartaoRepository.findByNumero(Mockito.anyString())).thenReturn(Optional.of(CartaoHelper.criarCartao()));

        Optional<Cartao> optionalCartao = cartaoRepository.findByNumero("5364 8532 7186 2921");

        Assertions.assertNotNull(optionalCartao);
        Assertions.assertFalse(optionalCartao.isEmpty());
    }

    @Test
    public void testSaveCartaoComSucesso() {
        var cartao = CartaoHelper.criarCartao();

        cartaoRepository.save(cartao);

        Mockito.verify(jpaCartaoRepository, Mockito.times(1)).save(Mockito.any(Cartao.class));
    }

    @Test
    public void testAtualizarSaldoComSucesso() {
        var cartao = CartaoHelper.criarCartao();
        var novoSaldo = BigDecimal.valueOf(150);
        cartao.setSaldo(novoSaldo);

        cartaoRepository.atualizarSaldo(cartao);

        Mockito.verify(jpaCartaoRepository, Mockito.times(1)).save(Mockito.any(Cartao.class));
        Assertions.assertEquals(novoSaldo.doubleValue(), cartao.getSaldo().doubleValue(), 0.0001d);
    }
}
