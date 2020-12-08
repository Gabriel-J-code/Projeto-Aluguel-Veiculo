package com.ufpb.projetoAluguelVeiculo.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataRegister {

    private String path;

    public DataRegister(String path) {
        this.path = path;
    }

    public void saveData(String dados) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
        bw.write(dados);
        bw.close();
    }

    public ArrayList<String> recoveryData() throws FileNotFoundException {
        ArrayList<String> retorno = new ArrayList<String>();
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(path)));
        while (scanner.hasNextLine()){
            String aux = scanner.nextLine();
            retorno.add(aux);
        }
        scanner.close();
        return retorno;

    }

}
