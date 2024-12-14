package br.com.vr.autenticador.application.execption;

public class EntidadeNaoEncontradoException extends RuntimeException {

    public EntidadeNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

}
