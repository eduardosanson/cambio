package com.br.cortex.cambio.application.domain.model;

import java.util.Objects;

public class Moeda {

    private String codigo;

    private Dinheiro compra;

    private Dinheiro venda;

    public Moeda(String codigo, Dinheiro compra, Dinheiro venda) {
        this.codigo = codigo;
        this.compra = compra;
        this.venda = venda;
    }

    public String getCodigo() {
        return codigo;
    }

    public Dinheiro getCompra() {
        return compra;
    }

    public Dinheiro getVenda() {
        return venda;
    }

    @Override
    public String toString() {
        return "Moeda{" +
                "codigo='" + codigo + '\'' +
                ", real=" + compra +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Moeda moeda = (Moeda) o;

        if (!Objects.equals(codigo, moeda.codigo)) return false;
        return Objects.equals(compra, moeda.compra);
    }

    @Override
    public int hashCode() {
        int result = codigo != null ? codigo.hashCode() : 0;
        result = 31 * result + (compra != null ? compra.hashCode() : 0);
        return result;
    }
}
