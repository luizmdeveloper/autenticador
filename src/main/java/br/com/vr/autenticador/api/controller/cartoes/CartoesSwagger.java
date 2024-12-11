package br.com.vr.autenticador.api.controller.cartoes;

import br.com.vr.autenticador.api.request.CartaoRequest;
import br.com.vr.autenticador.api.response.CartaoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;

public interface CartoesSwagger {

    @PostMapping
    CartaoResponse criar(@RequestBody CartaoRequest request);

    @GetMapping("/{numero}")
    BigDecimal obterSaldo(@PathVariable String numero);

}
