package br.com.vr.autenticador.api.controller.cartoes;

import br.com.vr.autenticador.api.request.CartaoRequest;

import br.com.vr.autenticador.application.core.entidade.Cartao;
import br.com.vr.autenticador.application.core.presetation.CartaoPresentation;
import br.com.vr.autenticador.application.core.usecase.ConsultaSaldoCartaoUseCase;
import br.com.vr.autenticador.application.core.usecase.CriacaoCartaoUseCase;
import br.com.vr.autenticador.application.infrastructure.repository.JpaCartaoRepository;
import br.com.vr.autenticador.helper.CartaoHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartoesController.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class CartaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CriacaoCartaoUseCase criacaoCartaoUseCase;

    @MockitoBean
    private CartaoPresentation cartaoPresentation;

    @MockitoBean
    private ConsultaSaldoCartaoUseCase consultaSaldoCartaoUseCase;

    private static final String URL_BASE_CARTOES = "/v1/cartoes";
    private static final String URL_BASE_CARTOES_CONSULTA_SALDO = "/v1/cartoes/1234/saldo";
    private static final String HEADER_AUTORIZATION = "Authorization";
    private static final String HEADER_AUTORIZATION_VALUE = "Basic bHVpei5jYXZhbGNhbnRlQHZyLmNvbS5icjpAZG1pbmlzdHJhZG9y";

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
                        .header(HEADER_AUTORIZATION, HEADER_AUTORIZATION_VALUE)
                        .content(objectMapper.writeValueAsString(CartaoHelper.criarRequest()))
        ).andExpect(status().isCreated());
    }

    @Test
    public void testCriaCartaoComNumeroCartaoInvalido() throws Exception {
        var cartaoRequest = new CartaoRequest();

        mockMvc.perform(
                post(URL_BASE_CARTOES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HEADER_AUTORIZATION, HEADER_AUTORIZATION_VALUE)
                        .content(objectMapper.writeValueAsString(cartaoRequest))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void testConsultaSaldoCartaoValido() throws Exception {
        given(consultaSaldoCartaoUseCase.consultar(Mockito.any())).willReturn(BigDecimal.TEN);

        mockMvc.perform(
                get(URL_BASE_CARTOES_CONSULTA_SALDO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HEADER_AUTORIZATION, HEADER_AUTORIZATION_VALUE)
        ).andExpect(status().isOk());
    }
}
