package com.ufpb.projetoAluguelVeiculo.DTO;

public class IdCnpjDTO {

    private String id;
    private String cnpj;

    public IdCnpjDTO() {
    }

    public IdCnpjDTO(String id, String cnpj) {
        this.id = id;
        this.cnpj = cnpj;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
