package br.com.vr.autenticador.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(name = "numeroCartao", example = "5364 8532 7186 2923", requiredMode = Schema.RequiredMode.REQUIRED)
    private String numeroCartao;

    @NotBlank
    @Schema(name = "senha", example = "1234", requiredMode = Schema.RequiredMode.REQUIRED)
    private String senha;

    @NotNull
    @DecimalMin(value = "0.01")
    @Schema(name = "valor", example = "0.1", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal valor;
}
