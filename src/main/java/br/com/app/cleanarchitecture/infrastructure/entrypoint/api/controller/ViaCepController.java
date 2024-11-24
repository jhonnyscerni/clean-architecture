package br.com.app.cleanarchitecture.infrastructure.entrypoint.api.controller;

import br.com.app.cleanarchitecture.application.dto.response.ViaCepResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/viacep")
@Tag(name = "ViaCep Management", description = "Operations related to ViaCep management")
public interface ViaCepController {

    @GetMapping("/{cep}")
    @Operation(summary = "Get CEP info", description = "Returns information about a given CEP")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ViaCepResponse.class)))
    ResponseEntity<ViaCepResponse> getCepInfo(@PathVariable String cep);
}
