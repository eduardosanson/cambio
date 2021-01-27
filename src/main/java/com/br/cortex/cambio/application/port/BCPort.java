package com.br.cortex.cambio.application.port;

import com.br.cortex.cambio.application.domain.model.Data;
import com.br.cortex.cambio.application.domain.model.Moeda;

public interface BCPort {

    Moeda converter(String codigo, Data data);
}
