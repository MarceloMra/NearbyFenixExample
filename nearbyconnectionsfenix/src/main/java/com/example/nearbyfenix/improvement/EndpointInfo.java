package com.example.nearbyfenix.improvement;

import com.example.nearbyfenix.enums.Comportamento;

public class EndpointInfo {
    private String endpointID;
    private Comportamento comportamento;

    public EndpointInfo(String endpointID, Comportamento comportamento) {
        this.endpointID = endpointID;
        this.comportamento = comportamento;
    }

    public String getEndpointID() {
        return endpointID;
    }

    public void setEndpointID(String endpointID) {
        this.endpointID = endpointID;
    }

    public Comportamento getComportamento() {
        return comportamento;
    }

    public void setComportamento(Comportamento comportamento) {
        this.comportamento = comportamento;
    }

    @Override
    public boolean equals(Object o){
        EndpointInfo endpointInfo = (EndpointInfo) o;
        if(endpointInfo.getComportamento() == this.getComportamento() && endpointInfo.getEndpointID().equals(this.getEndpointID())){
            return true;
        }
        return false;
    }
}
