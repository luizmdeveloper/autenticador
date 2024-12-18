package br.com.vr.autenticador.api.controller.transacoes;

import br.com.vr.autenticador.api.exception.response.ProblemaDetalheResponse;
import br.com.vr.autenticador.api.request.TransacaoRequest;
import br.com.vr.autenticador.api.response.CartaoResponse;
import br.com.vr.autenticador.api.response.TransacaoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Transações", description = "operções transação")
public interface TransacoesSwagger {

    @Operation(
            summary = "Salvar transação",
            description = "Cria uma transação",
            tags = { "Transações" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = TransacaoResponse.class), mediaType = "application/json")
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
    TransacaoResponse criar(@RequestBody @Validated TransacaoRequest request);
}
