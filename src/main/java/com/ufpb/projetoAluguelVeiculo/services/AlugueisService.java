package com.ufpb.projetoAluguelVeiculo.services;

import java.util.ArrayList;

import com.ufpb.projetoAluguelVeiculo.entities.Aluguel;
import com.ufpb.projetoAluguelVeiculo.repositories.AlugueisRepository;
import com.ufpb.projetoAluguelVeiculo.utils.Static;

import org.springframework.stereotype.Service;

@Service
public class AlugueisService {
    private AlugueisRepository ar;
    
    public AlugueisService() {
		this.ar = new AlugueisRepository();
	}

	public AlugueisService(AlugueisRepository alugueisRepository) {
		this.ar = alugueisRepository;
	}

	public Aluguel addAluguel(Aluguel aluguel) {
        validarAluguel(aluguel);
        return ar.saveAluguel(aluguel);
    }

    public Aluguel getAluguel(String id, String cnpj) {
        return ar.findById(id, cnpj);
    }

    public ArrayList<Aluguel> listaAlugueisDaLoja(String cnpj) {
        try {
            ArrayList<Aluguel> alugueisDaLoja = ar.findByCnpj(cnpj);
            return alugueisDaLoja;
        } catch (IndexOutOfBoundsException ioobe) {
            throw new RuntimeException();
        }
    }

    public ArrayList<Aluguel> listarAlugueisPorCpf(String cpf, String cnpj) {
        ArrayList<Aluguel> encontrados = ar.findByCpf(cpf, cnpj);
        return encontrados;
    }

    public void validarAluguel(Aluguel aluguel) {
        if (Static.isEmpty(aluguel.getId()))
            throw new RuntimeException("Aluguel sem id.");
        if (Static.isEmpty(aluguel.getCpfCliente()))
            throw new RuntimeException("Aluguel sem cpf.");
        if (Static.isEmpty(aluguel.getCnpjLoja()))
            throw new RuntimeException("Aluguel sem cnpj.");
        if (Static.isEmpty(aluguel.getHorarioInicio()))
            throw new RuntimeException("Aluguel sem hora de inicio.");
        if (Static.isEmpty(aluguel.getHorarioTermino()))
            throw new RuntimeException("Aluguel sem hora de fim.");
    }

    public Boolean deletarAluguel(String id, String cnpj) {
        return ar.deleteById(id, cnpj);
    }

    public Double pagarAluguel(String id, String cnpj, Double valorRecebido) {
        Aluguel a = getAluguel(id, cnpj);
        if (a.getvalor() > valorRecebido) {
            throw new RuntimeException("Valor insuficiente");
        }
        Double troco = valorRecebido - a.getvalor();
        Boolean deletar = deletarAluguel(id, cnpj);
        if (deletar) {
            return troco;
        }
        throw new RuntimeException("Aluguel não existe.");
    }

}
