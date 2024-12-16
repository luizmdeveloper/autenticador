package br.com.vr.autenticador.api.controller.cartoes;

import br.com.vr.autenticador.api.request.CartaoRequest;
import br.com.vr.autenticador.api.response.CartaoResponse;
import br.com.vr.autenticador.application.core.presetation.CartaoPresentation;
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
    private final CartaoPresentation presentation;

    public CartoesController(CriacaoCartaoUseCase criacaoCartaoUseCase, ConsultaSaldoCartaoUseCase consultaSaldoCartaoUseCase, CartaoPresentation presentation) {
        this.criacaoCartaoUseCase = criacaoCartaoUseCase;
        this.consultaSaldoCartaoUseCase = consultaSaldoCartaoUseCase;
        this.presentation = presentation;
    }

    @Override
    public CartaoResponse criar(CartaoRequest request) {
        return presentation.convertEntityToResponse(criacaoCartaoUseCase.save(request));
    }

    @Override
    public BigDecimal obterSaldo(String numeroCartao) {
        return consultaSaldoCartaoUseCase.consultar(numeroCartao);
    }
}
