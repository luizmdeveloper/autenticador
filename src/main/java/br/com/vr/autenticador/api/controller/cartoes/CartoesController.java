package br.com.vr.autenticador.api.controller.cartoes;

import br.com.vr.autenticador.api.request.CartaoRequest;
import br.com.vr.autenticador.api.response.CartaoResponse;
import br.com.vr.autenticador.application.core.usecase.ConsultaSaldoCartaoUseCase;
import br.com.vr.autenticador.application.core.usecase.CriacaoCartaoUseCase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/cartoes")
public class CartoesController implements CartoesSwagger {

    private final CriacaoCartaoUseCase criacaoCartaoUseCase;
    private final ConsultaSaldoCartaoUseCase consultaSaldoCartaoUseCase;

    public CartoesController(CriacaoCartaoUseCase criacaoCartaoUseCase, ConsultaSaldoCartaoUseCase consultaSaldoCartaoUseCase) {
        this.criacaoCartaoUseCase = criacaoCartaoUseCase;
        this.consultaSaldoCartaoUseCase = consultaSaldoCartaoUseCase;
    }

    @Override
    public CartaoResponse criar(CartaoRequest request) {
        var cartao = criacaoCartaoUseCase.save(request);
        var response = new CartaoResponse();
        response.setNumeroCartao(cartao.getNumero());
        response.setSenha(cartao.getSenha());
        return response;
    }

    @Override
    public BigDecimal obterSaldo(String numeroCartao) {
        return consultaSaldoCartaoUseCase.consultar(numeroCartao);
    }
}
