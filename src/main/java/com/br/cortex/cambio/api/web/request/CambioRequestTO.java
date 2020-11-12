package com.br.cortex.cambio.api.web.request;

import java.math.BigDecimal;

public class CambioRequestTO {

    private String moedaOrigem;

    private String moedaDestino;

    private BigDecimal valor;

    private String dataCotacao;

    public CambioRequestTO(String moedaOrigem, String moedaDestino, BigDecimal valor, String dataCotacao) {
        this.moedaOrigem = moedaOrigem;
        this.moedaDestino = moedaDestino;
        this.valor = valor;
        this.dataCotacao = dataCotacao;
    }

    public CambioRequestTO() {

    }

    public String getMoedaOrigem() {
        return moedaOrigem;
    }

    public void setMoedaOrigem(String moedaOrigem) {
        this.moedaOrigem = moedaOrigem;
    }

    public String getMoedaDestino() {
        return moedaDestino;
    }

    public void setMoedaDestino(String moedaDestino) {
        this.moedaDestino = moedaDestino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDataCotacao() {
        return dataCotacao;
    }

    public void setDataCotacao(String dataCotacao) {
        this.dataCotacao = dataCotacao;
    }
}
