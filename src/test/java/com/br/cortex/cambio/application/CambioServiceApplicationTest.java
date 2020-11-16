package com.br.cortex.cambio.application;

import com.br.cortex.cambio.application.cambio.ICotacao;
import com.br.cortex.cambio.domain.Cambio;
import com.br.cortex.cambio.domain.Data;
import com.br.cortex.cambio.domain.Dinheiro;
import com.br.cortex.cambio.domain.Moeda;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class CambioServiceApplicationTest {

    @InjectMocks
    private CambioServiceApplication cambioServiceApplication;

    @Mock
    private ICotacao cotacao;

    @Test
    public void testeConvercaoDeValores(){
        var euro = new Moeda("EUR", new Dinheiro(new BigDecimal(6.37)), new Dinheiro(new BigDecimal(6.48)));
        var dolar = new Moeda("USD", new Dinheiro(new BigDecimal(5.36)), new Dinheiro(new BigDecimal(5.46)));

        given(cotacao.converter(anyString(), any(Data.class)))
                .willReturn(euro)
                .willReturn(dolar);

        Cambio cambio = cambioServiceApplication.converter("EUR",
                "USD", new Dinheiro(new BigDecimal(150)), LocalDate.now());

        Cambio valorExperado = new Cambio(euro,dolar, new Dinheiro(new BigDecimal(150)));

        assertEquals(valorExperado, cambio);

    }

}