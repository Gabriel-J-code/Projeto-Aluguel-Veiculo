package com.ufpb.projetoAluguelVeiculo.repositories;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.ufpb.projetoAluguelVeiculo.entities.*;
import com.ufpb.projetoAluguelVeiculo.utils.DataRegister;

import org.springframework.stereotype.Repository;

@Repository
public class LojasRepository {

    private static DataRegister dataRegister;

    private static ArrayList<Loja> lojas;

    public LojasRepository() {
        dataRegister = new DataRegister("src/main/java/com/ufpb/projetoAluguelVeiculo/utils/loja_database.txt");
        lojas = new ArrayList<Loja>();
        recuperarDados();
    }
    
    public LojasRepository(String pathLojas) {
        dataRegister = new DataRegister(pathLojas);
        lojas = new ArrayList<Loja>();
        recuperarDados();
    }

    protected void saveData() {
        Gson gson = new Gson();
        try {
            StringBuilder save = new StringBuilder();
            for (Loja loja : lojas){
                save.append(gson.toJson(loja));

            }
            dataRegister.saveData(save.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Loja saveLoja(Loja loja) {
        if (!isDuplicadoLoja(loja)) {
            lojas.add(loja);

            saveData();
            return loja;
        }
        throw new IndexOutOfBoundsException("Loja já cadastrada no sistema.");
    }

    public void recuperarDados() {
        try {   
            Gson gson = new Gson();
            gson.fieldNamingStrategy();          
            for (String line : dataRegister.recoveryData()) {
                Loja aux = gson.fromJson(line,Loja.class);
                lojas.add(aux);                
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e){
            e.printStackTrace();
        }

    }

    public ArrayList<Loja> findAll() {
        return lojas;
    }

    public Loja findByCnpj(String cnpj) {
        for (Loja item : lojas) {
            if (item.getCnpj().equals(cnpj))
                return item;
        }
        throw new IndexOutOfBoundsException();
    }

    private boolean isDuplicadoLoja(Loja loja) {
        for (Loja item : lojas) {
            if (loja.getCnpj().equals(item.getCnpj())) {
                return true;
            }
        }
        return false;
    }

    public boolean deleteByCNPJ(String cnpj) {
        for (Loja loja : lojas) {
            if (loja.getCnpj().equals(cnpj)) {
                lojas.remove(loja);
                saveData();
                return true;
            }
        }
        return false;
    }

	public void setPath(String pathLojas) {
        dataRegister = new DataRegister(pathLojas);
	}


}
