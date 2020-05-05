package com.example.nearbyfenix.improvement;

import com.example.nearbyfenix.enums.Comportamento;
import com.example.nearbyfenix.enums.TipoPacote;
import com.example.nearbyfenix.interfaces.Receiver;
import com.example.nearbyfenix.interfaces.Sender;

public abstract class ReqReplyObject extends PatternComunicationObject implements Receiver, Sender {

    public ReqReplyObject(Comportamento c) {
        super.comportamento = c;
    }

    @Override
    public void send(byte[] dados, String endPointID) {
        nearbyAccessObject.send(endPointID, dados, TipoPacote.CONTENT);
    }
}
