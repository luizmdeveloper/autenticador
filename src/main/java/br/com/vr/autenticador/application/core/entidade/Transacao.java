package br.com.vr.autenticador.application.core.entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "transacoes")
public class Transacao {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cartao_id")
    @NotNull
    private Cartao cartao;

    @NotNull
    private OffsetDateTime date;

    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private TipoTransacao tipo;

    @NotNull
    private BigDecimal valor;
}
