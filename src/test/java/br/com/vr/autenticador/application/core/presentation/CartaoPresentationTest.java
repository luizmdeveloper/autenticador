package br.com.vr.autenticador.application.core.presentation;

import br.com.vr.autenticador.application.core.presetation.CartaoPresentation;
import br.com.vr.autenticador.application.core.presetation.impl.CartaoPresentationImpl;
import br.com.vr.autenticador.helper.CartaoHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CartaoPresentationTest {

    private CartaoPresentation cartaoPresentation;

    @BeforeEach
    public void setUp() {
        this.cartaoPresentation = new CartaoPresentationImpl();
    }

    @Test
    public void testConvertRequestToEntityComSucesso() {
        var request = CartaoHelper.criarRequest();

        var cartao = cartaoPresentation.convertRequestToEntity(request);

        Assertions.assertNotNull(cartao);
        Assertions.assertEquals(request.getNumeroCartao(), cartao.getNumero());
        Assertions.assertEquals(request.getSenha(), cartao.getSenha());
    }

    @Test
    public void testConvertRequestNullToEntityComSucesso() {
        Assertions.assertNull(cartaoPresentation.convertRequestToEntity(null));
    }

    @Test
    public void testConvertEntityToResponseComSucesso() {
        var cartao = CartaoHelper.criarCartao();

        var response = cartaoPresentation.convertEntityToResponse(cartao);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(cartao.getNumero(), response.getNumeroCartao());
        Assertions.assertEquals(cartao.getSenha(), response.getSenha());
    }

    @Test
    public void testConvertEntityNullToResponseComSucesso() {
        Assertions.assertNull(cartaoPresentation.convertEntityToResponse(null));
    }
}
