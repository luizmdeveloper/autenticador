package br.com.vr.autenticador.api.controller.transacoes;

import br.com.vr.autenticador.api.request.TransacaoRequest;
import br.com.vr.autenticador.api.response.TransacaoResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface TransacoesSwagger {

    @PostMapping
    TransacaoResponse criar(@RequestBody TransacaoRequest request);
}
