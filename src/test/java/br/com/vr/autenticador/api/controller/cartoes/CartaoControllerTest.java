package br.com.vr.autenticador.api.controller.cartoes;

import br.com.vr.autenticador.api.request.CartaoRequest;

import br.com.vr.autenticador.application.core.presetation.CartaoPresentation;
import br.com.vr.autenticador.application.core.usecase.ConsultaSaldoCartaoUseCase;
import br.com.vr.autenticador.application.core.usecase.CriacaoCartaoUseCase;
import br.com.vr.autenticador.application.execption.EntidadeNaoEncontradoException;
import br.com.vr.autenticador.application.execption.NumeroCartaoJaCadastradoException;
import br.com.vr.autenticador.helper.CartaoHelper;
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

import java.math.BigDecimal;

import static br.com.vr.autenticador.helper.ConfigApiHelper.HEADER_AUTORIZATION_VALUE;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CartaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CriacaoCartaoUseCase criacaoCartaoUseCase;

    @MockBean
    private CartaoPresentation cartaoPresentation;

    @MockBean
    private ConsultaSaldoCartaoUseCase consultaSaldoCartaoUseCase;

    private static final String URL_BASE_CARTOES = "/v1/cartoes";
    private static final String URL_BASE_CARTOES_CONSULTA_SALDO = "/v1/cartoes/1234/saldo";


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriaCartaoComSucesso() throws Exception {
        given(criacaoCartaoUseCase.save(Mockito.any())).willReturn(CartaoHelper.criarCartao());

        mockMvc.perform(
                post(URL_BASE_CARTOES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, HEADER_AUTORIZATION_VALUE)
                        .content(objectMapper.writeValueAsString(CartaoHelper.criarRequest()))
        ).andExpect(status().isCreated());
    }

    @Test
    public void testCriaCartaoComNumeroCartaoInvalido() throws Exception {
        var cartaoRequest = new CartaoRequest();

        mockMvc.perform(
                post(URL_BASE_CARTOES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartaoRequest))
                        .header(AUTHORIZATION, HEADER_AUTORIZATION_VALUE)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void testConsultaSaldoCartaoValido() throws Exception {
        given(consultaSaldoCartaoUseCase.consultar(Mockito.any())).willReturn(BigDecimal.TEN);

        mockMvc.perform(
                get(URL_BASE_CARTOES_CONSULTA_SALDO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, HEADER_AUTORIZATION_VALUE)
        ).andExpect(status().isOk());
    }

    @Test
    public void testCriaCartaoQuandoNumerCartaoJaCadastrado() throws Exception {
        given(criacaoCartaoUseCase.save(Mockito.any())).willThrow(NumeroCartaoJaCadastradoException.class);

        mockMvc.perform(
                post(URL_BASE_CARTOES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, HEADER_AUTORIZATION_VALUE)
                        .content(objectMapper.writeValueAsString(CartaoHelper.criarRequest()))
        ).andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testConsultaSaldoCartaoQuandoCartaoNaoEncontrado() throws Exception {
        given(consultaSaldoCartaoUseCase.consultar(Mockito.any())).willThrow(EntidadeNaoEncontradoException.class);

        mockMvc.perform(
                get(URL_BASE_CARTOES_CONSULTA_SALDO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, HEADER_AUTORIZATION_VALUE)
        ).andExpect(status().isNotFound());
    }
}
