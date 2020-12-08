package com.ufpb.projetoAluguelVeiculo.services;

import java.util.ArrayList;

import com.ufpb.projetoAluguelVeiculo.entities.ModeloVeiculo;
import com.ufpb.projetoAluguelVeiculo.entities.Veiculo;
import com.ufpb.projetoAluguelVeiculo.repositories.VeiculosRepository;
import com.ufpb.projetoAluguelVeiculo.utils.Static;

import org.springframework.stereotype.Service;

@Service
public class VeiculosService {
	private VeiculosRepository vr;

	public VeiculosService(){
		this.vr = new VeiculosRepository();
	}

	public VeiculosService(VeiculosRepository veiculoRepository) {
		this.vr = veiculoRepository;
	}

	public Veiculo buscarVeiculoPorCodigo(String codigoVeiculo) {

	return vr.findById(codigoVeiculo);
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
	return vr.findAll();
	}

	// adicionar
	public Veiculo adicionarVeiculo(Veiculo veiculo) {
	validarVeiculo(veiculo);
	return vr.addVeiculo(veiculo);
	}
	// deletar
	public boolean deletarVeiculo(Veiculo veiculo){
		return vr.deletarVeiculo(veiculo);
	}


}
