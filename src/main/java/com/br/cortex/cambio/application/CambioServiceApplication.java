package com.br.cortex.cambio.application;

import com.br.cortex.cambio.application.cambio.ICotacao;
import com.br.cortex.cambio.domain.Cambio;
import com.br.cortex.cambio.domain.Dinheiro;
import com.br.cortex.cambio.domain.Moeda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CambioServiceApplication {

    @Autowired
    private ICotacao cotacao;

    public Dinheiro converter(String moedaOrigem, String moedaDestino, Dinheiro valor, LocalDate localDate){
        Moeda de = cotacao.converter(moedaOrigem, localDate);
        Moeda para = cotacao.converter(moedaDestino, localDate);
        Cambio cambio = new Cambio(de, para, valor);
        return cambio.converter();
    }
}
