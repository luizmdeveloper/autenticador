package br.com.vr.autenticador.api.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CartaoRequest implements Serializable {

    @NotBlank
    private String numeroCartao;

    @NotBlank
    private String senha;
}
