package br.com.vr.autenticador.application.core.entidade;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Cartao {

    private Long id;
    private String numero;
    private String senha;
    private BigDecimal saldo;

}
