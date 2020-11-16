package com.br.cortex.cambio.api.web;

import com.br.cortex.cambio.api.web.response.CambioResponse;
import com.br.cortex.cambio.application.CambioServiceApplication;
import com.br.cortex.cambio.domain.Dinheiro;
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
    private CambioServiceApplication cambioServiceApplication;

    @GetMapping("/v1/conversor")
    public ResponseEntity<CambioResponse> conversor(@RequestParam String moedaOrigem, @RequestParam String moedaDestino,
                                                    @RequestParam BigDecimal valor, @RequestParam LocalDate data){
        var response = cambioServiceApplication.converter(moedaOrigem, moedaDestino, new Dinheiro(valor), data);

        return ResponseEntity.ok(new CambioResponse(response.getValorCompra(), response.getValorVenda()));
    }

}
