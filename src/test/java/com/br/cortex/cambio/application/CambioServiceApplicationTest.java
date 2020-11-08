package com.br.cortex.cambio.application;

import com.br.cortex.cambio.application.cambio.ICotacao;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class CambioServiceApplicationTest {

    @InjectMocks
    private CambioServiceApplication cambioServiceApplication;

    @Mock
    private ICotacao cotacao;

    @Test
    public void test(){

        given(cotacao.converter(anyString(), any(LocalDate.class)))
                .willReturn(new Moeda("EUR", new Dinheiro(new BigDecimal(6.37))))
                .willReturn(new Moeda("USD", new Dinheiro(new BigDecimal(5.36))));

        Dinheiro valor = cambioServiceApplication.converter("EUR",
                "USD", new Dinheiro(new BigDecimal(150)), LocalDate.now());

        Dinheiro valorExperado = new Dinheiro(new BigDecimal(178.22));

        assertEquals(valorExperado, valor);

    }

}