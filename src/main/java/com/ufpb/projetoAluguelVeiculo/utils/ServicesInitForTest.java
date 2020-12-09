package com.ufpb.projetoAluguelVeiculo.utils;

import com.ufpb.projetoAluguelVeiculo.services.AlugueisService;
import com.ufpb.projetoAluguelVeiculo.services.ClientesService;
import com.ufpb.projetoAluguelVeiculo.services.LojasService;
import com.ufpb.projetoAluguelVeiculo.services.VeiculosService;

public class ServicesInitForTest {
    
    private RepositoriesInitForTest rTest;

    private LojasService lojasService;
    private AlugueisService alugueisService;
    private ClientesService clientesService;
    private VeiculosService veiculosService;

    public ServicesInitForTest() {
        this.rTest = new RepositoriesInitForTest("src/test/java/com/ufpb/projetoAluguelVeiculo/testingLojas.txt","src/test/java/com/ufpb/projetoAluguelVeiculo/TestingVeiculos.txt");

        this.lojasService = new LojasService(rTest.getLojasRepository());
        this.alugueisService = new AlugueisService(rTest.getAlugueisRepository());
        this.clientesService = new ClientesService(rTest.getClientesReposiroty());
        this.veiculosService = new VeiculosService(rTest.getVeiculoRepository());
    }
    
    public LojasService getLojasService() {
        return lojasService;
    }

    public AlugueisService getAlugueisService() {
        return alugueisService;
    }

    public ClientesService getClientesService() {
        return clientesService;
    }

    public VeiculosService getVeiculosService() {
        return veiculosService;
    }

    public void cleanRepository(){
        rTest.cleanData();
    }    
}