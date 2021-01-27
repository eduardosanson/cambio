package com.br.cortex.cambio.application.domain.service;

import com.br.cortex.cambio.application.domain.model.Cambio;
import com.br.cortex.cambio.application.domain.model.Dinheiro;
import com.br.cortex.cambio.application.domain.model.Moeda;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EscamboTest {

    @Test
    public void testeConvercaoDeValores(){
        var euro = new Moeda("EUR", new Dinheiro(new BigDecimal(6.37)), new Dinheiro(new BigDecimal(6.48)));
        var dolar = new Moeda("USD", new Dinheiro(new BigDecimal(5.36)), new Dinheiro(new BigDecimal(5.46)));
        var valorExperado = new Cambio(euro,dolar, new Dinheiro(new BigDecimal(150)));

        var cambio = new Escambo().cambiar(euro, dolar, new Dinheiro(new BigDecimal(150)));

        assertEquals(valorExperado, cambio);
    }

}