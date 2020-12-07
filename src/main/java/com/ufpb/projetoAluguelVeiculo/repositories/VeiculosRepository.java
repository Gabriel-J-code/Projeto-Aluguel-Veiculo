package com.ufpb.projetoAluguelVeiculo.repositories;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.ufpb.projetoAluguelVeiculo.entities.Veiculo;

import java.util.Scanner;



public class VeiculosRepository {
    private static final String VEICULO_DATABASE_URL = "src/main/java/com/ufpb/projetoAluguelVeiculo/utils/veiculo_database.txt";
    private ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();

    public VeiculosRepository() {
        this.importVeiculos();
    }

    private String recoveryData() throws FileNotFoundException {
        try {
            BufferedReader myObj = new BufferedReader(new FileReader(VEICULO_DATABASE_URL));
            Scanner myReader = new Scanner(myObj);
            String data = "";
            while (myReader.hasNextLine()) {
                data += myReader.nextLine() + "\n";
            }

            myReader.close();
            return data;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("An error occurred.");
        }
    }

    private void importVeiculos() {
        try {
            String jsonImportado = recoveryData();
            Gson gson = new Gson();
            Scanner sc = new Scanner(jsonImportado);

            while (sc.hasNextLine()) {
                this.veiculos.add(gson.fromJson(sc.nextLine(), Veiculo.class));
            }
            sc.close();
        } catch (FileNotFoundException fnfe) {
        }

    }

    public ArrayList<Veiculo> getVeiculos() {
        return veiculos;
    }

    public Veiculo findByCodigo(String codigo) {
        for (Veiculo veiculo : this.veiculos) {
            if (veiculo.getId().equals(codigo)) {
                return veiculo;
            }
        }
        throw new RuntimeException("Veículo não encontrado.");
    }

    public Veiculo addVeiculo(Veiculo veiculo) {
        this.veiculos.add(veiculo);
        updateDataBase();
        return veiculo;
    }

    private void updateDataBase() {
        Gson gson = new Gson();

        StringBuilder sb = new StringBuilder();

        for (Veiculo v : this.veiculos) {
            sb.append(gson.toJson(v) + "\n");
        }
        saveData(sb.toString());
    }

    private void saveData(String json) {
        try {
            FileWriter dataToSave = new FileWriter(VEICULO_DATABASE_URL);
            dataToSave.write(json);
            dataToSave.close();
        } catch (Exception e) {
        }
    }
}