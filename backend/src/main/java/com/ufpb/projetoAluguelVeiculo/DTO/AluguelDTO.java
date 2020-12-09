package com.ufpb.projetoAluguelVeiculo.DTO;

public class AluguelDTO {
    private String codigoVeiculo;
    private String cnpjLoja;
    private String cpfCliente;
    private String horarioInicio;
    private String horarioTermino;

    public AluguelDTO(
        String codigoVeiculo, 
        String cnpjLoja, 
        String cpfCliente, 
        String horarioInicio, 
        String horarioTermino
    ) {
        this.codigoVeiculo = codigoVeiculo;
        this.cnpjLoja = cnpjLoja;
        this.cpfCliente = cpfCliente;
        this.horarioInicio = horarioInicio;
        this.horarioTermino = horarioTermino;
    }

    public String getCodigoVeiculo() {
        return this.codigoVeiculo;
    }

    public void setCodigoVeiculo(String codigoVeiculo) {
        this.codigoVeiculo = codigoVeiculo;
    }

    public String getCnpj() {
        return this.cnpjLoja;
    }

    public void setCnpj(String cnpj) {
        this.cnpjLoja = cnpj;
    }

    public String getCpf() {
        return this.cpfCliente;
    }

    public void setCpf(String cpf) {
        this.cpfCliente = cpf;
    }

    public String getHorarioInicio() {
        return this.horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioTermino() {
        return this.horarioTermino;
    }

    public void setHorarioTermino(String horarioTermino) {
        this.horarioTermino = horarioTermino;
    }
   
}
