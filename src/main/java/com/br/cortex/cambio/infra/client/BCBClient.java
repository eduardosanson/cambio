package com.br.cortex.cambio.infra.client;

import com.br.cortex.cambio.infra.dto.CotacaoReponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "BCB" , url = "https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/")
public interface BCBClient {

    @RequestMapping("CotacaoMoedaDia(moeda=@moeda,dataCotacao=@dataCotacao)")
    CotacaoReponse cotacaoMoeda(@RequestParam("@moeda") String moeda, @RequestParam("@dataCotacao") String dataCotacao);

}
