package com.br.cortex.cambio.domain;

import java.math.BigDecimal;

public class Cambio {

    private Moeda de;

    private Moeda para;

    private Dinheiro valor;

    private Dinheiro valorCompra;

    private Dinheiro valorVenda;

    public Cambio(Moeda de, Moeda para, Dinheiro valor) {
        this.de = de;
        this.para = para;
        this.valor = valor;
        this.valorCompra = converter(de.getCompra(), para.getCompra(), valor);
        this.valorVenda = converter(de.getVenda(), para.getVenda(), valor);
    }

    private Dinheiro converter(Dinheiro de, Dinheiro para, Dinheiro valor){
        Dinheiro reais = de.multiplicar(valor.getValor());
        return reais.dividir(para.getValor());
    }

    public Dinheiro getValorCompra() {
        return valorCompra;
    }

    public Dinheiro getValorVenda() {
        return valorVenda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cambio cambio = (Cambio) o;

        if (de != null ? !de.equals(cambio.de) : cambio.de != null) return false;
        if (para != null ? !para.equals(cambio.para) : cambio.para != null) return false;
        if (valor != null ? !valor.equals(cambio.valor) : cambio.valor != null) return false;
        if (valorCompra != null ? !valorCompra.equals(cambio.valorCompra) : cambio.valorCompra != null) return false;
        return valorVenda != null ? valorVenda.equals(cambio.valorVenda) : cambio.valorVenda == null;
    }

    @Override
    public int hashCode() {
        int result = de != null ? de.hashCode() : 0;
        result = 31 * result + (para != null ? para.hashCode() : 0);
        result = 31 * result + (valor != null ? valor.hashCode() : 0);
        result = 31 * result + (valorCompra != null ? valorCompra.hashCode() : 0);
        result = 31 * result + (valorVenda != null ? valorVenda.hashCode() : 0);
        return result;
    }
}
