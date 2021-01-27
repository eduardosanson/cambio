package com.br.cortex.cambio.api.web;

import com.br.cortex.cambio.application.domain.model.Cambio;
import com.br.cortex.cambio.application.domain.model.Dinheiro;
import com.br.cortex.cambio.application.domain.model.Moeda;
import com.br.cortex.cambio.application.usecase.CambiarMoeda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class CambioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CambiarMoeda cambioServiceApplication;

//    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        var euro = new Moeda("EUR", new Dinheiro(new BigDecimal(5.6)), new Dinheiro(new BigDecimal(5.7)));
        var dolar = new Moeda("USD", new Dinheiro(new BigDecimal(6.6)), new Dinheiro(new BigDecimal(6.9)));
        Cambio cambio = new Cambio(euro,dolar, new Dinheiro(new BigDecimal(150)));
        when(cambioServiceApplication.converter(anyString(),anyString(),any(),any())).thenReturn(cambio);
        this.mockMvc.perform(get("/cambio/v1/conversor/?moedaOrigem=EUR&moedaDestino=USD&valor=150&data=10/11/2020"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"valorCompra\":126.86,\"valorVenda\": 126.86 }"));
    }

}