package br.com.vr.autenticador.api.controller.transacoes;

import br.com.vr.autenticador.api.request.TransacaoRequest;
import br.com.vr.autenticador.api.response.TransacaoResponse;
import br.com.vr.autenticador.application.core.usecase.CriacaoTransacaoUseCase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/transacoes")
public class TransacoesController implements TransacoesSwagger {

    private final CriacaoTransacaoUseCase criacaoTransacaoUseCase;

    public TransacoesController(CriacaoTransacaoUseCase criacaoTransacaoUseCase) {
        this.criacaoTransacaoUseCase = criacaoTransacaoUseCase;
    }


    @Override
    public TransacaoResponse criar(TransacaoRequest request) {
        var transacao = criacaoTransacaoUseCase.criar(request);
        var response = new TransacaoResponse();
        response.setStatus("");
        return response;
    }

}
