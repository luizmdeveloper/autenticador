package br.com.vr.autenticador.api.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CartaoRequest implements Serializable {

    private String numeroCartao;
    private String senha;
}
