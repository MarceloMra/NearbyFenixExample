package com.example.nearbyfenix.improvement;

import com.example.nearbyfenix.enums.Comportamento;

import java.util.ArrayList;

public abstract class PatternComunicationObject {
    protected NearbyAccessObject nearbyAccessObject;
    protected Comportamento comportamento;
    private ArrayList<EndpointInfo> endpointIDsConnected;
    private ArrayList<String> endpointIDsConnectedString;

    public PatternComunicationObject() {
        endpointIDsConnected = new ArrayList<>();
        endpointIDsConnectedString = new ArrayList<>();
    }

    public Comportamento getComportamento() {
        return comportamento;
    }

    public void startAdvertising(){
        nearbyAccessObject.startAdvertising();
    }
    public void startDiscovery(){
        nearbyAccessObject.startDiscovery();
    }

    public void addNewEndpointID(String endpointID, Comportamento c){
        EndpointInfo epi = new EndpointInfo(endpointID, c);
        endpointIDsConnected.add(epi);
        endpointIDsConnectedString.add(endpointID);
        novaConexaoEfetuada(epi);
    }

    protected abstract void novaConexaoEfetuada(EndpointInfo endpointInfo);
    protected abstract void conexaoEncerrada(EndpointInfo endpointInfo);
    public abstract void onSuccessStartAdvertising();
    public abstract void onFeilureStartAdvertising(Exception e);
    public abstract void onSuccessStartDiscovery();
    public abstract void onFeilureStartDiscovery(Exception e);

    public void removeEndpointID(String endpointID){
        for(EndpointInfo epi : endpointIDsConnected){
            if(epi.getEndpointID().equals(endpointID)) {
                endpointIDsConnected.remove(epi);
                endpointIDsConnectedString.remove(epi.getEndpointID());
                if (comportamento == Comportamento.PUBLISHER) {
                    PublisherObject po = (PublisherObject) this;
                    po.removeSubscricao(endpointID);
                }
            }
        }
    }

    public ArrayList<EndpointInfo> getEndpointIDsConnected() {
        return endpointIDsConnected;
    }

    public ArrayList<String> getEndpointIDsConnectedString() {
        return endpointIDsConnectedString;
    }

    public void setNearbyAccessObject(NearbyAccessObject nearbyAccessObject) {
        this.nearbyAccessObject = nearbyAccessObject;
    }

    public Comportamento getComportamentoDoEndpointID(String endpointID){
        for(EndpointInfo epi : endpointIDsConnected){
            if(epi.getEndpointID().equals(endpointID)){
                return epi.getComportamento();
            }
        }
        return null;
    }
}
