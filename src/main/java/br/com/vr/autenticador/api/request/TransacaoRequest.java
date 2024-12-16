package br.com.vr.autenticador.api.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class TransacaoRequest implements Serializable {

    @NotBlank
    private String numeroCartao;

    @NotBlank
    private String senha;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal valor;
}
