package com.br.cortex.cambio.infra.dto;

import java.util.List;

public class CotacaoReponse {

    private List<TipoCotacaoMoeda> value;

    public CotacaoReponse(List<TipoCotacaoMoeda> value) {
        this.value = value;
    }

    public CotacaoReponse() {
    }

    public List<TipoCotacaoMoeda> getValue() {
        return value;
    }

    public void setValue(List<TipoCotacaoMoeda> value) {
        this.value = value;
    }
}
