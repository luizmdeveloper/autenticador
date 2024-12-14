package br.com.vr.autenticador.application.execption;

public class NumeroCartaoJaCadastradoException extends RuntimeException {

    public NumeroCartaoJaCadastradoException(String mensagem) {
        super(mensagem);
    }
}
