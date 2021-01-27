package com.br.cortex.cambio.application.domain.service;

import com.br.cortex.cambio.application.domain.model.Cambio;
import com.br.cortex.cambio.application.domain.model.Dinheiro;
import com.br.cortex.cambio.application.domain.model.Moeda;
import org.springframework.stereotype.Service;

@Service
public class Escambo {

    public Cambio cambiar(Moeda origem, Moeda destido, Dinheiro valor){
        return new Cambio(origem, destido, valor);
    }
}
