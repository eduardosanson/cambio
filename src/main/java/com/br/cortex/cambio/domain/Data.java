package com.br.cortex.cambio.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Data {

    private LocalDate value;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    public Data(LocalDate value) {
        this.value = value;
    }

    public String value(){
        return this.value.format(formatter);
    }
}
