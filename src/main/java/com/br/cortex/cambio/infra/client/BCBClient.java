package com.br.cortex.cambio.infra.client;

import com.br.cortex.cambio.infra.dto.TipoCotacaoMoeda;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(url = "https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/")
public interface BCBClient {

    @RequestMapping("CotacaoMoedaDia(moeda=@moeda,dataCotacao=@dataCotacao)")
    List<TipoCotacaoMoeda> cotacaoMoeda(@Param("@moeda") String moeda, @Param("@dataCotacao") String dataCotacao);


}
