package br.com.vr.autenticador.api.controller.transacoes;

import br.com.vr.autenticador.api.request.TransacaoRequest;
import br.com.vr.autenticador.api.response.TransacaoResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/transacoes")
public class TransacoesController implements TransacoesSwagger {

    @Override
    public TransacaoResponse criar(TransacaoRequest request) {
        return null;
    }

}
