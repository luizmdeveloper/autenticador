package br.com.vr.autenticador.application.core.entidade;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class Transacao {

    private Long id;
    private Cartao cartao;
    private OffsetDateTime date;
    private TipoTransacao tipo;
    private BigDecimal valor;
}
