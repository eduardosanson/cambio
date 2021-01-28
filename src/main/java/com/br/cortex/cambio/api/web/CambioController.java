package com.br.cortex.cambio.api.web;

import com.br.cortex.cambio.api.web.response.CambioResponse;
import com.br.cortex.cambio.application.domain.model.Dinheiro;
import com.br.cortex.cambio.application.usecase.port.CambioPort;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("cambio")
public class CambioController {

    @Autowired
    private CambioPort cambio;

    private static final String allowableValues = "EUR, USD, AUD, CAD, CHF, DKK, GBP, JPY, NOK, SEK";

    @GetMapping("/v1/conversor")
    public ResponseEntity<CambioResponse> conversor(@RequestParam
                                                    @ApiParam(allowableValues = allowableValues)
                                                            String moedaOrigem,
                                                    @RequestParam
                                                    @ApiParam(allowableValues = allowableValues)
                                                            String moedaDestino,
                                                    @RequestParam BigDecimal valor,
                                                    @RequestParam @ApiParam(example = "27/01/2020") LocalDate data){
        var response = cambio.converter(moedaOrigem, moedaDestino, new Dinheiro(valor), data);

        return ResponseEntity.ok(new CambioResponse(response.getValorCompra(), response.getValorVenda()));
    }

}
