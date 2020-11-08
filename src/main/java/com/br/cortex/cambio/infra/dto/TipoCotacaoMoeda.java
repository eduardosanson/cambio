package com.br.cortex.cambio.infra.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TipoCotacaoMoeda {

    private BigDecimal paridadeCompra;

    private BigDecimal paridadeVenda;

    private BigDecimal cotacaoCompra;

    private BigDecimal cotacaoVenda;

    private LocalDateTime dataHoraCotacao;

    private String tipoBoletim;

    public TipoCotacaoMoeda() {
    }

    public BigDecimal getParidadeCompra() {
        return paridadeCompra;
    }

    public void setParidadeCompra(BigDecimal paridadeCompra) {
        this.paridadeCompra = paridadeCompra;
    }

    public BigDecimal getParidadeVenda() {
        return paridadeVenda;
    }

    public void setParidadeVenda(BigDecimal paridadeVenda) {
        this.paridadeVenda = paridadeVenda;
    }

    public BigDecimal getCotacaoCompra() {
        return cotacaoCompra;
    }

    public void setCotacaoCompra(BigDecimal cotacaoCompra) {
        this.cotacaoCompra = cotacaoCompra;
    }

    public BigDecimal getCotacaoVenda() {
        return cotacaoVenda;
    }

    public void setCotacaoVenda(BigDecimal cotacaoVenda) {
        this.cotacaoVenda = cotacaoVenda;
    }

    public LocalDateTime getDataHoraCotacao() {
        return dataHoraCotacao;
    }

    public void setDataHoraCotacao(LocalDateTime dataHoraCotacao) {
        this.dataHoraCotacao = dataHoraCotacao;
    }

    public String getTipoBoletim() {
        return tipoBoletim;
    }

    public void setTipoBoletim(String tipoBoletim) {
        this.tipoBoletim = tipoBoletim;
    }
}
