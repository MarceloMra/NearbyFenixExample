package com.example.nearbyfenix.improvement;

import com.example.nearbyfenix.enums.Comportamento;
import com.example.nearbyfenix.enums.TipoPacote;
import com.example.nearbyfenix.interfaces.Concluivel;
import com.example.nearbyfenix.interfaces.SenderAllOnce;

import java.util.ArrayList;

public abstract class VentilatorObject extends PatternComunicationObject implements SenderAllOnce, Concluivel {
    private ArrayList<ArrayList<byte[]>> mensagens;
    private ArrayList<ArrayList<byte[]>> mensagensEmFilaParaEnvio;

    public VentilatorObject() {
        super();
        super.comportamento = Comportamento.VENTILATOR;
        mensagens = new ArrayList<>();
        mensagensEmFilaParaEnvio = new ArrayList<>();
    }

    @Override
    public void comunicarConclusao(){
        for (EndpointInfo epi : super.getEndpointIDsConnected()){
            if(epi.getComportamento() == Comportamento.WORKER){
                nearbyAccessObject.comunicaConclusao(epi.getEndpointID(), "-@-OKprocessing-@-");
            }
        }
    }

    @Override
    public void send(final ArrayList<byte[]> dados) {
        mensagens.add(dados);
        mensagensEmFilaParaEnvio.add(dados);

        final ArrayList<EndpointInfo> endpoints = super.getEndpointIDsConnected();
        if(endpoints.size() == 0 && mensagensEmFilaParaEnvio.size() > 0){
            new Thread(){
                @Override
                public void run(){
                    while(endpoints.size() == 0 && mensagensEmFilaParaEnvio.size() > 0){
                        try {
                            this.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    enviarDadosEmFilaPorRoundRobin();
                }
            }.start();
        }else{
            enviarDadosEmFilaPorRoundRobin();
        }
    }

    private void enviarDadosEmFilaPorRoundRobin(){
        ArrayList<EndpointInfo> endpoints = super.getEndpointIDsConnected();
        int index = 0;

        for(int j = 0; j < mensagensEmFilaParaEnvio.size(); j++){
            //ROUND ROBIN
            for(int i = 0; i < mensagensEmFilaParaEnvio.get(j).size(); i++){
                nearbyAccessObject.send(endpoints.get(index).getEndpointID(), mensagensEmFilaParaEnvio.get(j).get(i), TipoPacote.CONTENT);

                if(index + 1 < endpoints.size()){
                    index++;
                }else{
                    index = 0;
                }

            }
            mensagensEmFilaParaEnvio.remove(mensagensEmFilaParaEnvio.get(j));
        }
    }

    public ArrayList<ArrayList<byte[]>> getMensagens() {
        return mensagens;
    }

    public ArrayList<ArrayList<byte[]>> getMensagensEmFilaParaEnvio() {
        return mensagensEmFilaParaEnvio;
    }
}
