package com.ufpb.projetoAluguelVeiculo.DTO;

public class CnpjDTO {
  private String cnpj;

  public CnpjDTO() {
  }

  public CnpjDTO(String cnpj) {
      this.cnpj = cnpj;
  }

  public String getCnpj() {
      return this.cnpj;
  }

  public void setCnpj(String cnpj) {
      this.cnpj = cnpj;
  }
}
