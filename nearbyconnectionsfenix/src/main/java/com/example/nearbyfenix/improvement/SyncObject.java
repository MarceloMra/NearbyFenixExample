package com.example.nearbyfenix.improvement;

import com.example.nearbyfenix.enums.Comportamento;
import com.example.nearbyfenix.interfaces.RecebedorDeConclusoes;
import com.example.nearbyfenix.interfaces.Receiver;

public abstract class SyncObject extends PatternComunicationObject implements Receiver, RecebedorDeConclusoes {

    public SyncObject() {
        super();
        super.comportamento = Comportamento.SYNCR;
    }
}
