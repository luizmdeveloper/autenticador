package br.com.vr.autenticador.application.core.entidade;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
public class Cartao {

    private BigInteger id;
    private String numerCartao;
    private String senha;
    private BigDecimal saldo;

}
