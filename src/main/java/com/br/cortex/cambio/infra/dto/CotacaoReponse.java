package com.br.cortex.cambio.infra.dto;

import java.io.Serializable;
import java.util.List;

public class CotacaoReponse implements Serializable {

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
