package br.com.vr.autenticador.application.core.usecase;

import br.com.vr.autenticador.application.core.entidade.Cartao;
import br.com.vr.autenticador.application.core.entidade.Transacao;
import br.com.vr.autenticador.application.core.presetation.TransacaoPresentation;
import br.com.vr.autenticador.application.core.repository.CartaoRepository;
import br.com.vr.autenticador.application.core.repository.TransacaoRepository;
import br.com.vr.autenticador.application.execption.ErroGenericoException;
import br.com.vr.autenticador.application.execption.RegraDeNegocioException;
import br.com.vr.autenticador.application.infrastructure.security.encoder.Encoder;
import br.com.vr.autenticador.helper.CartaoHelper;
import br.com.vr.autenticador.helper.TransacaoHelper;
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
public class CriacaoTransacaoUseCaseTest {

    private CriacaoTransacaoUseCase criacaoTransacaoUseCase;

    @Mock
    private TransacaoRepository repository;

    @Mock
    private CartaoRepository cartaoRepository;

    @Mock
    private TransacaoPresentation presentation;

    @Mock
    private Encoder encoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.criacaoTransacaoUseCase = new CriacaoTransacaoUseCase(repository, cartaoRepository, presentation, encoder);
    }

    @Test
    public void testCriaTransacaoComSucesso() {
        var transacaoRequest = TransacaoHelper.criarRequest();
        transacaoRequest.setSenha("3214");
        var cartao = new Cartao();
        cartao.setNumero("5364 8532 7186 2921");
        cartao.setSenha("1EF3F6149E99629350145BFF101BFCEF56A55432DCB23AFFCDC47FA77E91AB40");
        cartao.setSaldo(BigDecimal.valueOf(500));
        var saldoFinal = cartao.getSaldo().subtract(transacaoRequest.getValor());

        Mockito.when(cartaoRepository.findByNumero(Mockito.any())).thenReturn(Optional.of(cartao));
        Mockito.when(presentation.convertRequestToEntity(Mockito.any())).thenReturn(TransacaoHelper.criarTransacao());
        Mockito.when(encoder.encode(Mockito.anyString())).thenReturn("1EF3F6149E99629350145BFF101BFCEF56A55432DCB23AFFCDC47FA77E91AB40");

        var response = criacaoTransacaoUseCase.criar(transacaoRequest);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(saldoFinal.doubleValue(), cartao.getSaldo().doubleValue(), 0.0001d);
        Mockito.verify(cartaoRepository, Mockito.times(1)).atualizarSaldo(Mockito.any(Cartao.class));
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(Transacao.class));
    }

    @Test
    public void testCriaTransacaoCartaoInexistente() {
        var transacaoRequest = TransacaoHelper.criarRequest();
        var cartao = CartaoHelper.criarCartao();
        cartao.setSaldo(BigDecimal.valueOf(500));

        Mockito.when(cartaoRepository.findByNumero(Mockito.anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(RegraDeNegocioException.class, () -> criacaoTransacaoUseCase.criar(transacaoRequest));
    }

    @Test
    public void testCriaTransacaoComSenhaInvalida() {
        var transacaoRequest = TransacaoHelper.criarRequest();
        var cartao = CartaoHelper.criarCartao();
        cartao.setSaldo(BigDecimal.valueOf(500));
        cartao.setSenha("senhaErrada");

        Mockito.when(cartaoRepository.findByNumero(Mockito.anyString())).thenReturn(Optional.of(cartao));

        Assertions.assertThrows(RegraDeNegocioException.class, () -> criacaoTransacaoUseCase.criar(transacaoRequest));
    }

    @Test
    public void testCriaTransacaoComSaldoInsuficiente() {
        var transacaoRequest = TransacaoHelper.criarRequest();
        var cartao = CartaoHelper.criarCartao();
        cartao.setSaldo(BigDecimal.ZERO);

        Mockito.when(cartaoRepository.findByNumero(Mockito.anyString())).thenReturn(Optional.of(cartao));

        Assertions.assertThrows(RegraDeNegocioException.class, () -> criacaoTransacaoUseCase.criar(transacaoRequest));
    }

    @Test
    public void testCriaTransacaoErroAoInserirRegistro() {

        var transacaoRequest = TransacaoHelper.criarRequest();
        transacaoRequest.setSenha("1234");
        var cartao = new Cartao();
        cartao.setNumero("5364 8532 7186 2921");
        cartao.setSenha("$2a$10$BIkzoGgSDCWMWZdxeFMOEutUXUJNOuFmLA7ETdh4mWMCB8.kb2bzm");
        cartao.setSaldo(BigDecimal.valueOf(500));
        var saldoFinal = cartao.getSaldo();

        Mockito.when(cartaoRepository.findByNumero(Mockito.any())).thenReturn(Optional.of(cartao));
        Mockito.when(presentation.convertRequestToEntity(Mockito.any())).thenReturn(TransacaoHelper.criarTransacao());
        Mockito.when(encoder.encode(Mockito.anyString())).thenReturn("$2a$10$BIkzoGgSDCWMWZdxeFMOEutUXUJNOuFmLA7ETdh4mWMCB8.kb2bzm");
        Mockito.doThrow(new RuntimeException()).when(repository).save(Mockito.any(Transacao.class));

        Assertions.assertThrows(ErroGenericoException.class, () -> criacaoTransacaoUseCase.criar(transacaoRequest));
        Mockito.verify(cartaoRepository, Mockito.times(2)).atualizarSaldo(Mockito.any(Cartao.class));
    }
}
