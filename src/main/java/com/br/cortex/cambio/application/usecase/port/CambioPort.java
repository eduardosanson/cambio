package com.br.cortex.cambio.application.usecase.port;

import com.br.cortex.cambio.application.domain.model.Cambio;
import com.br.cortex.cambio.application.domain.model.Dinheiro;

import java.time.LocalDate;

public interface CambioPort {

    Cambio converter(String moedaOrigem, String moedaDestino, Dinheiro valor, LocalDate localDate);
}
