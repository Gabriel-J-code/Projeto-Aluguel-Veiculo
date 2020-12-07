package com.ufpb.projetoAluguelVeiculo.entities;

import java.util.ArrayList;

public class Loja {
  private String nome;
    private String cnpj;
    private String ie;
    private String cep;
    private String numero;
    private ArrayList<Aluguel> alugueis;
    private ArrayList<Cliente> clientes;

    public Loja(String nome, String cnpj, String ie, String cep, String numero) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.ie = ie;
        this.cep = cep;
        this.numero = numero;
        this.alugueis = new ArrayList<Aluguel>();
        this.setClientes(new ArrayList<Cliente>());
    }

    // Getters
    
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }    
    
    public String getIe() {
        return this.ie;
    }

    public String getCep() {
        return this.cep;
    }

    public String getNumero() {
        return this.numero;
    }
    
    public ArrayList<Aluguel> getAlugueis() {
        return this.alugueis;
    }

    public String getCnpj() {
        return this.cnpj;
    }
    
    
    public String getNome() {
        return this.nome;
    }


    // Setters

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }   

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }    

    public void setCep(String cep) {
        this.cep = cep;
    }
   
    public void setNumero(String numero) {
        this.numero = numero;
    }

    //Adds
    
    public void addAluguel(Aluguel aluguel) {
        this.alugueis.add(aluguel);
    }
    
    public void addCliente(Cliente cliente){
        this.clientes.add(cliente);
    }

    //Removes
    public void removeAluguel(Aluguel aluguel){
        this.alugueis.remove(aluguel);
    }

    public void removeCliente(Cliente cliente){
        this.clientes.remove(cliente);
    }
}
