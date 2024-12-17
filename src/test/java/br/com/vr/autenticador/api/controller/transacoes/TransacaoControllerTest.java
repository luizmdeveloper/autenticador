package br.com.vr.autenticador.api.controller.transacoes;

import br.com.vr.autenticador.api.request.TransacaoRequest;
import br.com.vr.autenticador.application.core.usecase.CriacaoTransacaoUseCase;
import br.com.vr.autenticador.helper.TransacaoHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static br.com.vr.autenticador.helper.ConfigApiHelper.HEADER_AUTORIZATION;
import static br.com.vr.autenticador.helper.ConfigApiHelper.HEADER_AUTORIZATION_VALUE;

@WebMvcTest(TransacaoControllerTest.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class TransacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
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
                    .header(HEADER_AUTORIZATION, HEADER_AUTORIZATION_VALUE)
                    .content(objectMapper.writeValueAsString(TransacaoHelper.criarRequest()))
                ).andExpect(status().isCreated());
    }

    @Test
    public void testCriaTransacaoComNumeroCartaoInvalido() throws Exception {
        var transacaoRequest = new TransacaoRequest();
        mockMvc.perform(
                post(URL_BASE_TRANSACOES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HEADER_AUTORIZATION, HEADER_AUTORIZATION_VALUE)
                        .content(objectMapper.writeValueAsString(transacaoRequest))
        ).andExpect(status().isBadRequest());
    }
}
