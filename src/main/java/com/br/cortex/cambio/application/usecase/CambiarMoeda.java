package com.br.cortex.cambio.application.usecase;

import com.br.cortex.cambio.application.domain.model.Cambio;
import com.br.cortex.cambio.application.domain.model.Data;
import com.br.cortex.cambio.application.domain.model.Dinheiro;
import com.br.cortex.cambio.application.domain.model.Moeda;
import com.br.cortex.cambio.application.domain.service.Escambo;
import com.br.cortex.cambio.application.port.BCPort;
import com.br.cortex.cambio.application.usecase.port.CambioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CambiarMoeda implements CambioPort {

    @Autowired
    private BCPort cotacao;

    @Autowired
    private Escambo escambo;

    @Override
    public Cambio converter(String moedaOrigem, String moedaDestino, Dinheiro valor, LocalDate localDate) {
        Moeda de = cotacao.converter(moedaOrigem, new Data(localDate));
        Moeda para = cotacao.converter(moedaDestino, new Data(localDate));
        return escambo.cambiar(de, para, valor);
    }
}
