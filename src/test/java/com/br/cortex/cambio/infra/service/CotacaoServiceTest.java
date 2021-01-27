package com.br.cortex.cambio.infra.service;

import com.br.cortex.cambio.adapter.BCBAdapter;
import com.br.cortex.cambio.application.domain.model.Data;
import com.br.cortex.cambio.application.domain.model.Dinheiro;
import com.br.cortex.cambio.application.domain.model.Moeda;
import com.br.cortex.cambio.adapter.client.BCBClient;
import com.br.cortex.cambio.adapter.dto.CotacaoReponse;
import com.br.cortex.cambio.adapter.dto.TipoCotacaoMoeda;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class CotacaoServiceTest {

    @InjectMocks
    private BCBAdapter cotacaoService;

    @Mock
    private BCBClient bcbClient;

    @Test
    public void testDeBuscaCotacao(){
        TipoCotacaoMoeda tipoCotacaoMoeda1 = this.createTipoCotacao(LocalDateTime.now());
        TipoCotacaoMoeda tipoCotacaoMoeda2 = this.createTipoCotacao(LocalDateTime.now().plusHours(2));

        when(bcbClient.cotacaoMoeda(anyString(), anyString()))
                .thenReturn(new CotacaoReponse(Arrays.asList(tipoCotacaoMoeda2, tipoCotacaoMoeda1)));

        Moeda moeda = cotacaoService.converter("EUR", new Data(LocalDate.now()));

        Moeda moedaExperada = new Moeda("EUR",
                new Dinheiro(tipoCotacaoMoeda2.getCotacaoCompra()), new Dinheiro(tipoCotacaoMoeda2.getCotacaoVenda()));

        assertEquals(moedaExperada, moeda);

    }

    private TipoCotacaoMoeda createTipoCotacao(LocalDateTime dateTime){
        TipoCotacaoMoeda tipoCotacaoMoeda = new TipoCotacaoMoeda();
        tipoCotacaoMoeda.setCotacaoCompra(new BigDecimal(Math.random() * 1));
        tipoCotacaoMoeda.setCotacaoVenda(new BigDecimal(Math.random() * 1));
        tipoCotacaoMoeda.setParidadeCompra(new BigDecimal(Math.random() * 1));
        tipoCotacaoMoeda.setParidadeVenda(new BigDecimal(Math.random() * 1));
        tipoCotacaoMoeda.setDataHoraCotacao(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        return tipoCotacaoMoeda;
    }

}