package br.com.vr.autenticador.api.exception.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PropriedadeResponse implements Serializable {

    private String nome;
    private String mensagemUsuario;
}
