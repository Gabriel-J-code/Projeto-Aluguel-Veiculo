package com.ufpb.projetoAluguelVeiculo.utils;

import com.ufpb.projetoAluguelVeiculo.repositories.*;

/**
 * RepositoriesInit
 */
public class RepositoriesInit {

    private String pathLojas;
    private String pathVeiculos;

    private LojasRepository lojasRepository;
    private AlugueisRepository alugueisRepository;
    private ClientesRepository clientesRepository;
    private VeiculosRepository veiculosRepository;

    public RepositoriesInit() {
        this.pathLojas = "src/main/java/com/ufpb/projetopoo/repositories/loja_database.txt";
        this.pathVeiculos = "src/main/java/com/ufpb/projetopoo/repositories/veiculo_database.txt";

        this.lojasRepository = new LojasRepository(this.pathLojas);

        this.alugueisRepository = new AlugueisRepository(this.lojasRepository);
        

        this.clientesRepository = new ClientesRepository(this.lojasRepository);
        
        this.veiculosRepository = new VeiculosRepository();
        this.veiculosRepository.setPath(pathVeiculos);	
    }    

    public RepositoriesInit(String pathLojas, String pathVeiculos) {
        this.pathLojas = pathLojas;
        this.pathVeiculos = pathVeiculos;

        this.lojasRepository = new LojasRepository(this.pathLojas);

        this.alugueisRepository = new AlugueisRepository(this.lojasRepository);
        

        this.clientesRepository = new ClientesRepository(this.lojasRepository);
        
        this.veiculosRepository = new VeiculosRepository();
        this.veiculosRepository.setPath(pathVeiculos);
	}

	public LojasRepository getLojasRepository() {
        return lojasRepository;
    }

    public AlugueisRepository getAlugueisRepository() {
        return alugueisRepository;
    }

    public ClientesRepository getClientesRepository() {
        return clientesRepository;
    }

    public VeiculosRepository getVeiculoRepository() {
        return veiculosRepository;
    }
}