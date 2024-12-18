package br.com.vr.autenticador.api.controller.cartoes;

import br.com.vr.autenticador.api.exception.response.ProblemaDetalheResponse;
import br.com.vr.autenticador.api.request.CartaoRequest;
import br.com.vr.autenticador.api.response.CartaoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;

@Tag(name = "Cartões", description = "operções cartão")
public interface CartoesSwagger {

    @Operation(
            summary = "Salvar cartão",
            description = "Criar um novo cartão com um valor padrão",
            tags = { "Cartões" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = CartaoResponse.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = ProblemaDetalheResponse.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "422", content = {
                    @Content(schema = @Schema(implementation = ProblemaDetalheResponse.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "401")
        }
    )
    @SecurityRequirement(name = "BasicAuth")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    CartaoResponse criar(@RequestBody @Validated CartaoRequest request);

    @Operation(
            summary = "Verifca saldo do cartão",
            description = "Consulta o saldo do cartão",
            tags = { "Cartões" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = BigDecimal.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = ProblemaDetalheResponse.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "401")
        }
    )
    @SecurityRequirement(name = "BasicAuth")
    @GetMapping("{numeroCartao}/saldo")
    BigDecimal obterSaldo(@PathVariable String numeroCartao);

}
