package br.com.vr.autenticador.api.controller.transacoes;

import br.com.vr.autenticador.api.request.TransacaoRequest;
import br.com.vr.autenticador.application.core.usecase.CriacaoTransacaoUseCase;
import br.com.vr.autenticador.application.execption.RegraDeNegocioException;
import br.com.vr.autenticador.helper.TransacaoHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static br.com.vr.autenticador.helper.ConfigApiHelper.HEADER_AUTORIZATION_VALUE;

@SpringBootTest
@AutoConfigureMockMvc
public class TransacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CriacaoTransacaoUseCase criacaoTransacaoUseCase;

    private static final String URL_BASE_TRANSACOES = "/v1/transacoes";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriaTransacaoComSucesso() throws Exception {
        mockMvc.perform(
                post(URL_BASE_TRANSACOES)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(AUTHORIZATION, HEADER_AUTORIZATION_VALUE)
                    .content(objectMapper.writeValueAsString(TransacaoHelper.criarRequest()))
                ).andExpect(status().isCreated());
    }

    @Test
    public void testCriaTransacaoComNumeroCartaoInvalido() throws Exception {
        var transacaoRequest = new TransacaoRequest();
        mockMvc.perform(
                post(URL_BASE_TRANSACOES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, HEADER_AUTORIZATION_VALUE)
                        .content(objectMapper.writeValueAsString(transacaoRequest))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void testCriaTransacaoQuandoSenhaInvalida() throws Exception {
        var request = TransacaoHelper.criarRequest();

        given(criacaoTransacaoUseCase.criar(Mockito.any())).willThrow(RegraDeNegocioException.class);

        mockMvc.perform(
                post(URL_BASE_TRANSACOES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, HEADER_AUTORIZATION_VALUE)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isUnprocessableEntity());
    }
}
