package com.ufpb.projetoAluguelVeiculo.repositories;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.util.ArrayList;

import com.ufpb.projetoAluguelVeiculo.entities.Aluguel;
import com.ufpb.projetoAluguelVeiculo.entities.Cliente;
import com.google.gson.Gson;

import java.util.Scanner;

import com.ufpb.projetoAluguelVeiculo.entities.Loja;

public class LojasRepository {
    private static final String LOJA_DATABASE_URL = "src/main/java/com/ufpb/projetopoo/repositories/loja_database.txt";
    private ArrayList<Loja> lojas;

    public LojasRepository() {
        this.lojas = new ArrayList<Loja>();
    }

    // Save and recovery

    public static String recoveryData() {
        try {
            BufferedReader myObj = new BufferedReader(
                new FileReader(LOJA_DATABASE_URL)
            );
            Scanner myReader = new Scanner(myObj);
            String data = "";
            
            while (myReader.hasNextLine()) {
                data += myReader.nextLine() + "\n";
            }

            myReader.close();
            return data;
        } catch (FileNotFoundException e) { 
            throw new RuntimeException("Arquivo não encontrado");
        }
    }

    public void importLojas() {
        try {
            String jsonImportado = recoveryData();

            Gson gson = new Gson();
            Scanner sc = new Scanner(jsonImportado);
            this.lojas = new ArrayList<Loja>();
            while (sc.hasNextLine()) {
                this.lojas.add(gson.fromJson(
                    sc.nextLine(), Loja.class
                ));
            }
            sc.close();

        } catch (Exception e) { }
    }

    private void updateDataBase() {
        Gson gson = new Gson();

        StringBuilder sb = new StringBuilder();

        for (Loja loja : this.lojas) {
            sb.append(gson.toJson(loja) + "\n");
        }
        saveData(sb.toString());
    }

    private void saveData(String json) {
        try {
            FileWriter dataToSave = new FileWriter(LOJA_DATABASE_URL);
            dataToSave.write(json);
            dataToSave.close();
        } catch (Exception e) { }
    }

    // Cliente métodos
    
    public Cliente saveCliente(Cliente cliente, String cnpj) {
        importLojas();
        Gson gson = new Gson();
        for (Loja loja : this.lojas) {
            if (loja.getCnpj().equalsIgnoreCase(cnpj)) {
                
                Loja l = loja;
                ArrayList<Cliente> clientesLoja = loja.getClientes();
                clientesLoja.add(cliente);
                
                l.setClientes(clientesLoja);
                deleteByCNPJ(cnpj);
                save(l);

                updateDataBase();
                return cliente;
            }
        }
        throw new RuntimeException("Loja não cadastrada.");
    }

    public ArrayList<Cliente> findClientsByCnpj(String cnpj) {
        importLojas();

        for (Loja loja : this.lojas) {
            if (loja.getCnpj().equals(cnpj)) {
                return loja.getClientes();
            }
        }
        throw new RuntimeException("Loja não cadastrada.");
    }

    public Cliente findClienteByCpf(String cpf, String cnpj) {
        importLojas();
        ArrayList<Cliente> clientes = findClientsByCnpj(cnpj);
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    public boolean deleteClienteByCpf(String cpf, String cnpj) {
        importLojas();
        Loja l = findByCnpj(cnpj);
        for (Cliente c : l.getClientes()) {
            if (c.getCpf().equals(cpf)) {
                l.removeCliente(c);
                updateDataBase();
                return true;
            }
        }
        return false;
    }
}
