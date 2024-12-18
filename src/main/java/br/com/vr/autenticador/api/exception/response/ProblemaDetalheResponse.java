package br.com.vr.autenticador.api.exception.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProblemaDetalheResponse implements Serializable {

    private Integer status;
    private String tipo;
    private String titulo;
    private String detalhe;
    private LocalDateTime dataHora;
    private String mensagemUsuario;
    private List<PropriedadeResponse> propriedades;
}
