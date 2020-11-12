package com.br.cortex.cambio.infra.service;

import com.br.cortex.cambio.application.cambio.ICotacao;
import com.br.cortex.cambio.domain.Data;
import com.br.cortex.cambio.domain.Dinheiro;
import com.br.cortex.cambio.domain.Moeda;
import com.br.cortex.cambio.infra.client.BCBClient;
import com.br.cortex.cambio.infra.dto.TipoCotacaoMoeda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;

@Service
public class CotacaoService implements ICotacao {

    @Autowired
    private BCBClient bcbClient;

    @Override
    public Moeda converter(String codigo, Data data) {
        StringBuilder codigoBuilder = new StringBuilder("'");
        codigoBuilder.append(codigo);
        codigoBuilder.append("'");
        StringBuilder localDateBuilder = new StringBuilder("'");
        localDateBuilder.append(data.value());
        localDateBuilder.append("'");
        return bcbClient.cotacaoMoeda(codigoBuilder.toString(), localDateBuilder.toString()).getValue().stream()
                .sorted(Comparator.comparing(TipoCotacaoMoeda::getDataHoraCotacao).reversed())
                .findFirst()
                .map(tipoCotacaoMoeda -> new Moeda(codigo, new Dinheiro(tipoCotacaoMoeda.getCotacaoCompra()),
                        new Dinheiro(tipoCotacaoMoeda.getCotacaoVenda())))
                .orElseThrow(() -> new IllegalStateException("A Busca por cotacao não retornou nada"));
    }
}
