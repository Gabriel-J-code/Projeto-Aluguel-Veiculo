package com.ufpb.projetoAluguelVeiculo.utils;

import java.util.ArrayList;

import com.ufpb.projetoAluguelVeiculo.entities.Loja;
import com.ufpb.projetoAluguelVeiculo.repositories.AlugueisRepository;
import com.ufpb.projetoAluguelVeiculo.repositories.ClientesRepository;
import com.ufpb.projetoAluguelVeiculo.repositories.LojasRepository;
import com.ufpb.projetoAluguelVeiculo.repositories.VeiculosRepository;

public class RepositoriesInitForTest extends RepositoriesInit {    

    private RepositoriesInit ri;

    public RepositoriesInitForTest(){
        String pathLojas = "src/main/java/com/ufpb/projetopoo/repositories/for_test_loja_database.txt";
        String pathVeiculos = "src/main/java/com/ufpb/projetopoo/repositories/for_test_veiculo_database.txt";
        this.ri = new RepositoriesInit(pathLojas, pathVeiculos);
    } 

    public RepositoriesInitForTest(String pathLojas, String pathVeiculos){        
        this.ri = new RepositoriesInit(pathLojas, pathVeiculos);
    }  

    public LojasRepository getLojasRepository() {
        return ri.getLojasRepository();
    }

    public AlugueisRepository getAlugueisRepository() {
        return ri.getAlugueisRepository();
    }

    public ClientesRepository getClientesReposiroty() {
        return ri.getClientesRepository();
    }

    public VeiculosRepository getVeiculoRepository() {
        return ri.getVeiculoRepository();
    }

    public void cleanData(){
        ArrayList<Loja> lojas = new ArrayList<Loja>(getLojasRepository().findAll());
        if (lojas.size()>0){
            for (Loja loja : lojas) {
                this.getLojasRepository().deleteByCNPJ(loja.getCnpj());            
            }
        }
        // for (Veiculo veiculo : getVeiculoRepository().findAll()) {
        //     getVeiculoRepository().deletarVeiculo(veiculo);            
        // }
    }
    
}
