package com.ufpb.projetoAluguelVeiculo.DTO;

public class CpfCnpjDTO {

    private String cpf;
    private String cnpj;

    public CpfCnpjDTO() {
    }

    public CpfCnpjDTO(String cpf, String cnpj) {
        this.cpf = cpf;
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getCpf() {
        return cpf;
    }
}
