package com.br.cortex.cambio.application.cambio;

import com.br.cortex.cambio.domain.Data;
import com.br.cortex.cambio.domain.Moeda;

import java.time.LocalDate;

public interface ICotacao {

    Moeda converter(String codigo, Data data);
}
