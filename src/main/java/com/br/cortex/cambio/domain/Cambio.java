package com.br.cortex.cambio.domain;

public class Cambio {

    private Moeda de;

    private Moeda para;

    private Dinheiro valor;

    public Cambio(Moeda de, Moeda para, Dinheiro valor) {
        this.de = de;
        this.para = para;
        this.valor = valor;
    }

    public Dinheiro converter(){
        Dinheiro reais = de.getReal().multiplicar(valor.getValor());
        return reais.dividir(para.getReal().getValor());
    }
}
