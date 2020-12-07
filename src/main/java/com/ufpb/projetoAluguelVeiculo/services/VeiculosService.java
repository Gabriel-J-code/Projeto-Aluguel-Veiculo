package com.ufpb.projetoAluguelVeiculo.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

import com.ufpb.projetoAluguelVeiculo.entities.ModeloVeiculo;
import com.ufpb.projetoAluguelVeiculo.entities.Veiculo;
import com.ufpb.projetoAluguelVeiculo.repositories.VeiculosRepository;
import com.ufpb.projetoAluguelVeiculo.utils.Static;

/**
 * VeiculoService
 */
@Service
public class VeiculosService {
  private VeiculosRepository veiculos_repository;

  public VeiculosService() {
    this.veiculos_repository = new VeiculosRepository();
  }

  public Veiculo buscarVeiculoPorCodigo(String codigoVeiculo) {

    return veiculos_repository.findByCodigo(codigoVeiculo);
  }

  // validar
  public void validarVeiculo(Veiculo veiculo) {
    validarModeloVeiculo(veiculo.getModeloVeiculo());
    if (Static.isEmpty(veiculo.getId())) {
      throw new RuntimeException("veiculo sem id");
    }
  }

  private void validarModeloVeiculo(ModeloVeiculo modVeiculo) {
    // {Bicicleta, skate, patins} !contains RuntimeException
    if (Static.isEmpty(modVeiculo.getTipo())) {
      throw new RuntimeException("veiculo sem tipo.");
    }
    if (Static.isEmpty(modVeiculo.getModelo())) {
      throw new RuntimeException("veiculo sem modelo.");
    }
    if (modVeiculo.getValorHora() <= 1) {
      throw new RuntimeException("veiculo sem valor.");
    }

  }

  // listar todos os veiculos
  public ArrayList<Veiculo> listarVeiculos() {
    return veiculos_repository.getVeiculos();
  }

  // adicionar
  // public Veiculo adicionarVeiculo(Veiculo veiculo) {
  //   validarVeiculo(veiculo);
  //   return veiculos_repository.addVeiculo(veiculo);
  // }
  // deletar
}