package com.br.cortex.cambio.api.web.response;

import com.br.cortex.cambio.application.domain.model.Dinheiro;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CambioResponse {

    private BigDecimal valorCompra;

    private BigDecimal valorVenda;

    public CambioResponse(Dinheiro valorCompra, Dinheiro valorVenda) {
        this.valorCompra = valorCompra.getValor().setScale(2, RoundingMode.CEILING);
        this.valorVenda = valorVenda.getValor().setScale(2, RoundingMode.CEILING);
    }

    public BigDecimal getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(BigDecimal valorCompra) {
        this.valorCompra = valorCompra;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }
}
