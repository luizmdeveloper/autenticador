package br.com.vr.autenticador.api.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class TransacaoRequest implements Serializable {

    private String numeroCartao;
    private String senha;
    private BigDecimal valor;
}
