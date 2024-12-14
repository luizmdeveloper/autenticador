package br.com.vr.autenticador.api.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MiniAutorizadorGlobalExceptionHandler extends ResponseEntityExceptionHandler {
}
