package com.ufpb.projetoAluguelVeiculo.entities;

/**
 * ModeloVeiculo
 */
public class ModeloVeiculo {

    private String tipo;
    private String modelo;
    private Double valorHora;

    public ModeloVeiculo(
        String tipo,
        String modelo,
        Double valorHora){
            this.tipo = tipo;
            this.modelo = modelo;
            this.valorHora = valorHora;        
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getTipo() {
        return tipo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getModelo() {
        return modelo;
    }
    public void setValorHora(Double valorHora) {
        this.valorHora = valorHora;
    }
    public Double getValorHora() {
        return valorHora;
    }
}