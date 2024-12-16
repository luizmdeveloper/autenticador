package br.com.vr.autenticador.api.exception;

import br.com.vr.autenticador.api.exception.response.ProblemaDetalheResponse;
import br.com.vr.autenticador.api.exception.response.PropriedadeResponse;
import br.com.vr.autenticador.api.exception.response.TipoProblema;
import br.com.vr.autenticador.application.execption.EntidadeNaoEncontradoException;
import br.com.vr.autenticador.application.execption.ErroGenericoException;
import br.com.vr.autenticador.application.execption.NumeroCartaoJaCadastradoException;
import br.com.vr.autenticador.application.execption.RegraDeNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class MiniAutorizadorGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var detalhe = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

        List<PropriedadeResponse> propriedades = ex.getFieldErrors().stream()
                .map(FieldError ->  {
                    var prorideade = new PropriedadeResponse();
                    prorideade.setNome(FieldError.getField());
                    prorideade.setMensagemUsuario(messageSource.getMessage(FieldError, LocaleContextHolder.getLocale()));
                    return prorideade;
                })
                .collect(Collectors.toList());

        var problema = criarProblema(TipoProblema.DADOS_INVALIDOS, status, detalhe);
        problema.setMensagemUsuario(detalhe);
        problema.setPropriedades(propriedades);

        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    @ExceptionHandler({NumeroCartaoJaCadastradoException.class})
    public ResponseEntity<Object> handleNumeroCartaoJaCadastradoException(NumeroCartaoJaCadastradoException ex, WebRequest request){
        HttpStatusCode status = HttpStatusCode.valueOf(422);
        var problema = criarProblema(TipoProblema.ERRO_NEGOCIO, status, ex.getMessage());
        problema.setMensagemUsuario(ex.getMessage());
        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler({EntidadeNaoEncontradoException.class})
    public ResponseEntity<Object> handleEntidadeNaoEncontradoException(EntidadeNaoEncontradoException ex, WebRequest request){
        HttpStatusCode status = HttpStatusCode.valueOf(404);
        var problema = criarProblema(TipoProblema.ENTIDADE_NAO_ENCONTRADA, status, ex.getMessage());
        problema.setMensagemUsuario(ex.getMessage());
        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler({RegraDeNegocioException.class})
    public ResponseEntity<Object> handleRegraDeNegocioException(RegraDeNegocioException ex, WebRequest request){
        HttpStatusCode status = HttpStatusCode.valueOf(422);
        var problema = criarProblema(TipoProblema.ERRO_NEGOCIO, status, ex.getMessage());
        problema.setMensagemUsuario(ex.getMessage());
        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler({ErroGenericoException.class})
    public ResponseEntity<Object> handleErroGenericoException(ErroGenericoException ex, WebRequest request){
        HttpStatusCode status = HttpStatusCode.valueOf(422);
        var problema = criarProblema(TipoProblema.ERRO_NEGOCIO, status, ex.getMessage());
        problema.setMensagemUsuario(ex.getMessage());
        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex, WebRequest request){
        HttpStatusCode status = HttpStatusCode.valueOf(401);
        var problema = criarProblema(TipoProblema.ERRO_NAO_AUTORIZADO, status, ex.getMessage());
        problema.setMensagemUsuario(ex.getMessage());
        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        var problema = new ProblemaDetalheResponse();
        if (body instanceof String) {
            problema.setStatus(statusCode.value());
            problema.setDetalhe((String) body);
            problema.setDataHora(LocalDateTime.now());
            body = problema;
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private ProblemaDetalheResponse criarProblema(TipoProblema tipoProblema, HttpStatusCode status, String detalhe) {
        var problema = new ProblemaDetalheResponse();
        problema.setStatus(status.value());
        problema.setTipo(tipoProblema.getPath());
        problema.setTitulo(tipoProblema.getTitulo());
        problema.setDetalhe(detalhe);
        problema.setDataHora(LocalDateTime.now());
        return problema;
    }
}
