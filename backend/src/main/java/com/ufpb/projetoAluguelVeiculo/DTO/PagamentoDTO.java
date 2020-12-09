package com.ufpb.projetoAluguelVeiculo.DTO;

public class PagamentoDTO {
    private Double valor;
    private String cnpj;

    public PagamentoDTO(Double valor, String cnpj) {
        this.valor = valor;
        this.cnpj = cnpj;
    }

    public Double getValor() {
        return this.valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}