package com.br.cortex.cambio.adapter.client;

import com.br.cortex.cambio.adapter.dto.CotacaoReponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "BCB" , url = "${clients.BCB.host}")
public interface BCBClient {

    @RequestMapping("/CotacaoMoedaDia(moeda=@moeda,dataCotacao=@dataCotacao)")
    @Cacheable(cacheNames = "Moeda", key = "#moeda.concat('-').concat(#dataCotacao)")
    CotacaoReponse cotacaoMoeda(@RequestParam("@moeda") String moeda, @RequestParam("@dataCotacao") String dataCotacao);

}
