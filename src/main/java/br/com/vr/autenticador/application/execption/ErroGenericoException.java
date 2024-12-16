package br.com.vr.autenticador.application.execption;

public class ErroGenericoException extends RuntimeException {

    public ErroGenericoException(String mensagem) {
        super(mensagem);
    }
}
