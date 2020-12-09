package com.ufpb.projetoAluguelVeiculo.utils;

import com.ufpb.projetoAluguelVeiculo.controllers.AlugueisController;
import com.ufpb.projetoAluguelVeiculo.controllers.ClientesController;
import com.ufpb.projetoAluguelVeiculo.controllers.LojasController;
import com.ufpb.projetoAluguelVeiculo.controllers.VeiculosController;

public class ControllesInit {

    private AlugueisController alugueisController;
    private LojasController lojasController;
    private ClientesController clientesController;
    private VeiculosController veiculosController;

    public ControllesInit(){
        ServicesInit sInit = new ServicesInit();
        this.alugueisController = new AlugueisController(sInit.getAlugueisService(), sInit.getVeiculosService());
        this.clientesController = new ClientesController(sInit.getClientesService());
        this.lojasController = new LojasController(sInit.getLojasService());
        this.veiculosController = new VeiculosController(sInit.getVeiculosService());
    }
    public LojasController getLojasController() {
        return lojasController;
    }
    public AlugueisController getAlugueisController() {
        return alugueisController;
    }
    public ClientesController getClientesController() {
        return clientesController;
    }
    public VeiculosController getVeiculosController() {
        return veiculosController;
    }


    
}
