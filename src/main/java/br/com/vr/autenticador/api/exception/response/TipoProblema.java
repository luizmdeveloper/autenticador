package br.com.vr.autenticador.api.exception.response;

import lombok.Getter;

@Getter
public enum TipoProblema {

    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    DADOS_INVALIDOS("/erro-dados-invalidos", "Dados inválido");

    private String titulo;
    private String path;

    private TipoProblema(String path, String titulo) {
        this.path = "https://vr.com.br" + path;
        this.titulo = titulo;
    }
}
