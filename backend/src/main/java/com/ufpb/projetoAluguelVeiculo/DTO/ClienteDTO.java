package com.ufpb.projetoAluguelVeiculo.DTO;

public class ClienteDTO {
  private String cnpjLoja;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    public ClienteDTO(
        String cnpjLoja, 
        String nome, 
        String cpf, 
        String email, 
        String telefone
    ) {
        this.cnpjLoja = cnpjLoja;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public String getCnpjLoja() {
        return this.cnpjLoja;
    }

    public void setCnpjLoja(String cnpjLoja) {
        this.cnpjLoja = cnpjLoja;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
