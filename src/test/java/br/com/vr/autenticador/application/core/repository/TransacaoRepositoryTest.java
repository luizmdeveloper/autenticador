package br.com.vr.autenticador.application.core.repository;

import br.com.vr.autenticador.application.core.entidade.Transacao;
import br.com.vr.autenticador.application.core.repository.impl.TransacaoRepositoryImpl;
import br.com.vr.autenticador.application.infrastructure.repository.JpaTransacaoRepository;
import br.com.vr.autenticador.helper.TransacaoHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransacaoRepositoryTest {

    private TransacaoRepository transacaoRepository;

    @Mock
    private JpaTransacaoRepository jpaTransacaoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.transacaoRepository = new TransacaoRepositoryImpl(jpaTransacaoRepository);
    }

    @Test
    public void testSaveComSucesso() {
        var transacao = TransacaoHelper.criarTransacao();

        transacaoRepository.save(transacao);

        Mockito.verify(jpaTransacaoRepository, Mockito.times(1)).save(Mockito.any(Transacao.class));
    }
}
