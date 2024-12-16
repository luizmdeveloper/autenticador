package br.com.vr.autenticador.api.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TransacaoResponse implements Serializable {

    private String status;

}
