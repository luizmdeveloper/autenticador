package br.com.vr.autenticador.api.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CartaoResponse implements Serializable {

    private String numeroCartao;
    private String senha;
}
