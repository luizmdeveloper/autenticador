package br.com.vr.autenticador.api.controller.cartoes;

import br.com.vr.autenticador.api.request.CartaoRequest;
import br.com.vr.autenticador.api.response.CartaoResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/cartoes")
public class CartoesController implements CartoesSwagger {

    @Override
    public CartaoResponse criar(CartaoRequest request) {
        return null;
    }

    @Override
    public BigDecimal obterSaldo(String numeroCartao) {
        return null;
    }
}
