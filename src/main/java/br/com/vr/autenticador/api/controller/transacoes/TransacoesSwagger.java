package br.com.vr.autenticador.api.controller.transacoes;

import br.com.vr.autenticador.api.request.TransacaoRequest;
import br.com.vr.autenticador.api.response.TransacaoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface TransacoesSwagger {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    TransacaoResponse criar(@RequestBody @Validated TransacaoRequest request);
}
