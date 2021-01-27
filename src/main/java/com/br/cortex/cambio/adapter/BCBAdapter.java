package com.br.cortex.cambio.adapter;

import com.br.cortex.cambio.application.domain.model.Data;
import com.br.cortex.cambio.application.domain.model.Dinheiro;
import com.br.cortex.cambio.application.domain.model.Moeda;
import com.br.cortex.cambio.application.port.BCPort;
import com.br.cortex.cambio.adapter.client.BCBClient;
import com.br.cortex.cambio.adapter.dto.TipoCotacaoMoeda;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;

public class BCBAdapter implements BCPort {

    @Autowired
    private BCBClient bcbClient;

    private static final Logger LOG = LoggerFactory.getLogger(BCBAdapter.class);

    @Override
    public Moeda converter(String codigo, Data data) {
        LOG.info("Inicio da cotacao do {} e data {}", codigo, data.asString());
        StringBuilder codigoBuilder = new StringBuilder("'");
        codigoBuilder.append(codigo);
        codigoBuilder.append("'");
        StringBuilder localDateBuilder = new StringBuilder("'");
        localDateBuilder.append(data.asString());
        localDateBuilder.append("'");
        return bcbClient.cotacaoMoeda(codigoBuilder.toString(), localDateBuilder.toString()).getValue().stream()
                .sorted(Comparator.comparing(TipoCotacaoMoeda::getDataHoraCotacao).reversed())
                .findFirst()
                .map(tipoCotacaoMoeda -> new Moeda(codigo, new Dinheiro(tipoCotacaoMoeda.getCotacaoCompra()),
                        new Dinheiro(tipoCotacaoMoeda.getCotacaoVenda())))
                .orElseThrow(() -> new IllegalStateException("A Busca por cotacao não retornou nada"));
    }
}
