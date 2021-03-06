package com.example.nearbyfenix.improvement;

import com.example.nearbyfenix.enums.TipoPacote;

import java.io.Serializable;

public class Pacote implements Serializable {
    private TipoPacote tipo;
    private Object conteudo;

    public Pacote(TipoPacote tipo, Object conteudo) {
        this.tipo = tipo;
        this.conteudo = conteudo;
    }

    public TipoPacote getTipo() {
        return tipo;
    }

    public Object getConteudo() {
        return conteudo;
    }
}
