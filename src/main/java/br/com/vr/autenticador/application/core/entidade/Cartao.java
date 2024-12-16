package br.com.vr.autenticador.application.core.entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "cartoes")
public class Cartao {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String numero;

    @NotBlank
    private String senha;

    @NotNull
    private BigDecimal saldo;
}
