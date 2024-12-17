package br.com.vr.autenticador.application.core.presentation;

import br.com.vr.autenticador.application.core.presetation.TransacaoPresentation;
import br.com.vr.autenticador.application.core.presetation.impl.TransacaoPresentationImpl;
import br.com.vr.autenticador.helper.TransacaoHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransacaoPresentationTest {

    private TransacaoPresentation transacaoPresentation;

    @BeforeEach
    public void setUp() {
        this.transacaoPresentation = new TransacaoPresentationImpl();
    }

    @Test
    public void testConvertRequestToEntityComSucesso() {
        var transacao = TransacaoHelper.criarRequest();

        var response = transacaoPresentation.convertRequestToEntity(transacao);

        Assertions.assertNotNull(response);
    }

}
