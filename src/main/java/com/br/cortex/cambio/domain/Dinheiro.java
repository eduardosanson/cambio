package com.br.cortex.cambio.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Dinheiro {

    private BigDecimal valor;

    public Dinheiro(BigDecimal valor) {
        if (valor.longValue() < 0) throw new IllegalArgumentException("valor inválido");
        this.valor = valor.setScale(3, RoundingMode.CEILING);
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Dinheiro multiplicar(BigDecimal valor){
        return new Dinheiro(this.valor.multiply(valor));
    }

    public Dinheiro dividir(BigDecimal valor){
        return new Dinheiro(this.valor.divide(valor, 4, RoundingMode.CEILING));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dinheiro dinheiro = (Dinheiro) o;

        return valor != null ? valor.equals(dinheiro.valor) : dinheiro.valor == null;
    }

    @Override
    public int hashCode() {
        return valor != null ? valor.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Dinheiro{" +
                "valor=" + valor +
                '}';
    }
}
