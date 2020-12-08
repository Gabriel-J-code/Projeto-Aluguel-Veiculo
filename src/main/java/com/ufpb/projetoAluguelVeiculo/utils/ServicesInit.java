package com.ufpb.projetoAluguelVeiculo.utils;

import com.ufpb.projetoAluguelVeiculo.services.AlugueisService;
import com.ufpb.projetoAluguelVeiculo.services.ClientesService;
import com.ufpb.projetoAluguelVeiculo.services.LojasService;
import com.ufpb.projetoAluguelVeiculo.services.VeiculosService;

public class ServicesInit {
    private LojasService lojasService;
    private AlugueisService alugueisService;
    private ClientesService clientesService;
    private VeiculosService veiculosService;

    public ServicesInit() {
        RepositoriesInit rInit = new RepositoriesInit();

        this.lojasService = new LojasService(rInit.getLojasRepository());
        this.alugueisService = new AlugueisService(rInit.getAlugueisRepository());
        this.clientesService = new ClientesService(rInit.getClientesRepository());
        this.veiculosService = new VeiculosService(rInit.getVeiculoRepository());
    }

    public ServicesInit(String pathLojas, String pathVeiculos) {
        RepositoriesInit rInit = new RepositoriesInit(pathLojas, pathVeiculos);

        this.lojasService = new LojasService(rInit.getLojasRepository());
        this.alugueisService = new AlugueisService(rInit.getAlugueisRepository());
        this.clientesService = new ClientesService(rInit.getClientesRepository());
        this.veiculosService = new VeiculosService(rInit.getVeiculoRepository());
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
    
}