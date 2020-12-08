package com.ufpb.projetoAluguelVeiculo.repositories;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.ufpb.projetoAluguelVeiculo.entities.Veiculo;
import com.ufpb.projetoAluguelVeiculo.utils.DataRegister;

import org.springframework.stereotype.Repository;

@Repository
public class VeiculosRepository {

    private DataRegister dataRegister;

    private ArrayList<Veiculo> veiculos; 
    
    
    public VeiculosRepository(){
        this.dataRegister = new DataRegister("src/main/java/com/ufpb/projetoAluguelVeiculo/utils/veiculo_database.txt");
        this.veiculos = new ArrayList<Veiculo>();
        recuperarDados();
    }

    public void recuperarDados() {
        try {   
            Gson gson = new Gson();             
            for (String line : dataRegister.recoveryData()) {
                this.veiculos.add(gson.fromJson(line, Veiculo.class));;                
            }
            
        } catch (FileNotFoundException e) {
        } 
    }

    protected void saveData() {
        Gson gson = new Gson();
        try {
            dataRegister.saveData(gson.toJson(veiculos));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Veiculo addVeiculo(Veiculo veiculo) {
        this.veiculos.add(veiculo);
        saveData();
        return veiculo;
    }

    public ArrayList<Veiculo> findAll(){
        return veiculos;
    }

    public Veiculo findById(String id){
        for(Veiculo v : findAll()){
            if (v.getId().equals(id)){
                return v;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    public boolean deletarVeiculo(Veiculo veiculo){
        Gson gson = new Gson();
        for (Veiculo v : veiculos) {
            if (gson.toJson(v).equals(gson.toJson(veiculo))){
                this.veiculos.remove(v);
                return true;
            }
        }
        return false;
    }

	public void setPath(String pathVeiculos) {
        this.dataRegister = new DataRegister(pathVeiculos);
	}

}
