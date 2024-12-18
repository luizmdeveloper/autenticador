package br.com.vr.autenticador.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CartaoRequest implements Serializable {

    @NotBlank
    @Schema(name = "numeroCartao", example = "5364 8532 7186 2923", requiredMode = Schema.RequiredMode.REQUIRED)
    private String numeroCartao;

    @NotBlank
    @Schema(name = "senha", example = "1234", requiredMode = Schema.RequiredMode.REQUIRED)
    private String senha;
}
