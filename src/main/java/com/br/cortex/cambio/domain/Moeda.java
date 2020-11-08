package com.br.cortex.cambio.domain;

public class Moeda {

    private String codigo;

    private Dinheiro real;

    public Moeda(String codigo, Dinheiro real) {
        this.codigo = codigo;
        this.real = real;
    }

    public String getCodigo() {
        return codigo;
    }

    public Dinheiro getReal() {
        return real;
    }
}
